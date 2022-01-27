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

public class TransferMoneyViewController {
    private Main main;
    private Stage mainStage;

    @FXML
    private TextField TextFieldPhoneNumber;

    @FXML
    private TextField TextFieldNumberCoder;

    @FXML
    private TextField TextFieldSumm;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    public void makeTransaction() throws SQLException, IOException {
        String phoneNumber = null;
        String numberCodeOfReceiver = null;
        float summ = 0;

        if(TextFieldPhoneNumber.getText() == " " || TextFieldPhoneNumber.getText() == ""){
            JOptionPane.showMessageDialog(null, "Вкажіть ваш номер телефону!");
        }else {
            phoneNumber = TextFieldPhoneNumber.getText();
        }
        if(TextFieldNumberCoder.getText() == " " || TextFieldNumberCoder.getText() == ""){
                JOptionPane.showMessageDialog(null, "Вкажіть номер рахунку особи, яка отримає кошти!");
        }else {
            numberCodeOfReceiver = TextFieldNumberCoder.getText();
        }
        if(TextFieldSumm.getText() == " " || TextFieldSumm.getText() == ""){
            JOptionPane.showMessageDialog(null, "Вкажіть суму переказу!");
        }else {
            if (Float.parseFloat(TextFieldSumm.getText()) <= 0){
                JOptionPane.showMessageDialog(null, "Сума не може бути 0!");
            }else{
                summ = Float.parseFloat(TextFieldSumm.getText());
            }
        }

        if(!DataBaseHandler.checkForNumberCode(numberCodeOfReceiver)){
            JOptionPane.showMessageDialog(null, "Такий рахунок відсутній!");
        }else {
            main.switchViewMoneyReceiver(new OperationOfTerminal(phoneNumber, numberCodeOfReceiver, summ));
            System.out.println(phoneNumber + " " + numberCodeOfReceiver + " " + summ);
        }

    }

    @FXML
    public void buttonExit() throws IOException {
        main.switchViewChange();
    }
}
