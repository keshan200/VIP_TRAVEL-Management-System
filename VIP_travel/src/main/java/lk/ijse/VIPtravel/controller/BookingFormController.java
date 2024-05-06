package lk.ijse.VIPtravel.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import model.TM.CartTM;
import repository.BookingDetailsRepo;
import repository.BookingFormRepo;
import repository.VehicleRepo;
import repository.ReservationRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class BookingFormController {

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colDays;
    @FXML
    private TableColumn<?, ?> colEDate;
    @FXML
    private TableColumn<?, ?> colFullCost;
    @FXML
    private TableColumn<?, ?> colNIC;
    @FXML
    private TableColumn<?, ?> colRegNo;
    @FXML
    private TableColumn<?, ?> colResID;
    @FXML
    private TableColumn<?, ?> colSDate;
    @FXML
    private TableView<CartTM> tblReservation;
    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private DatePicker DateEnd;
    @FXML
    private DatePicker DateStart;

    @FXML
    private ComboBox<String> cmbVehicleName;
    @FXML
    private TextField txtCostPerDay;
    @FXML
    private TextField txtDayCount;
    @FXML
    private TextField txtNIC;
    @FXML
    private TextField txtRegNo;
    @FXML
    private TextField txtReservationID;
    @FXML
    private TextField txtTotalCost;

    @FXML
    private TextField txtCusName;
    ObservableList<CartTM> resList = FXCollections.observableArrayList();



    public void initialize() {
        getAvailableVehicleNames();
        calculateDaysCount();
        getCurrentBookingId();
        calculateNetTotal();
        setCellVFactory();
       loadAllReservations();
    }


    private void setCellVFactory() {
        colResID.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
        colRegNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colSDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("daysCount"));
        colFullCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }


    private void getCurrentBookingId() {
        try {
            String currentId = ReservationRepo.getCurrentReservationId();
            String nextOrderId = generateNextReservationID(currentId);
            txtReservationID.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting current Reservation ID", e);
        }
    }


    public String generateNextReservationID(String currentID) {
        if (currentID != null && currentID.startsWith("R")) {
            try {
                int idNum = Integer.parseInt(currentID.substring(1));
                idNum++;
                return "R" + String.format("%03d", idNum);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid ID format", e);
            }
        }
        return "R001";
    }


    @FXML
    void EndDateOnAction(ActionEvent event) {
        String value = String.valueOf(DateEnd.getValue());
        calculateDaysCount();
    }


    @FXML
    void StartDateOnAction(ActionEvent event) {

        String value = String.valueOf(DateStart.getValue());
        calculateDaysCount();
    }


    private void calculateDaysCount() {
        LocalDate startDate = DateStart.getValue();
        LocalDate endDate = DateEnd.getValue();

        if (startDate != null && endDate != null) {
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
            txtDayCount.setText(String.valueOf(daysBetween));
        }
    }


    @FXML
    void selectNIC(ActionEvent event) {
        String NIC = txtNIC.getText();
        try {
            CustomerModle customerNameByNIC = BookingDetailsRepo.getCustomerNameByNIC(NIC);
            if (customerNameByNIC != null) {
                txtCusName.setText(customerNameByNIC.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private void getAvailableVehicleNames() {
     ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> nameList = VehicleRepo.getAllAvailableVehicles();

            for (String name : nameList) {
                obList.add(name);
            }
            cmbVehicleName.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    private void calculateNetTotal() {
        double netTotal = 0;
        for (CartTM cartItem : resList) {
            netTotal += cartItem.getTotalCost();
        }
        txtTotalCost.setText(String.valueOf(netTotal));
    }

   /* private void calculateNetTotal() {
        String days = txtDayCount.getText();
        String costPerDay = txtCostPerDay.getText();

        if (!days.isEmpty() && !costPerDay.isEmpty()) {
            double daysValue = Double.parseDouble(days);
            double costPerDayValue = Double.parseDouble(costPerDay);
            double fullCost = daysValue * costPerDayValue;
            txtTotalCost.setText(String.valueOf(fullCost));
        }
    }*/

    @FXML
    void vehicleNameOnAction(ActionEvent event) {


        String vehicleName = cmbVehicleName.getValue();
        try {
            List<VehicleModle> vehicleList = VehicleRepo.SearchByVehicleName(vehicleName);
            if (!vehicleList.isEmpty()) {
                // Assuming you want to handle only the first vehicle in the list
                VehicleModle vehicleModle = vehicleList.get(0);
                txtRegNo.setText(vehicleModle.getRegNo());
                txtCostPerDay.setText(String.valueOf(vehicleModle.getCost()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }


    @FXML
    void btnConfirm(ActionEvent event) {

       String resID = txtReservationID.getText();
        String regNo = txtRegNo.getText();
        LocalDate startDate = DateStart.getValue();
        LocalDate endDate = DateEnd.getValue();
        int days = Integer.parseInt(txtDayCount.getText());
        double costPerDay = Double.parseDouble(txtCostPerDay.getText());
        double totalCost = costPerDay * days;

        JFXButton remove = new JFXButton("❌");
        remove.setCursor(Cursor.HAND);
        remove.setStyle("-fx-text-fill: red;");
        remove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
            if (type.orElse(no) == yes) {
                int selectedIndex = tblReservation.getSelectionModel().getSelectedIndex();
                resList.remove(selectedIndex);
                calculateNetTotal();
            }
        });




        if (!resList.isEmpty()) {
            for (int i = 0; i < resList.size(); i++) {
                if (regNo.equals(colRegNo.getCellData(i))) {
                    CartTM tm = resList.get(i);
                    int newDays = tm.getDaysCount() + days;
                    double newTotalCost = tm.getTotalCost() + totalCost;
                    tm.setDaysCount(newDays);
                    tm.setTotalCost(newTotalCost);
                    tblReservation.refresh();
                    return;
                }
            }
        }


        CartTM tm = new CartTM(resID, regNo, startDate, endDate, days, totalCost, remove);
               resList.add(tm);
               tblReservation.setItems(resList);
               calculateNetTotal();

        txtRegNo.setText("");
        txtDayCount.setText("");
        txtCostPerDay.setText("");
        cmbVehicleName.setValue("");
        DateStart.setValue(null);
        DateEnd.setValue(null);


    }



    private void loadAllReservations() {

        List<CartTM> cartList = FXCollections.observableArrayList();

        try {
            List<BookingDetailsModle> allBookingDetails = ReservationRepo.getAllBookingDetails();


            for (BookingDetailsModle bookingDetails : allBookingDetails) {
                String reservationID = bookingDetails.getReservationID();
                String regNo = bookingDetails.getRegNo();
                LocalDate startDate = bookingDetails.getStartDate();
                LocalDate endDate = bookingDetails.getEndDate();
                int daysCount = bookingDetails.getDaysCount();
                double totalCost = bookingDetails.getTotalCost();


                CartTM cartItem = new CartTM(reservationID, regNo, startDate, endDate, daysCount, totalCost, new JFXButton("❌"));
                cartList.add(cartItem);
            }

            tblReservation.setItems(FXCollections.observableArrayList(cartList));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnDelete(ActionEvent event) {

    }


    public  void clearFields(){
        txtReservationID.setText("");
        txtRegNo.setText("");
        txtDayCount.setText("");
        txtCostPerDay.setText("");
        cmbVehicleName.setValue("");
        DateStart.setValue(null);
        DateEnd.setValue(null);
        txtTotalCost.setText("");
        txtCusName.setText("");
        txtNIC.setText("");
    }

    @FXML
    public void btnAdd(ActionEvent event) {

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

    public void btnPayment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewForms/SubForms/PaymentForm.fxml"));
       Parent load = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }
}

