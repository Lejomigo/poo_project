package Configuration;

import javax.crypto.SecretKey;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * The {@code Register} class manages user registration and login functionalities.
 * It handles the storage and retrieval of user data, utilizing encryption for security.
 */
public class Register{

    /**
     * A static {@link List} to hold {@link User} objects, representing all registered users.
     */
    public static List <User> users = new ArrayList<>();
    /**
     * A static {@link SecretKey} used for encrypting and decrypting user data.
     * The key is generated from the string "miclave123".
     */
    public static SecretKey clave =Encrypt.generateKey("miclave123");
    /**
     * A static {@link File} object representing the file where encrypted user data is stored.
     * The file is named "users".
     */
    public static File fileUsers = new File("users");


    /**
     * Handles the user registration process.
     * This method decrypts existing user data from {@code fileUsers},
     * prompts the user for a new email and password, verifies them,
     * creates a new {@link User} object, and then encrypts and saves
     * the updated user list back to the file.
     *
     * @throws Exception If an error occurs during file operations or encryption/decryption.
     */
    public static void register() throws Exception {
        users = List.of(Encrypt.decryptUsersFromFile(fileUsers, clave));

        User user = new User(VerifyInfo.verifyEmail(users),VerifyInfo.verifyPassword(), users.size());
        Encrypt.addUserToEncryptedFile(user, fileUsers, clave);
        System.out.println("Usuario registrado con exito. ");

    }

    /**
     * Handles the user login process.
     * This method decrypts existing user data, prompts the user for their email,
     * checks if the user is registered, and if so, prompts for and verifies the password.
     *
     * @return The {@link User} object if login is successful, or {@code null} if the password is incorrect.
     * If the user is not registered, it recursively calls itself to prompt again.
     * @throws Exception If an error occurs during file operations or encryption/decryption.
     */
    public static User login() throws Exception {
        users = List.of(Encrypt.decryptUsersFromFile(fileUsers, clave));
        User user=null;
        String tryUser = Easymeth.getString("Ingrese su correo: ");
        if(VerifyInfo.checkLog(users, tryUser)){
            for (User u : users){
                if (u.getMail().equals(tryUser)){
                    String tryPassword = Easymeth.getString("Ingrese contrasena: ");
                    if (!u.getPassword().equals(tryPassword)) {
                        System.out.println("Contrasena incorrecta.");
                        return null;
                    }
                    else {
                        System.out.println("Sesion iniciada con exito. ");
                        user = u;
                        return user;
                    }
                }else{
                    user = null;}
            }
        }else{
            System.out.println("Usuario no registrado. ");
            return  null;
        }
        return user;
    }

    /**
     * The main method for testing the registration functionality.
     * It calls the {@link #register()} method to initiate a user registration.
     *
     * @param args Command line arguments (not used in this application).
     * @throws Exception If an error occurs during the registration process.
     */

}