package Persistence;

import java.lang.reflect.Type;

/**
 * A generic interface for managing file operations for any data type.
 *
 * @param <T> the type of object to read/write
 */
public interface FileManager<T> {
    /**
     * Writes an object to the specified file.
     *
     * @param filePath the path to the file
     * @param object the object to write
     */
    void writeToFile(String filePath, T object);

    /**
     * Reads an object from the specified file.
     *
     * @param filePath the path to the file
     * @param type the type of the object to be returned
     * @return the deserialized object
     */
    T readFromFile(String filePath, Type type);
}
