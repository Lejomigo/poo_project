package Game.GameBoard.Matrix;

import Game.GameBoard.Box.Box;

public abstract class Matrix {
    protected Box[][] board;

    public Matrix(Box[][] board) {
        this.board = board;
    }

    public Box[][] getBoard() {
        return board;
    }

    public void printBoard() {
        for (Box[] fila : board) {
            for (Box casilla : fila) {
                System.out.print("[" + casilla.getNumber() + "]");
            }
            System.out.println();
        }
    }
}
