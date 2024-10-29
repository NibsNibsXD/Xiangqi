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
public class Knight extends Piece implements Movable {
    
    public Knight(Board board, int col, int row, boolean isRed){
        super(board);
        
        this.name = "Knight";
        this.col = col;
        this.row = row;
        this.xpos = col * board.tileSize;
        this.ypos = row * board.tileSize;       
        this.isRed = isRed;

        this.sprite = page.getSubimage(2*pageScale, isRed ? 0 : pageScale, pageScale, pageScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }
    
    
    @Override
    public boolean isValidMovePiece(int col, int row){
        if((this.row == 4 || this.row == 3 || this.row == 6 || this.row == 7) && (row == 4 || row == 3 || row == 6 || row == 7)){
            return ((Math.abs(col - this.col)* Math.abs(row - this.row)) == 3 );
        }
        return ((Math.abs(col - this.col)* Math.abs(row - this.row)) == 2 ) ; 
    }
    
    @Override
    public boolean moveCollision(int col, int row){
        
        // revisando arriba
        if(this.row > row){
            for(int r = this.row -1; r > row; r--){
                if(board.getPiece(this.col, r) != null){
                    return true;
                }
            }
        }
        //revisando abajo
        if(this.row < row){
            for(int r = this.row +1; r < row; r++){
                if(board.getPiece(this.col, r) != null){
                    return true;
                }
            }
        }
        
        
        //revisando izquierda
        if(this.col > col){
            for(int c = this.col -1; c > col; c--){
                if(board.getPiece(c,this.row) != null){
                    return true;
                }
            }
        }
        //revisando derecha
        if(this.col < col){
            for(int c = this.col +1; c < col; c++){
                if(board.getPiece(c,this.row) != null){
                    return true;
                }
            }
        }
        
        
        return false;
    }
}
