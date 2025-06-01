package Session;

/**
 * Represents a game session with its configuration and progress.
 * Stores whether the game is timed, the time limit per question,
 * total score, and number of answered questions.
 */
public class GameSession {

    private boolean timedGame;
    private int timeLimitPerQuestion; // in seconds
    private int totalScore;
    private int answeredQuestions;

    /**
     * Default constructor that initializes a new game session.
     */
    public GameSession() {
        this.timedGame = false;
        this.timeLimitPerQuestion = 30;
        this.totalScore = 0;
        this.answeredQuestions = 0;
    }

    /**
     * Checks whether the game is configured to be timed.
     *
     * @return true if the game is timed, false otherwise.
     */
    public boolean isTimedGame() {
        return timedGame;
    }

    /**
     * Configures the game to be timed or not.
     *
     * @param timedGame true to enable timed gameplay, false to disable it.
     */
    public void setTimedGame(boolean timedGame) {
        this.timedGame = timedGame;
    }

    /**
     * Retrieves the time limit per question for the session.
     *
     * @return the time limit in seconds.
     */
    public int getTimeLimitPerQuestion() {
        return timeLimitPerQuestion;
    }

    /**
     * Sets the time limit per question.
     *
     * @param timeLimitPerQuestion the time limit in seconds.
     */
    public void setTimeLimitPerQuestion(int timeLimitPerQuestion) {
        this.timeLimitPerQuestion = timeLimitPerQuestion;
    }

    /**
     * Returns the current total score of the game session.
     *
     * @return the total score as an integer.
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Adds points to the total score.
     *
     * @param points the number of points to be added.
     */
    public void addPoints(int points) {
        this.totalScore += points;
    }

    /**
     * Returns the number of questions answered during the session.
     *
     * @return the number of answered questions.
     */
    public int getAnsweredQuestions() {
        return answeredQuestions;
    }

    /**
     * Increments the counter of answered questions by one.
     */
    public void incrementAnsweredQuestions() {
        this.answeredQuestions++;
    }

    /**
     * Resets the game session statistics including score and answered questions.
     */
    public void resetSession() {
        this.totalScore = 0;
        this.answeredQuestions = 0;
    }
}