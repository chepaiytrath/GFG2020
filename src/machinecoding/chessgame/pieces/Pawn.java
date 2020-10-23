package machinecoding.chessgame.pieces;

import machinecoding.chessgame.Board;
import machinecoding.chessgame.Box;

public class Pawn extends Piece {
    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        // PAWN ONLY MOVES FORWARD
        // FOLLOWING IS COPIED FROM KING WHICH MOVES BACKWARDS TOO

        // we can't move the piece to a box that has a piece of the same color
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());    // Diff of x coordinate
        int y = Math.abs(start.getY() - end.getY());    // Diff of y coordinate

        // For Horizontal and vertical movement when destination box is unoccupied
        if (x + y == 1 && end.getPiece() == null) {
            // TODO check if this move will not result in king being attacked, if so return true
            return true;
        }

        // For diagonal movement when destination box is occupied by piece of different color
        if(x + y == 2 && end.getPiece() != null && end.getPiece().isWhite() != start.getPiece().isWhite()){
            return true;
        }
        return false;
    }
}

