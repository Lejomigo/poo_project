package Game.GameBoard.Matrix;

import Configuration.Questions.CategoryColors;
import Configuration.Questions.QuestionUpdates;
import Game.GameBoard.Box.Box;
import Game.GameBoard.Box.CenterBox;
import Game.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class MatrixBoard extends Matrix {



    public MatrixBoard() {
        super(generateMatrix()); // esto llama al constructor de Matrix y pasa la matriz
    }

    private static Box[][] generateMatrix() {
        Box[][] matrix = new Box[7][6];
        List<CategoryColors> colors = QuestionUpdates.createCategoryColors();
        List<Piece> pieces = new ArrayList<>();
        int cont = 1;
        int contColor = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (contColor > 5) contColor = 0;

                if (j == 2 || j == 5) {
                    matrix[j][i] = new CenterBox(null, cont, pieces);
                } else {
                    CategoryColors color = colors.get(contColor);
                    matrix[j][i] = new Box(color, cont, pieces);
                    contColor++;
                }
                cont++;
            }
        }
        return matrix;
    }


}
