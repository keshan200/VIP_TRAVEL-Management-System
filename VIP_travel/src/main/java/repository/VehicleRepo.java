package repository;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.VehicleModle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static javafx.scene.control.ButtonBar.*;

public class VehicleRepo {

    public static boolean Save(VehicleModle vehicleModle) throws SQLException {
        String sql = "INSERT INTO vehicle VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, vehicleModle.getVehicleID());
        pstm.setObject(2, vehicleModle.getRegNo());
        pstm.setObject(3, vehicleModle.getYear());
        pstm.setObject(4, vehicleModle.getVehicleName());
        pstm.setObject(5, vehicleModle.getFuelType());
        pstm.setObject(6, vehicleModle.getVehicleType());
        pstm.setObject(7, vehicleModle.getCost());
        pstm.setObject(8, vehicleModle.getAvailability());


        return pstm.executeUpdate() > 0;

    }


    public static boolean update(VehicleModle vehicle) throws SQLException {
        String sql = "UPDATE vehicle SET cost = ?, availability = ? WHERE vehicleID = ?";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setDouble(1, vehicle.getCost());
            pstmt.setString(2, vehicle.getAvailability());
            pstmt.setString(3, vehicle.getVehicleID());

            return pstmt.executeUpdate() > 0;

        }


        /*public static List<String> getCodes() throws SQLException {
        String sql = "SELECT code FROM items";
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection()
                .prepareStatement(sql)
                .executeQuery();

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }*/
        public static List<String>getAllAvailableVehicles() throws SQLException {

            String sql ="select vehicleName from vehicle where availability='Available'";

            ResultSet resultSet = DBconnection.getInstance().getConnection().prepareStatement(sql).executeQuery();

            List<String> nameList = new ArrayList<>();
            while (resultSet.next()){
                nameList.add(resultSet.getString("vehicleName"));
            }
            return nameList;
        }

   /* public static List<VehicleModle> SearchByVehicleName(String vehicleName) throws SQLException {

        String sql = "SELECT vehicleName, regNo, cost AS costPerDay FROM vehicle WHERE availability = 'Available' AND vehicleName = ?";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, vehicleName);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String vehicleNameResult = resultSet.getString(1);
            String regNo = resultSet.getString(2);
            Double cost = resultSet.getDouble(3);

            VehicleModle vehicleModle = new VehicleModle(vehicleNameResult, regNo, cost);

           return vehicleModle;
        }

        return null;
    }*/
   public static List<VehicleModle> SearchByVehicleName(String vehicleName) throws SQLException {

       String sql = "SELECT vehicleName, regNo, cost AS costPerDay FROM vehicle WHERE availability = 'Available' AND vehicleName = ?";
       Connection connection = DBconnection.getInstance().getConnection();
       PreparedStatement pstm = connection.prepareStatement(sql);
       pstm.setString(1, vehicleName);
       ResultSet resultSet = pstm.executeQuery();

       List<VehicleModle> resultList = new ArrayList<>();
       while (resultSet.next()) {
           String vehicleNameResult = resultSet.getString(1);
           String regNo = resultSet.getString(2);
           Double cost = resultSet.getDouble(3);
           VehicleModle vehicleModle = new VehicleModle(vehicleNameResult, regNo, cost);
           resultList.add(vehicleModle);
       }
       return resultList;
   }






    public static VehicleModle Search(String vehicleID) throws SQLException {

        String searchSql = "Select * from vehicle where vehicleID =?";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(searchSql);
        pstm.setObject(1, vehicleID);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {

            String  vehicleId = resultSet.getString(1);
            String RegNo = resultSet.getString(2);
            String year = resultSet.getString(3);
            String vehicleName = resultSet.getString(4);
            String fuelType = resultSet.getString(5);
            String vehicleType = resultSet.getString(6);
            double cost = resultSet.getDouble(7);
            String availability = resultSet.getString(8);


            VehicleModle vehicleModle = new VehicleModle(vehicleId,RegNo,year,vehicleName,fuelType,vehicleType,cost,availability);

            return  vehicleModle;

        }
              return  null;
    }


    public static String getCurrentId() throws SQLException {

        String Sql = "SELECT vehicleID FROM vehicle ORDER BY vehicleID DESC LIMIT 1";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement ptsm = connection.prepareStatement(Sql);

        ResultSet resultSet = ptsm.executeQuery();

        if (resultSet.next()) {
            String vehicleID = resultSet.getString(1);
            return vehicleID;
        }
 return  null;
    }

    public static boolean delete(String  vhicleId) throws SQLException {

        String sql = "delete from vehicle where vehicleID =?";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement ptsm = connection.prepareStatement(sql);
        ptsm.setObject(1,vhicleId);

        return  ptsm.executeUpdate()>0;

    }



  public static List<VehicleModle> getAll() throws SQLException {
        String sql = "SELECT * FROM vehicle";

        PreparedStatement pstm = DBconnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<VehicleModle> vehicleList = new ArrayList<>();

        while (resultSet.next()) {
            String vehicleID = resultSet.getString(1);
            String regNo = resultSet.getString(2);
            String year = resultSet.getString(3);
            String vehicleName = resultSet.getString(4);
            String fuelType = resultSet.getString(5);
            String vehicleType = resultSet.getString(6);
            double cost = Double.parseDouble(resultSet.getString(7));
            String availability = resultSet.getString(8);

            VehicleModle vehicle = new VehicleModle(vehicleID,regNo,year,vehicleName,fuelType,vehicleType,cost,availability);
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

}
