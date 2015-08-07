package crypto.view;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by Вова on 04.08.2015.
 */
public class EnterController {

    private Stage enter;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.enter = dialogStage;
    }

    @FXML
    private void handleCancel() {
        enter.close();
    }

}
