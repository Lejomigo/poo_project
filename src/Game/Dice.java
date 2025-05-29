package Game;
import java.util.Random;
public class Dice {
    private final int caras;       // Número de caras del dado (por defecto 6)
    private final Random random;   // Generador de números aleatorios
    public Dice() {
        this.caras = 6;
        this.random = new Random();
    }

    public Dice(int caras) {
        this.caras = caras;
        this.random = new Random();
    }

    // Método para lanzar el dado y obtener un número entre 1 y caras
    public int lanzar() {
        return random.nextInt(caras) + 1;
    }

    public int getCaras() {
        return caras;
    }
}
