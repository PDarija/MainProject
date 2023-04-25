package helpers;

import com.google.gson.Gson;
import dto.Credentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class SetupFunctions {
    //api
    String baseUrl;
    String username;
    String password;
//db

    String dbhost;
    String dbport;
    String dbname;
    String dbusername;
    String dbpassword;

    public String getDbhost() {
        return dbhost;
    }

    public String getDbport() {
        return dbport;
    }

    public String getDbname() {
        return dbname;
    }

    public String getDbusername() {
        return dbusername;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    public SetupFunctions() {
        try (InputStream input = new FileInputStream("settings.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            //api
            baseUrl = properties.getProperty("baseUrl");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            //
            dbhost  = properties.getProperty("dbhost");
            dbport = properties.getProperty("dbport");
            dbname = properties.getProperty("dbname");
            dbusername = properties.getProperty("dbusername");
            dbpassword = properties.getProperty("dbpassword");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createUser() {
        Credentials user = new Credentials(username, password);
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {

        return given().
                header("Content-type", "application/json").
                log().
                all().
                body(createUser()).
                when().
                post(  baseUrl + "/login/student").
                then().
                log().
                all().
                extract().
                response().
                asString();
    }
}
