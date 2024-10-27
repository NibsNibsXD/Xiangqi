/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xianquiproyectop2q4;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pieces.Piece;

/**
 *
 * @author Jorge Aguirre
 */
public class Input extends MouseAdapter{
    
    Board board;
    
    public Input(Board board){
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        
        int col = e.getX()/board.tileSize;
        int row = e.getY()/board.tileSize;
        
        Piece pieceXY = board.getPiece(col, row);
        if (pieceXY != null){
            board.selectedPiece = pieceXY;
        }
        
        
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;
        
        if(board.selectedPiece != null){
            Move move = new  Move(board, board.selectedPiece, col, row);
            
            if(board.isValidMove(move)){
                board.makeMove(move);
            }else{
                board.selectedPiece.xpos = board.selectedPiece.col * board.tileSize;
                board.selectedPiece.ypos = board.selectedPiece.row * board.tileSize;
            }
        }
        
        board.selectedPiece = null;
        board.repaint();
        
        //row new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        
        if(board.selectedPiece != null){
            board.selectedPiece.xpos = e.getX() - board.tileSize /2;
            board.selectedPiece.ypos = e.getY() - board.tileSize/2;
            
            board.repaint();
        }
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
