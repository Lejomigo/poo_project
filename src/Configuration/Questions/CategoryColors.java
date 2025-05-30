package Configuration.Questions;

public class CategoryColors {
    String category;
    String color;
    int position;

    public CategoryColors(String category, String color, int position) {
        setCategory(category);
        setColor(color);
        setPosition(position);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCategory() {
        return category;
    }

    public String getColor() {
        return color;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Categoria='" + category + '\'' +
                ", Color='" + color + '\'' +
                ", Indice=" + position;
    }
}
