package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.DashBoardModle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMrepo {

    public int getCustomerCount(DashBoardModle dashBoardModle) throws SQLException {

        String sql = "SELECT COUNT(*) AS customer_count FROM customer";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("customer_count");
        }
        return 0;

    }


    public int getEmployeeCount(DashBoardModle dashBoardModle) throws SQLException {
        String sql = "select count (*) as employee_count from employee";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("employee_count");
        }
        return 0;
    }
}
