package com.JavaFX_interface.view;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by Вова on 03.08.2015.
 */
public class RegController {

    private Stage reg;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage)    {
        this.reg = dialogStage;
    }

    @FXML
    private void handleCancel() {
        reg.close();
    }


    @FXML
    private void handleOk() {

        reg.close();
    }

}
