package JavaFX_interface.view;

import data_base.data_access_objects.UsersDAO;
import data_base.entities.DBConstant;
import data_base.entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Вова on 03.08.2015.
 */
public class RegController {

    private Stage reg;
    private UsersDAO usersDao = new UsersDAO();

    @FXML
    private TextField log1;

    @FXML
    private TextField name2;

    @FXML
    private TextField mail3;

    @FXML
    private TextField pass4;

    @FXML
    private TextField pass5;

    @FXML
    private Label regLabel;

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
        regLabel.setTextFill(Color.RED);
        if(
                !pass4.getText().equals(pass5.getText())
                ) {
            regLabel.setText("Password is not equal");
            return;
        }
        if (!isValidateMail(mail3.getText())){
            regLabel.setText("Mail is not valid");
            return;
        }
        if (!isValidateLogin(log1.getText())){
            regLabel.setText("Login must have over 4 symbols");
            return;
        }
        User newUser = new User();
        newUser.setLogin(log1.getText());
        newUser.setName(name2.getText());
        newUser.setMail(mail3.getText());
        newUser.setPasswordToMD5(pass4.getText());
        newUser.setLastVisitDate(new Date());
        newUser.setRegistrationDate(new Date());
        newUser.setMaxNoteLength(DBConstant.maxNoteLength);
        newUser.setMaxNotes(DBConstant.maxNotes);
        usersDao.add(newUser);
        System.out.println("Successful.\n\t" + newUser);




        reg.close();
    }

    private boolean isValidateLogin(String login) {
        if(login == null)
            return false;
        if(login.length() < 4)
            return false;
        return true;
    }

    private boolean isValidateMail(String text) {
        if(text == null)
            return false;
        if(text.equals(""))
            return false;

        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(text);
        return m.matches();
    }

}
