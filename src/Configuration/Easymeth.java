package Configuration;
import java.util.Scanner;

/**
 * The {@code Easymeth} class provides utility methods for easy input handling
 * from the console, such as reading integers and strings.
 */
public class Easymeth {
    /**
     * A static {@link Scanner} object used to read input from the console.
     */
    static Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user with a message and reads an integer input from the console.
     * This method includes input validation to ensure that the entered value is an integer.
     * If the input is not a valid integer, it will display an error message and
     * repeatedly prompt the user until a valid integer is provided.
     *
     * @param message The message to display to the user as a prompt for input.
     * @return The integer value entered by the user.
     */
    public static int getInt(String message){
        int integer;
        System.out.println(message);
        while (true) {
            if (sc.hasNextInt()) {
                // if the correct datatype
                integer = sc.nextInt();
                sc.nextLine(); // Consume the newline character left by nextInt()
                return integer;
            } else {
                //if it's not the correct datatype.
                System.out.println("Tipo de dato no válido.\n" + message);
                sc.next(); // Consume the invalid token
                sc.nextLine(); // Consume the rest of the line to not bug
            }
        }

    }

    /**
     * Prompts the user with a message and reads a line of string input from the console.
     * The input string is trimmed of leading and trailing whitespace.
     *
     * @param message The message to display to the user as a prompt for input.
     * @return The trimmed string value entered by the user.
     */
    public static String getString(String message) {
        System.out.println(message);
        return sc.nextLine().trim();
    }
}