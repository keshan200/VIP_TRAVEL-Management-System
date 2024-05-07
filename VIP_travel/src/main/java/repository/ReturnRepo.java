package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.BookingDetailsModle;
import model.ReturnDetailsModle;
import model.ReturnModle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReturnRepo {

    public static  boolean save(ReturnModle retModle) throws SQLException {

        String sql ="INSERT INTO return_ (returnID, status, returnDate, NIC, damages, description) VALUES (?,?,?,?,?,?)";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,retModle.getReturnID());
        pstm.setString(2,retModle.getStatus());
        pstm.setDate(3,java.sql.Date.valueOf(retModle.getReturnDate()));
        pstm.setString(4,retModle.getNIC());
        pstm.setString(5,retModle.getDamages());
        pstm.setString(6,retModle.getDesc());

      return pstm.executeUpdate()>0;

    }


    public static String getCurrentId() throws SQLException {

        String sql = "SELECT returnID FROM return_ ORDER BY returnID DESC LIMIT 1";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement ptsm = connection.prepareStatement(sql);

        ResultSet resultSet = ptsm.executeQuery();

        if (resultSet.next()) {
            String returnID = resultSet.getString(1);
            return returnID;
        }
        return  null;
    }




    public  static  List<ReturnDetailsModle>getAllReturns() throws SQLException {

        List<ReturnDetailsModle> returnDetailsList = new ArrayList<>();

        String sql ="SELECT r.returnID, r.status, r.returnDate, r.NIC, r.damages, r.description, rd.regNo, v.year, v.vehicleName, v.fuelType, v.vehicleType, v.cost, v.availability FROM returndetails rd JOIN return_ r ON rd.returnID = r.returnID JOIN vehicle v ON rd.vehicleID = v.vehicleID";


        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement ptsm = connection.prepareStatement(sql);

        ResultSet resultSet = ptsm.executeQuery();
        while (resultSet.next());

        String returnID = resultSet.getString("returnID");
        String status = resultSet.getString("status");
        LocalDate returnDate = resultSet.getDate("returnDate").toLocalDate();
        String NIC = resultSet.getString("NIC");
        String damages = resultSet.getString("damages");
        String description = resultSet.getString("description");
        String regNo = resultSet.getString("regNo");

        ReturnDetailsModle er= new ReturnDetailsModle(returnID, status, returnDate, NIC, damages, description,regNo);
        returnDetailsList.add(er);


        return returnDetailsList;
    }

}
