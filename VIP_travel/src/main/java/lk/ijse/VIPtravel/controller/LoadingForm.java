package lk.ijse.VIPtravel.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.VIPtravel.service.LoadingTask;

import java.io.IOException;

public class LoadingForm {

    @FXML
    private Label lblprogras;

    @FXML
    private Rectangle reqmain;

    @FXML
    private Rectangle reqsub;


    public void initialize(){

        LoadingTask task = new LoadingTask();
        task.progressProperty().addListener((ob,oldValue, newValue) ->{
           // System.out.println(newValue.doubleValue()*100);
            String format = String.format("%.0f", newValue.doubleValue() * 100);
            System.out.println(format);
            lblprogras.setText(format+ "%");
            reqsub.setWidth(reqmain.getWidth()*newValue.doubleValue());

            if (newValue.doubleValue()==1.0) {
                Parent load;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewForms/LoginForm.fxml"));
                    load = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage lo = new Stage();
                lo.setScene(new Scene(load));
                lo.setTitle("Login Form");
                lo.show();


                Window window = lblprogras.getScene().getWindow();
                Stage stage = (Stage) window;
                stage.close();

            }
        });
        new Thread(task).start();
    }
}
