package crypto.view;

import crypto.MainApp;
import javafx.fxml.FXML;


/**
 * Created by Вова on 04.08.2015.
 */
public class MainController  {

       @FXML
        public void EnterMethod(){
            MainApp.initShowEnter();
        }

        @FXML
        public void RegMethod(){
            MainApp.initShowReg();
    }


    public void methodA() throws Exception{
        MainApp.CloseMethod();
        MainApp.initMenu();
    }
}
