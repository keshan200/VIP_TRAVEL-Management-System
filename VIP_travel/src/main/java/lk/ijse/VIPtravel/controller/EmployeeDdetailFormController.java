package lk.ijse.VIPtravel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.EmpDtlsModle;
import repository.EmployeeDetailsRepo;

import java.awt.*;
import java.sql.SQLException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import repository.RegisterRepo;

public class EmployeeDdetailFormController {

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtAdress;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtuserID;



  public void initialize() {

        getCurrentempID();
    }

    private void getCurrentempID(){

        try {
            String currentId = EmployeeDetailsRepo.getCurentempID();

            String nextOrderId = generateNextEmpID(currentId);
            txtid.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private String generateNextEmpID(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("E");
            int idNum = Integer.parseInt(split[1]);
            return "E" + String.format("%03d", ++idNum);
        }
        return "E001";
    }

    public void btnRegister(ActionEvent event) {

        String empID = txtid.getText();
        String NIC = txtNIC.getText();
        String name = txtName.getText();
        String address = txtAdress.getText();
        String userID = txtuserID.getText();


        EmpDtlsModle empDtlsModle = new EmpDtlsModle(empID,NIC,name,address,userID);

        try {
            boolean isSaved = EmployeeDetailsRepo.Save(empDtlsModle);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
