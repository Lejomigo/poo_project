package Configuration;
import com.google.gson.Gson;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Encrypt {

    private static final Gson gson = new Gson();

    public static SecretKey generateKey(String password) {
        byte[] keyBytes = new byte[16];
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(passwordBytes, 0, keyBytes, 0, Math.min(passwordBytes.length, keyBytes.length));
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static void encryptUsersToFile(List<User> users, File outputFile, SecretKey key) throws Exception {
        String jsonContent = gson.toJson(users);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        try (FileOutputStream fos = new FileOutputStream(outputFile);
             CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {
            cos.write(jsonContent.getBytes(StandardCharsets.UTF_8));
        }
    }

    public static User[] decryptUsersFromFile(File inputFile, SecretKey key) throws Exception {
        if (!inputFile.exists()) return new User[0];

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (FileInputStream fis = new FileInputStream(inputFile);
             CipherInputStream cis = new CipherInputStream(fis, cipher)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = cis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
        }

        String json = baos.toString(StandardCharsets.UTF_8.name());
        return gson.fromJson(json, User[].class);
    }

    // ✅ Método nuevo: agrega un solo usuario al archivo cifrado
    public static void addUserToEncryptedFile(User newUser, File file, SecretKey key) throws Exception {
        List<User> usuariosActuales = new ArrayList<>(Arrays.asList(decryptUsersFromFile(file, key)));
        usuariosActuales.add(newUser);
        encryptUsersToFile(usuariosActuales, file, key);
    }
}