package machinecoding.chessgame;

import machinecoding.chessgame.pieces.Bishop;
import machinecoding.chessgame.pieces.Knight;
import machinecoding.chessgame.pieces.Pawn;
import machinecoding.chessgame.pieces.Rook;

public class Board {
    Box[][] boxes;

    public Board() {
        this.resetBoard();
    }

    public Box getBox(int x, int y) {

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new RuntimeException("Index out of bound");
        }

        return boxes[x][y];
    }

    public void resetBoard() {
        // initialize white pieces
        boxes[0][0] = new Box(0, 0, new Rook(true));        // true : is white
        boxes[0][1] = new Box(0, 1, new Knight(true));
        boxes[0][2] = new Box(0, 2, new Bishop(true));
        //...
        boxes[1][0] = new Box(1, 0, new Pawn(true));
        boxes[1][1] = new Box(1, 1, new Pawn(true));
        //...

        // initialize black pieces
        boxes[7][0] = new Box(7, 0, new Rook(false));       // false : is black
        boxes[7][1] = new Box(7, 1, new Knight(false));
        boxes[7][2] = new Box(7, 2, new Bishop(false));
        //...
        boxes[6][0] = new Box(6, 0, new Pawn(false));
        boxes[6][1] = new Box(6, 1, new Pawn(false));
        //...

        // initialize remaining boxes without any piece
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = new Box(i, j, null);              // All other boxes will not have any piece so null
            }
        }
    }
}