package Game.StartGame;

import Persistence.FileManager;
import Persistence.JsonFileManager;
import Persistence.ProgressManager;

import Configuration.Register;
import Configuration.User;
import Game.Dice;
import Game.GameBoard.Matrix.Matrix;
import Game.GameBoard.Matrix.MatrixCenter;
import Game.Pieces.Piece;
import Game.Pieces.Player;
import Utilies.Easymeth;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static Game.StartGame.Turn.turn;

public class Play {

    public static void playGame(User userIn, int time) throws Exception {


            List<Player>players = new ArrayList<>();
            Matrix matrixCenter = new MatrixCenter();
            Dice dice = new Dice();
            File fileQuestion = new File("preguntasJuegoTrivia.json");
            players.add(new Player(userIn.getMail(), new Piece(matrixCenter, 0, 5)));
            System.out.println("Que empiece el juego! " + userIn.getMail());
            int option = Easymeth.getInt("Desea agregar otro jugador? (1) si (2)no:");
            while (option == 1) {
                User addPlayer = Register.login();
                players.add(new Player(addPlayer.getMail(), new Piece(matrixCenter, 0, 5)));
                option = Easymeth.getInt("Desea agregar otro jugador? (1) si (2)no:");
                players = lanzarYOrdenarJugadores(players);

            }
            int size = 0;
            FileManager fileManager = new JsonFileManager();
            String filePath = "data/progress.json";
            ProgressManager progressManager = new ProgressManager(fileManager,filePath);
            while (true) {
                if (size == players.size()) {
                    size = 0;
                }

                turn(players.get(size), dice, fileQuestion, time, progressManager);
                size++;
            }






    }

    public static List<Player> lanzarYOrdenarJugadores(List<Player> jugadores) {
        Dice dado = new Dice(6); // dado de 6 caras
        Map<Player, Integer> resultados = new HashMap<>();

        for (Player jugador : jugadores) {
            int resultado = dado.lanzar();
            resultados.put(jugador, resultado);
            System.out.println(jugador.getUser() + " lanzó: " + resultado);
        }

        // Ordenar por resultado de mayor a menor
        return resultados.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
