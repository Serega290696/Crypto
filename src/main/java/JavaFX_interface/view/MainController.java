package JavaFX_interface.view;

import JavaFX_interface.MainAppFx;
import javafx.fxml.FXML;


/**
 * Created by Вова on 04.08.2015.
 */
public class MainController {

    @FXML
    public void EnterMethod() {
        MainAppFx.initShowEnter();
    }

    @FXML
    public void RegMethod() {
        MainAppFx.initShowReg();
    }


    public void methodA() throws Exception {
        MainAppFx.CloseMethod();
       // MainAppFx.initMenu();
    }
}
