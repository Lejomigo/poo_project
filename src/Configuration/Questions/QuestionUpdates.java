package Configuration.Questions;
import Configuration.User;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;


/**
 * Utility class for managing question-related file operations including
 * adding, reading, updating, deleting, and printing questions from JSON files.
 */
public class QuestionUpdates {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static final List<CategoryColors> colors = createCategoryColors();

    /**
     * Creates the list of predefined category colors used in the game.
     *
     * @return A list of CategoryColors representing the available categories.
     */
    public static List<CategoryColors> createCategoryColors(){
        List<CategoryColors> categoryColors = new ArrayList<>();
        categoryColors.add(new CategoryColors("Geografía","Azul", 0));
        categoryColors.add(new CategoryColors("Historia","Amarillo", 1));
        categoryColors.add(new CategoryColors("Deportes","Naranja", 2));
        categoryColors.add(new CategoryColors("Ciencia","Verde", 3));
        categoryColors.add(new CategoryColors("Arte y Literatura", "Morado", 4));
        categoryColors.add(new CategoryColors("Entretenimiento","Rosado", 5));
        return categoryColors;
    }

    /**
     * Adds a question pending approval to the specified file.
     *
     * @param file     The file to write to.
     * @param question The question to be added for approval.
     */
    public static void addQuestionToApprove(File file, QuestionApprove question) {
        try {
            Map<String, List<Map<String, String>>> data = new HashMap<>();

            // Leer contenido existente
            if (file.exists() && file.length() > 0) {
                try (Reader reader = new FileReader(file)) {
                    Type type = new TypeToken<Map<String, List<Map<String, String>>>>() {}.getType();
                    data = gson.fromJson(reader, type);
                }
            }

            // Obtener o crear la lista de la categoría
            List<Map<String, String>> questionsList = data.getOrDefault(question.getCategory(), new ArrayList<>());

            // Crear nueva pregunta con LinkedHashMap para mantener orden
            Map<String, String> newQuestion = new LinkedHashMap<>();
            newQuestion.put("usuario", question.userAsk);
            newQuestion.put("pregunta", question.getQuestion());
            newQuestion.put("respuesta", question.getAnswer());

            questionsList.add(newQuestion);
            data.put(question.getCategory().category, questionsList);

            // Escribir al archivo
            try (Writer writer = new FileWriter(file)) {
                gson.toJson(data, writer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a question to the specified file under the appropriate category.
     * Appends the question if the category already exists.
     *
     * @param file     The file to write to.
     * @param question The question to be added.
     */
    public static void addQuestionToFile(File file, Question question) {
        try {
            Map<String, List<Map<String, String>>> data = new HashMap<>();

            // Read existing content
            if (file.exists() && file.length() > 0) {
                try (Reader reader = new FileReader(file)) {
                    Type type = new TypeToken<Map<String, List<Map<String, String>>>>() {}.getType();
                    data = gson.fromJson(reader, type);
                }
            }

            // Normalize category key
            String categoryKey = question.getCategory().getCategory().trim().toLowerCase();

            // Get or create the list for the category
            List<Map<String, String>> questionsList = data.getOrDefault(categoryKey, new ArrayList<>());

            // Create new question map with insertion order preserved
            Map<String, String> newQuestion = new LinkedHashMap<>();
            newQuestion.put("pregunta", question.getQuestion());
            newQuestion.put("respuesta", question.getAnswer());

            questionsList.add(newQuestion);
            data.put(categoryKey, questionsList);

            // Write updated data back to file
            try (Writer writer = new FileWriter(file)) {
                gson.toJson(data, writer);
            }

        } catch (IOException e) {
            System.err.println("Error añadiendo la pregunta al archivo: " + e.getMessage());
        }
    }

    /**
     * Overwrites the file with a new list of approved questions.
     *
     * @param file The file to be overwritten.
     * @param list The list of approved questions.
     */
    public static void updateFileApprove(File file, List<QuestionApprove> list){
        try {
            FileWriter writer = new FileWriter(file, false); // false para sobrescribir
            writer.write(""); // Escribimos una cadena vacía
            writer.close();

            for (QuestionApprove q: list){
                addQuestionToApprove(file, q);
                writer.flush();
            }
        } catch (IOException e) {
            System.err.println("Error al vaciar el archivo: " + e.getMessage());
        }
    }

    /**
     * Overwrites the file with a new list of final game questions.
     *
     * @param file The file to be overwritten.
     * @param list The list of questions.
     */
    public static void updateFileQuestion(File file, List<Question> list){
        try {
            FileWriter writer = new FileWriter(file, false); // false para sobrescribir
            writer.write(""); // Escribimos una cadena vacía
            writer.close();

            for (Question q: list){
                addQuestionToFile(file, q);

            }
        } catch (IOException e) {
            System.err.println("Error al vaciar el archivo: " + e.getMessage());
        }
    }

    /**
     * Removes the specified question from the list of questions.
     *
     * @param questions      The list of questions.
     * @param questionDelete The question to be removed.
     */
    public static void deleteQuestion(List<Question>questions, Question questionDelete){
        for (Question q: questions){
            if (q.equals(questionDelete)){
                questions.remove(questionDelete);
                return;
            }

        }

    }

    /**
     * Reads and returns all approved questions from the given file.
     *
     * @param file The JSON file containing the questions.
     * @return A list of Question objects.
     */
    public static List<Question> readQuestions(File file) {
        List<Question> questionList = new ArrayList<>();
        try {
            if (!file.exists()) {
                System.out.println("Archivo no encontrado. Creando archivo...");
                try (Writer writer = new FileWriter(file)) {
                    writer.write("{}");
                }
            }

            try (Reader reader = new FileReader(file)) {
                Type type = new TypeToken<Map<String, List<Map<String, String>>>>() {}.getType();
                Map<String, List<Map<String, String>>> data = gson.fromJson(reader, type);

                for (Map.Entry<String, List<Map<String, String>>> entry : data.entrySet()) {
                    String category = entry.getKey();
                    CategoryColors categoryColors = null;
                    for (CategoryColors cat: colors){

                        if (category.trim().equalsIgnoreCase(cat.getCategory().trim())){
                            categoryColors = cat;
                            break;
                        }
                    }

                    List<Map<String, String>> rawQuestions = entry.getValue();



                    for (Map<String, String> q : rawQuestions) {
                        String pregunta = q.get("pregunta");
                        String respuesta = q.get("respuesta");
                        questionList.add(new Question(pregunta, respuesta, categoryColors));
                    }


                }
            }
            return questionList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionList;
    }

    /**
     * Reads and returns all questions of a specified category from the file.
     *
     * @param file           The JSON file containing the questions.
     * @param targetCategory The category to filter by.
     * @return A list of questions matching the specified category.
     */
    public static List<Question> readQuestionForCategory(File file, CategoryColors targetCategory) {
        List<Question> questionList = new ArrayList<>();

        try {
            if (!file.exists()) {
                System.out.println("Archivo no encontrado. Creando archivo...");
                try (Writer writer = new FileWriter(file)) {
                    writer.write("{}");
                }
            }

            try (Reader reader = new FileReader(file)) {
                Type type = new TypeToken<Map<String, List<Map<String, String>>>>() {}.getType();
                Map<String, List<Map<String, String>>> data = gson.fromJson(reader, type);

                List<Map<String, String>> rawQuestions = data.get(targetCategory.getCategory());

                if (rawQuestions != null) {
                    for (CategoryColors cat : colors) {
                        if (cat.getCategory().equals(targetCategory.getCategory())) {
                            for (Map<String, String> q : rawQuestions) {
                                String pregunta = q.get("pregunta");
                                String respuesta = q.get("respuesta");
                                questionList.add(new Question(pregunta, respuesta, cat));
                            }
                            break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionList;
    }

    /**
     * Reads and returns all pending questions for approval excluding those submitted by the given user.
     *
     * @param file The file containing questions to approve.
     * @param user The current user.
     * @return A list of questions to approve.
     */
    public static List<QuestionApprove> readQuestionsToApprove(File file , User user) {
        List<QuestionApprove> questionList = new ArrayList<>();

        try {
            if (!file.exists()) {
                System.out.println("Archivo no encontrado. Creando archivo...");
                try (Writer writer = new FileWriter(file)) {
                    writer.write("{}");
                }
            }

            try (Reader reader = new FileReader(file)) {
                Type type = new TypeToken<Map<String, List<Map<String, String>>>>() {}.getType();
                Map<String, List<Map<String, String>>> data = gson.fromJson(reader, type);

                for (Map.Entry<String, List<Map<String, String>>> entry : data.entrySet()) {
                    String category = entry.getKey();
                    CategoryColors categoryColors = null;
                    for (CategoryColors cat: colors){

                        if (category.trim().equalsIgnoreCase(cat.getCategory().trim())){
                            categoryColors = cat;
                            break;
                        }
                    }

                    List<Map<String, String>> rawQuestions = entry.getValue();


                    for (Map<String, String> q : rawQuestions) {
                        String user1 = q.get("usuario");
                        if (user.getMail().equals(user1)){
                            continue;
                        };
                        String question = q.get("pregunta");
                        String answer = q.get("respuesta");
                        questionList.add(new QuestionApprove(question, answer, categoryColors,user1));

                    }


                }

            }
            return questionList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionList;
    }

    /**
     * Reads and returns all questions pending approval, regardless of user.
     *
     * @param file The file containing questions to approve.
     * @return A list of all questions pending approval.
     */
    public static List<QuestionApprove> readQuestionsToApproveAll(File file) {
        List<QuestionApprove> questionList = new ArrayList<>();

        try {
            if (!file.exists()) {
                System.out.println("Archivo no encontrado. Creando archivo...");
                try (Writer writer = new FileWriter(file)) {
                    writer.write("{}");
                }
            }

            try (Reader reader = new FileReader(file)) {
                Type type = new TypeToken<Map<String, List<Map<String, String>>>>() {}.getType();
                Map<String, List<Map<String, String>>> data = gson.fromJson(reader, type);

                for (Map.Entry<String, List<Map<String, String>>> entry : data.entrySet()) {
                    String category = entry.getKey();
                    CategoryColors categoryColors = null;
                    for (CategoryColors cat: colors){

                        if (category.trim().equalsIgnoreCase(cat.getCategory().trim())){
                            categoryColors = cat;
                            break;
                        }
                    }

                    List<Map<String, String>> rawQuestions = entry.getValue();


                    for (Map<String, String> q : rawQuestions) {
                        String user1 = q.get("usuario");
                        String question = q.get("pregunta");
                        String answer = q.get("respuesta");
                        questionList.add(new QuestionApprove(question, answer, categoryColors,user1));

                    }


                }

            }
            return questionList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionList;
    }

    /**
     * Prints all approved questions to the console.
     *
     * @param questions The list of approved questions.
     */
    public static void printQuestions(List<Question> questions) {
            int i= 1;
            for (Question q : questions) {

                System.out.println(i+") " +q);
                i++;
            }

    }

    /**
     * Prints all questions pending approval to the console.
     *
     * @param questions The list of questions to approve.
     */
    public static void printQuestionsApprove(List<QuestionApprove> questions) {
        int i= 1;
        for (QuestionApprove q : questions) {
            System.out.println(i + ") "+ q);
            i++;
        }

    }

    /**
     * Prints all available category colors to the console.
     */
    public static void printColors(){
        for (CategoryColors c: colors){
            System.out.println(c);
        }

    }

    /**
     * Reads and returns all rejected questions from the specified file.
     *
     * @param file The file containing rejected questions.
     * @return A list of rejected questions.
     */
    public static List<QuestionReject> readRejectedQuestions(File file) {
        List<QuestionReject> rechazadas = new ArrayList<>();
        try {
            if (!file.exists()) {
                // Crear archivo si no existe
                try (Writer writer = new FileWriter(file)) {
                    writer.write("[]");
                }
            }

            try (Reader reader = new FileReader(file)) {
                Type type = new TypeToken<List<QuestionReject>>() {}.getType();
                rechazadas = gson.fromJson(reader, type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rechazadas;
    }
    /**
     * Overwrites the file with a new list of rejected questions.
     *
     * @param file The file to update.
     * @param list The list of rejected questions.
     */
    public static void updateFileRejected(File file, List<QuestionReject> list) {
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            System.err.println("Error al actualizar archivo de rechazadas: " + e.getMessage());
        }
    }

}


