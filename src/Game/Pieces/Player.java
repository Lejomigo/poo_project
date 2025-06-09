package Game.Pieces;

import java.util.Set;

public class Player {
    public String user;
    public Piece piece;
    private Set<String> completedCategories;

    public Player(String user, Piece piece) {
        this.user = user;
        this.piece = piece;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public String getUser() {
        return user;
    }

    public Piece getPiece() {
        return piece;
    }
    public void markCategoryAsCompleted(String category) {
        completedCategories.add(category);
    }
    public boolean hasCompletedCategory(String category) {
        return completedCategories.contains(category);
    }
    public boolean hasCompletedAllCategories(Set<String> allCategories) {
        return completedCategories.containsAll(allCategories);
    }
}
