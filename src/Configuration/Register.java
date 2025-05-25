package Configuration;

import javax.crypto.SecretKey;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register{

    public static List <User> users = new ArrayList<>();
    public static SecretKey clave =Encrypt.generateKey("miclave123");
    public static File fileUsers = new File("users");


    public static void register() throws Exception {
        users = List.of(Encrypt.decryptUsersFromFile(fileUsers, clave));

        User user = new User(VerifyInfo.verifyEmail(users),VerifyInfo.verifyPassword(), users.size());
        Encrypt.addUserToEncryptedFile(user, fileUsers, clave);
        System.out.println("Usuario registrado con exito. ");

    }

    public static User login() throws Exception {
        users = List.of(Encrypt.decryptUsersFromFile(fileUsers, clave));
        User user = null;
        int position = 0;
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
            user = login();
            return user ;
        }
        return user;
    }

    public static void main(String[] args) throws Exception {
        login();
    }

    }
