import Configuration.Easymeth;
import Configuration.Register;
import Configuration.User;

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
                    System.out.println("pene");
                    userIn = Register.login();
                    System.out.println(userIn);
                    break;
                case 3:
                    return;

            }
        }while (userIn !=null){
                System.out.println("Bienvenido, esta usando: "+ userIn.getMail()+".");
                System.out.println("1.Preguntas del juego.");
                System.out.println("2.Configurar tiempo. ");
                System.out.println("3.Aprobacion de preguntas.");
                System.out.println("4.Jugar.");
                System.out.println("5.Cerrar Sesion.");
                System.out.println("6.Salir del programa.");
                int menuOption2 = Easymeth.getInt("Ingrese opcion: ");
                switch (menuOption2) {
                    case 1:
                        break;
                    case 2:

                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        userIn = null;
                        break;
                    case 6:
                        return;

                }
            }
        }while (menuOption!=3);
    }

    public static void main(String[] args) throws Exception {
        menu();
    }
}
