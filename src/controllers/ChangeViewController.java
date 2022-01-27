package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

public class ChangeViewController {
    private Main main;
    private Stage mainStage;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    public void buttonTransferMoney() throws IOException {
        main.switchViewTransferMoney();
    }

    @FXML
    public void buttonPayByService() throws IOException {
        main.switchViewServiceMoney();
    }

    @FXML
    public void buttonExit() {
        mainStage.close();
    }
}
