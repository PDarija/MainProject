package db;

import helpers.SetupFunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBmanager {


//    private final String url = "jdbc:postgresql://rc1b-3vawdqzm9kwkzvqn.mdb.yandexcloud.net:6432/db1";
//    private final String user = "userqa07";
//    private final String password = "y5AYmjGudCEfs";

    public Connection connect() {

        SetupFunctions setupFunctions = new SetupFunctions();
        String host = setupFunctions.getDbhost();
        String port = setupFunctions.getDbport();
        String dbname = setupFunctions.getDbname();
        String dbusername = setupFunctions.getDbusername();
        String pwd = setupFunctions.getDbpassword();

        String connectionString = host + ":" + port + "/" + dbname;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't load class for db driver");
        }

//инициализация гашего соединения
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionString, dbname, pwd);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println("Can't establish connection ...");
            System.out.println(e.getMessage());
        }

//        Statement s = connection.createStatement();

        return connection;
    }

    public void close(Connection connection) {

        if (connection != null) {

            try {
                connection.close();
                System.out.println("Closed successfully");
            } catch (SQLException e) {
                System.out.println("error while closing connection:" + e);
            }

        } else {
            System.out.println("Connection does not exist");
        }

    }

}
