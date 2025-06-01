package Game.GameBoard.Box;

import Configuration.Questions.CategoryColors;
import Game.Pieces.Piece;

import java.util.List;

public class SpecialBox extends Box {

    public SpecialBox(CategoryColors color, int number, List<Piece> pieces) {
        super(null, number, pieces);
    }

}