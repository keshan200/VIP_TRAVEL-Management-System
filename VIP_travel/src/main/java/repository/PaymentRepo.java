package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.PaymentModle;
import model.VehicleModle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {

    public static String getCurrentId() throws SQLException {

        String sql = "SELECT paymentID FROM payment ORDER BY paymentID DESC LIMIT 1";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement ptsm = connection.prepareStatement(sql);

        ResultSet resultSet = ptsm.executeQuery();

        if (resultSet.next()) {
            String PaymentID = resultSet.getString(1);
            return PaymentID;
        }
        return  null;
    }





    public static boolean savePayment(PaymentModle paymentModel) throws SQLException {
        String sql = "INSERT INTO payment VALUES (?, ?, ?, ?, ?, ?,?)";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, paymentModel.getPaymentID());
        pstm.setObject(2, paymentModel.getStatus());
        pstm.setObject(3, paymentModel.getType());
        pstm.setObject(4, paymentModel.getFullPayment());
        pstm.setObject(5, paymentModel.getReservationID());
        pstm.setObject(6, paymentModel.getPaydate());
        pstm.setObject(7,paymentModel.getPaymentMethod());

        return pstm.executeUpdate() > 0;
    }



    public static List<PaymentModle> getAllPayments() throws SQLException {
        String sql = "SELECT * FROM payment";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<PaymentModle> paymentList = new ArrayList<>();

        while (resultSet.next()) {
            String paymentID = resultSet.getString("paymentID");
            String status = resultSet.getString("status");
            String type = resultSet.getString("type");
            double fullPayment = resultSet.getDouble("fullpayment");
            String reservationID = resultSet.getString("reservationID");
            LocalDate paymentDate = resultSet.getDate("paymentDate").toLocalDate();
            String paymentMethod = resultSet.getString("paymentMethod");

            PaymentModle payment = new PaymentModle(paymentID, status, type, fullPayment, reservationID, paymentDate,paymentMethod);
            paymentList.add(payment);
        }
        return paymentList;
    }



    public static List<PaymentModle> getPaymentsByNIC(String NIC) throws SQLException {
        List<PaymentModle> paymentList = new ArrayList<>();
        String sql = "SELECT * FROM payment WHERE reservationID IN (SELECT reservationID FROM reservation WHERE NIC = ?)";


        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, NIC);
            try (ResultSet resultSet = pstm.executeQuery()) {
                while (resultSet.next()) {
                    PaymentModle paymentModel = new PaymentModle(
                            resultSet.getString("paymentID"),
                            resultSet.getString("status"),
                            resultSet.getString("type"),
                            resultSet.getDouble("fullpayment"),
                            resultSet.getString("reservationID"),
                            resultSet.getDate("paymentDate").toLocalDate(), // Assuming paymentDate is a date type
                            resultSet.getString("paymentMethod")
                    );
                    paymentList.add(paymentModel);
                }
            }

        return paymentList;
    }


    public static boolean update(String payID,Double cost,String typpe,String status) throws SQLException {

        String sql = "UPDATE payment SET fullpayment = ?, type = ?, status = ? WHERE paymentID = ?";


        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);


        pstmt.setDouble(1, cost);
        pstmt.setString(2,typpe );
        pstmt.setString(3,status);
        pstmt.setString(4,payID);

            return pstmt.executeUpdate() > 0;

        }

    }


