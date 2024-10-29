/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.awt.image.BufferedImage;
import xianquiproyectop2q4.Board;

/**
 *
 * @author Jorge Aguirre
 */
public class Cannon extends Piece implements Movable{
    
    
    
    public Cannon(Board board, int col, int row, boolean isRed){
        super(board);
        
        this.name = "Cannon";
        this.col = col;
        this.row = row;
        this.xpos = col * board.tileSize;
        this.ypos = row * board.tileSize;       
        this.isRed = isRed;

        this.sprite = page.getSubimage(5*pageScale, isRed ? 0 : pageScale, pageScale, pageScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }
    
    @Override
    public boolean isValidMovePiece(int col, int row){
        
        
        return ((this.col == col ) || (this.row == row));
    }
    
    public boolean moveCollision(int col, int row) {
        int count = 0;

        // horizontal
        if (this.col == col) {
            int step = (row > this.row) ? 1 : -1;
            for (int r = this.row + step; r != row; r += step) {
                if (board.getPiece(this.col, r) != null) {
                    count++;
                }
            }
        }
        // vertical
        else if (this.row == row) {
            int step = (col > this.col) ? 1 : -1;
            for (int c = this.col + step; c != col; c += step) {
                if (board.getPiece(c, this.row) != null) {
                    count++;
                }
            }
        } else {
            
            return true; // Hay colision
        }

        Piece targetPiece = board.getPiece(col, row);

        if (targetPiece == null) {
            return count != 0; 
        } else {
            
            return count != 1; // Hay colisión si no hay exactamente una pieza entre medio
        }
    }
    
}