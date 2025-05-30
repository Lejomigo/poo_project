package Game;

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
}