package machinecoding.chessgame.models.pieces;

import machinecoding.chessgame.Board;
import machinecoding.chessgame.Box;

public class Rook extends Piece {
    public Rook(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return true;
    }
}

