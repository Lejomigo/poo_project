package Game.StartGame;

import Configuration.Questions.CategoryColors;
import Configuration.Questions.Question;
import Configuration.Questions.QuestionUpdates;
import Game.Dice;
import Game.GameBoard.BoardMovements;
import Game.GameBoard.Box.Box;
import Game.GameBoard.Matrix.Matrix;
import Game.GameBoard.Matrix.MatrixBoard;
import Game.GameBoard.Matrix.MatrixCenter;
import Game.Pieces.Piece;
import Game.Pieces.Player;
import Game.Stopwatch;
import Utilies.Easymeth;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static Configuration.Questions.QuestionUpdates.*;

public class Turn {

    public static void turn(Player player, Dice dice, File fileQuestions, int time){
        Scanner sc = new Scanner(System.in);
        System.out.println("Turno de " + player.getUser() + " presione enter para lanzar dado:");
        sc.nextLine();
        int movs =dice.lanzar();
        System.out.println("Tienes: "+ movs + " movimientos.");
        BoardMovements.selectMovement(player.getPiece(),movs);
        Box boxToAnsw = player.getPiece().getMatrix().getBoard()[player.getPiece().getPositionY()][player.getPiece().getPositionX()];
        if (player.getPiece().getPositionY() == 5 && player.getPiece().getMatrix().getBoard().length ==6 ){
            System.out.println("Estas en el centro. ");
            if (player.getPiece().checkWin()){
                if (player.getPiece().checkWin()){
                    winCondition(player,fileQuestions,time);
                }
            }else{
                return;
            }
        }
        if (boxToAnsw.getColor() == null){
            System.out.println("Caiste en casilla especial. ");
            turn(player,dice,fileQuestions,time);
            return;
        }else {
            if (answerQuestion(player,fileQuestions,boxToAnsw.getColor(),time)){
                player.getPiece().getListAnswer().get(boxToAnsw.getColor().getPosition()).setAnswered(true);
                turn(player,dice,fileQuestions,time);
                return;
            }
        }




    }

    public static void winCondition(Player player,File fileQuestions, int time){
            printColors();
            int option = Easymeth.getInt("Selecciona el tipo de pregunta que quieres (si respondes ganas): ");
            while (option<1 && option >6){
                option = Easymeth.getInt("Selecciona el tipo de pregunta que quieres (si respondes ganas): ");
            }
            CategoryColors colorToWin = colors.get(option-1);
            boolean win =answerQuestion(player, fileQuestions, colorToWin,time);
            if (win){
                System.out.println("Felicidades, has ganado. " + player.getUser());
                System.exit(0);
            }else{
                System.out.println("Sigue intentando. ");
                return;
            }

    }

    public static boolean answerQuestion(Player player,File fileQuestions, CategoryColors colorToAsk, int time){
        boolean answered = false;
        Stopwatch timer = new Stopwatch();
        List<Question> questionList = readQuestionForCategory(fileQuestions,colorToAsk);
        Random r = new Random();
        int randomNum = r.nextInt(questionList.size());
        Question questToAnsw = questionList.get(randomNum);
        if (time == 0){
            System.out.println(questToAnsw.getQuestion());
            String answer = Easymeth.getString("Respuesta: " );
            if (CompareAnswers.compareAnswers(questToAnsw.getAnswer(),answer)){
                System.out.println("Haz respondido Correctamente. ");
                return answered = true;
            }else{
                System.out.println("❌ Incorrecto. La respuesta correcta era: " + questToAnsw.getAnswer());
                return answered;
            }
        }else {
            timer.start(time);
            String answerUser = "";

            while (!timer.hasExpired() && answerUser.isBlank()){

                System.out.println("Tiempo: " + time + " segundos restantes.");
                System.out.println(questToAnsw.getQuestion());
                answerUser = Easymeth.getString("Respuesta: ");
            }
            if (timer.hasExpired()) {
                System.out.println("\n⏰ ¡Se acabó el tiempo!");
                answered = false;
                return answered;
            }
            if (!answerUser.isBlank()) {
                if (CompareAnswers.compareAnswers(questToAnsw.getAnswer(),answerUser)){
                    System.out.println("Haz respondido Correctamente. ");
                    return true;
                }else{
                    System.out.println("❌ Incorrecto. La respuesta correcta era: " + questToAnsw.getAnswer());
                    return answered;
                }
            }


        }

        return answered;
    }

    public static void main(String[] args) {

        int time = 5;
        File file = new File("preguntasJuegoTrivia.json");
        Piece piece = new Piece( new MatrixCenter(), 0,5);
        Player p= new Player("leo@gmail.com",piece );
        Dice dado = new Dice(6);
        turn(p,dado,file,time);
        System.out.println();
    }
}
