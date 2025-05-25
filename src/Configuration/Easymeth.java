package Configuration;
import java.util.Scanner;

public class Easymeth {
    static Scanner sc = new Scanner(System.in);
    static int getInt(String message){
        int integer;
        System.out.println(message);
        while (true) {
            if (sc.hasNextInt()) {
                // if the correct datatype
                integer = sc.nextInt();
                return integer;
            } else {
                //if it's not the correct datatype.
                System.out.println("Tipo de dato no válido.\n" + message);
                sc.next();
                sc.nextLine(); // Consume the past line to not bug
            }
        }

    }
    static String getString(String message) {
        System.out.println(message);
        return sc.nextLine().trim();
    }
}
