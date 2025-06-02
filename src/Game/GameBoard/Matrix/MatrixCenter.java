package Game.GameBoard.Matrix;

import Configuration.Questions.CategoryColors;
import Configuration.Questions.QuestionUpdates;
import Game.GameBoard.Box.Box;
import Game.GameBoard.Box.SpecialBox;
import Game.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class MatrixCenter extends Matrix {

    public MatrixCenter() {
        super(generateMatrix()); // esto llama al constructor de Matrix y pasa la matriz
    }

    public static Box[][] generateMatrix(){
        Box[][] center = new Box[6][6];
        List<CategoryColors> colors = QuestionUpdates.createCategoryColors();
        List<Piece> pieces = new ArrayList<>();
        CategoryColors selectColor;
        int cont = 43;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6 ; j++) {
                if (j == 5){

                    center[j][i]= new SpecialBox(null,0,pieces);

                }else {

                    selectColor = colors.get(j);
                    center[j][i] = new Box(selectColor,cont,pieces);

                }
                cont++;
            }
        }
        return center;
    }

    public static void main(String[] args) {
        Matrix m = new MatrixCenter();
        System.out.println("MatrizCentro");
        m.printBoard();
        Matrix m1 = new MatrixBoard();
        System.out.println("matriz borde");
        m1.printBoard();
    }

}
