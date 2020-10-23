package machinecoding.chessgame;

import machinecoding.chessgame.account.Player;
import machinecoding.chessgame.pieces.Piece;

public class Move {
  private Player player;
  private Box start;
  private Box end;
  private Piece pieceMoved;
  private Piece pieceKilled;
  private boolean castlingMove = false;

  public Move(Player player, Box start, Box end){
    this.player = player;
    this.start = start;
    this.end = end;
    this.pieceMoved = start.getPiece();
  }

  public boolean isCastlingMove() {
    return this.castlingMove == true;
  }

  public void setCastlingMove(boolean castlingMove) {
    this.castlingMove = castlingMove;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Box getStart() {
    return start;
  }

  public void setStart(Box start) {
    this.start = start;
  }

  public Box getEnd() {
    return end;
  }

  public void setEnd(Box end) {
    this.end = end;
  }

  public Piece getPieceMoved() {
    return pieceMoved;
  }

  public void setPieceMoved(Piece pieceMoved) {
    this.pieceMoved = pieceMoved;
  }

  public Piece getPieceKilled() {
    return pieceKilled;
  }

  public void setPieceKilled(Piece pieceKilled) {
    this.pieceKilled = pieceKilled;
  }
}