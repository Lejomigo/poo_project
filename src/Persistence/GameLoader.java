package Persistence;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the loading of game state from a persistent storage (e.g., JSON file).
 */
public class GameLoader {

    private static final Logger LOGGER = Logger.getLogger(GameLoader.class.getName());

    /**
     * Loads the saved game state from a JSON file.
     *
     * @param filePath the path to the saved game file
     * @return the loaded GameState object, or null if loading fails
     */
    public static GameState loadGame(String filePath) {
        try {
            FileManager<GameState> fileManager = new JsonFileManager<>();
            return fileManager.readFromFile(filePath, GameState.class);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to load game state from: " + filePath, e);
            return null;
        }
    }
    /**
     * Saves the current game state to a JSON file.
     *
     * @param filePath the path where the game state should be saved
     * @param gameState the GameState object to save
     */
    public static void saveGame(String filePath, GameState gameState) {
        try {
            FileManager<GameState> fileManager = new JsonFileManager<>();
            fileManager.writeToFile(filePath, gameState);
            LOGGER.info("Game state successfully saved to: " + filePath);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to save game state to: " + filePath, e);
        }
    }
}
