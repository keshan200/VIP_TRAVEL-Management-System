package repository;

import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.RegisterModle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RegisterRepo {


    public static  boolean userSave(RegisterModle registerModle) throws SQLException {

        String sql = "insert into user values(?,?,?)";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setObject(1,registerModle.getUserID());
        pst.setObject(2,registerModle.getPassword());
        pst.setObject(3,registerModle.getEmail());

        return pst.executeUpdate() > 0;
    }





}


