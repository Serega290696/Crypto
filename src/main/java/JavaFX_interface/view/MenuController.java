package JavaFX_interface.view;

import JavaFX_interface.model.RandomInteger;
import data_base.data_access_objects.NotesDAO;
import data_base.data_access_objects.UsersDAO;
import data_base.entities.Note;
import data_base.entities.User;
import encryption.AesEncrypter;
import encryption.DesEncrypter;
import encryption.hashing.HashingMD5;
import file_worker.FileWorker;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Вова on 07.08.2015.
 */
public class MenuController implements Initializable {

    ObservableList<Note> data = FXCollections.observableArrayList();
    private static User currentUser;
    FileWorker fileWorker = new FileWorker();

    @FXML
    private Stage menu;

    @FXML
    private Tab exit;

    @FXML
    private Button fileChooser;

    @FXML
    private ComboBox<String> comboBox1;

    @FXML
    private ComboBox<String> comboBox2;

    @FXML
    private TextArea eTextIn;

    @FXML
    private TextArea eTextOut;

    @FXML
    private TextArea dTextIn;

    @FXML
    private TextArea dTextOut;

    @FXML
    private TextField nameFile;

    @FXML
    private TextField key;

    @FXML
    private TextField dkey;

    @FXML
    private Label label;

    @FXML
    private Label label1;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Button enterE;

    @FXML
    private ComboBox<String> comboBox11;

    @FXML
    private ComboBox<String> comboBox3;

    @FXML
    private Button fileChooser1;

    @FXML
    private TextField log31;

    @FXML
    private TextField name32;

    @FXML
    private TextField mail33;

    @FXML
    private TextField pass34;

    @FXML
    private TextField pass35;

    @FXML
    private TextField pass36;

    @FXML
    private Label helloUser;

    @FXML
    private Label dateReg;

    @FXML
    private Label dateEnter;

    @FXML
    private Label alert;


    @FXML
    private Label fLimit;

    @FXML
    private TableColumn<Note, String> nameColumn;

    @FXML
    private TableColumn<Note, String> encColumn;

    @FXML
    private TableView notesTable;

    @FXML
    private Button deleteNotes;

    @FXML
    private TextField dName;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        enterE.setDisable(false);
        comboBox1.setItems(chooser1);
        comboBox11.setItems(chooser1);

        comboBox1.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(comboBox1.getValue());

                if (comboBox1.getValue().equals("Text")) {
                    System.out.println("Well done");
                    fileChooser.setDisable(true);
                } else {
                    fileChooser.setDisable(false);
                }
            }
        });
        comboBox11.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(comboBox11.getValue());

                if (comboBox11.getValue().equals("Text")) {
                    System.out.println("Well done");
                    fileChooser1.setDisable(true);
                } else {
                    fileChooser1.setDisable(false);
                }
            }
        });

        comboBox2.setItems(chooser2);
        comboBox2.setValue("Encrypt Method");
        comboBox2.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(comboBox2.getValue());
            }
        });
        comboBox3.setItems(chooser2);
        comboBox3.setValue("Decrypt Method");
        comboBox3.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(comboBox3.getValue());
            }
        });

        initMenu();
    }

    @FXML
    public void randomKey() {
        if (checkBox.isSelected()) {
            label.setText("Save this key");
            RandomInteger randomInteger = new RandomInteger();
            String a = randomInteger.main();

            System.out.println(a);

            key.setText(a);
        } else {
            label.setText("");
            int n = key.getLength();
            key.deleteText(0, n);
        }
    }

    @FXML
    private void exitMethod() {
//        MainAppFx.launch();
        menu.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.menu = dialogStage;
    }

    ObservableList<String> chooser1 = FXCollections.observableArrayList(
            "Text",
            "File"

    );
    ObservableList<String> chooser2 = FXCollections.observableArrayList(
            "DES",
            "AES",
            "RSA"
    );

    @FXML
    public void setEMethodChooser() {

        switch (comboBox2.getValue()) {
            case "DES":
                DesEncrypter desEncrypter = new DesEncrypter();
                String key1 = desEncrypter.setKey(key.getText());
                System.out.println(key1);
                String enc1 = desEncrypter.encrypt(eTextIn.getText(), key1);
                eTextOut.setText(enc1);

                label.setText("Need enter to update result");
                System.out.println("well done");
                break;
            case "AES":
                AesEncrypter aesEncrypter = new AesEncrypter();
                String key2 = aesEncrypter.setKey(key.getText());
                System.out.println(key2);
                String enc2 = aesEncrypter.encrypt(eTextIn.getText(), key2);
                eTextOut.setText(enc2);

                label.setText("Need enter to update result");
                System.out.println("well done");
                break;
            case "RSA":
                label.setText("This method will be in next update");
                System.out.println("well done");
                break;
        }
    }

    @FXML
    public void setDMethodChooser() {

        switch (comboBox3.getValue()) {
            case "DES":
                DesEncrypter desEncrypter = new DesEncrypter();
                String key1 = desEncrypter.setKey(dkey.getText());
                System.out.println(key1);
                String enc1 = desEncrypter.decrypt(dTextIn.getText(), key1);
                dTextOut.setText(enc1);

                label1.setText("Need enter to update result");
                System.out.println("well done");
                break;
            case "AES":
                AesEncrypter aesEncrypter = new AesEncrypter();
                String key2 = aesEncrypter.setKey(dkey.getText());
                System.out.println(key2);
                String enc2 = aesEncrypter.decrypt(dTextIn.getText(), key2);
                dTextOut.setText(enc2);

                label1.setText("Need enter to update result");
                System.out.println("well done");
                break;
            case "RSA":
                label1.setText("This method will be in next update");
                System.out.println("well done");
                break;
        }
    }

    @FXML
    private void enterEResult() throws Exception {
        setEMethodChooser();
        System.out.println();
        System.out.println("key" + ": " + key.getText());
        System.out.println("nameFile" + ": " + nameFile.getText());
        System.out.println("Text" + ": " + eTextIn.getText());
        label.setText("Would you like save result?");
        label.setTextFill(Color.GREEN);
        saveInNotes();

    }

    @FXML
    private void enterDResult() throws Exception {
        setDMethodChooser();
        System.out.println();
        System.out.println("key" + ": " + dkey.getText());
        System.out.println("nameFile" + ": " + nameFile.getText());
        System.out.println("Text" + ": " + dTextIn.getText());
        label1.setText("Would you like save result?");
        label1.setTextFill(Color.GREEN);
    }

    private void saveInNotes() {
        NotesDAO notesDAO = new NotesDAO();
        Note newNote = new Note();
        newNote.setIdUser(currentUser.getId());
        newNote.setTitle(nameFile.getText());
        newNote.setValue(eTextOut.getText());
        notesDAO.add(newNote);
    }



    @FXML
    private void saveFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Document");
        fileChooser.titleProperty();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(menu);
        if (file != null) {
            saveFile1(eTextOut.getText(), file);
        }
    }


    private void saveFile1(String content, File file) {
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public void chooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Document");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(menu);

        System.out.println(file);

        String textFromFile = fileWorker.read(String.valueOf(file));
        eTextIn.setText(textFromFile);
    }


    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        MenuController.currentUser = currentUser;
    }

    private void initMenu() {
        System.out.println("INIT MENU begin : ");
        if (currentUser.getName() != null)
            if (currentUser.getName().length() > 0)
                helloUser.setText("Hello, " + currentUser.getName() + "!");
        log31.setPromptText(currentUser.getLogin());
        name32.setPromptText(currentUser.getName());
        mail33.setPromptText(currentUser.getMail());
        dateReg.setText("Registration date: " + String.valueOf(currentUser.getRegistrationDate()));
        dateEnter.setText("Last visit date: " + String.valueOf(currentUser.getLastVisitDate()));
        NotesDAO notesDao = new NotesDAO();
        fLimit.setText("Notes: "
                + notesDao.getAll().stream().filter((n) -> n.getIdUser() == currentUser.getId()).count()
                + "/"
                + currentUser.getMaxNotes());
        System.out.println("INIT MENU end");

        initNotesTable();
    }

    public void initNotesTable() {
        NotesDAO notesDao = new NotesDAO();
        data.addAll(notesDao.getAll().stream().filter(n -> n.getIdUser() == currentUser.getId()).collect(Collectors.toList()));

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        encColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        notesTable.setItems(data);
    }

    public void saveChanges() {
        UsersDAO usersDao = new UsersDAO();
//        User changedUser = currentUser;
        if (!log31.getText().equals(""))
            currentUser.setLogin(log31.getText());
        if (!name32.getText().equals(""))
            currentUser.setName(name32.getText());
        if (!mail33.getText().equals(""))
            currentUser.setMail(mail33.getText());
        HashingMD5 hashing = new HashingMD5();
        if (hashing.toHashCode(pass34.getText()).equals(currentUser.getPassword()) &&
                pass35.getText().equals(pass36.getText())) {
            currentUser.setPasswordToMD5(pass35.getText());
            alert.setTextFill(Color.GREEN);
            alert.setText("Successful!");
        } else if (!pass35.getText().equals("")) {
            passAlert();
        }
        usersDao.update(currentUser);
    }

    private void passAlert() {
        alert.setTextFill(Color.RED);
        if (!(pass34.getText()).equals(currentUser.getPassword()))
            alert.setText("Old password is wrong!");
        if (!pass35.getText().equals(pass36.getText()))
            alert.setText("New passwords aren't equals!");
    }

    public void deleteNote() {
        NotesDAO notesDAO = new NotesDAO();
//        resetNotesTable();

//        int selectedIndex = ;
        int selectedIndex = notesTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex > 0) {
            notesTable.getItems().remove(selectedIndex);
            notesDAO.remove((Note) notesTable.getSelectionModel().getSelectedItem());
            System.out.println("D: " + selectedIndex);
        }

    }

    public void decryptText() {
        dTextIn.setText(((Note) notesTable.getSelectionModel().getSelectedItem()).getTitle());
        dName.setText(((Note) notesTable.getSelectionModel().getSelectedItem()).getValue());
    }

    public void cancel() {
        log31.clear();
        name32.clear();
        mail33.clear();
        pass34.clear();
        pass35.clear();
        pass36.clear();

    }

    public void refreshAction() {
        initNotesTable();
    }

}


