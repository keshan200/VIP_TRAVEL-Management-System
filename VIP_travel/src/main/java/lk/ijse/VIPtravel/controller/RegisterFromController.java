package lk.ijse.VIPtravel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.RegisterModle;
import repository.RegisterRepo;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterFromController {

    @FXML
    private AnchorPane ancMain;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtuserID;

    public AnchorPane root;

    @FXML
    void btnClear(ActionEvent event) {

    }


    @FXML
    void btnSave(ActionEvent event) throws IOException {

        String userID = txtuserID.getText();
        String password = txtPassword.getText();
        String email = txtemail.getText();


        RegisterModle registerModle = new RegisterModle(userID, password, email);

        try {
            boolean isSaved = RegisterRepo.userSave(registerModle);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "user saved!").show();


                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/ViewForms/SubForms/EmployeeDdetailForm.fxml"));
                Parent load = loader.load();
                //EmployeeDdetailFormController controller = loader.getController();

               /* ancMain.getChildren().clear();
                ancMain.getChildren().add(load);*/

                Stage stage = new Stage();
                stage.setScene(new Scene(load));
                stage.show();

                Stage window = (Stage) txtuserID.getScene().getWindow();
                window.close();


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    }




