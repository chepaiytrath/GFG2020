package machinecoding.chessgame.models.pieces;

import machinecoding.chessgame.Board;
import machinecoding.chessgame.Box;

public class Queen extends Piece {
    public Queen(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return true;
    }
}

