package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.BookingDetailsModle;
import model.CustomerModle;
import model.TM.CartTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
