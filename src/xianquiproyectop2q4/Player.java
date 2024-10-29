/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xianquiproyectop2q4;

import java.util.Calendar;

/**
 *
 * @author Jorge Aguirre
 */


public class Player {
    private String username;
    private String password;
    private int points;
    private Calendar joinDate;
    private boolean active;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.points = 0;
        this.joinDate = Calendar.getInstance();
        this.active = true;
    }

    // Métodos getter y setter
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void winPoints(){
        points += 3;
    }

    public int getPoints() {
        return points;
    }

    public Calendar getJoinDate() {
        return joinDate;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivateAccount() {
        this.active = false;
    }
}
