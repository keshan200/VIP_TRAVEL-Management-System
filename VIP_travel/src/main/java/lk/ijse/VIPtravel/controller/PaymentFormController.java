package lk.ijse.VIPtravel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.VIPtravel.DBconnection.DBconnection;
import model.DashBoardModle;
import model.PaymentModle;
import model.TM.PaymentTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import repository.DashboardRepo;
import repository.PaymentRepo;
import repository.ReservationRepo;
import repository.VehicleRepo;

import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentFormController {

    @FXML
    private TableColumn<?, ?> colBalanc;

    @FXML
    private TableColumn<?, ?> colAdvanced;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPayID;

    @FXML
    private TableColumn<?, ?> colPayMethod;

    @FXML
    private TableColumn<?, ?> colPayType;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableView<PaymentTM> tblPayment;

    @FXML
    private ComboBox<String> cmbPaymentMethod;

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private TextField paymentID;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtReservationID;

    @FXML
    private Label lblDate;

    @FXML
    private TextField txtBalancce;

    @FXML
    private TextField txtAdvaced;
    @FXML
    private Label lblPending;

    @FXML
    private Label lblComplete;



    public void initialize(){
        setCmbPaymentMethod();
        setCmbStatus();
        setCmbType();
        getCurrentID();
        getCurrentBookingId();
        setDate();
        loadAllPayments();
        showSelectedPaymentDetails();
        setPaymentCellValues();
        setCompletePayment();
        setPendingPayment();
        clear();
    }






    public  void clear(){
        txtAmount.setText("");
        txtBalancce.setText("");
        txtAdvaced.setText("");
        cmbType.setValue("");
        cmbStatus.setValue("");
        cmbPaymentMethod.setValue("");


    }



    public void setCmbType(){

        ObservableList<String> list = FXCollections.observableArrayList("Full","Advanced","Half");
        cmbType.setItems(list);
    }


    public  void  setCmbStatus(){

        ObservableList<String> list = FXCollections.observableArrayList("Completed", "Pending");
        cmbStatus.setItems(list);

    }


    public  void setCmbPaymentMethod(){

        ObservableList<String> list = FXCollections.observableArrayList("Cash", "Credit Card","Debit Card");
        cmbPaymentMethod.setItems(list);

    }

    public String generateNextVehicleID(String curentID){
        if (curentID != null) {
            String[] split = curentID.split("#P");
            int idNum = Integer.parseInt(split[1]);
            return "#P" + String.format("%02d", ++idNum);
        }
        return "#P01";
    }

    public  void getCurrentID(){
        try {
            String curentID = PaymentRepo.getCurrentId();
            String nextPayID = generateNextVehicleID(curentID);
           paymentID.setText(nextPayID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private void getCurrentBookingId() {
        try {
            String currentId = ReservationRepo.getCurrentReservationId();

            txtReservationID.setText(currentId);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting current Reservation ID", e);
        }
    }

    public  void setDate(){
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }


    public void btnSavePay(ActionEvent event) {

        String paymentid = paymentID.getText();
        String status = cmbStatus.getValue();
        String type = cmbType.getValue();
        double fullPayment = Double.parseDouble(txtAmount.getText());
        String reservationID = txtReservationID.getText();
        LocalDate paymentDate = LocalDate.parse(lblDate.getText());
        String PaymentMethod = cmbPaymentMethod.getValue();
        double AdvancedPay = Double.parseDouble(txtAdvaced.getText());
        double balancedPay = Double.parseDouble(txtBalancce.getText());


        PaymentModle paymentModel = new PaymentModle(paymentid, status, type, fullPayment, reservationID, paymentDate,PaymentMethod,AdvancedPay,balancedPay);

        try {
            boolean isSaved = PaymentRepo.savePayment(paymentModel);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment saved successfully!").show();
              loadAllPayments();
              refreshCounts();
              clear();


            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error saving payment: " + e.getMessage()).show();
        }
    }



    private void showSelectedPaymentDetails() {
        PaymentTM selectedPayment = tblPayment.getSelectionModel().getSelectedItem();
        tblPayment.setOnMouseClicked(event -> showSelectedPaymentDetails());
        if (selectedPayment != null) {
            paymentID.setText(selectedPayment.getPaymentID());
           cmbStatus.setValue(selectedPayment.getStatus());
            cmbType.setValue(selectedPayment.getType());
           txtAmount.setText(String.valueOf(selectedPayment.getFullPayment()));
            txtReservationID.setText(selectedPayment.getReservationID());
            lblDate.setText(String.valueOf(selectedPayment.getPaydate()));
            cmbPaymentMethod.setValue(selectedPayment.getPaymentMethod());
            txtAdvaced.setText(String.valueOf(selectedPayment.getAdvancedPay()));
            txtBalancce.setText(String.valueOf(selectedPayment.getBalancedPay()));

        }
    }

    private void setPaymentCellValues() {
        colPayID.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colPayType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPayMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("fullPayment"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Paydate"));
        colAdvanced.setCellValueFactory(new PropertyValueFactory<>("advancedPay"));
        colBalanc.setCellValueFactory(new PropertyValueFactory<>("balancedPay"));
    }

    private void loadAllPayments() {
        ObservableList<PaymentTM> paymentList = FXCollections.observableArrayList();

        try {
            List<PaymentModle> paymentModels = PaymentRepo.getAllPayments();

            for (PaymentModle paymentModel : paymentModels) {
                PaymentTM paymentTM = new PaymentTM(
                        paymentModel.getPaymentID(),
                        paymentModel.getStatus(),
                        paymentModel.getType(),
                        paymentModel.getFullPayment(),
                        paymentModel.getReservationID(),
                        paymentModel.getPaydate(),
                        paymentModel.getPaymentMethod(),
                        paymentModel.getAdvancedPay(),
                        paymentModel.getBalancedPay()
                );

                paymentList.add(paymentTM);
            }

            tblPayment.setItems(paymentList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    @FXML
    void txtAdavacedToCalBalanced(ActionEvent event) {

        double fullAmount = Double.parseDouble(txtAmount.getText());
        double advanced = Double.parseDouble(txtAdvaced.getText());

        double balaced = fullAmount - advanced;
        txtBalancce.setText(String.valueOf(balaced));




    }

   public void NicOnAction(ActionEvent event) {
       String NIC = txtNIC.getText().trim();

       if (!NIC.isEmpty()) {
           try {
               List<PaymentModle> paymentList = PaymentRepo.getPaymentsByNIC(NIC);
               if (!paymentList.isEmpty()) {
                   loadPayments(paymentList);
               } else {
                   tblPayment.getItems().clear();
                   new Alert(Alert.AlertType.INFORMATION, "No payments found for the provided NIC.").show();
               }
           } catch (SQLException e) {
               new Alert(Alert.AlertType.ERROR, "Error occurred while fetching payments: " + e.getMessage()).show();
           }
       } else {
           new Alert(Alert.AlertType.WARNING, "Please enter a NIC.").show();
       }

      }



    private void loadPayments(List<PaymentModle> paymentList) {
       tblPayment.getItems().clear();
        for (PaymentModle paymentModel : paymentList) {
            PaymentTM paymentTM = new PaymentTM(
                    paymentModel.getPaymentID(),
                    paymentModel.getStatus(),
                    paymentModel.getType(),
                    paymentModel.getFullPayment(),
                    paymentModel.getReservationID(),
                    paymentModel.getPaydate(),
                    paymentModel.getPaymentMethod(),
                    paymentModel.getAdvancedPay(),
                    paymentModel.getBalancedPay()
            );
            tblPayment.getItems().add(paymentTM);
        }
    }


  @FXML
    public void btnUpdate(ActionEvent event) {

        String paymentId = paymentID.getText();
        Double fullpayment = Double.valueOf(txtAmount.getText());
        String type = cmbType.getValue();
        String status = cmbStatus.getValue();

        try {
            boolean isSaved = PaymentRepo.update(paymentId,fullpayment,type,status);
            if (isSaved) {
                 new Alert(Alert.AlertType.CONFIRMATION,"Payment updated successfully!").show();
                 loadAllPayments();
                 refreshCounts();
            }else{
                new Alert(Alert.AlertType.ERROR,"Failed to update payment!").show();
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while updating payment: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
}



    public void setCompletePayment()  {

        try {
            PaymentRepo paymentRepo = new PaymentRepo();
            PaymentModle paymentModle = new PaymentModle();

            int completePayment = paymentRepo.getCompletePayment(paymentModle);
            lblComplete.setText(String.valueOf(completePayment));


        }catch (SQLException i){
            i.printStackTrace();
        }

    }





    public void setPendingPayment()  {
        try {
            PaymentRepo paymentRepo = new PaymentRepo();
            PaymentModle paymentModle = new PaymentModle();

            int PendingPayment = paymentRepo.getPendingPayment(paymentModle);
            lblPending.setText(String.valueOf(PendingPayment));


        }catch (SQLException i){
            i.printStackTrace();
        }

    }

    private void refreshCounts() {
        try {
            PaymentRepo paymentRepo = new PaymentRepo();
            PaymentModle paymentModle = new PaymentModle();

            int completePayment = paymentRepo.getCompletePayment(paymentModle);
            lblComplete.setText(String.valueOf(completePayment));

            int pendingPayment = paymentRepo.getPendingPayment(paymentModle);
            lblPending.setText(String.valueOf(pendingPayment));

        } catch (SQLException i) {
            i.printStackTrace();
        }
    }


    @FXML
    void btnPrintBill(ActionEvent event) throws JRException, SQLException {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Report/Payment.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        String resID = txtReservationID.getText();
        data.put("ResID", resID);
        String nic = txtNIC.getText();  data.put("endDate", nic);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DBconnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);

    }


}
