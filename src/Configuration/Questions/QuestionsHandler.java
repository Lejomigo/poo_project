package Configuration.Questions;

import Configuration.Register;
import Configuration.User;
import Utilies.Easymeth;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static Configuration.Questions.QuestionUpdates.*;
import Configuration.Questions.QuestionReject;

public class QuestionsHandler {
    public static final File fileQuestions = new File("preguntasJuegoTrivia.json");
    public static final File fileToApprove = new File("preguntasPorAprobar");
    public static final File fileRejected = new File("preguntasRechazadas.json");


    public static void createQuestion(User userAsk){
        String newQuest = Easymeth.getValidInput("Crea la nueva pregunta (sin signo de interrogacion): ");
        newQuest += "?";
        String newAnsw = Easymeth.getValidInput("Escribe la respuesta: ");
        QuestionUpdates.printColors();
        int newCat = Easymeth.getInt("Introduce el indice de la categoria a la que pertenece tu pregunta. ");
        while (newCat>5 || newCat<0){
            QuestionUpdates.printColors();
            newCat = Easymeth.getInt("Introduce un indice valido.");
        }

        QuestionUpdates.addQuestionToApprove(fileToApprove,new QuestionApprove(newQuest,newAnsw,colors.get(newCat), userAsk.getMail()));
        System.out.println("Pregunta creada. ");
        System.out.println("");
        String option = Easymeth.getString("Quieres crear otra (s/n) ? ");
        if (option.equals("s")){
            createQuestion(userAsk);
        }
    }

    public static void approveQuestion(User userApprove){
        if (fileToApprove.length() == 0) {
            System.out.println("El archivo de preguntas por aprobar está vacío.");
            return;
        }
        List<QuestionApprove> listToApprove = readQuestionsToApprove(fileToApprove, userApprove);
        List<QuestionApprove> listAll = readQuestionsToApproveAll(fileToApprove);
        if(listToApprove.isEmpty()){
            System.out.println("No hay preguntas por aprobar. ");
            return;
        }
        QuestionUpdates.printQuestionsApprove(listToApprove);
        System.out.println("");
        int num = Easymeth.getInt("Indice (del 1 al " + listToApprove.size() + " ).");
        while(num >listToApprove.size() || num<1){
            num = Easymeth.getInt("Introduce un indice valido.");
        }
        Question approved = (Question) listToApprove.get(num -1);
        for(QuestionApprove q: listAll){
            if (q.getQuestion().equals(approved.getQuestion())){
                listAll.remove(q);
                break;
            }
        }

        updateFileApprove(fileToApprove,listAll);
        QuestionUpdates.addQuestionToFile(fileQuestions,approved);
        System.out.println("Pregunta aprobada. ");
        System.out.println("");
        String option = Easymeth.getString("Quieres aprobar otra (s/n) ? ");
        if (option.equals("s")){
            approveQuestion(userApprove);
        }

    }
    public static void rejectQuestion(User userReject) {
        if (fileToApprove.length() == 0) {
            System.out.println("El archivo de preguntas por aprobar está vacío.");
            return;
        }
        List<QuestionApprove> listToApprove = readQuestionsToApprove(fileToApprove, userReject);
        List<QuestionApprove> listAll = readQuestionsToApproveAll(fileToApprove);
        if (listToApprove.isEmpty()) {
            System.out.println("No hay preguntas por rechazar.");
            return;
        }
        printQuestionsApprove(listToApprove);
        System.out.println("");
        int num = Easymeth.getInt("Índice (del 1 al " + listToApprove.size() + " ):");
        while (num > listToApprove.size() || num < 1) {
            num = Easymeth.getInt("Introduce un índice válido.");
        }
        QuestionApprove toReject = listToApprove.get(num - 1);
        String motivo = Easymeth.getString("¿Por qué estás rechazando esta pregunta?");
        QuestionReject rejected = new QuestionReject(
            toReject.getQuestion(),
            toReject.getAnswer(),
            toReject.getCategory(),
            toReject.getUserAsk(),
            userReject.getMail(),
            motivo
        );

        List<QuestionReject> rechazadas = readRejectedQuestions(fileRejected);
        rechazadas.add(rejected);
        updateFileRejected(fileRejected, rechazadas);

        listAll.removeIf(q -> q.getQuestion().equals(toReject.getQuestion()));
        updateFileApprove(fileToApprove, listAll);

        System.out.println("Pregunta rechazada y registrada.");
        String option = Easymeth.getString("¿Quieres rechazar otra (s/n)? ");
        if (option.equalsIgnoreCase("s")) {
            rejectQuestion(userReject);
        }
    }
    public static void removeQuestion(){
        List<Question> questionList = readQuestions(fileQuestions);
        printQuestions(questionList);
        int num = Easymeth.getInt("Indice (del 1 al " + questionList.size() + " ).");
        while(num >questionList.size() || num<1){
            num = Easymeth.getInt("Introduce un indice valido.");
        }
        Question delete = questionList.get(num-1);
        deleteQuestion(questionList, delete);
        updateFileQuestion(fileQuestions,questionList);
        System.out.println("Eliminado Correctamente. ");
        System.out.println("");
        String option = Easymeth.getString("Quieres eliminar otra (s/n) ? ");
        if (option.equals("s")){
            removeQuestion();
        }

    }

    public static void modifyQuestion(){
        List<Question> questionList = readQuestions(fileQuestions);
        printQuestions(questionList);
        int num = Easymeth.getInt("Indice (del 1 al " + questionList.size() + " ).");
        while(num >questionList.size() || num<1){
            num = Easymeth.getInt("Introduce un indice valido.");
        }
        Question modify = questionList.get(num-1);
        deleteQuestion(questionList, modify);
        String newQuest = Easymeth.getValidInput("Escribe la nueva pregunta: ");
        String newAnsw = Easymeth.getValidInput("Escribe la nueva respuesta: ");
        Question newObject = new Question(newQuest,newAnsw,modify.getCategory());
        questionList.add(newObject);
        updateFileQuestion(fileQuestions,questionList);
        System.out.println("Modificado Correctamente.");
        System.out.println("");
        String option = Easymeth.getString("Quieres modificar otra (s/n) ? ");
        if (option.equals("s")){
           modifyQuestion();
        }
    }

    public static void menuQuestion(User user) throws Exception {
        int menuOption;

        do {
            System.out.println();
            System.out.println("1. Crear Pregunta");
            System.out.println("2. Eliminar Pregunta");
            System.out.println("3. Modificar Pregunta");
            System.out.println("4. Aprobar pregunta");
            System.out.println("5. Rechazar pregunta");
            System.out.println("6. Volver");
            menuOption = Easymeth.getInt("Ingrese opción: ");

            switch (menuOption) {
                case 1:
                    createQuestion(user);
                    break;
                case 2:
                    removeQuestion();
                    break;
                case 3:
                    modifyQuestion();
                    break;
                case 4:
                    approveQuestion(user);
                    break;
                case 5:
                    rejectQuestion(user);
                    break;
                case 6:
                    System.out.println("Volviendo al menú anterior...");
                    return;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (menuOption != 5);
    }

}
