package lk.ijse.VIPtravel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.EmpDtlsModle;
import model.TM.EmpTm;
import model.TM.VehicleTM;
import model.VehicleModle;
import repository.EmployeeDetailsRepo;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

import repository.RegisterFromRepo;
import repository.RegisterRepo;
import repository.VehicleRepo;

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

    @FXML
    private TextField tp;


    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtemail;



    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colUserID;

    @FXML
    private TableColumn<?, ?> coltel;



    @FXML
    private TableView<EmpTm> tblEMp;


    public void initialize() {

      loadAllvehicles();
      setCellValues();
    }



    @FXML
    void NicOnAction(ActionEvent event) throws SQLException {

            String NIC = txtNIC.getText();


            EmpDtlsModle emp = EmployeeDetailsRepo.Search(NIC);
            if (emp != null) {
                txtNIC.setText(emp.getNIC());
                txtName.setText(emp.getName());
                txtAdress.setText(emp.getAddress());
                tp.setText(String.valueOf(emp.getTp()));
                txtuserID.setText(emp.getUserID());
                txtPassword.setText(String.valueOf(emp.getPassword()));
                txtemail.setText(emp.getEmail());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
                clearFields();
            }
        }

    private void clearFields() {
        txtNIC.setText("");
        txtName.setText("");
        txtAdress.setText("");
        tp.setText("");
        txtuserID.setText("");
        txtPassword.setText("");
        txtemail.setText("");
    }


    private  void setCellValues(){

        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("tp"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }


    private void loadAllvehicles() {
        ObservableList<EmpTm> obList = FXCollections.observableArrayList();

        try {
            List<EmpDtlsModle> emplist = EmployeeDetailsRepo.getAll();
            for (EmpDtlsModle emp : emplist) {

                EmpTm tm = new EmpTm(
                        emp.getNIC(),
                        emp.getName(),
                        emp.getAddress(),
                        emp.getTp(),
                        emp.getUserID(),
                        emp.getEmail()

                );

                obList.add(tm);
            }
            //System.out.println(obList);
            tblEMp.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnRegister(ActionEvent event) throws SQLException {


        String NIC = txtNIC.getText();
        String name = txtName.getText();
        String address = txtAdress.getText();
        int Tp = Integer.parseInt(tp.getText());
        String userID = txtuserID.getText();
        String pass = txtPassword.getText();
        String emai = txtemail.getText();


        EmpDtlsModle empDtlsModle = new EmpDtlsModle(NIC,name,address,Tp,userID,pass,emai);

        try {
            boolean isSaved = EmployeeDetailsRepo.Save(empDtlsModle);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

      /*  String empID = txtid.getText();
        String NIC = txtNIC.getText();
        String name = txtName.getText();
        String address = txtAdress.getText();
        String userID = txtuserID.getText();
        String pass = txtPassword.getText();
        String email = txtemail.getText();

        try {
            RegisterFromRepo.addEmployee(empID, NIC, name, address, userID, pass, email);


            new Alert(Alert.AlertType.INFORMATION, "Material added successfully.");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to add material.");
            e.printStackTrace();
        }

    }*/
}



