package Utilies;

import Configuration.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code VerifyInfo} class provides utility methods for verifying user information
 * such as email addresses and passwords. It includes validation against a regex pattern
 * for emails and checks for existing user registrations.
 */
public class VerifyInfo {
    /**
     * A regular expression string used to validate email addresses.
     * It ensures the email follows a standard format (e.g., user@domain.com).
     */
    private static final String EMAIL_REGEX = "^[a-z0-9+_.-]+@[a-z0-9.-]+\\.[a-z]{2,}$";
    /**
     * A compiled {@link Pattern} object for the {@code EMAIL_REGEX}.
     * This is used for efficient matching of email strings.
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Prompts the user to enter an email address and verifies its validity.
     * This method performs the following checks:
     * <ul>
     * <li>Checks if the email format is valid using a regular expression.</li>
     * <li>Checks if the email is already registered in the provided list of users.</li>
     * </ul>
     * If the email is invalid or already registered, it recursively prompts the user again.
     *
     * @param users A {@link List} of {@link User} objects to check against for existing registrations.
     * @return A valid and unregistered email address entered by the user.
     */
    public static String verifyEmail(List<User> users){
        //Create a variable to return
        String email = Easymeth.getString("Ingrese correo (ejemplo@gmail.com):");
        //Use the matcher to verify  the pattern
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        //Check if the email is already logged in
        if (checkLog(users, email)){
            System.out.println("Usuario ya registrado. ");
            email = verifyEmail(users);
            return email;
        }

        //Conditional that makes a loop in case email wrong.
        if (!matcher.matches()) {
            System.out.println("Correo invalido. ");
            email =  verifyEmail(users);
            return email;
        }
        // return email
        System.out.println("Correo valido. ");
        return email;
    }

    /**
     * Checks if a target email address already exists in the provided list of users.
     *
     * @param users The {@link List} of {@link User} objects to search through.
     * @param targetMail The email address to check for existence.
     * @return {@code true} if the {@code targetMail} is found in the list, {@code false} otherwise.
     */
    public static boolean checkLog(List<User>users,String targetMail){
        for (User user : users){
            if (user.getMail().equals(targetMail)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Prompts the user to enter a password and verifies its validity and consistency.
     * This method performs the following checks:
     * <ul>
     * <li>Ensures the password has a length of exactly 6 characters.</li>
     * <li>Prompts the user to repeat the password and checks if both entries match.</li>
     * </ul>
     * If the password is invalid or the repeated password does not match, it recursively prompts the user again.
     *
     * @return A valid and confirmed password string.
     */
    public static String verifyPassword(){

        String password = Easymeth.getString("Ingrese contrasena (6 caracteres de cualquier tipo): ");
        if(password.length() != 6){
            System.out.println("Error, son seis caracteres");
            password = verifyPassword();
            return password;
        }
        String repeatPassword = Easymeth.getString("Repita la contrasena: ");
        if (!password.equals(repeatPassword)){
            System.out.println("Error, no coinciden las contrasenas. ");
            password = verifyPassword();
            return password;
        }
        return password;
    }
}
