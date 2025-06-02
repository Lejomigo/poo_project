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

    public String getFormattedBox() {
        // Si la caja es especial, el número se imprimirá en blanco
        if (this instanceof SpecialBox) {
            return String.format("\033[0;37m%d\033[0m", number);  // ANSI escape code para blanco
        }
        return String.format("%d", number);
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
