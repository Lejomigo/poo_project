package Persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * JsonFileManager is a generic implementation of FileManager for JSON files.
 * It uses Gson for serialization and deserialization.
 *
 * @param <T> the type of objects to be managed
 */
public class JsonFileManager<T> implements FileManager<T> {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Writes an object to a JSON file.
     *
     * @param filePath The path to the file where the object should be written.
     * @param object   The object to be serialized and written.
     */
    public void writeToFile(String filePath, T object) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(object, writer);
            System.out.println("Successfully wrote to JSON file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing JSON to file '" + filePath + "': " + e.getMessage());
        }
    }

    /**
     * Reads an object from a JSON file.
     *
     * @param filePath The path to the file to be read.
     * @param type     The type of the object to be returned.
     * @return The object read from the file, or null if reading fails.
     */
    public T readFromFile(String filePath, Type type) {
        try (FileReader reader = new FileReader(filePath)) {
            T obj = gson.fromJson(reader, type);
            System.out.println("Successfully read from JSON file: " + filePath);
            return obj;
        } catch (IOException e) {
            System.err.println("Error reading JSON from file '" + filePath + "': " + e.getMessage());
            return null;
        }
    }
}
