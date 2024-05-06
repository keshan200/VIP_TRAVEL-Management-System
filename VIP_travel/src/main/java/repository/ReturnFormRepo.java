package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.BookingFormModle;
import model.ReturnFromModle;

import java.sql.Connection;
import java.sql.SQLException;

public class ReturnFormRepo {

/**/
    public static boolean placeReturn(ReturnFromModle b) throws SQLException {
        Connection connection = DBconnection.getInstance().getConnection();
        connection.setAutoCommit(false);


        try {
            boolean isReturnSaved = ReturnRepo.Save(b.getRetrun());
            if (isReturnSaved) {
                boolean isReturnDetailSaved = ReturnDetailsRepo.save(b.getResturnList());
                if (isReturnDetailSaved) {
                    connection.commit();
                    return true;
                }
            }

            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
