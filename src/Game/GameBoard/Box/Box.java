package Game.GameBoard.Box;

import Configuration.Questions.CategoryColors;
import Game.Pieces.Piece;

import java.util.List;

public class Box {
    CategoryColors color;
    public int number;
    private List<Piece> pieces;

    public Box(CategoryColors color, int number, List<Piece> pieces) {
        setColor(color);
        setNumber(number);
        setPieces(pieces);
    }

    public void setColor(CategoryColors color) {
        this.color = color;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public CategoryColors getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
