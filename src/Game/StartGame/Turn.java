package Game.StartGame;

import Configuration.Questions.CategoryColors;
import Configuration.Questions.Question;
import Configuration.Questions.QuestionUpdates;
import Game.Dice;
import Game.GameBoard.BoardMovements;
import Game.GameBoard.Box.Box;
import Game.GameBoard.Matrix.Matrix;
import Game.GameBoard.Matrix.MatrixBoard;
import Game.Pieces.Piece;
import Game.Pieces.Player;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Turn {
    public static void turn(Player player, Dice dice, File fileQuestions){
        Scanner sc = new Scanner(System.in);
        System.out.println("Turno de " + player.getUser() + " presione enter para lanzar dado:");
        sc.nextLine();
        int movs =dice.lanzar();
        System.out.println("Tienes: "+ movs + " movimientos.");
        BoardMovements.selectMovement(player.getPiece(),movs);
        Box boxToAnsw = player.getPiece().getMatrix().getBoard()[player.getPiece().getPositionY()][player.getPiece().getPositionX()];
        CategoryColors colorToAsk = boxToAnsw.getColor();
        System.out.println("Categoría buscada: " + colorToAsk.getCategory());
        List<Question> questionList = QuestionUpdates.readQuestionForCategory(fileQuestions,colorToAsk);
        System.out.println(questionList.size());




    }

    public static void main(String[] args) {
        File file = new File("preguntasJuegoTrivia.json");
        Piece piece = new Piece( new MatrixBoard(), 3,2);
        Player p= new Player("leo@gmail.com",piece );
        Dice dado = new Dice(6);
        turn(p,dado,file);
    }
}
