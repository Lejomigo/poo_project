package Configuration;

public class User {
    private int id;
    public String mail;
    public String password;

    public User(String mail, String password, int id) {
        setMail(mail);
        setPassword(password);
        setId(id);
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Usuario{" + "correo='" + mail + "', password='" + password + "'}";
    }
}
