package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.ReservationModle;
import model.ReturnModle;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReturnRepo {

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


    public static boolean Save(ReturnModle reModle) throws SQLException {

        String ReservationSql = "INSERT INTO return_ (returnID, status, returnDate,  NIC, damages, description) VALUES(?,?,?,?,?,?)";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm1 = connection.prepareStatement(ReservationSql);

        pstm1.setString(1, reModle.getReturnID());
        pstm1.setString(2, reModle.getStates());
        pstm1.setDate(3, Date.valueOf(reModle.getDate()));//date redda
        pstm1.setString(4, reModle.getNIC());
        pstm1.setString(5, reModle.getDamge());
        pstm1.setString(6, reModle.getDesc());

        return pstm1.executeUpdate() > 0;
    }



    public static List<ReturnModle> getAllReturns() throws SQLException {
        List<ReturnModle> returnList = new ArrayList<>();
        String sql = "SELECT r.returnID, r.status, r.returnDate, r.NIC, r.damages, r.description, rd.regNo FROM `return_` r JOIN returndetails rd ON r.returnID = rd.returnID";
              Connection connection = DBconnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery() ;

            while (resultSet.next()) {
                String returnID = resultSet.getString("returnID");
                String status = resultSet.getString("status");
                LocalDate returnDate = resultSet.getDate("returnDate").toLocalDate();
                String NIC = resultSet.getString("NIC");
                String damages = resultSet.getString("damages");
                String description = resultSet.getString("description");

                ReturnModle returnModel = new ReturnModle(returnID, status, returnDate, NIC, damages, description);
                returnList.add(returnModel);
            }

        return returnList;
    }



}
