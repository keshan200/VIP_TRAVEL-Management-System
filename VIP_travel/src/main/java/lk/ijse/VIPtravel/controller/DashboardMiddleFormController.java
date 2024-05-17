package lk.ijse.VIPtravel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DashBoardModle;
import model.TM.DashboardTableTM;
import repository.DashboardRepo;

import java.sql.SQLException;
import java.util.List;

public class DashboardMiddleFormController {

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

    @FXML
    private Label lblCar;

    @FXML
    private Label lblCustomer;

    @FXML
    private Label lblEmploye;

    @FXML
    private Label lblSuv;

    @FXML
    private Label lblVan;

    public void initialize(){

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

}
