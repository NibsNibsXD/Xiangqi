    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
     */
    package xianquiproyectop2q4;

    import java.awt.*;
    import javax.swing.*;

    /**
     *
     * @author Jorge Aguirre
     */
    public class XianquiProyectoP2Q4 {

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {

            JFrame frame = new JFrame();
            frame.setLayout(new GridBagLayout());

            frame.setMinimumSize(new Dimension(1025,1025));
            frame.setLocationRelativeTo(null);


            Board board = new Board();
            frame.add(board);

            frame.setVisible(true);



        }

}
