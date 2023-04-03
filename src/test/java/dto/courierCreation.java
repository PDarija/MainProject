package dto;

public class courierCreation {


        int id;
        String login;
        String password;
        String name;

    public courierCreation(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }
}
