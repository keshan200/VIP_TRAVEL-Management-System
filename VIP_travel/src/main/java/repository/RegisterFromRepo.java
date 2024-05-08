package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterFromRepo {

    public static boolean addEmployee (String UserID, String  Pass, String email, String empID, String name, String NIC, String Adress) throws SQLException {

        Connection connection = DBconnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {

            String sql = "INSERT INTO user (userID, password, email) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try  {

                preparedStatement.setString(1, UserID);
                preparedStatement.setString(2, Pass);
                preparedStatement.setString(3, email);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            String Sql = "INSERT INTO employee  VALUES (?, ?, ?, ?,?)";
            PreparedStatement p = connection.prepareStatement(Sql);
            try  {
                p.setString(1, empID);
                p.setString(2, NIC);
                p.setString(3, name);
                p.setString(4,Adress);
                p.setString(5,UserID);

                p.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);

        }
        return false;
    }

}

