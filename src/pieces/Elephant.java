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

public class Elephant extends Piece implements Movable {
    
    public Elephant(Board board, int col, int row, boolean isRed) {
        super(board);
        this.name = "Elephant";
        this.col = col;
        this.row = row;
        this.xpos = col * board.tileSize;
        this.ypos = row * board.tileSize;       
        this.isRed = isRed;
        
        this.sprite = page.getSubimage(3*pageScale, isRed ? 0 : pageScale, pageScale, pageScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
        
    }
    
    @Override
    public boolean isValidMovePiece(int col, int row){
        if((!isRed && row == 6)||(isRed && row == 4)){
            return false;
        }
        
        return Math.abs(this.col - col) == Math.abs(this.row -row) && ((Math.abs(this.col - col) == 2)&& Math.abs(this.row - row) == 2 ) ;
    }
    
    
    @Override
    public boolean moveCollision(int col, int row){
    
        //Arriba e izquierda
        if(this.col > col && this.row > row){
            for(int i = 1; i < Math.abs(this.col - col);i++){
                if(board.getPiece(this.col - i, this.row - i) != null){
                    return true;
                }
            }
        }
        
        //arriba y derecha
        if(this.col < col && this.row > row){
            for(int i = 1; i < Math.abs(this.col - col);i++){
                if(board.getPiece(this.col + i, this.row - i) != null){
                    return true;
                }
            }
        }
        
        //ABAJO e izquierda
        if(this.col > col && this.row < row){
            for(int i = 1; i < Math.abs(this.col - col);i++){
                if(board.getPiece(this.col - i, this.row + i) != null){
                    return true;
                }
            }
        }
        
        //ABAJO y derecha
        if(this.col < col && this.row < row){
            for(int i = 1; i < Math.abs(this.col - col);i++){
                if(board.getPiece(this.col + i, this.row + i) != null){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    
    
    
}
