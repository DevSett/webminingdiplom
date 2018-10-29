package automining.model;

public class User {

    private int id;
    private String login;
    private String password;
    private String keyUser;
    private String keyTelegram;
    private String email;
    private int right;
    private String account;

    public User(int id, String login, String password, String keyUser, String keyTelegram, String email, int right, String account) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.keyUser = keyUser;
        this.keyTelegram = keyTelegram;
        this.email = email;
        this.right = right;
        this.account = account;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(String keyUser) {
        this.keyUser = keyUser;
    }

    public String getKeyTelegram() {
        return keyTelegram;
    }

    public void setKeyTelegram(String keyTelegram) {
        this.keyTelegram = keyTelegram;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", keyUser='" + keyUser + '\'' +
                ", keyTelegram='" + keyTelegram + '\'' +
                ", email='" + email + '\'' +
                ", right=" + right +
                ", account='" + account + '\'' +
                '}';
    }
}
