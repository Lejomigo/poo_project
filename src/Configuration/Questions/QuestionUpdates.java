package Configuration.Questions;
import Configuration.User;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;


public class QuestionUpdates {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static final List<CategoryColors> colors = createCategoryColors();

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

    public static void addQuestionToFile(File file, Question question) {
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

    public static void deleteQuestion(List<Question>questions, Question questionDelete){
        for (Question q: questions){
            if (q.equals(questionDelete)){
                questions.remove(questionDelete);
                return;
            }

        }

    }

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

    public static void printQuestions(List<Question> questions) {
            int i= 1;
            for (Question q : questions) {

                System.out.println(i+") " +q);
                i++;
            }

    }

    public static void printQuestionsApprove(List<QuestionApprove> questions) {
        int i= 1;
        for (QuestionApprove q : questions) {
            System.out.println(i + ") "+ q);
            i++;
        }

    }

    public static void printColors(){
        for (CategoryColors c: colors){
            System.out.println(c);
        }

    }


}
