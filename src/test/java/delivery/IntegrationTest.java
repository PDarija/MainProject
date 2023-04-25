package delivery;

import db.DBmanager;
import org.junit.jupiter.api.Test;


import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class IntegrationTest {

    @Test
    public void dummy () throws SQLException{
        DBmanager dBmanager = new db.DBmanager();
        Connection connection = dBmanager.connect();

//        String sql = "select * from orders";


        dBmanager.close(connection);


    }


}
