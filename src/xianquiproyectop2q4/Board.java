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
    ArrayList<Piece> capturedBlackPieces = new ArrayList<>();
    ArrayList<Piece> capturedRedPieces = new ArrayList<>();
    
    public Board(){
        int extraCols = 2; 
        this.setPreferredSize(new Dimension((cols + extraCols) * tileSize, rows * tileSize));
        this.capturedBlackPanel = capturedBlackPanel;
        this.capturedRedPanel = capturedRedPanel;
        
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        
        addPiece();
        
        
    }
    
    
    public int redWinsCounter = 0;
    public int blackWinsCounter = 0;
    
    private boolean isRedTurn = true;
    private boolean isGameOver = false;
    
    private JPanel capturedBlackPanel;
    private JPanel capturedRedPanel;
    
    
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D graphics2d = (Graphics2D) g;

        // Llamada al método recursivo
        drawTiles(graphics2d, 0, 0);

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

    // Método RECURSIVO
    private void drawTiles(Graphics2D graphics2d, int r, int c) {
        if(r >= rows) {
            return; //se han procesado todas las filas
        }
        if(c >= cols) {
            // Se ha completado la fila actual y pasa a la siguiente
            drawTiles(graphics2d, r + 1, 0);
            return;
        }

        graphics2d.setColor(
            ((((r == 0) || (r == 1)) || (r == 2)) && (((c == 3) || (c == 4)) || (c == 5)) ||
            (((r == 10) || (r == 9)) || (r == 8)) && (((c == 3) || (c == 4)) || (c == 5))) ?  
                (((c + r) % 2 == 0) ? Color.MAGENTA : Color.CYAN) :
                ((c + r) % 2 == 0) & r != 5 ? Color.WHITE : (r == 5) ? Color.BLUE :  Color.BLACK
        );
        //relleno a los cuadrados del tablero
        graphics2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);

        // Llamada recursiva para la siguiente columna
        drawTiles(graphics2d, r, c + 1);
    }
    
    
    public Piece selectedPiece;
    
    
    
    Input input = new Input(this);
    
    CheckScanner checkScanner = new CheckScanner(this);
    
    public final void addPiece(){
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
        
        if(isGameOver){
            return false;
        }
        
        if(move.piece.isRed  != isRedTurn){
            return false;
        }
        
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
        if(checkScanner.isKingChecked(move)){
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
    
    private void updateGameState(){
        Piece king = findKing(isRedTurn);
        
        if(checkScanner.isGameOver(king) && isRedTurn){
            blackWinsCounter+= 3;
            //player win points de : winPoints de clase player
            
        }
        if(checkScanner.isGameOver(king) && !isRedTurn){
            redWinsCounter+= 3;
        }
        isGameOver = true;
    }
    
    
    public void makeMove(Move move){
        move.piece.col = move.newCol;
        move.piece.row = move.newRow;
        move.piece.xpos = move.newCol * tileSize;
        move.piece.ypos = move.newRow * tileSize;
        
        capture(move);
        
        isRedTurn = !isRedTurn;
        
        
    }
    
    public void capture(Move move){
      if (move.capture != null) {
          pieceList.remove(move.capture);
          if (move.capture.isRed) {
              capturedRedPieces.add(move.capture);
          } else {
              capturedBlackPieces.add(move.capture);
          }
      }
  }
    
    Piece findKing(boolean isRed){
        for(Piece piece : pieceList){
            if(isRed == piece.isRed && piece.name.equals("King")){
                return piece;
            }
        }
        return null;
    }
    
}
