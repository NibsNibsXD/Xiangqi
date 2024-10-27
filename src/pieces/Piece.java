/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import xianquiproyectop2q4.Board;


/**
 *
 * @author Jorge Aguirre
 */
public class Piece {
    
    public int col, row, xpos, ypos;
    public boolean isRed;
    public String name;
    public int value;
    
    BufferedImage page;
    {
        try{
            page = ImageIO.read(ClassLoader.getSystemResourceAsStream("Resources/pieces.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    protected int pageScale = page.getWidth()/7;
    
    Image sprite;
    Board board;
    
    public Piece(Board board){
        this.board = board;
    }
    
    
    //testing
    
    public void paint(Graphics2D graphics2d){
        graphics2d.drawImage(sprite, xpos, ypos, null);
    }
    
    public boolean isValidMovePiece(int col, int row){
        return true;
    }
    
    public boolean moveCollision(int col, int row){
        return false; //false temporal?
    }
    
    
    
}
