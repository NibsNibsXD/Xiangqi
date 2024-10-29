    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
     */


    package xianquiproyectop2q4;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;
    import java.util.ArrayList;
    import java.util.Calendar;

    /**
     *
     * @author Jorge Aguirre
     */

public class XianquiProyectoP2Q4 {

    private static ArrayList<Player> players = new ArrayList<>();
    private static Player currentPlayer = null;
    private static JFrame frame;
    private static JPanel initialMenu;

    public static void main(String[] args) {

        
        frame = new JFrame("Xiangqi");
        frame.setSize(1025, 1025);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.RED);
        frame.setLocationRelativeTo(null);
        showInitialMenu();
        frame.setVisible(true);
        
    }

    private static void showInitialMenu() {
        // Panel del menú inicial
        initialMenu = new JPanel();
        initialMenu.setBackground(Color.RED);
        initialMenu.setLayout(new GridBagLayout());

        JButton loginButton = new JButton("LOG IN");
        JButton createPlayerButton = new JButton("CREAR PLAYER");
        JButton exitButton = new JButton("SALIR");

        
        loginButton.setPreferredSize(new Dimension(200, 50));
        createPlayerButton.setPreferredSize(new Dimension(200, 50));
        exitButton.setPreferredSize(new Dimension(200, 50));

        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        createPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCreatePlayer();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        initialMenu.add(loginButton, gbc);

        gbc.gridy = 1;
        initialMenu.add(createPlayerButton, gbc);

        gbc.gridy = 2;
        initialMenu.add(exitButton, gbc);

        frame.getContentPane().removeAll();
        frame.add(initialMenu);
        frame.revalidate();
        frame.repaint();
    }

    private static void handleLogin() {
        
        JPanel loginPanel = new JPanel(new GridLayout(2, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(frame, loginPanel, 
            "Log In", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            boolean found = false;
            for (Player p : players) {
                if (p.getUsername().equals(username)) {
                    if (p.getPassword().equals(password)) {
                        currentPlayer = p;
                        found = true;
                        showMainMenu();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Contraseña incorrecta");
                    }
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(frame, "Usuario no encontrado");
            }
        }
    }

    private static void handleCreatePlayer() {
        
        JPanel createPanel = new JPanel(new GridLayout(2, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        createPanel.add(new JLabel("Username:"));
        createPanel.add(usernameField);
        createPanel.add(new JLabel("Password (5 caracteres):"));
        createPanel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(frame, createPanel, 
            "Crear Player", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            boolean exists = false;
            for (Player p : players) {
                if (p.getUsername().equals(username)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                JOptionPane.showMessageDialog(frame, "El nombre de usuario ya existe");
            } else if (password.length() != 5) {
                JOptionPane.showMessageDialog(frame, "La contraseña debe tener exactamente 5 caracteres");
            } else {
                Player newPlayer = new Player(username, password);
                players.add(newPlayer);
                currentPlayer = newPlayer;
                JOptionPane.showMessageDialog(frame, "Jugador creado exitosamente");
                showMainMenu();
            }
        }
    }

    private static void showMainMenu() {
     
        JPanel mainMenu = new JPanel();
        mainMenu.setBackground(Color.RED);
        mainMenu.setLayout(new GridBagLayout());

        JButton playButton = new JButton("JUGAR XIANGQI");
        JButton myAccountButton = new JButton("MI CUENTA");
        JButton reportButton = new JButton("REPORTE"); // Botón "REPORTE" añadido
        JButton logoutButton = new JButton("LOG OUT");

        playButton.setPreferredSize(new Dimension(200, 50));
        myAccountButton.setPreferredSize(new Dimension(200, 50));
        reportButton.setPreferredSize(new Dimension(200, 50)); // Configuración del tamaño
        logoutButton.setPreferredSize(new Dimension(200, 50));

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //llamo al board del juego
                frame.getContentPane().removeAll();
                frame.repaint();
                Board board = new Board();
                frame.add(board);
                frame.revalidate();
            }
        });

        myAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMyAccountMenu();
            }
        });

        

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPlayer = null;
                showInitialMenu();
            }
        });

        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainMenu.add(playButton, gbc);

        gbc.gridy = 1;
        mainMenu.add(myAccountButton, gbc);

        gbc.gridy = 2;
        mainMenu.add(reportButton, gbc); // Añadir el botón "REPORTE"

        gbc.gridy = 3;
        mainMenu.add(logoutButton, gbc);

        frame.getContentPane().removeAll();
        frame.add(mainMenu);
        frame.revalidate();
        frame.repaint();
    }
    
    public void jugador1Wins(){
        JOptionPane.showMessageDialog(null, "Jugador 1 gana, se añadieron 3 puntos");
    }
    
    public void jugador2Wins(){
        JOptionPane.showMessageDialog(null, "Jugador 2 gana, se añadieron 3 puntos");
    }

    private static void showMyAccountMenu() {
        
        JPanel accountMenu = new JPanel();
        accountMenu.setBackground(Color.RED);
        accountMenu.setLayout(new GridBagLayout());

        JButton viewInfoButton = new JButton("Ver Mi Información");
        JButton changePasswordButton = new JButton("Cambiar Password");
        JButton closeAccountButton = new JButton("Cerrar mi Cuenta");
        JButton backButton = new JButton("Regresar");

        viewInfoButton.setPreferredSize(new Dimension(200, 50));
        changePasswordButton.setPreferredSize(new Dimension(200, 50));
        closeAccountButton.setPreferredSize(new Dimension(200, 50));
        backButton.setPreferredSize(new Dimension(200, 50));

        viewInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String info = "Username: " + currentPlayer.getUsername() + "\n" +
                        "Points: " + currentPlayer.getPoints() + "\n" +
                        "Join Date: " + currentPlayer.getJoinDate().getTime().toString() + "\n" +
                        "Active: " + currentPlayer.isActive();
                JOptionPane.showMessageDialog(frame, info, "Mi Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPassword = JOptionPane.showInputDialog(frame, "Ingrese nueva contraseña (5 caracteres):");
                if (newPassword != null && newPassword.length() == 5) {
                    currentPlayer.setPassword(newPassword);
                    JOptionPane.showMessageDialog(frame, "Contraseña cambiada exitosamente");
                } else {
                    JOptionPane.showMessageDialog(frame, "La contraseña debe tener exactamente 5 caracteres");
                }
            }
        });

        closeAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "¿Está seguro que desea cerrar su cuenta?", 
                    "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    currentPlayer.deactivateAccount();
                    JOptionPane.showMessageDialog(frame, "Cuenta cerrada");
                    currentPlayer = null;
                    showInitialMenu();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMainMenu();
            }
        });
        
        

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        accountMenu.add(viewInfoButton, gbc);

        gbc.gridy = 1;
        accountMenu.add(changePasswordButton, gbc);

        gbc.gridy = 2;
        accountMenu.add(closeAccountButton, gbc);

        gbc.gridy = 3;
        accountMenu.add(backButton, gbc);

        frame.getContentPane().removeAll();
        frame.add(accountMenu);
        frame.revalidate();
        frame.repaint();
    }
}
