package controllers;

import conn.DataBaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import models.OperationOfTerminal;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class DataToPayOfSeviceController {
    private Main main;
    private Stage mainStage;

    String serviceName = null;
    float summGeneral = 0;

    @FXML
    private TextField TextFieldNumberCode;

    @FXML
    private TextField TextFieldNumberPhone;

    public void broadcastData(String service, float summ) {
        serviceName = service;
        summGeneral = summ;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    public void OnwardsToPay() throws SQLException, IOException {
        if(TextFieldNumberCode.getText() == "" || TextFieldNumberCode.getText() == " "){
            JOptionPane.showMessageDialog(null, "Введіть ваш номер рахунку!");
        }else{
            if(TextFieldNumberPhone.getText() == "" || TextFieldNumberPhone.getText() == " "){
                JOptionPane.showMessageDialog(null, "Введіть ваш номер телефону!");
            }else{
                if(!DataBaseHandler.checkForNumberCode(TextFieldNumberCode.getText())){
                    JOptionPane.showMessageDialog(null, "Цей рахунок відстуній в системі!");
                }else{
                    //dotask
                    main.switchViewServiceReceiver(TextFieldNumberCode.getText(), new OperationOfTerminal(TextFieldNumberPhone.getText(), serviceName, summGeneral));
                }
            }
        }
    }

    @FXML
    public void CloseOperation() throws IOException {
        main.switchViewChange();
    }
}
