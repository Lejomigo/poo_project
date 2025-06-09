package Persistence;

import Game.Pieces.Player;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * PlayerDataStore manages the persistence and retrieval of player-related data,
 * including progress, statistics, settings, and session tracking.
 */
public class PlayerDataStore {

    private static final String FILE_PATH = "players_data.json";
    private List<Player> players;
    private final Map<String, PlayerProgress> progressMap;
    private String currentPlayerId;
    private final Gson gson;

    /**
     * Constructs a PlayerDataStore instance with initialized data structures.
     */
    public PlayerDataStore() {
        this.gson = new Gson();
        this.players = new ArrayList<>();
        this.progressMap = new HashMap<>();
    }

    /**
     * Loads the list of players and their data from the storage file.
     */
    public void loadPlayers() {
        try (FileReader reader = new FileReader(new File(FILE_PATH))) {
            TypeToken<List<Player>> token = new TypeToken<>() {};
            players = gson.fromJson(reader, token.getType());
        } catch (IOException e) {
            e.printStackTrace();
            players = new ArrayList<>();
        }
    }

    /**
     * Saves the current list of players to the storage file.
     */
    public void savePlayers() {
        try (FileWriter writer = new FileWriter(new File(FILE_PATH))) {
            gson.toJson(players, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new player to the datastore.
     *
     * @param player The player to be added.
     */
    public void addPlayer(Player player) {
        players.add(player);
        progressMap.put(player.getUser(), new PlayerProgress());
        savePlayers();
    }

    /**
     * Retrieves a player by ID.
     *
     * @param id The unique ID of the player.
     * @return The corresponding Player or null if not found.
     */
    public Player getPlayerById(String id) {
        for (Player player : players) {
            if (player.getUser().equals(id)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Updates the progress of a player.
     *
     * @param playerId The ID of the player.
     * @param progress The new progress to store.
     */
    public void updatePlayerProgress(String playerId, PlayerProgress progress) {
        progressMap.put(playerId, progress);
    }

    /**
     * Retrieves the progress of a player.
     *
     * @param playerId The ID of the player.
     * @return The PlayerProgress object or a new one if not found.
     */
    public PlayerProgress getPlayerProgress(String playerId) {
        return progressMap.getOrDefault(playerId, new PlayerProgress());
    }

    /**
     * Sets the current active player ID.
     *
     * @param playerId The ID of the active player.
     */
    public void setCurrentPlayer(String playerId) {
        this.currentPlayerId = playerId;
    }

    /**
     * Gets the current active player ID.
     *
     * @return The current player's ID.
     */
    public String getCurrentPlayerId() {
        return currentPlayerId;
    }

    /**
     * Inner class to track individual player progress and statistics.
     */
    public static class PlayerProgress {
        public int questionsAnswered;
        public int categoriesCompleted;
        public int points;
        public int diceRolls;
        public String favoriteCategory;
        public int correctAnswers;
        public int totalAnswers;
        public String pieceColor;
        public int preferredTimeLimit;

        public PlayerProgress() {
            this.questionsAnswered = 0;
            this.categoriesCompleted = 0;
            this.points = 0;
            this.diceRolls = 0;
            this.favoriteCategory = "";
            this.correctAnswers = 0;
            this.totalAnswers = 0;
            this.pieceColor = "default";
            this.preferredTimeLimit = 30;
        }
    }
}
