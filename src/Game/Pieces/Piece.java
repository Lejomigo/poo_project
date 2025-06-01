package Game.Pieces;


import Game.GameBoard.Matrix.Matrix;

import java.util.ArrayList;
import java.util.List;

public class Piece {
    private static List<PieceColor> listAnswer;
    private Matrix matrix;
    private int positionX;
    private int getPositionY;

    public Piece( Matrix matrix, int positionX, int getPositionY) {
        this.listAnswer = setListAnswer();
        this.matrix = matrix;
        this.positionX = positionX;
        this.getPositionY = getPositionY;
    }

    public List<PieceColor> setListAnswer() {
        List<PieceColor> categoryColors = new ArrayList<>();
        categoryColors.add(new PieceColor("Azul",false));
        categoryColors.add(new PieceColor("Amarillo",false));
        categoryColors.add(new PieceColor("Naranja",false));
        categoryColors.add(new PieceColor("Verde",false));
        categoryColors.add(new PieceColor("Morado",false));
        categoryColors.add(new PieceColor("Rosado",false));
        return categoryColors;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int getPositionY) {
        this.getPositionY = getPositionY;
    }

    public List<PieceColor> getListAnswer() {
        return listAnswer;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return getPositionY;
    }
}
