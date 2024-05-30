package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.BookingDetailsModle;
import model.DashBoardModle;
import model.TM.DashboardTableTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardRepo {

    public int getCustomerCount(DashBoardModle dashBoardModle) throws SQLException {

        String sql = "SELECT COUNT(*) AS customer_count FROM customer";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("customer_count");
        }
        return 0;

    }


    public int getEmployeeCount(DashBoardModle dashBoardModle) throws SQLException {
        String sql = "select count(*) as employee_count from employee";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("employee_count");
        }
        return 0;
    }



    public  int getcarCount(DashBoardModle dashBoardModle) throws SQLException {

        String sql = "SELECT COUNT(*) AS carCount FROM vehicle WHERE vehicleType = 'Car' ";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();


        if(resultSet.next()){
            return  resultSet.getInt("carCount");
        }
       return  0;
    }


    public  int getvanCount(DashBoardModle dashBoardModle) throws SQLException {

        String sql = "SELECT COUNT(*) AS vanCount FROM vehicle WHERE vehicleType = 'Van' ";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet .next()) {
            return resultSet.getInt("vanCount");

        }
    return  0;
    }


    public  int getsuvCount(DashBoardModle dashBoardModle) throws SQLException {

        String sql = "SELECT COUNT(*) AS suvCount FROM vehicle WHERE vehicleType ='Suv'";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("suvCount");

        }
        return 0;
    }


    public  int getbookingCount(String reservations) throws SQLException {

        String sql ="SELECT COUNT(*) AS totalReservations FROM reservation";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {

            return resultSet.getInt("totalReservations");
        }
 return 0;
    }


    public static  List<DashboardTableTM> getVehicleStatistics() throws SQLException {

        String sql = "SELECT vehicleName, COUNT(*) AS totalAvailable, AVG(cost) AS costPerDay FROM vehicle WHERE availability = 'Available' GROUP BY vehicleName ";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();


        List<DashboardTableTM> vehicleStatisticsList = new ArrayList<>();

            while (resultSet.next()) {
                String vehicleName = resultSet.getString("vehicleName");
                int totalAvailable = resultSet.getInt("totalAvailable");
                double costPerDay = resultSet.getDouble("costPerDay");
                vehicleStatisticsList.add(new DashboardTableTM(vehicleName, totalAvailable, costPerDay));
            }
        return vehicleStatisticsList;
        }




    public static List<BookingDetailsModle> getAllSalesByPaymentStatus() throws SQLException {
        List<BookingDetailsModle> sales = new ArrayList<>();
        String sql = "SELECT status, COUNT(*) AS Count FROM payment GROUP BY status";
        Connection connection = DBconnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String paymentStatus = resultSet.getString("status");
                int count = resultSet.getInt("Count");
                sales.add(new BookingDetailsModle(paymentStatus, count));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sales;
    }









    }










