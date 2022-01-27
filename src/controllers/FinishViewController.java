package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import main.Main;

public class FinishViewController {
    private Main main;
    private Stage mainStage;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    public void buttonExit() {
        mainStage.close();
    }
}
