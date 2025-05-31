package Game.GameBoard;

import Configuration.Questions.CategoryColors;
import Configuration.Questions.QuestionUpdates;
import Game.Piece;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public static Box[][] center = new Box[6][7];
    public static Box[][]matrixCenter = new Box[6][6] ;

    public static Box[][] board(){
        Box[][] board = center;
        List<CategoryColors> colors = QuestionUpdates.createCategoryColors();
        List<Piece> pieces = new ArrayList<>();
        CategoryColors selectColor;
        int cont = 1;
        int contColor=0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7 ; j++) {
                if (contColor> 5){
                    contColor = 0;
                }
                if (j == 2 || j == 5){

                    center[i][j]= new SpecialBox(null,cont,pieces);

                }else {

                    selectColor = colors.get(contColor);
                    center[i][j] = new Box(selectColor,cont,pieces);
                    contColor++;
                }
                cont++;
            }
        }
        return board;
    }

    public static Box[][] center(){
        Box[][] center= matrixCenter;
        List<CategoryColors> colors = QuestionUpdates.createCategoryColors();
        List<Piece> pieces = new ArrayList<>();
        CategoryColors selectColor;
        int cont = 1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6 ; j++) {
                if (j == 5){

                   center[i][j]= new SpecialBox(null,cont,pieces);

                }else {

                    selectColor = colors.get(j);
                    center[i][j] = new Box(selectColor,cont,pieces);

                }
                cont++;
            }
        }
        return center;
    }

    public static void main(String[] args) {
        Box[][]matriXBoard= center();
        for (Box[] fila : matriXBoard) {
            for (Box casilla : fila) {
                System.out.println(casilla.getNumber());
                System.out.println(casilla.getColor());
            }
        }
    }
}

