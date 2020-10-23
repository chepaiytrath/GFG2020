package machinecoding.chessgame.pieces;

import machinecoding.chessgame.Board;
import machinecoding.chessgame.Box;
import machinecoding.chessgame.pieces.Piece;

public class Bishop extends Piece {
    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {

        // we can't move the piece to a box that has a piece of the same color
        if(end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        if(x == y){
            if(true /*no pieces between start and end*/){
                return true;
            }
        }
        return false;
    }
}

