package Configuration;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyInfo {
    private static final String EMAIL_REGEX = "^[a-z0-9+_.-]+@[a-z0-9.-]+\\.[a-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    //method to verify email
    public static String verifyEmail(List<User> users){
        System.out.println("\n");
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

    public static boolean checkLog(List<User>users,String targetMail){
        for (User user : users){
            if (user.getMail().equals(targetMail)) {
                return true;
            }
        }
        return false;
    }
    //method to verify password
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
