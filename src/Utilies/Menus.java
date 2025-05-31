package Utilies;

import Configuration.Questions.QuestionsHandler;
import Configuration.Register;
import Configuration.User;
import static Configuration.Questions.QuestionsHandler.menuQuestion;
import static Game.Cronometro.configurarCronometro;

public class Menus {

    public static void menu() throws Exception {
        int menuOption = 0;
        User userIn = null;
        do {
        while (userIn ==null) {
            System.out.println();
            System.out.println("Inicia sesion o registrate para jugar: ");
            System.out.println("1.Registrar nuevo usuario.");
            System.out.println("2.Iniciar sesion.");
            System.out.println("3.Salir.");
            menuOption = Easymeth.getInt("Ingrese opcion: ");
            switch (menuOption) {
                case 1:
                    Register.register();
                    break;
                case 2:
                    userIn = Register.login();
                    break;
                case 3:
                    return;

            }
        }while (userIn !=null){

                System.out.println("Bienvenido, esta usando: "+ userIn.getMail()+".");
                System.out.println("1.Preguntas del juego.");
                System.out.println("2.Configurar tiempo. ");
                System.out.println("3.Jugar.");
                System.out.println("4.Cerrar Sesion.");
                System.out.println("5.Salir del programa.");
                int menuOption2 = Easymeth.getInt("Ingrese opcion: ");
                switch (menuOption2) {
                    case 1:
                        menuQuestion(userIn);
                        break;
                    case 2:
                        configurarCronometro();
                        break;
                    case 3:
                        break;
                    case 4:
                        userIn = null;
                        break;
                    case 5:
                        return;

                }
            }
        }while (true);
    }

    public static void main(String[] args) throws Exception {
        menu();
    }
}
