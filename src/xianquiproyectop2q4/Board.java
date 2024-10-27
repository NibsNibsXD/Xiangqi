/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xianquiproyectop2q4;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import pieces.*;


/**
 *
 * @author Jorge Aguirre
 */
public class Board extends JPanel {
    
    public int tileSize = 75;
    
    int rows = 11;
    int cols = 9;
    
    ArrayList<Piece> pieceList = new ArrayList<>();
    
    public Board(){
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        
        addPiece();
        
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D graphics2d = (Graphics2D) g;
        
        for(int r = 0; r < rows; r++ ){
            for(int c = 0; c < cols; c++){
                graphics2d.setColor(((((r == 0) || (r == 1))||(r == 2)) && (((c ==3) || (c == 4))||(c == 5)) || (((r == 10) || (r == 9))||(r == 8)) && (((c ==3) || (c == 4))||(c == 5)) ) ?  
                        //ESTA PARTE ES PARA EL CUANDRANTE 3X3 EN ROW 0,1,2,8,9,10 CON COLUMNAS 3,4,5 PARA EL REY
                        (((c+r)%2 == 0) ? Color.MAGENTA : Color.CYAN) :((c+r)%2 == 0) & r != 5 ? Color.WHITE : (r == 5) ? Color.BLUE :  Color.BLACK);
                        //ESTE ES EL RELLENO DEL RESTO DEL TABLERO
                graphics2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }
        
        if(selectedPiece != null){
            for(int r = 0; r < rows; r++ ){
                for(int c = 0; c < cols; c++){

                    if(isValidMove(new Move(this, selectedPiece, c, r))){
                        graphics2d.setColor(new Color(70,170,60,170));
                        graphics2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
                    }
                }
            }
        }
        
        
        
        for(Piece piece : pieceList){
            piece.paint(graphics2d);
        }
        
    }
    
    public Piece selectedPiece;
    
    
    
    Input input = new Input(this);
    
    
    public void addPiece(){
        //Elefantes negros
        pieceList.add(new Elephant(this, 2, 0, false));
        pieceList.add(new Elephant(this, 6, 0, false));
        //Rojos
        pieceList.add(new Elephant(this, 2, 10, true));
        pieceList.add(new Elephant(this, 6, 10, true));
        
        
        //Knights negros
        pieceList.add(new Knight(this, 1, 0, false));
        pieceList.add(new Knight(this, 7, 0, false));
        //rojos
        pieceList.add(new Knight(this, 1, 10, true));
        pieceList.add(new Knight(this, 7, 10, true));
        
        //Rooks negros
        pieceList.add(new Rook(this, 0, 0, false));
        pieceList.add(new Rook(this, 8, 0, false));
        //rojos
        pieceList.add(new Rook(this, 0, 10, true));
        pieceList.add(new Rook(this, 8, 10, true));
        
        //Advisor negros
        pieceList.add(new Advisor(this, 3, 0, false));
        pieceList.add(new Advisor(this, 5, 0, false));
        //rojos
        pieceList.add(new Advisor(this, 3, 10, true));
        pieceList.add(new Advisor(this, 5, 10, true));
        
        //King negro
        pieceList.add(new King(this, 4, 0, false));
        //rojo
        pieceList.add(new King(this, 4, 10, true));
        
        //Pawns negros
        pieceList.add(new Pawn(this, 0, 3, false));
        pieceList.add(new Pawn(this, 2, 3, false));
        pieceList.add(new Pawn(this, 4, 3, false));
        pieceList.add(new Pawn(this, 6, 3, false));
        pieceList.add(new Pawn(this, 8, 3, false));
        //rojos
        pieceList.add(new Pawn(this, 0, 7, true));
        pieceList.add(new Pawn(this, 2, 7, true));
        pieceList.add(new Pawn(this, 4, 7, true));
        pieceList.add(new Pawn(this, 6, 7, true));
        pieceList.add(new Pawn(this, 8, 7, true));
        
        //Cannons negros
        pieceList.add(new Cannon(this, 1, 2, false));
        pieceList.add(new Cannon(this, 7, 2, false));
        //rojos
        pieceList.add(new Cannon(this, 1, 8, true));
        pieceList.add(new Cannon(this, 7, 8, true));
        
    }
    
    public Piece getPiece(int col, int row){
        
        for(Piece piece : pieceList){
            if(piece.col == col && piece.row == row){
                return piece;
            }
        }
        
        return null;
    }
    
    public boolean isValidMove(Move move){
        
        if(sameTeam(move.piece, move.capture)){
            return false;
        }
        if(!move.piece.isValidMovePiece(move.newCol, move.newRow)){
            return false;
        }
        if(move.newRow ==5){ //este solo es para confirmar si la pieza cae en el rio
            return false;
        }
        if(move.piece.moveCollision(move.newCol, move.newRow)){
            return false;
        }
        
        return true;        
    }
    
    public boolean sameTeam(Piece player1, Piece player2){
        if(player1 == null || player2 == null){
            return false;
        }
        return player1.isRed == player2.isRed;
    }
    
    
    
    public void makeMove(Move move){
        move.piece.col = move.newCol;
        move.piece.row = move.newRow;
        move.piece.xpos = move.newCol * tileSize;
        move.piece.ypos = move.newRow * tileSize;
        
        capture(move);
    }
    
    public void capture(Move move){
        pieceList.remove(move.capture);
    }
    
    
    
}
