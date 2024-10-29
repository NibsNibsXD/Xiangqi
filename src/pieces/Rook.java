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
public class Rook extends Piece implements Movable {
    
    public Rook(Board board, int col, int row, boolean isRed) {
        super(board);
        this.name = "Rook";
        this.col = col;
        this.row = row;
        this.xpos = col * board.tileSize;
        this.ypos = row * board.tileSize;       
        this.isRed = isRed;
        
        this.sprite = page.getSubimage(4*pageScale, isRed ? 0 : pageScale, pageScale, pageScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
        
    }
    
    @Override
    public boolean isValidMovePiece(int col, int row){
        
        
        return ((this.col == col ) || (this.row == row));
    }
    
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
