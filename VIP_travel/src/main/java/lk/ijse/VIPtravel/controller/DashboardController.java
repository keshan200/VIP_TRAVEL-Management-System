package lk.ijse.VIPtravel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.DashBoardModle;
import model.TM.DashboardTableTM;
import model.TM.VehicleTM;
import model.VehicleModle;
import repository.DashboardRepo;
import repository.VehicleRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DashboardController {


    @FXML
    private AnchorPane AncMain;

    @FXML
    private Label lblName;
    @FXML
    private Label lblCar;
    @FXML
    private Label lblCustomer;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblEmploye;
    @FXML
    private Label lblSuv;
    @FXML
    private Label lblVan;

    @FXML
    private TableColumn<DashboardTableTM, Double> colPrice;

    @FXML
    private TableColumn<DashboardTableTM,String> colAvailble;

    @FXML
    private TableColumn<DashboardTableTM, Integer> colCount;

    @FXML
    private TableView<DashboardTableTM> tblVehicleAvailable;

    @FXML
    private Label lblBookingCount;







    public void initialize() {
        setDate();
        setCellValues();
        loadAllvehicles();
        try {
            setCustomerCount();
            setEmployeeCount();
            setCarCount();
            setVanCount();
            setSuvCount();
            setReservationCount();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void setCellValues(){
        colAvailble.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));
        colCount.setCellValueFactory(new PropertyValueFactory<>("totalAvailable"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("costPerDay"));

    }



    private void loadAllvehicles() {
        ObservableList<DashboardTableTM> obLst = FXCollections.observableArrayList();

        try {
            List<DashboardTableTM> vehicleList = DashboardRepo.getVehicleStatistics();
            obLst.addAll(vehicleList);
            tblVehicleAvailable.setItems(obLst);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnCustomersOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewForms/SubForms/CustomerForm.fxml"));
        Parent load = loader.load();

        AncMain.getChildren().clear();
        AncMain.getChildren().add(load);


    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        FXMLLoader logOutloader = new FXMLLoader(getClass().getResource("/ViewForms/LoginForm.fxml"));
        Parent logoutload = logOutloader.load();

        Stage logstage = new Stage();
        logstage.setScene(new Scene(logoutload));
        logstage.show();

        Stage window = (Stage)lblName.getScene().getWindow();
        window.close();

    }


    @FXML
    void btnReservationOnAction(ActionEvent event) throws IOException {

        FXMLLoader VehicleLoader = new FXMLLoader(getClass().getResource("/ViewForms/SubForms/ReservationForm.fxml"));
        Parent vehicleLoad = VehicleLoader.load();


        AncMain.getChildren().clear();
        AncMain.getChildren().add(vehicleLoad);


    }


    @FXML
    void btnVehicleOnAction(ActionEvent event) throws IOException {

        FXMLLoader VehicleLoader = new FXMLLoader(getClass().getResource("/ViewForms/SubForms/VehicleForm.fxml"));
        Parent vehicleLoad = VehicleLoader.load();


        AncMain.getChildren().clear();
        AncMain.getChildren().add(vehicleLoad);


    }



        public void setCustomerCount() throws SQLException {
            try {
                DashboardRepo dashboardRepo = new DashboardRepo();
                DashBoardModle dashboardModel = new DashBoardModle();
                int customerCount = dashboardRepo.getCustomerCount(dashboardModel);
                lblCustomer.setText(String.valueOf(customerCount));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


       public void setEmployeeCount() throws SQLException {
        try {
            DashboardRepo dashboardRepo = new DashboardRepo();
            DashBoardModle dashBoardModle = new DashBoardModle();
            int EmployeeCount = dashboardRepo.getEmployeeCount(dashBoardModle);
            lblEmploye.setText(String.valueOf(EmployeeCount));

        }catch (SQLException i){
            i.printStackTrace();
        }

        }

        public  void setCarCount(){
        try {
            DashboardRepo dashboardRepo = new DashboardRepo();
            DashBoardModle dashBoardModle = new DashBoardModle();
            int CarCount = dashboardRepo.getcarCount(dashBoardModle);
            lblCar.setText(String.valueOf(CarCount));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }

    public void setVanCount() {

        try {
            DashboardRepo dashboardRepo = new DashboardRepo();
            DashBoardModle dashBoardModle = new DashBoardModle();
            int VanCount= dashboardRepo.getvanCount(dashBoardModle);

            lblVan.setText(String.valueOf(VanCount));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void setSuvCount(){

        try {
            DashboardRepo dashboardRepo = new DashboardRepo();
            DashBoardModle dashBoardModle = new DashBoardModle();
            int SuvCount = dashboardRepo.getsuvCount(dashBoardModle);

            lblSuv.setText(String.valueOf(SuvCount));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public  void setReservationCount(){

        try {
            DashboardRepo dashboardRepo = new DashboardRepo();
            DashBoardModle dashBoardModle = new DashBoardModle();
            int bookingCount = dashboardRepo.getbookingCount(String.valueOf(dashBoardModle));

            lblBookingCount.setText(String.valueOf(bookingCount));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewForms/SubForms/DashBoardMiddleForm.fxml"));
        Parent load = loader.load();

        DashboardMiddleFormController controller = loader.getController();

        AncMain.getChildren().clear();
        AncMain.getChildren().add(load);


    }



    public void setUserName(String name) {
        lblName.setText("Welcome"+" "+ name);

    }

    public  void setDate(){
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }


    @FXML
    void btnReturn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewForms/SubForms/ReturnForm.fxml"));
        Parent load = loader.load();

        AncMain.getChildren().clear();
        AncMain.getChildren().add(load);

    }

     }


