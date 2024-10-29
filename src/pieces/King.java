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
public class King extends Piece implements Movable {
    
    public King(Board board, int col, int row, boolean isRed){
        super(board);
        
        this.name = "King";
        this.col = col;
        this.row = row;
        this.xpos = col * board.tileSize;
        this.ypos = row * board.tileSize;       
        this.isRed = isRed;

        this.sprite = page.getSubimage(0*pageScale, isRed ? 0 : pageScale, pageScale, pageScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }
    
    public boolean isValidMovePiece(int col, int row){
        if((((row == 0) || (row == 1))||(row == 2)) && (((col ==3) || (col == 4))||(col == 5)) || (((row == 10) || (row == 9))||(row == 8)) && (((col ==3) || (col == 4))||(col == 5)) ){
            return (Math.abs((col - this.col) * Math.abs(row - this.row)) == 1 || Math.abs(col - this.col) + Math.abs(row -this.row) == 1) && !(Math.abs(this.col - col) == Math.abs(this.row -row) && !((Math.abs(this.col - col) > 1)&& Math.abs(this.row - row) > 1 ));
        }
        return false;
    }
    
    
}
