package Game.StartGame;

import Game.Pieces.Player;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que representa una sesión de juego.
 * Permite agregar jugadores, guardar y cargar la sesión desde un archivo JSON.
 */
public class GameSession {
    private String gameId;  // Identificador de la partida
    private List<Player> players;  // Lista de jugadores
    private transient String saveFileName;  // Nombre del archivo para guardar la partida (no se serializa)

    /**
     * Constructor que inicializa una nueva sesión de juego con un ID específico.
     *
     * @param gameId El identificador único de la partida.
     */
    public GameSession(String gameId) {
        this.gameId = gameId;
        this.players = new ArrayList<>();
        this.saveFileName = null;
    }

    /**
     * Agrega un jugador a la sesión de juego.
     *
     * @param player El jugador que se desea agregar.
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Devuelve la lista de jugadores en la sesión de juego.
     *
     * @return Lista de objetos Player.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Carga una sesión de juego desde un archivo JSON, si existe.
     * El archivo debe estar nombrado con el ID de la partida seguido de ".json".
     *
     * @param gameId El identificador de la partida que se desea cargar.
     */
    public void loadGame(String gameId) {
        File file = new File(gameId + ".json");
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Gson gson = new Gson();
                GameSession gameSession = gson.fromJson(reader, GameSession.class);
                this.gameId = gameSession.gameId;
                this.players = gameSession.players;
                this.saveFileName = gameId;  // Restaurar nombre del archivo
                System.out.println("Partida cargada con éxito.");
            } catch (IOException e) {
                System.err.println("Error al cargar la partida: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontró una partida con ese ID.");
        }
    }

    /**
     * Guarda la sesión de juego en un archivo JSON.
     * Solo pedirá el nombre del archivo la primera vez. Luego lo reutiliza.
     */
    public void saveGame() {
        if (saveFileName == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingresa el nombre para guardar la partida: ");
            String customName = scanner.nextLine().trim();

            if (customName.isEmpty()) {
                System.out.println("Nombre no válido. No se guardó la partida.");
                return;
            }

            saveFileName = customName;
        }

        File file = new File(saveFileName + ".json");

        if (file.exists()) {
            System.out.println("Sobrescribiendo archivo: " + saveFileName + ".json");
        } else {
            System.out.println("Creando nuevo archivo: " + saveFileName + ".json");
        }

        try (Writer writer = new FileWriter(file)) {
            Gson gson = new Gson();
            gson.toJson(this, writer);
            System.out.println("Partida guardada con éxito.");
        } catch (IOException e) {
            System.err.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
