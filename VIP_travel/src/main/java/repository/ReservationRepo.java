package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.BookingDetailsModle;
import model.ReservationModle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepo {




    public static boolean Save(ReservationModle resModle) throws SQLException {

        String ReservationSql = "INSERT INTO reservation (reservationID, NIC)values (?,?)";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm1 = connection.prepareStatement(ReservationSql);

        pstm1.setString(1, resModle.getReserstionID());
        pstm1.setString(2, resModle.getNIC());

        return pstm1.executeUpdate() > 0;
    }

    public static String getCurrentReservationId() throws SQLException {
        String sql = "SELECT reservationID FROM reservation ORDER BY reservationID DESC LIMIT 1";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement ptsm = connection.prepareStatement(sql);

        ResultSet resultSet = ptsm.executeQuery();

        if (resultSet.next()) {
            String ResID = resultSet.getString(1);
            return ResID;
        }
        return null;
    }










}

