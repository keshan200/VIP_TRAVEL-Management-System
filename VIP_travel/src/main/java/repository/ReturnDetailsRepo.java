package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.ReturnDetailsModle;
import model.TM.ReturnTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReturnDetailsRepo  {

    public static boolean save(List<ReturnDetailsModle> returnList) throws SQLException {
        for (ReturnDetailsModle returnDetail : returnList) {
            boolean isSaved = saveDetails(returnDetail);
            if (!isSaved) {
                return false;
            }
        }
        return true;
    }

    private static boolean saveDetails(ReturnDetailsModle returnDetail) throws SQLException {
        String sql = "INSERT INTO returndetails (returnID, regNo) VALUES (?,  ?)";

        PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, returnDetail.getReturnID());
        pstm.setString(2, returnDetail.getRegNo());

        return pstm.executeUpdate() > 0;
    }




    public static List<ReturnDetailsModle> getReturnsForCustomer(String nic) throws SQLException {
        List<ReturnDetailsModle> returnDetailsList = new ArrayList<>();




            String sql = "SELECT * FROM ReturnDetails WHERE NIC = ?";


        PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql);

           pstm.setString(1, nic);
           ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                ReturnDetailsModle returnDetails = new ReturnDetailsModle(
                        resultSet.getString("returnID"),
                        resultSet.getString("status"),
                        resultSet.getDate("returnDate").toLocalDate(),
                        resultSet.getString("NIC"),
                        resultSet.getString("regNo"),
                        resultSet.getString("damages"),
                        resultSet.getString("description")
                );
                returnDetailsList.add(returnDetails);
            }


        return returnDetailsList;
    }
}







