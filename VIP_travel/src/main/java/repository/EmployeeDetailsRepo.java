package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.EmpDtlsModle;
import model.RegisterModle;
import model.VehicleModle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDetailsRepo {



    public static boolean Save(EmpDtlsModle empDtlsModle) throws SQLException {

        String sql = "INSERT INTO employee (employe_NIC, name, address,TP,userID, password, email) VALUES (?, ?, ?, ?, ?,?,?)";


        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);


        pst.setObject(1,empDtlsModle.getNIC());
        pst.setObject(2,empDtlsModle.getName());
        pst.setObject(3,empDtlsModle.getAddress());
        pst.setObject(4,empDtlsModle.getTp());
        pst.setObject(5,empDtlsModle.getUserID());
        pst.setObject(6,empDtlsModle.getPassword());
        pst.setObject(7,empDtlsModle.getEmail());

          return pst.executeUpdate()>0;
    }


    public static List<EmpDtlsModle> getAll() throws SQLException {
        String sql = "SELECT * FROM employee";

        PreparedStatement pstm = DBconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<EmpDtlsModle> empList = new ArrayList<>();

        while (resultSet.next()) {
            String NIC = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            int tp = Integer.parseInt(resultSet.getString(4));
            String userID = resultSet.getString(5);
            String password = resultSet.getString(6);
            String email = resultSet.getString(7);

            EmpDtlsModle emp = new EmpDtlsModle(NIC,name,address,tp,userID,password,email);
            empList.add(emp);
        }
        return empList;
    }

    public static EmpDtlsModle Search(String NIC) throws SQLException {

        String searchSql = "Select * from employee where employe_NIC =?";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(searchSql);
        pstm.setObject(1, NIC);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {

            String NiC = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            int tp = Integer.parseInt(resultSet.getString(4));
            String userID = resultSet.getString(5);
            String password = resultSet.getString(6);
            String email = resultSet.getString(7);


            EmpDtlsModle emp = new EmpDtlsModle(NiC,name,address,tp,userID,password,email);

            return  emp;

        }
        return  null;
    }

}
