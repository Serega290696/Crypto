package JavaFX_interface.view;

import JavaFX_interface.MainAppFx;
import data_base.data_access_objects.UsersDAO;
import data_base.entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by Вова on 04.08.2015.
 */
public class EnterController {

    private Stage enter;
    private UsersDAO usersDao = new UsersDAO();

    @FXML
    private TextField log1;

    @FXML
    private TextField pass2;

    @FXML
    private Label logLabel;

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


    @FXML
    private void handleOk() throws Exception {

        if(log1.getText().equals("") || pass2.getText().equals("")) {
            System.err.println("Something go wrong. . .");
            logLabel.setText(log1.getText().equals("") ? "Field login is empty":"Field password is empty");
            logLabel.setTextFill(Color.RED);
            return;
        }
        User user = new User();
        user.setLogin(log1.getText());
        user.setPasswordToMD5(pass2.getText());
        User tmpUser;

        if((tmpUser = usersDao.get(user)) != null) {
            MenuController.setCurrentUser(tmpUser);
            enter.close();
            MainAppFx.CloseMethod();
            MainAppFx.initMenu();
//            new MenuController().initMenu();
        }
    }


}
