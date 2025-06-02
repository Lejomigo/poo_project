package Game.StartGame;

import Game.Pieces.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameSession {
    private String gameId;  // Identificador de la partida
    private List<Player> players;  // Lista de jugadores

    public GameSession(String gameId) {
        this.gameId = gameId;
        this.players = new ArrayList<>();
    }

    // Método para agregar un jugador a la partida
    public void addPlayer(Player player) {
        players.add(player);
    }

    // Método para obtener la lista de jugadores
    public List<Player> getPlayers() {
        return players;
    }

    // Método para cargar la partida desde un archivo JSON
    public void loadGame(String gameId) {
        File file = new File(gameId + ".json");
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Gson gson = new Gson();
                GameSession gameSession = gson.fromJson(reader, GameSession.class);
                this.gameId = gameSession.gameId;
                this.players = gameSession.players;
                System.out.println("Partida cargada con éxito.");
            } catch (IOException e) {
                System.err.println("Error al cargar la partida: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontró una partida con ese ID.");
        }
    }

    // Método para guardar la partida en un archivo JSON (crea un nuevo archivo si no existe)
    public void saveGame() {
        // Si el archivo ya existe, lo sobrescribimos
        File file = new File(gameId + ".json");

        // Crear el archivo si no existe
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Nuevo archivo creado para la partida: " + gameId);
            } else {
                System.out.println("Sobrescribiendo el archivo de la partida: " + gameId);
            }

            // Guardar la información de la partida en el archivo JSON
            try (Writer writer = new FileWriter(file)) {
                Gson gson = new Gson();
                gson.toJson(this, writer);
                System.out.println("Partida guardada con éxito.");
            }

        } catch (IOException e) {
            System.err.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    // Método para obtener el ID de la partida
    public String getGameId() {
        return gameId;
    }

    // Método para establecer el ID de la partida
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
