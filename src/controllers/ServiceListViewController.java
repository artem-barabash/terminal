package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import main.Main;

import javax.swing.*;
import java.io.IOException;
import java.sql.Timestamp;

public class ServiceListViewController {
    private Main main;
    private Stage mainStage;

    @FXML
    private RadioButton payStudyBtn;
    @FXML
    private RadioButton payGasBtn;
    @FXML
    private RadioButton payWaterBtn;
    @FXML
    private RadioButton payElectricBtn;
    @FXML
    private RadioButton payInternetBtn;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    public void payByService() throws IOException {

        if(payStudyBtn.isSelected() == true){
            payInSystem("За навчання", 5000);
        }else if(payGasBtn.isSelected() == true){
            payInSystem("За опалення", 3000);
        }else if(payWaterBtn.isSelected() == true){
            payInSystem("За водопостачання", 500);
        }else if(payElectricBtn.isSelected() == true){
            payInSystem("За електроенергію", 300);
        }else if(payInternetBtn.isSelected() == true){
            payInSystem("За інтернет", 200);
        }else{
            JOptionPane.showMessageDialog(null, "Оберіть послугу за яку будете платити!");
        }
    }

    private void payInSystem(String service, float summ) throws IOException {
        main.switchViewDataToPayOfService(service, summ);
    }

    @FXML
    public void closeOperation() throws IOException {
        main.switchViewChange();
    }
}
