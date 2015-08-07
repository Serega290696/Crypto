package crypto.view;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

/**
 * Created by Вова on 07.08.2015.
 */
public class MenuController {

    private Stage menu;

    @FXML
    private Tab exit;
    
    @FXML
    private void initialize() {
    }

    @FXML
    private void exitMethod() {
        menu.close();
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.menu = dialogStage;
    }
}
