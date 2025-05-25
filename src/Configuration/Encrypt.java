package Configuration;
import com.google.gson.Gson;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The {@code Encrypt} class provides utility methods for encrypting and decrypting
 * a list of {@link User} objects to and from a file. It uses AES encryption
 * and the Gson library for JSON serialization/deserialization.
 */
public class Encrypt {

    /**
     * A static {@link Gson} instance used for converting Java objects to JSON
     * and vice-versa.
     */
    private static final Gson gson = new Gson();

    /**
     * Generates an AES {@link SecretKey} from a given password string.
     * The password string is truncated or padded to 16 bytes (128 bits)
     * to fit the AES key size.
     *
     * @param password The password string to use for key generation.
     * @return A {@link SecretKey} suitable for AES encryption.
     */
    public static SecretKey generateKey(String password) {
        byte[] keyBytes = new byte[16];
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(passwordBytes, 0, keyBytes, 0, Math.min(passwordBytes.length, keyBytes.length));
        return new SecretKeySpec(keyBytes, "AES");
    }

    /**
     * Encrypts a list of {@link User} objects and writes them to the specified file.
     * The list of users is first converted to a JSON string, which is then encrypted
     * using the provided {@link SecretKey} and AES algorithm.
     *
     * @param users The {@link List} of {@link User} objects to encrypt.
     * @param outputFile The {@link File} to which the encrypted data will be written.
     * @param key The {@link SecretKey} to use for encryption.
     * @throws Exception If an error occurs during encryption or file writing.
     */
    public static void encryptUsersToFile(List<User> users, File outputFile, SecretKey key) throws Exception {
        String jsonContent = gson.toJson(users);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        try (FileOutputStream fos = new FileOutputStream(outputFile);
             CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {
            cos.write(jsonContent.getBytes(StandardCharsets.UTF_8));
        }
    }

    /**
     * Decrypts user data from the specified file and converts it into an array of {@link User} objects.
     * If the input file does not exist, an empty array of users is returned.
     * The data is decrypted using the provided {@link SecretKey} and AES algorithm,
     * and then parsed from a JSON string.
     *
     * @param inputFile The {@link File} from which the encrypted data will be read.
     * @param key The {@link SecretKey} to use for decryption.
     * @return An array of {@link User} objects decrypted from the file.
     * @throws Exception If an error occurs during decryption or file reading.
     */
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

    /**
     * Adds a single new {@link User} to an existing encrypted file.
     * This method first decrypts all current users from the file, adds the new user
     * to the list, and then re-encrypts and writes the entire updated list back to the file.
     *
     * @param newUser The new {@link User} object to add to the file.
     * @param file The {@link File} where the encrypted user data is stored.
     * @param key The {@link SecretKey} to use for encryption and decryption.
     * @throws Exception If an error occurs during file operations or encryption/decryption.
     */
    public static void addUserToEncryptedFile(User newUser, File file, SecretKey key) throws Exception {
        List<User> actualUsers = new ArrayList<>(Arrays.asList(decryptUsersFromFile(file, key)));
        actualUsers.add(newUser);
        encryptUsersToFile(actualUsers, file, key);
    }
}
