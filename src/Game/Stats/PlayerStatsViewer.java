package Game.Stats;

import Game.Pieces.Player;
import Game.Pieces.Piece;
import Game.Pieces.PieceColor;

import java.util.Map;

/**
 * Utility class to print the player's game statistics.
 */
public class PlayerStatsViewer {

    /**
     * Prints the statistics of a given player.
     *
     * @param player the player whose stats are to be displayed
     */
    public static void printPlayerStats(Player player) {
        System.out.println("Estadísticas del jugador: " + player.getUser());
        System.out.println("Progreso por categoría:");

        Piece piece = player.getPiece();
        int totalCategories = piece.getListAnswer().size();
        int categoriesAnswered = 0;

        for (PieceColor color : piece.getListAnswer()) {
            boolean answered = color.getAnswered();
            System.out.printf("- %s: %s\n", color.getColor(), answered ? "Respondida" : "Pendiente");

            if (answered) {
                categoriesAnswered++;
            }
        }

        double progress = (double) categoriesAnswered / totalCategories * 100;
        System.out.printf("Progreso total: %.2f%%\n", progress);
    }
}
