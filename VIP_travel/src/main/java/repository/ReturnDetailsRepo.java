package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.ReturnDetailsModle;
import model.TM.ReturnTM;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
