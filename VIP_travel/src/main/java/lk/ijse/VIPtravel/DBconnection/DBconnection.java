package lk.ijse.VIPtravel.DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static DBconnection db;
    private Connection connection;

    private String url = "jdbc:mysql://localhost:3306/viptravels";
    private String user = "root";
    private String password = "Ijse@123";

    private DBconnection() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }


    public static DBconnection getInstance() throws SQLException {
        if (db == null) {
            db = new DBconnection();

        }
        return db;
    }

    public  Connection getConnection() {
        return connection;
    }
}
