package main;

import conn.DataBaseHandler;
import controllers.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.OperationOfTerminal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {
    private Stage mainStage;
    private BorderPane mainPane;


    @FXML
    public void initialize() {}

    @Override
    public void start(Stage stage) throws Exception{
        mainStage = stage;
        mainStage.setTitle(" ");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/main_view.fxml"));
        mainPane = loader.load();
        Scene scene = new Scene(mainPane);
        mainStage.setScene(scene);

        MainViewController controller = loader.getController();
        controller.setMainStage(mainStage);
        controller.setMain(this);

        switchViewChange();

        mainStage.show();
    }

    public void switchViewChange() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/change_view.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        ChangeViewController controller = loader.getController();
        controller.setMainStage(mainStage);
        controller.setMain(this);
    }

    public void switchViewTransferMoney() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/transfer_money_view.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        TransferMoneyViewController controller = loader.getController();
        controller.setMainStage(mainStage);
        controller.setMain(this);
    }

    public void switchViewMoneyReceiver(OperationOfTerminal operationOfTerminal) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/money_receiver_view.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        MoneyReceiverViewController controller = loader.getController();
        controller.processPayment(operationOfTerminal);
        controller.setMainStage(mainStage);
        controller.setMain(this);
    }

    public void switchViewFinishFirst() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/finish_view.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        FinishViewController controller = loader.getController();
        controller.setMainStage(mainStage);
        controller.setMain(this);
    }

    public void switchViewFinishSecond(OperationOfTerminal operationOfTerminal, int generalSumm) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/remainder_view.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        RemainderViewController controller = loader.getController();
        controller.paymentData(operationOfTerminal, generalSumm);
        controller.setMainStage(mainStage);
        controller.setMain(this);
    }

    public void switchViewServiceMoney() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/service_list_view.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        ServiceListViewController controller = loader.getController();
        controller.setMainStage(mainStage);
        controller.setMain(this);
    }

    public void switchViewDataToPayOfService(String service, float summ) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/data_to_pay_service.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        DataToPayOfSeviceController controller = loader.getController();
        controller.broadcastData(service, summ);
        controller.setMainStage(mainStage);
        controller.setMain(this);
    }

    public void switchViewServiceReceiver(String numberCode, OperationOfTerminal operationOfTerminal) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/service_receiver_view.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        ServiceReceiverViewController controller = loader.getController();
        controller.processPaymentForService(numberCode, operationOfTerminal);
        controller.setMainStage(mainStage);
        controller.setMain(this);
    }

    public static void main(String[] args) throws SQLException {
        //DataBaseHandler.saveOperation("431282587", 60);
        launch(args);
    }
}
