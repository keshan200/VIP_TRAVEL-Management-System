package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.EmpDtlsModle;
import model.RegisterModle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDetailsRepo {

    public static String getCurentempID() throws SQLException {

        String empIDsql = "SELECT employeeID FROM employee ORDER BY employeeID DESC LIMIT 1";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(empIDsql);

        ResultSet Rset = preparedStatement.executeQuery();
        if (Rset.next()) {
            String employeeID = Rset.getString(1);
            return employeeID;

        }
        return null;
    }


    public static boolean Save(EmpDtlsModle empDtlsModle) throws SQLException {

        String sql = "INSERT INTO employee (employeeID,  employe_NIC, name, address,userID) VALUES (?, ?, ?, ?, ?)";


        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setObject(1,empDtlsModle.getEmpID());
        pst.setObject(2,empDtlsModle.getNIC());
        pst.setObject(3,empDtlsModle.getName());
        pst.setObject(4,empDtlsModle.getAddress());
        pst.setObject(5,empDtlsModle.getUserID());

          return pst.executeUpdate()>0;
    }

}
