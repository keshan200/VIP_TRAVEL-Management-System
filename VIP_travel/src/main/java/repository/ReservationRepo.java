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




    public static List<BookingDetailsModle> getAllBookingDetails() throws SQLException {

        List<BookingDetailsModle> bookingDetailsList = new ArrayList<>();

        String sql = "SELECT bd.reservationID, bd.regNo, bd.fullCost, bd.startDate, bd.endDate, bd.Days FROM bookingDetails bd JOIN reservation r ON bd.reservationID = r.reservationID";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String reservationID = resultSet.getString("reservationID");
            String regNo = resultSet.getString("regNo");
            double fullCost = resultSet.getDouble("fullCost");
            LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
            LocalDate endDate = resultSet.getDate("endDate").toLocalDate();
            int days = resultSet.getInt("Days");

            BookingDetailsModle bookingDetails = new BookingDetailsModle(regNo, reservationID, startDate, endDate, fullCost, days);
            bookingDetailsList.add(bookingDetails);
        }
        return bookingDetailsList;

    }

}







