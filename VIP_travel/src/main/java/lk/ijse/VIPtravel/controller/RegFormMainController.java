package lk.ijse.VIPtravel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.EmpDtlsModle;
import repository.EmployeeDetailsRepo;

import java.io.IOException;
import java.sql.SQLException;

public class RegFormMainController {

    @FXML
    private TextField adrs;

    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private TextField nic;

    @FXML
    private TextField pas;

    @FXML
    private TextField tP;

    @FXML
    private TextField usrID;

    @FXML
    void btnRegister(ActionEvent event) {

        String NIC = nic.getText();
        String Name = name.getText();
        String address = adrs.getText();
        int Tp = Integer.parseInt(tP.getText());
        String userID = usrID.getText();
        String pass = pas.getText();
        String emai = email.getText();


        EmpDtlsModle empDtlsModle = new EmpDtlsModle(NIC,Name,address,Tp,userID,pass,emai);

        try {
            boolean isSaved = EmployeeDetailsRepo.Save(empDtlsModle);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }







    @FXML
    void btnBack(ActionEvent event) throws IOException {
        FXMLLoader Regloader = new FXMLLoader(getClass().getResource("/ViewForms/LoginForm.fxml"));
        Parent Regload = Regloader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(Regload));
        stage.show();


        Stage window = (Stage) name.getScene().getWindow();
        window.close();

    }
}
