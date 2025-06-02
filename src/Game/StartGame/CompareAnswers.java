package Game.StartGame;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class CompareAnswers {


    public static String normalize(String text) {
        text = text.toLowerCase();
        text = text.replaceAll("[^a-z0-9 ]", "");
        text = text.replaceAll("\\b(el|la|los|las|un|una|unos|unas|de|del|es|en)\\b", "");
        text = text.trim().replaceAll(" +", " ");
        return text;
    }

    public static boolean compareAnswers(String correctAnswer, String userAnswer) {
        String a = normalize(correctAnswer);
        String b = normalize(userAnswer);

        LevenshteinDistance ld = new LevenshteinDistance();
        int distance = ld.apply(a, b);
        int maxLen = Math.max(a.length(), b.length());

        if (maxLen == 0) return false;

        double similarity = 1.0 - ((double) distance / maxLen);

        return similarity >= 0.65;
    }

}
