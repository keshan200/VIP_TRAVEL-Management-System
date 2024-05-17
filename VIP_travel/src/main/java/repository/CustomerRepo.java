package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.CustomerModle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {


    public  static  boolean Add(CustomerModle cusModle) throws SQLException {

        String sql = "insert into customer values(?,?,?,?,?)";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, cusModle.getCustomerID());
        pstm.setObject(2,cusModle.getNIC());
        pstm.setObject(3,cusModle.getName());
        pstm.setObject(4,cusModle.getTelNO());
        pstm.setObject(5,cusModle.getAddress());

        return pstm.executeUpdate()>0;

    }

    public  static  boolean Delete(String cusID) throws SQLException {

        String sql = "delete from customer where customerID =?";


        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,cusID);

        return pstm.executeUpdate() > 0;

    }



    public static boolean Update(CustomerModle cus) throws SQLException {

        String sql = "UPDATE customer SET name = ?, telephoneNO = ?, address = ? WHERE NIC = ?";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,cus.getName());
        pstm.setObject(2, cus.getTelNO());
        pstm.setObject(3, cus.getAddress());
        pstm.setObject(4, cus.getNIC());

        return pstm.executeUpdate() > 0;
    }



    public  static  CustomerModle Search(String NIC) throws SQLException {

        String  sql = "select  * from customer where NIC =?";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,NIC);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {

            String custID = resultSet.getString(1);
            String Nic = resultSet.getString(2);
            String name = resultSet.getString(3);
            int telNo = Integer.parseInt(resultSet.getString(4));
            String adrs = resultSet.getString(5);

            CustomerModle customerModle = new CustomerModle(custID,Nic,name,telNo,adrs);

           return  customerModle;

        }

  return  null;
    }



    public static List<CustomerModle> getAll() throws SQLException {

        String sql = "select * from customer";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

       List<CustomerModle> customer = new ArrayList<>();

       while (resultSet.next()){

           String cutomerID = resultSet.getString(1);
           String NIC = resultSet.getString(2);
           String name = resultSet.getString(3);
           int telno = Integer.parseInt(resultSet.getString(4));
           String addrs = resultSet.getString(5);


           CustomerModle customerModle = new CustomerModle(cutomerID,NIC,name,telno,addrs);

           customer.add(customerModle);
       }

return  customer;
    }

    public static String getCurrentId() throws SQLException {

        String Sql = "SELECT customerID FROM customer ORDER BY customerID DESC LIMIT 1";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement ptsm = connection.prepareStatement(Sql);

        ResultSet resultSet = ptsm.executeQuery();

        if (resultSet.next()) {
            String vehicleID = resultSet.getString(1);
            return vehicleID;
        }
        return  null;
    }
}
