package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.BookingDetailsModle;
import model.ReturnDetailsModle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReturnDetailsRepo {


    public static boolean save(List<ReturnDetailsModle> ReturnList) throws SQLException {
        for (ReturnDetailsModle Return : ReturnList) {
            boolean isSaved = SaveDetails(Return);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }



    public static boolean SaveDetails(ReturnDetailsModle r) throws SQLException {
        String sql = "INSERT INTO returndetails (returnID,regNO) VALUES (?, ?)";

        PreparedStatement pstm = DBconnection.getInstance().getConnection()
                .prepareStatement(sql);


        pstm.setString(1, r.getReturnID());
        pstm.setString(2, r.getRegNo());


        return pstm.executeUpdate() > 0;
    }
}
