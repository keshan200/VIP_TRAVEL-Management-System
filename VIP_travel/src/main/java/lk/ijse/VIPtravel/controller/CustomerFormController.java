package lk.ijse.VIPtravel.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.VIPtravel.Util.Regex;
import model.CustomerModle;
import model.ReservationModle;
import model.TM.CustomerTM;
import repository.CustomerRepo;
import repository.ReservationRepo;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {


    @FXML
    private TableColumn<?,?> ColAdrs;

    @FXML
    private TableColumn<?,?> ColTelno;

    @FXML
    private TableColumn<?,?> colCusID;

    @FXML
    private TableColumn<?,?> colNIC;

    @FXML
    private TableColumn<?,?> colName;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtAddrs;

    @FXML
    private TextField txtCustomerID;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTelNO;





   public  void initialize(){
       clearFields();
       setcellValues();
       loadAllCustomers();
       ShowSelectedCustomerDetails();
       getCurrentID();

   }




private void ShowSelectedCustomerDetails(){

   CustomerTM details = tblCustomer.getSelectionModel().getSelectedItem();
    tblCustomer.setOnMouseClicked(event -> ShowSelectedCustomerDetails());
    if (details != null) {
        txtCustomerID.setText(details .getCustomerID());
        txtNIC.setText(details .getNIC());
        txtName.setText(details .getName());
        txtTelNO.setText(String.valueOf(details .getTelNO()));
        txtAddrs.setText(details .getAddress());
    }
}
   private  void loadAllCustomers(){



       ObservableList<CustomerTM> obList = FXCollections.observableArrayList();

       try {
           List<CustomerModle> cusList = CustomerRepo.getAll();
           for (CustomerModle cusModle : cusList){

               CustomerTM TM = new CustomerTM(cusModle.getCustomerID(),cusModle.getNIC(),cusModle.getName(),cusModle.getTelNO(),cusModle.getAddress());

               obList.add(TM);
               tblCustomer.setItems(obList);
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }


   }


    public String generateNextCustomerID(String curentID){
        if (curentID != null) {
            String[] split = curentID.split("C");
            int idNum = Integer.parseInt(split[1]);
            return "C" + String.format("%03d", ++idNum);
        }
        return "C001";

    }

    public  void getCurrentID(){
        try {
            String curentID = CustomerRepo.getCurrentId();

            String nextCusID= generateNextCustomerID(curentID);
            txtCustomerID.setText(nextCusID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




private  void setcellValues(){

    colCusID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
    colName.setCellValueFactory(new PropertyValueFactory<>("name"));
    ColTelno.setCellValueFactory(new PropertyValueFactory<>("telNO"));
    ColAdrs.setCellValueFactory(new PropertyValueFactory<>("address"));
}


    @FXML
    void btnAdd(ActionEvent event) {

        String customerID = txtCustomerID.getText();
        String NIC = txtNIC.getText();
        String name = txtName.getText();
        int telNo = Integer.parseInt(txtTelNO.getText());
        String adrs = txtAddrs.getText();

        CustomerModle customerModle = new CustomerModle(customerID, NIC, name, telNo, adrs);
        boolean Vaild = isValied();
        try {
            if (Vaild) {


                boolean isSaved = CustomerRepo.Add(customerModle);
                System.out.println(customerModle);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved!!").show();
                    clearFields();
                    loadAllCustomers();
                }
            }
            } catch(SQLException e){
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }


    @FXML
    void btnClear(ActionEvent event) {
       clearFields();

    }


    public  void clearFields(){
        txtCustomerID.setText("");
        txtNIC.setText("");
        txtName.setText("");
        txtTelNO.setText("");
        txtAddrs.setText("");

    }


    @FXML
    void btnDelete(ActionEvent event) {

        String cuID = txtCustomerID.getText();
        if (!cuID.isEmpty()){
            new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure you want Delete ? ").showAndWait();
        }
        try {
                if (CustomerRepo.Delete(cuID)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted ").show();
                    clearFields();
                    loadAllCustomers();

}

            } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

    }

    @FXML
    void btnUpdate(ActionEvent event) throws SQLException {

        String cusID = txtCustomerID.getText();
        String name = txtName.getText();
        int telNO = Integer.parseInt(txtTelNO.getText());
        String adrs = txtAddrs.getText();

        CustomerModle cusmodle = new CustomerModle(cusID,name,telNO,adrs);

        boolean isUpdate = CustomerRepo.Update(cusmodle);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated Sucsesssfully!").show();
                clearFields();
                loadAllCustomers();

            } else {
                new Alert(Alert.AlertType.ERROR, "Can't Update").show();
            }
        }


    public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.VIPtravel.Util.TextField.TELNO, txtTelNO)) return false;
        return true;
    }




    @FXML
    void NicOnAction(ActionEvent event) throws SQLException {

       String nic = txtNIC.getText();

       CustomerModle cusmodel = CustomerRepo.Search(nic);

        if (cusmodel != null) {
            txtCustomerID.setText(cusmodel.getCustomerID());
            txtName.setText(cusmodel.getName());
            txtTelNO.setText(String.valueOf(cusmodel.getTelNO()));
            txtAddrs.setText(cusmodel.getAddress());

        }else{
            new Alert(Alert.AlertType.INFORMATION, "Customer not found!").show();
            clearFields();

        }
    }

    public void telNoAction(javafx.scene.input.KeyEvent keyEvent) {

        Regex.setTextColor(lk.ijse.VIPtravel.Util.TextField.TELNO,txtTelNO);
    }
}
