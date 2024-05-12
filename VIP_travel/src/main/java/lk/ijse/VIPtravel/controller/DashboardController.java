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
    private Label lblDate;








    public void initialize() {
        setDate();





           setMIDDLE();

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





    @FXML
    void btnDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewForms/SubForms/DashBoardMiddleForm.fxml"));
        Parent load = loader.load();

        DashboardMiddleFormController controller = loader.getController();

        AncMain.getChildren().clear();
        AncMain.getChildren().add(load);


    }

public  void setMIDDLE() {

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewForms/SubForms/DashBoardMiddleForm.fxml"));
        Parent load =loader.load();

        AncMain.getChildren().clear();
        AncMain.getChildren().add(load);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

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

    public void btnSettingOnAction(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewForms/SubForms/EmployeeDdetailForm.fxml"));
        Parent load = loader.load();

        AncMain.getChildren().clear();
        AncMain.getChildren().add(load);
    }
}


