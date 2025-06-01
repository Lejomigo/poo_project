package Game.Pieces;

public class PieceColor {
    String color;
    Boolean answered;

    public PieceColor(String color, Boolean answered) {
        this.color = color;
        this.answered = answered;
    }

    public String getColor() {
        return color;
    }

    public Boolean getAnswered() {
        return answered;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }
}