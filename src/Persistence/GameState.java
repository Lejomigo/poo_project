package Persistence;

import Game.Pieces.Player;
import java.io.Serializable;
import java.util.List;

/**
 * GameState stores information about the current state of the game,
 * such as the list of players and other persistent details.
 */
public class GameState implements Serializable {

    private List<Player> players;
    private int currentPlayerIndex;

    /**
     * Default constructor.
     */
    public GameState() {
    }

    /**
     * Constructs a GameState with the provided player list and current turn index.
     *
     * @param players             List of players in the game.
     * @param currentPlayerIndex  Index of the player whose turn it is.
     */
    public GameState(List<Player> players, int currentPlayerIndex) {
        this.players = players;
        this.currentPlayerIndex = currentPlayerIndex;
    }

    /**
     * Gets the list of players.
     *
     * @return List of players.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Sets the list of players.
     *
     * @param players List of players.
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Gets the index of the current player.
     *
     * @return Index of current player.
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Sets the index of the current player.
     *
     * @param currentPlayerIndex Index to set.
     */
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
}
