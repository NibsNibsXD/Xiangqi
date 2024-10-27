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
public class Pawn extends Piece{
    
    private boolean overRiver;
    public Pawn(Board board, int col, int row, boolean isRed){
        super(board);
        
         //pendiente para mover a los lados
        this.name = "Pawn";
        this.col = col;
        this.row = row;
        this.xpos = col * board.tileSize;
        this.ypos = row * board.tileSize;       
        this.isRed = isRed;
        

        this.sprite = page.getSubimage(6*pageScale, isRed ? 0 : pageScale, pageScale, pageScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }
    
    @Override
    public boolean isValidMovePiece(int col, int row){
        
        int directionByColor = !isRed ? -1 : 1;
        
        //hacia los lados
        if(((this.col == col -1 && row == this.row)||(this.col == col +1 && row == this.row))&&overRiver ){
            return true;
        }
        
        if(((this.row == 4) && isRed) || ((this.row == 6) && !isRed)){
            overRiver = true;
        }
        
        
        
        //pasando el rio
        if((this.col == col && row == this.row - directionByColor * 2)&& (((this.row == 6) && isRed)|| ((this.row == 4) && !isRed) )){
            return true;           
        }
        
        
        //antes de rio hacia enfrente
        if(this.col == col && row == this.row - directionByColor){
            return true;
        }
        
        
        
        
        
        return false;
    }
    
}