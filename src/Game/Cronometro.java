package Game;

import java.util.Scanner;

public class Cronometro {

    private long tiempoInicio;   // Timestamp cuando empieza
    private long tiempoLimite;   // Tiempo límite en milisegundos
    private boolean enCurso;

    public Cronometro() {
        this.enCurso = false;
    }

    // Inicia el cronómetro con un tiempo límite (en segundos)
    public void iniciar(int segundosLimite) {
        this.tiempoInicio = System.currentTimeMillis();
        this.tiempoLimite = segundosLimite * 1000L;
        this.enCurso = true;
    }

    // Devuelve true si el tiempo límite ya fue alcanzado
    public boolean haExpirado() {
        if (!enCurso) return false;
        return System.currentTimeMillis() - tiempoInicio >= tiempoLimite;
    }

    // Detiene el cronómetro y devuelve el tiempo transcurrido (en milisegundos)
    public long detener() {
        if (!enCurso) return 0;
        long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
        this.enCurso = false;
        return tiempoTranscurrido;
    }

    // Devuelve el tiempo restante (en segundos)
    public int tiempoRestante() {
        if (!enCurso) return 0;
        long restante = tiempoLimite - (System.currentTimeMillis() - tiempoInicio);
        return (int) Math.max(restante / 1000, 0);
    }

    public boolean estaActivo() {
        return enCurso;
    }
    public static Cronometro configurarCronometro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Quieres activar el cronómetro?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        int opcion = scanner.nextInt();

        Cronometro cronometro = new Cronometro();

        if (opcion == 1) {
            System.out.println("Selecciona el tiempo límite por pregunta:");
            System.out.println("1. 15 segundos");
            System.out.println("2. 30 segundos");
            System.out.println("3. 60 segundos");
            System.out.println("4. Custom Time");
            int tiempo = scanner.nextInt();
            int segundos = switch (tiempo) {
                case 1 -> 15;
                case 2 -> 30;
                case 3 -> 60;
                case 4 -> {
                    System.out.print("Ingresa el tiempo personalizado en segundos: ");
                    yield scanner.nextInt();
                }
                default -> 30;
            };
            cronometro.iniciar(segundos);
        }

        return cronometro;
    }
}