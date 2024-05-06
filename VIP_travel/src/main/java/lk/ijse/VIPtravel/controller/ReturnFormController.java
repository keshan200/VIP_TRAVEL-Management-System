package lk.ijse.VIPtravel.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import model.TM.CartTM;
import model.TM.ReturnTM;
import repository.BookingFormRepo;
import repository.PaymentRepo;
import repository.ReturnFormRepo;
import repository.ReturnRepo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private TextField txtvehicle;

    @FXML
    private TextArea txtdesc;


    ObservableList<ReturnTM> retList = FXCollections.observableArrayList();





    public  void initialize(){

        getCurrentID();
        setCmbDamage();
        setCmbStatus();
        loadAllReturns();
      setCellValues();
    }




/*
        String resID = txtReservationID.getText();
        String NIC = txtNIC.getText();

        List<BookingDetailsModle> bookingList = new ArrayList<>();


        for (CartTM tm : tblReservation.getItems()) {
            BookingDetailsModle od = new BookingDetailsModle(
                    tm.getRegNo(),
                    tm.getReservationID(),
                    tm.getStartDate(),
                    tm.getEndDate(),
                    tm.getTotalCost(),
                    tm.getDaysCount()
            );
            bookingList.add(od);
        }


        ReservationModle reservationModle = new ReservationModle(resID, NIC);
        BookingFormModle bm = new BookingFormModle(reservationModle, bookingList);

        try {
            boolean isPlaced = BookingFormRepo.placeBooking(bm);
            if (isPlaced) {
                resList.clear();
                clearFields();
                loadAllReservations();
                new Alert(Alert.AlertType.CONFIRMATION, "Booking Confirmed!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Reservation Unsuccessful!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
*/

    @FXML
    void btnAdd(ActionEvent event) {

        String returnID = txtReturn.getText();
        LocalDate date = ReturnDate.getValue();
        String NIC = txtNIC.getText();
        String status = cmbStatus.getValue();
        String damage = cmbDamge.getValue();
        String desc = txtdesc.getText();

        List<ReturnDetailsModle> retList = new ArrayList<>();


        for (ReturnTM tm : tblReturn.getItems()) {
           ReturnDetailsModle od = new ReturnDetailsModle(
                   tm.getReturnID(),
                   tm.getRegNO()


            );
            retList.add(od);
        }

        ReturnModle retunModel = new ReturnModle(returnID,date,NIC,status,damage,desc);
        ReturnFromModle bm = new ReturnFromModle(retunModel, retList);

        try {
            boolean isPlaced = ReturnFormRepo.placeReturn(bm);
            if (isPlaced) {
                retList.clear();
                //clearFields();
                 loadAllReturns();
                  new Alert(Alert.AlertType.CONFIRMATION, "Booking Confirmed!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Reservation Unsuccessful!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }







    @FXML
    void btnConfirm(ActionEvent event) {

        String returnID = txtReturn.getText();
        String status = cmbStatus.getValue();
        LocalDate date = ReturnDate.getValue();
        String NIC = txtNIC.getText();
        String regNO = txtRegNO.getText();
        String damage = cmbDamge.getValue();
        String desc = txtdesc.getText();



        if (!retList.isEmpty()) {
            for (int i = 0; i < retList.size(); i++) {
                if (regNO.equals(colRegNo.getCellData(i))) {
                    ReturnTM tm = retList.get(i);
                    tblReturn.refresh();
                    return;
                }
            }
        }

        ReturnTM tm = new ReturnTM(returnID, status, date, NIC, regNO, damage, desc);
        retList.add(tm);
        tblReturn.setItems(retList);

    }





    public  void  setCmbDamage(){

        ObservableList<String> list = FXCollections.observableArrayList("Yes", "No", "Hard Damage");
        cmbDamge.setItems(list);

    }



    public  void  setCmbStatus(){

        ObservableList<String> list = FXCollections.observableArrayList("Complete", "Not Complete");
        cmbStatus.setItems(list);

    }



    private void setCellValues() {
        colReturnID.setCellValueFactory(new PropertyValueFactory<>("returnID"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("states"));
        colRetDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colRegNo.setCellValueFactory(new PropertyValueFactory<>("regNO"));
        colDamage.setCellValueFactory(new PropertyValueFactory<>("damge"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("desc"));
    }

    private void loadAllReturns() {
        try {
            List<ReturnModle> returnsList = ReturnRepo.getAllReturns();
            ObservableList<ReturnTM> obList = FXCollections.observableArrayList();

            for (ReturnModle returnModel : returnsList) {
                ReturnTM tm = new ReturnTM(
                        returnModel.getReturnID(),
                        returnModel.getStates(),
                        returnModel.getDate(),
                        returnModel.getNIC(),
                        returnModel.getRegNo(),
                        returnModel.getDamge(),
                        returnModel.getDesc()
                );
                obList.add(tm);
            }
            tblReturn.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error loading returns: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdate(ActionEvent event) {

    }

    @FXML
    void setReturnDate(ActionEvent event) {
        String value = String.valueOf(ReturnDate.getValue());

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





}
