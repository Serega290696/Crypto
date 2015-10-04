package JavaFX_interface.view;

import JavaFX_interface.MainAppFx;
import javafx.fxml.FXML;


/**
 * Created by Вова.
 */
public class MainController {

    @FXML
    public void login() {
        MainAppFx.initShowEnter();
    }

    @FXML
    public void registration() {
        MainAppFx.initShowReg();
    }

    public void methodA() throws Exception {
        // !!!!  !!!!  !!!!!!! DELETE IN INIT METHOD IN MenuController !!!!!!

//        MainAppFx.CloseMethod();
        MainAppFx.initMenu();
    }
}
