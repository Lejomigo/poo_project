package Configuration.Questions;


/**
 * Represents a rejected trivia question, including the author, the reviewer who rejected it,
 * the reason for rejection, and the date of rejection.
 * Inherits basic question information from the Question class.
 */
public class QuestionReject extends Question {
    private String userAsk;     // Quién propuso
    private String userReject;  // Quién rechazó
    private String motivo;      // Opcional: razón de rechazo

    /**
     * Constructs a rejected question instance with the specified details.
     *
     * @param question the text of the question
     * @param answer the correct answer
     * @param category the category of the question
     * @param userAsk the user who proposed the question
     * @param userReject the user who rejected the question
     * @param motivo the reason for rejecting the question
     */
    public QuestionReject(String question, String answer, CategoryColors category, String userAsk, String userReject, String motivo) {
        super(question, answer, category);
        this.userAsk = userAsk;
        this.userReject = userReject;
        this.motivo = motivo;
    }
    /**
     * Gets the user who proposed the question.
     *
     * @return the proposing user
     */
    public String getUserAsk() {
        return userAsk;
    }

    /**
     * Sets the user who proposed the question.
     *
     * @param userAsk the proposing user
     */
    public void setUserAsk(String userAsk) {
        this.userAsk = userAsk;
    }

    /**
     * Gets the user who rejected the question.
     *
     * @return the rejecting user
     */
    public String getUserReject() {
        return userReject;
    }

    /**
     * Sets the user who rejected the question.
     *
     * @param userReject the rejecting user
     */
    public void setUserReject(String userReject) {
        this.userReject = userReject;
    }

    /**
     * Gets the reason for rejecting the question.
     *
     * @return the rejection reason
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Sets the reason for rejecting the question.
     *
     * @param motivo the rejection reason
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }




    /**
     * Returns a string representation of the rejected question,
     * including author, reviewer, category, reason and date.
     *
     * @return a formatted string of rejection details
     */
    @Override
    public String toString() {
        return "Pregunta rechazada por " + userReject + " (autor: " + userAsk + ") → " +
                getQuestion() + " [" + getCategory() + "]\n" +
                "Motivo: " + motivo ;
    }
}
