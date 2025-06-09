package Persistence;

import java.io.File;

import Game.Pieces.Player;

/**
 * The ProgressManager class handles saving and loading a player's progress
 * by delegating to a FileManager implementation.
 */
public class ProgressManager {

    private final FileManager<Player> fileManager;
    private final String filePath;

    /**
     * Constructs a ProgressManager with the specified FileManager and path.
     *
     * @param fileManager The FileManager to use for saving/loading.
     * @param filePath    The file path for storing progress.
     */
    public ProgressManager(FileManager<Player> fileManager, String filePath) {
        this.fileManager = fileManager;
        this.filePath = filePath;
    }


    /**
     * Saves the current progress of the specified player.
     *
     * @param player The player whose progress is to be saved.
     */
    public void saveProgress(Player player) {
        File file = new File(this.filePath);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs(); // Ensure directories exist
        }
        fileManager.writeToFile(this.filePath, player);
    }

    /**
     * Loads the progress of the player from the file.
     *
     * @return A Player object with loaded progress, or null if loading fails.
     */
    public Player loadProgress() {
        return fileManager.readFromFile(this.filePath, Player.class);
    }
}
