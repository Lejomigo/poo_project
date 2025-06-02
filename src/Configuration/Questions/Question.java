package Configuration.Questions;

import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;


public class Question {
    private String question;
    private String answer;
    private CategoryColors category;

    public Question(String question, String answer, CategoryColors category) {
        setQuestion(question);
        setAnswer(answer);
        setCategory(category);

    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCategory(CategoryColors category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public CategoryColors getCategory() {
        return category;
    }

    /**
     * Returns a string representation of the question, including the question text,
     * its answer, and the associated category.
     *
     * @return A formatted string displaying the question, answer, and category.
     */
    @Override
    public String toString() {
        return "Pregunta: " + question + " Respuesta: " + answer + " Categoria: " + category;
    }

}
