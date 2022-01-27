package controllers;

import conn.DataBaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.Main;
import models.OperationOfTerminal;

import javax.swing.*;
import java.awt.*;

public class RemainderViewController {
    private Main main;
    private Stage mainStage;
    @FXML
    Label labelRemainder;
    String phoneNumber;

    double remainder = 0;

    public void paymentData(OperationOfTerminal operation, int general){
        //operation = operationOfTerminal;
        //general = generalSumm;
        remainder = (general - (operation.getSumm() + (operation.getSumm() * 0.025)));
        String format = String.format("%.2f", remainder);
        phoneNumber = operation.phoneNumber;
        //JOptionPane.showMessageDialog(null, operation.toString());

        //labelRemainder.setText(String.valueOf(operation.getSumm()));
        labelRemainder.setText("ВАША РЕШТА - " + format + " ГРН.");
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    public void sendCharityFond() {
        DataBaseHandler.saveOperationOfCharityFond((float) remainder);
        JOptionPane.showMessageDialog(null, "Кошти успішно нараховані в благодійний фонд!");
        mainStage.close();
    }

    @FXML
    public void sendtoPhoneNumber() {
        JOptionPane.showMessageDialog(null, "Сума " + remainder + " грн. успішно нарахована на номер телефону " + phoneNumber);
        mainStage.close();
    }

    @FXML
    public void buttonExit() {
        mainStage.close();
    }
}
