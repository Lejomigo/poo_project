package Game.Pieces;

public class Player {
    String user;
    Piece piece;

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
}
