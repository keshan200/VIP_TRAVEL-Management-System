package lk.ijse.VIPtravel.controller;



import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.*;
import model.TM.CartTM;
import model.TM.ReturnTM;
import repository.ReservationRepo;
import repository.ReturnDetailsRepo;
import repository.ReturnFormRepo;
import repository.ReturnRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReturnFormController {

    @FXML
    private DatePicker ReturnDate;

    @FXML
    private JFXComboBox<String> cmbDamge;

    @FXML
    private JFXComboBox<String> cmbStatus;

    @FXML
    private TableColumn<?, ?> colDamage;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colRegNo;

    @FXML
    private TableColumn<?, ?> colRetDate;

    @FXML
    private TableColumn<?, ?> colReturnID;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colac;

    @FXML
    private TableView<ReturnTM> tblReturn;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRegNO;

    @FXML
    private TextField txtReturn;

    @FXML
    private TextArea txtdesc;

    @FXML
    private TextField txtvehicle;

    ObservableList<ReturnTM>retList = FXCollections.observableArrayList();






    public  void initialize(){
        setCmbStatus();
        setCmbDamage();
        getCurrentID();
        setCellVFactory();
 loadAllReservations();



    }



    @FXML
    void nicOnAction(ActionEvent event) {
        try {
            loadReturnsForCustomer();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error loading customer details: " + e.getMessage()).show();
        }
    }

    private void loadReturnsForCustomer() throws SQLException {
        String nic = txtNIC.getText();
        if (!nic.isEmpty()) {
            List<ReturnTM> returnList = new ArrayList<>();
            List<ReturnDetailsModle> returnDetailsList = ReturnDetailsRepo.getReturnsForCustomer(nic);
            for (ReturnDetailsModle returnDetails : returnDetailsList) {
                ReturnTM returnTM = new ReturnTM(
                        returnDetails.getReturnID(),
                        returnDetails.getStatus(),
                        returnDetails.getReturnDate(),
                        returnDetails.getNIC(),
                        returnDetails.getRegNo(),
                        returnDetails.getDamages(),
                        returnDetails.getDesc(),
                        new JFXButton("❌")
                );
                returnList.add(returnTM);
            }
            tblReturn.setItems(FXCollections.observableArrayList(returnList));
        }
    }


    private void setCellVFactory() {
        colReturnID.setCellValueFactory(new PropertyValueFactory<>("returnID"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colRetDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colRegNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colDamage.setCellValueFactory(new PropertyValueFactory<>("damages"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colac.setCellValueFactory(new PropertyValueFactory<>("remove"));
    }





    @FXML
    void btnAdd(ActionEvent event) {

        String retID = txtReturn.getText();
        String status = cmbStatus.getValue();
        LocalDate date = ReturnDate.getValue();
        String nic = txtNIC.getText();
        String damage = cmbDamge.getValue();
        String desc = txtdesc.getText();


        List<ReturnDetailsModle>RetunList = new ArrayList<>();

        for (ReturnTM tm : tblReturn.getItems()) {
            ReturnDetailsModle R = new ReturnDetailsModle(

                    tm.getReturnID(),
                    tm.getRegNo()

            );

            RetunList.add(R);

        }

        ReturnModle returnModle = new ReturnModle(retID,status,date,nic,damage,desc);
        RetutnFormModle retutnFormModle = new RetutnFormModle(returnModle, RetunList);

          try {
              boolean isOk = ReturnFormRepo.SetReturn(retutnFormModle);
              if (isOk ) {
                  retList.clear();

                  new Alert(Alert.AlertType.CONFIRMATION,"Return Sucsess").show();

              }else {
                  new Alert(Alert.AlertType.ERROR,"Return Sucsess").show();
              }

          } catch (SQLException e) {
              throw new RuntimeException(e);
          }

    }

    @FXML
    void btnConfirm(ActionEvent event) {

        String returnID = txtReturn.getText();
        String status = cmbStatus.getValue();
        LocalDate date = ReturnDate.getValue();
        String NIC = txtNIC.getText();
        String regNo = txtRegNO.getText();
        String damages = cmbDamge.getValue();
        String desc = txtdesc.getText();

        JFXButton remove = new JFXButton("❌");
        remove.setCursor(Cursor.HAND);
        remove.setStyle("-fx-text-fill: red;");
        remove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
            if (type.orElse(no) == yes) {
                int selectedIndex = tblReturn.getSelectionModel().getSelectedIndex();
                retList.remove(selectedIndex);

            }
        });


        if (!retList.isEmpty()) {
          for(int i =0;i< retList.size();i++){
              if (regNo.equals(colRegNo.getCellData(i))) {
                  ReturnTM tm = retList.get(i);
                  tblReturn.refresh();
                  return;
              }

          }
        }
ReturnTM tm = new ReturnTM(returnID,status,date,NIC,regNo,damages,desc,remove);
        retList.add(tm);
        tblReturn.setItems(retList);

    }

    @FXML
    void btnUpdate(ActionEvent event) {

    }

    @FXML
    void setReturnDate(ActionEvent event) {
        String value = String.valueOf(ReturnDate.getValue());

    }



    public  void  setCmbDamage(){

        ObservableList<String> list = FXCollections.observableArrayList("Yes", "No", "Hard Damage");
        cmbDamge.setItems(list);

    }



    public  void  setCmbStatus(){

        ObservableList<String> list = FXCollections.observableArrayList("Complete", "Not Complete");
        cmbStatus.setItems(list);

    }



    public String generateNextVehicleID(String curentID){
        if (curentID != null) {
            String[] split = curentID.split("RT");
            int idNum = Integer.parseInt(split[1]);
            return "RT" + String.format("%02d", ++idNum);
        }
        return "RT01";
    }

    public  void getCurrentID(){
        try {
            String curentID = ReturnRepo.getCurrentId();
            String nextRetunrID = generateNextVehicleID(curentID);
            txtReturn.setText(nextRetunrID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private void loadAllReservations() {
        List<ReturnTM> RetList = FXCollections.observableArrayList();

        try {
            List<ReturnDetailsModle> AllReturns = ReturnRepo.getAllReturns();


            for (ReturnDetailsModle ret : AllReturns) {
                String ReturnID = ret.getReturnID();
                String Status = ret.getStatus();
                LocalDate rDate = ret.getReturnDate();
                String NIC = ret.getNIC();
                String regNo = ret.getRegNo();
                String damages = ret.getDamages();
                String desc = ret.getDesc();


                ReturnTM rett = new ReturnTM(ReturnID,Status,rDate,NIC,regNo,damages,desc,new JFXButton("❌"));
                RetList.add(rett);
            }

            tblReturn.setItems(FXCollections.observableArrayList(RetList));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}



