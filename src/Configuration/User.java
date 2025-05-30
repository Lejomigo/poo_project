package Configuration;

/**
 * The {@code User} class represents a user with an ID, email address, and password.
 * It provides methods to set and retrieve these properties.
 */
public class User {
    /**
     * The unique identifier for the user.
     */
    private String mail;
    /**
     * The password of the user. This field is public for direct access.
     */
    private String password;

    /**
     * Constructs a new {@code User} object with the specified email, password, and ID.
     *
     * @param mail The email address of the user.
     * @param password The password of the user.
     */
    public User(String mail, String password) {
        setMail(mail);
        setPassword(password);
    }

    /**
     * Sets the email address of the user.
     *
     * @param mail The new email address to set.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The new password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param id The new ID to set.
     */

    /**
     * Returns the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Returns the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }


    /**
     * Returns a string representation of the {@code User} object.
     * This string includes the user's email and password.
     *
     * @return A string in the format "Usuario{correo='[mail]', password='[password]'}"
     */
    public String toString() {
        return "Usuario{" + "correo='" + mail + "', password='" + password + "'}";
    }
}
