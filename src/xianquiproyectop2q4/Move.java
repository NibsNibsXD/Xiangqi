/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xianquiproyectop2q4;

import pieces.Piece;

/**
 *
 * @author Jorge Aguirre
 */ 
public class Move {
    
    int oldCol;
    int oldRow;
    int newCol;
    int newRow;
    
    public Piece piece;
    public Piece capture;
    
    public Move(Board board, Piece piece, int newCol, int newRow){
    
        this.oldCol = piece.col;
        this.oldRow = piece.row;
        this.newRow = newRow;
        this.newCol = newCol;
        
        this.piece = piece;
        this.capture = board.getPiece(newCol, newRow);
        
    }
    
    
}
