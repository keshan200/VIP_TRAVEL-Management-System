package lk.ijse.VIPtravel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.VIPtravel.DBconnection.DBconnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private AnchorPane ancLogin;
    @FXML
    private PasswordField txtPassword;


    @FXML
    private TextField txtUser;

    @FXML
    void btnLogin(ActionEvent event) throws SQLException, IOException {
        String userID = txtUser.getText();
        String password = txtPassword.getText();

        if (userID.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter  User ID and Password!").show();
            return;
        }

        checkCredential(userID, password, (Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void checkCredential(String userID, String password, Stage stage) throws SQLException, IOException {
        String sql = "SELECT userID, password FROM user WHERE userID = ?";

        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userID);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String dbPw = resultSet.getString("password");

            if (password.equals(dbPw)) {
                navigateDashboard(userID, stage);
            } else {
                new Alert(Alert.AlertType.ERROR, "Sorry! Password is incorrect!").show();
            }

        } else {
            new Alert(Alert.AlertType.INFORMATION, "Sorry! User ID can't be found!").show();
        }
    }

    public void navigateDashboard(String userID, Stage stage) throws IOException, SQLException {
        String sqlgetName = "SELECT employee.name FROM user JOIN employee ON user.userID = employee.userID WHERE user.userID = ?";
        Connection con = DBconnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sqlgetName);
        ps.setString(1, userID);

        ResultSet resultSet = ps.executeQuery();
        String employeeName = null;
        if (resultSet.next()) {
            employeeName = resultSet.getString("name");
        }

        FXMLLoader dashloader = new FXMLLoader(getClass().getResource("/ViewForms/Dashboard.fxml"));
        Parent load = dashloader.load();

        DashboardController controller = dashloader.getController();//
        controller.setUserName(employeeName); // dash eke lbl ekat userID ekata adala nama set wenawa
        Stage dashboardStage = new Stage();
        dashboardStage.setScene(new Scene(load));
        dashboardStage.show();

        stage.close();
    }

    public void btnRegister(ActionEvent event) throws IOException {

        /*FXMLLoader Regloader = new FXMLLoader(getClass().getResource("/ViewForms/RegisterFrom.fxml"));
        Parent Regload = Regloader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(Regload));
        stage.setTitle("Register Form");
        stage.show();*/


       /* Stage window = (Stage) txtUser.getScene().getWindow();
        window.close();*/

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/ViewForms/RegisterFrom.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Registration Form");
        stage.show();


    }
}