package Utilies;

import Configuration.Questions.QuestionsHandler;
import Configuration.Register;
import Configuration.User;
import static Configuration.Questions.QuestionsHandler.menuQuestion;
import static Game.StartGame.Play.playGame;

import Game.StartGame.Play;
public class Menus {

    public static void menu() throws Exception {
        int time;
        User userIn = null;

        while (true) {
            if (userIn == null) {
                System.out.println();
                System.out.println("Inicia sesión o regístrate para jugar: ");
                System.out.println("1. Registrar nuevo usuario.");
                System.out.println("2. Iniciar sesión.");
                System.out.println("3. Salir.");
                int menuOption = Easymeth.getInt("Ingrese opción: ");
                switch (menuOption) {
                    case 1 -> Register.register();
                    case 2 -> userIn = Register.login();
                    case 3 -> {
                        System.out.println("¡Hasta luego!");
                        return;
                    }
                    default -> System.out.println("Opción inválida.");
                }
            } else {
                System.out.println("Bienvenido, estás usando: " + userIn.getMail() + ".");
                int configuredTime = 0;
                System.out.println("¿Desea jugar con tiempo?");
                System.out.println("1. Sí");
                System.out.println("2. No");
                int timeChoice = Easymeth.getInt("Ingrese opción: ");
                if (timeChoice == 1) {
                    do {
                        configuredTime = Easymeth.getInt("Ingrese el tiempo en segundos (mínimo 5s): ");
                        if (configuredTime < 5) {
                            System.out.println("El tiempo debe ser al menos 5 segundos.");
                        }
                    } while (configuredTime < 5);
                }
                System.out.println("1. Preguntas del juego.");
                System.out.println("2. Jugar.");
                System.out.println("3. Cerrar sesión.");
                System.out.println("4. Salir del programa.");
                int menuOption2 = Easymeth.getInt("Ingrese opción: ");
                switch (menuOption2) {
                    case 1 -> menuQuestion(userIn);
                    case 2 -> {
                        playGame(userIn, configuredTime);
                    }
                    case 3 -> userIn = null;
                    case 4 -> {
                        System.out.println("¡Gracias por jugar!");
                        return;
                    }
                    default -> System.out.println("Opción inválida.");
                }
            }
        }
    }

}
