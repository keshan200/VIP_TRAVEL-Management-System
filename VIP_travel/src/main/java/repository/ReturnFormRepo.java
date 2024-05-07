package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.RetutnFormModle;

import java.sql.Connection;
import java.sql.SQLException;

public class ReturnFormRepo {




    public static boolean SetReturn(RetutnFormModle ret) throws SQLException {

        Connection connection = DBconnection.getInstance().getConnection();
        connection.setAutoCommit(false);



        try {
            boolean isReturnSaved = ReturnRepo.save(ret.getReturn());
            if (isReturnSaved) {
                boolean isReturnDetailsSaved = ReturnDetailsRepo.save(ret.getReturnList());
                if (isReturnDetailsSaved) {
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




