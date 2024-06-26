package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.BookingDetailsModle;
import model.CustomerModle;
import model.TM.CartTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDetailsRepo extends CartTM {

public static boolean save(List<BookingDetailsModle> BookingList) throws SQLException {
        for (BookingDetailsModle bookings : BookingList) {
            boolean isSaved = SaveDetails(bookings);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }



    public static boolean SaveDetails(BookingDetailsModle b) throws SQLException {
        String sql = "INSERT INTO bookingDetails (RegNo,reservationID,fullCost,startDate,endDate,Days) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement pstm = DBconnection.getInstance().getConnection()
                .prepareStatement(sql);


        pstm.setString(1, b.getRegNo());
        pstm.setString(2, b.getReservationID());
        pstm.setDouble(3, b.getTotalCost());
        pstm.setDate(4, java.sql.Date.valueOf(b.getStartDate())); // Assuming getStartDate() returns LocalDate
        pstm.setDate(5, java.sql.Date.valueOf(b.getEndDate()));   // Assuming getEndDate() returns LocalDate
        pstm.setInt(6, b.getDaysCount());


            return pstm.executeUpdate() > 0;
        }






    public static CustomerModle getCustomerNameByNIC(String nic) throws SQLException {
        String sql = "SELECT  name FROM customer WHERE NIC = ?";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,nic);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            String customerNIC = nic;
            String name = resultSet.getString("name");

            CustomerModle customerModle = new CustomerModle(customerNIC, name);
            return customerModle;
        }
        return null;
    }





    public static List<BookingDetailsModle> getBookingDetailsByNIC(String NIC) throws SQLException {

    String sql = "SELECT  bd.regNo, bd.reservationID, bd.fullCost, bd.startDate, bd.endDate, bd.Days FROM bookingDetails bd JOIN reservation r ON bd.reservationID = r.reservationID WHERE r.NIC = ?";
        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, NIC);

        ResultSet resultSet = pstm.executeQuery();
        List<BookingDetailsModle> bookingDetailsList = new ArrayList<>();

        while (resultSet.next()) {
                String regNo = resultSet.getString("regNo");
                String reservationID = resultSet.getString("reservationID");
                LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
                LocalDate endDate = resultSet.getDate("endDate").toLocalDate();
                int daysCount = resultSet.getInt("Days");
                double totalCost = resultSet.getDouble("fullCost");


              BookingDetailsModle b = new BookingDetailsModle(reservationID,regNo,startDate,endDate,daysCount,totalCost);
            bookingDetailsList.add(b);
             }

        return bookingDetailsList;
        }



    public static String getCurrentReservationId() throws SQLException {
        String sql = "SELECT reservationID FROM bookingDetails ORDER BY reservationID DESC LIMIT 1";

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




