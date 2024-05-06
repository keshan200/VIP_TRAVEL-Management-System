package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.InsuranceModle;
import model.VehicleModle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsuranceRepo {

    public static  boolean Add(InsuranceModle ins) throws SQLException {

        String sql = "insert into insurance(insuranceID, companyName, type, endDate, regNO)values(?,?,?,?,?)";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,ins.getInsuranceID());
        pstm.setObject(2,ins.getCompanyName());
        pstm.setObject(3,ins.getType());
        pstm.setObject(4,ins.getEndDate());
        pstm.setObject(5,ins.getRegNO());
       // pstm.setObject(6,ins.getVehicleID());

        return pstm.executeUpdate() > 0;
    }



    public static boolean Update(InsuranceModle ins) throws SQLException {

        String sql = "UPDATE insurance SET companyName = ?, type = ?, endDate = ? WHERE insuranceID = ? ";


        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setObject(1,ins.getCompanyName());
        pstm.setObject(2,ins.getType());
        pstm.setObject(3,ins.getEndDate());
        pstm.setObject(4,ins.getInsuranceID());


        return pstm.executeUpdate() > 0;
    }


    public static InsuranceModle Search(String RegNo) throws SQLException {

        String searchSql = "Select * from insurance where regNo = ?";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(searchSql);
        pstm.setObject(1, RegNo);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {

          String insuranceID =  resultSet.getString(1);
            String companyName = resultSet.getString(2);
            String type = resultSet.getString(3);
            String date = resultSet.getString(4);
            String regNo  = resultSet.getString(5);
            String vehicleID = resultSet.getString(6);


            InsuranceModle insuranceModle = new InsuranceModle(insuranceID,companyName,type,date,regNo,vehicleID);
            return  insuranceModle;

        }
        return  null;
    }



    public static String getCurrentId() throws SQLException {

        String Sql = "SELECT insuranceID FROM insurance ORDER BY insuranceID DESC LIMIT 1";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement ptsm = connection.prepareStatement(Sql);

        ResultSet resultSet = ptsm.executeQuery();

        if (resultSet.next()) {
            String insuranceID = resultSet.getString(1);
            return insuranceID;
        }
        return  null;
    }

}
