package Configuration.Questions;

import java.time.LocalDateTime;

public class QuestionReject extends Question {
    private String userAsk;     // Quién propuso
    private String userReject;  // Quién rechazó
    private String motivo;      // Opcional: razón de rechazo
    private LocalDateTime fechaRechazo;

    public QuestionReject(String question, String answer, CategoryColors category, String userAsk, String userReject, String motivo) {
        super(question, answer, category);
        this.userAsk = userAsk;
        this.userReject = userReject;
        this.motivo = motivo;
        this.fechaRechazo = LocalDateTime.now();
    }
    public String getUserAsk() {
        return userAsk;
    }

    public void setUserAsk(String userAsk) {
        this.userAsk = userAsk;
    }

    public String getUserReject() {
        return userReject;
    }

    public void setUserReject(String userReject) {
        this.userReject = userReject;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFechaRechazo() {
        return fechaRechazo;
    }

    public void setFechaRechazo(LocalDateTime fechaRechazo) {
        this.fechaRechazo = fechaRechazo;
    }

    @Override
    public String toString() {
        return "Pregunta rechazada por " + userReject + " (autor: " + userAsk + ") → " +
                getQuestion() + " [" + getCategory() + "]\n" +
                "Motivo: " + motivo + "\nFecha: " + fechaRechazo;
    }
}

