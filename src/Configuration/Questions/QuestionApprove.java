package Configuration.Questions;

import Configuration.User;

public class QuestionApprove extends Question{
    String userAsk;
    public QuestionApprove(String question, String answer, CategoryColors category, String userAsk) {
        super(question, answer, category);
        setUserAsk(userAsk);
    }

    public void setUserAsk(String userAsk) {
        this.userAsk = userAsk;
    }

    @Override

    public String toString() {
        return "User: " + userAsk + " " + super.toString();
    }
}
