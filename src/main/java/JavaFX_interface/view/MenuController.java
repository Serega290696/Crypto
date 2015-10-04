package JavaFX_interface.view;

import JavaFX_interface.MainAppFx;
import JavaFX_interface.model.RandomInteger;
import data_base.data_access_objects.NotesDAO;
import data_base.data_access_objects.UsersDAO;
import data_base.entities.Note;
import data_base.entities.User;
import encryption.AesEncrypter;
import encryption.DesEncrypter;
import encryption.ICryptographer;
import encryption.hashing.HashingMD5;
import exceptions.ExceptionInputData;
import file_worker.FileWorker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by Вова.
 */
public class MenuController implements Initializable {

    private ObservableList<Note> data = FXCollections.observableArrayList();
    private static User currentUser = new User("Serega", "aaaaaa");
    private FileWorker fileWorker = new FileWorker();
    private ICryptographer crypto;
    private static final NotesDAO notesDAO = new NotesDAO();
    private static final UsersDAO usersDao = new UsersDAO();
    private static SingleSelectionModel<Tab> selectionModel;


    @FXML
    private Stage menu;
    @FXML
    private Tab encryptTab;
    @FXML
    private Tab decryptTab;
    @FXML
    private Tab profileTab;
    @FXML
    private Tab logOut;


    @FXML
    private VBox sourceBigBox;
    @FXML
    private VBox sourceBigBox2;
    @FXML
    private ComboBox<String> sourceBox;
    @FXML
    private TextArea inputText;
    @FXML
    private Button inputFileChooser;
    @FXML
    private TextArea outputText;
    @FXML
    private ComboBox<String> sourceBox2;
    @FXML
    private TextArea inputText2;
    @FXML
    private Button inputFileChooser2;
    @FXML
    private TextField inputNoteTitle2;
    @FXML
    private TextArea outputText2;


    @FXML
    private TextField key;
    @FXML
    private TextField repeatKey;
    @FXML
    private ComboBox<String> cryptMethodChooser;
    @FXML
    private CheckBox saveInFavorites;
    @FXML
    private TextField noteNameField;
    @FXML
    private CheckBox saveInFile;
    @FXML
    private Button fileChooserButton;
    @FXML
    private CheckBox showKey;
    @FXML
    private Button submit;
    @FXML
    private TextField key2;
    //    @FXML
//    private TextField repeatKey2;
    @FXML
    private ComboBox<String> cryptMethodChooser2;
    @FXML
    private CheckBox saveInFile2;
    @FXML
    private Button fileChooserButton2;
    @FXML
    private CheckBox showKey2;
    @FXML
    private Button submit2;


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
    private Label warnLabelKey, warnLabelKey2;
    @FXML
    private Label warnLabelNoteTitle;
    @FXML
    private Label warnLabelWrongFile, warnLabelWrongFile2;
    @FXML
    private Label warnLabelUnknown, warnLabelUnknown2;
    @FXML
    private Label doneLabelEncryption, doneLabelEncryption2;
    @FXML
    private Label doneLabelSaveInNotes, doneLabelSaveInNotes2;
    @FXML
    private Label doneLabelSaveInFile, doneLabelSaveInFile2;
    private final static String DONE_ENC = "ENCRYPTION SUCCESSFUL. Characters: ";
    private final static String DONE_SAVE_NOTE = "SAVE IN FAVOURITES SUCCESSFUL. Title: ";
    private final static String DONE_SAVE_FILE = "SAVE IN FILE SUCCESSFUL. File name: ";
    ObservableList<String> methods = FXCollections.observableArrayList(
            "DES",
            "AES"
    );
    ObservableList<String> source = FXCollections.observableArrayList(
            "Input text",
            "File"
    );
    ObservableList<String> source2 = FXCollections.observableArrayList(
            "Input text",
            "File",
            "Note"
    );
    private String encFileGetFrom = "C:\\";
    private String decFileGetFrom = "C:\\";
    private String encFileSaveTo = "C:\\";
    private String decFileSaveTo = "C:\\";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // DELETE THIS!!!
//        currentUser = new User("Serega", "aaaaaa");
        helloUser.setText("Hello!");
        if(currentUser.getName()!=null)
            if(currentUser.getName().length()>0)
                helloUser.setText("Hello, " + currentUser.getName() + "!");

        warnLabelKey.setText("");
        warnLabelNoteTitle.setText("");
        warnLabelWrongFile.setText("");
        warnLabelUnknown.setText("");
        doneLabelEncryption.setText("");
        doneLabelSaveInNotes.setText("");
        doneLabelSaveInFile.setText("");

        submit.setDisable(false);

        sourceBox.setItems(source);
        sourceBox.setValue(source.get(0));
        cryptMethodChooser.setItems(methods);
        cryptMethodChooser.setValue(methods.get(0));
        sourceBox2.setItems(source2);
        sourceBox2.setValue(source2.get(0));
        cryptMethodChooser2.setItems(methods);
        cryptMethodChooser2.setValue(methods.get(0));

        outputText.focusedProperty().addListener((observable, oldValue, newValue) -> {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(outputText.getText()), null);
        });
        outputText2.focusedProperty().addListener((observable, oldValue, newValue) -> {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(outputText2.getText()), null);
        });

//        inputFileChooser = (Button) sourceBigBox.getChildren().get(1);
//        inputText = (TextArea) sourceBigBox.getChildren().get(2);
        sourceBigBox.getChildren().remove(1, 2);
        sourceBigBox2.getChildren().remove(1, 3);
        changeSource();
        changeSource2();
        clearAlerts();
        initMenu();
    }

    public void changeSource() {
        if (sourceBox.getValue().equals(sourceBox.getItems().get(0))) {
            sourceBigBox.getChildren().remove(1);
            sourceBigBox.getChildren().add(1, inputText);
            System.out.println("!! Change source2: " + 0);
        } else if (sourceBox.getValue().equals(sourceBox.getItems().get(1))) {
            sourceBigBox.getChildren().remove(1);
            sourceBigBox.getChildren().add(1, inputFileChooser);
            System.out.println("!! Change source2: " + 1);
        } else {
            System.out.println("!! Change source2: ?");
        }
    }

    public void changeSource2() {
        if (sourceBox2.getValue().equals(sourceBox2.getItems().get(0))) {
            sourceBigBox2.getChildren().remove(1);
            sourceBigBox2.getChildren().add(1, inputText2);
            System.out.println("!! Change source2: " + 0);
        } else if (sourceBox2.getValue().equals(sourceBox2.getItems().get(1))) {
            sourceBigBox2.getChildren().remove(1);
            sourceBigBox2.getChildren().add(1, inputFileChooser2);
            System.out.println("!! Change source2: " + 1);
        } else {
            sourceBigBox2.getChildren().remove(1);
            sourceBigBox2.getChildren().add(1, inputNoteTitle2);
            System.out.println("!! Change source2: ?");
        }
    }

    public void changeSource3() {
        inputText2.setVisible(false);
        inputFileChooser2.setVisible(false);
        inputNoteTitle2.setVisible(false);
        if (sourceBox2.getValue().equals(sourceBox2.getItems().get(0))) {
            System.out.println("!! Change source2: " + 0);
            inputText2.setVisible(true);
        } else if (sourceBox2.getValue().equals(sourceBox2.getItems().get(1))) {
            System.out.println("!! Change source2: " + 1);
            inputFileChooser2.setVisible(true);
        } else if (sourceBox2.getItems().size() > 2) {
            if (sourceBox2.getValue().equals(sourceBox2.getItems().get(2))) {
                System.out.println("!! Change source2: " + 2);
                inputNoteTitle2.setVisible(true);
            }
        } else {
            System.out.println("!! Change source2: ?");
        }
    }


    @FXML
    public void chooseFile() {
        RandomInteger randomInteger = new RandomInteger();
        String a = randomInteger.main();
        key.setText(a);
        repeatKey.setText(a);
    }


    @FXML
    public void randomizeKey() {
        if (!encryptTab.isSelected())
            return;

        String a;
        final String allSymbols = (a = "abcdefghijklmnopqrstuvwxyz") + a.toUpperCase() + "1234567890";
        int length = (int) (8 + 6 * Math.random());
        StringBuilder pass = new StringBuilder();
        System.out.println(allSymbols);
        System.out.println(allSymbols.charAt(2));
        System.out.println(allSymbols.charAt(12));
        System.out.println(allSymbols);
        for (int i = 0; i < length; i++) {
            pass.append(
                    allSymbols.charAt((int) (Math.random() * allSymbols.length()))
            );
        }
        key.setText(pass.toString());
        repeatKey.setText(pass.toString());
    }

    public void setDialogStage(Stage dialogStage) {
        this.menu = dialogStage;
    }

    @FXML
    public void methodUpdate() {
        switch (cryptMethodChooser.getValue()) {
            case "DES":
                crypto = new DesEncrypter();
                break;
            case "AES":
                crypto = new AesEncrypter();
                break;
            default:
                crypto = new DesEncrypter();
        }
    }

    @FXML
    private void useMasterKey() {

    }

    @FXML
    private void submitAction() throws Exception {
        if (encryptTab.isSelected()) {
            logs();

            clearAlerts();
            methodUpdate();
            String resultString;
            if (key.getText().length() < 6)
                ExceptionInputData.showError("Key field is too short", warnLabelKey);
            else if (!key.getText().equals(repeatKey.getText()))
                ExceptionInputData.showError("Keys are not equals", warnLabelKey);
            else if ((sourceBox.getValue().equals(sourceBox.getItems().get(0)) &&
                    inputText.getText().length() == 0) ||
                    (sourceBox.getValue().equals(sourceBox.getItems().get(1)) &&
                            inputFileChooser.getText().length() == 0)
                    )
                ExceptionInputData.showError("Input text for encryption!", warnLabelUnknown);
            else if ((resultString = crypto.encrypt(
                    (sourceBox.getValue().equals(sourceBox.getItems().get(0)) ?
                            inputText.getText() :
                            fileWorker.read(encFileGetFrom)
                    )
                    ,
                    key.getText())) == null)
                ExceptionInputData.showError("Input data is not correct!", warnLabelUnknown);
            else {
                outputText.setText(resultString);
                doneLabelEncryption.setText(DONE_ENC + resultString.length());
                outputText.setDisable(false);
                if (saveInFavorites.isSelected()) {
                    if (noteNameField.getText().length() == 0)
                        ExceptionInputData.showError("Note's title is too short", warnLabelNoteTitle);
                    else {
                        saveInNotes(
                                noteNameField.getText(),
                                resultString
                        );
                        doneLabelSaveInNotes.setText(DONE_SAVE_NOTE + noteNameField.getText());
                    }
                }
                if (saveInFile.isSelected()) {
                    if (!new File(encFileSaveTo).exists())
                        ExceptionInputData.showError("File's path is wrong", warnLabelWrongFile);
                    else {
                        fileWorker.write(encFileSaveTo, resultString);
                        System.out.println("T: " + resultString);
                        System.out.println("F: " + encFileSaveTo);
                        doneLabelSaveInFile.setText(DONE_SAVE_FILE + new File(encFileSaveTo).getName());
                    }
                }
            }
        } else if (decryptTab.isSelected()) {
            logs();

            clearAlerts();
            methodUpdate();

            String resultString;
            if (key2.getText().length() == 0)
                ExceptionInputData.showError("Key field is too short", warnLabelKey2);
            else if (
                    (sourceBox2.getValue().equals(sourceBox2.getItems().get(0)) &&
                            inputText2.getText().length() == 0) ||
                            (sourceBox2.getValue().equals(sourceBox2.getItems().get(1)) &&
                                    inputFileChooser2.getText().length() == 0) ||
                            (sourceBox2.getValue().equals(sourceBox2.getItems().get(2)) &&
                                    inputNoteTitle2.getText().length() == 0)
                    )
                ExceptionInputData.showError("Input text for decryption!", warnLabelUnknown2);
            else if ((resultString = crypto.decrypt(
                    (sourceBox2.getValue().equals(sourceBox2.getItems().get(0)) ?
                            inputText2.getText() :
                            sourceBox2.getValue().equals(sourceBox2.getItems().get(1)) ?
                                    fileWorker.read(decFileGetFrom) :
                                    notesDAO.get(new Note(inputNoteTitle2.getText(), currentUser.getId())).getValue()
                    )
                    ,
                    key2.getText())) == null) {
                ExceptionInputData.showError("Key is wrong!", warnLabelUnknown2);
            } else {
                outputText2.setText(resultString);
                doneLabelEncryption2.setText(DONE_ENC + resultString.length());
                outputText2.setDisable(false);
                if (saveInFile2.isSelected()) {
                    if (!new File(decFileSaveTo).exists())
                        ExceptionInputData.showError("File's path is wrong", warnLabelWrongFile2);
                    else {
                        fileWorker.write(decFileSaveTo, resultString);
                        doneLabelSaveInFile2.setText(DONE_SAVE_FILE + new File(decFileSaveTo).getName());
                    }
                }
            }
        }
        refreshNotesTable();
    }


    private void logs() {
//        System.out.println("Key: " + key.getText());
//        System.out.println("Repeat key: " + repeatKey.getText());
//        System.out.println("File name: " + encFileSaveTo);
//        System.out.println("Note name: " + noteNameField.getText());
//        System.out.println("Method: " + cryptMethodChooser.getValue());
//        System.out.println("save in favorites: " + saveInFavorites.isSelected());
//        System.out.println("save in file: " + saveInFile.isSelected());
//        System.out.println("show key: " + showKey.isSelected());
    }


    private void saveInNotes(String title, String text) {
        if (text.length() == 0) return;
        Note newNote = new Note();
        NotesDAO notesDAO = new NotesDAO();
        if (notesDAO.get(newNote) != null) {
            ExceptionInputData.showError("Note with the same title is already exist", warnLabelNoteTitle);
            return;
        }
        newNote.setIdUser(currentUser.getId());
        newNote.setTitle(title);
        newNote.setValue(text);
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
            fileWorker.write(file, outputText.getText());
        }
    }


    private void saveFile1(String content, File file) {
        fileWorker.write(file, content);
    }

    public void chooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Document");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(menu);

        System.out.println(file);

        String textFromFile = fileWorker.read(String.valueOf(file));
        inputText.setText(textFromFile);
    }

    public void fileChooserAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(menu);
        if (file == null) {
            System.err.println("File is NULL!!");
            return;
        }
        if (encryptTab.isSelected()) {
            encFileSaveTo = file.getAbsolutePath();
            fileChooserButton.setText(file.getAbsolutePath().length() < 30 ?
                    file.getAbsolutePath() :
                    file.getName());
        } else if (decryptTab.isSelected()) {
            decFileSaveTo = file.getAbsolutePath();
            fileChooserButton2.setText(file.getAbsolutePath().length() < 30 ?
                    file.getAbsolutePath() :
                    file.getName());
        }
//        fileWorker.read(file);
    }

    public void fileChooserActionSource() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(menu);
        if (file == null) {
            System.err.println("File is NULL!!");
            return;
        }
        if (encryptTab.isSelected()) {
            encFileGetFrom = file.getAbsolutePath();
            System.out.println("Enc from: " + encFileGetFrom);
            inputFileChooser.setText(file.getAbsolutePath().length() < 30 ?
                    file.getAbsolutePath() :
                    file.getName());
        } else if (decryptTab.isSelected()) {
            decFileGetFrom = file.getAbsolutePath();
            System.out.println("Dec from: " + decFileGetFrom);
            inputFileChooser2.setText(file.getAbsolutePath().length() < 30 ?
                    file.getAbsolutePath() :
                    file.getName());
        }
//        fileWorker.read(file);
    }


    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User u) {
        MenuController.currentUser = u;
        System.out.println("id2: " + currentUser.getId());
    }

    private void initMenu() {
        System.out.println("INIT MENU begin : ");
        if (currentUser.getName() != null)
            if (currentUser.getName().length() > 0)
                helloUser.setText("Hello, " + currentUser.getName() + "!");
        log31.setPromptText(currentUser.getLogin());
        name32.setPromptText(currentUser.getName());
        mail33.setPromptText(currentUser.getMail());
        dateReg.setText("Registration date: " + String.valueOf(
                currentUser.getRegistrationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        dateEnter.setText("Last visit date: " + String.valueOf(
                currentUser.getLastVisitDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        System.out.println("INIT MENU end");

        refreshNotesTable();
    }

    public void refreshNotesTable() {
        NotesDAO notesDao = new NotesDAO();
        data.clear();
        data.addAll(notesDao.getAll().stream().filter(n -> n.getIdUser() == currentUser.getId()).collect(Collectors.toList()));

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        encColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
//        notesTable.setItems(FXCollections.observableArrayList());
        if (notesTable.getItems().isEmpty())
            notesTable.setItems(data);
        fLimit.setText("Notes: "
                + data.size()
                + "/"
                + currentUser.getMaxNotes());
    }

    public void saveChanges() {
        HashingMD5 hashing = new HashingMD5();

//        User changedUser = currentUser;
        if (!log31.getText().equals(""))
            if (usersDao.getAll().stream().noneMatch((a) -> log31.getText().equals(a.getLogin())))
                currentUser.setLogin(log31.getText());
        if (!name32.getText().equals(""))
            currentUser.setName(name32.getText());
        if (!mail33.getText().equals(""))
            currentUser.setMail(mail33.getText());
        if (!pass34.getText().equals("") && !pass35.getText().equals("")) {
            if (hashing.toHashCode(pass34.getText()).equals(currentUser.getPassword()) &&
                    pass35.getText().equals(pass36.getText()) && pass35.getText().length() >= 6) {
                currentUser.setPasswordToMD5(pass35.getText());
                alert.setTextFill(Color.GREEN);
                alert.setText("Successful!");
            } else {
                passAlert();
            }
        }
        usersDao.update(currentUser);
    }

    private void passAlert() {
        alert.setTextFill(Color.RED);
        if (!(pass34.getText()).equals(currentUser.getPassword()))
            alert.setText("Old password is wrong!");
        if (!pass35.getText().equals(pass36.getText()))
            alert.setText("New passwords aren't equals!");
        else if (pass35.getText().length() < 6)
            alert.setText("New password is too short");
    }

    public void deleteNote() {
//        int selectedIndex = notesTable.getSelectionModel().getSelectedIndex();
//        if (selectedIndex >= 0) {
        notesDAO.remove((Note) notesTable.getSelectionModel().getSelectedItem());
//        notesTable.getItems().remove(notesTable.getSelectionModel().getSelectedItem());
        refreshNotesTable();
//        System.out.println(notesTable.getSelectionModel().getSelectedItem());
//            System.out.println("D: " + selectedIndex);
//        }
    }

    public void decryptNote() {
        sourceBox2.setValue(
                sourceBox2.getItems().get(2)
        );
        changeSource2();
        inputNoteTitle2.setText(((Note) notesTable.getSelectionModel().getSelectedItem()).getTitle());
        selectionModel.select(1);
    }

    public void logOutAction() {
        currentUser = null;
        menu.close();
        try {
            MainAppFx.initMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void refreshAction() {
        refreshNotesTable();
    }

    public void resetData() {
        inputText.setText("");
        outputText.setText("");
        key.setText("");
        repeatKey.setText("");
        saveInFile.setSelected(false);
        noteNameField.setText("");
        saveInFavorites.setSelected(false);
        fileChooserButton.setText("Choose file");
        inputText2.setText("");
        outputText2.setText("");
        key2.setText("");
//        repeatKey2.setText("");
        saveInFile2.setSelected(false);
        fileChooserButton2.setText("Choose file");
    }

    private void clearAlerts() {
        warnLabelKey.setText("");
        warnLabelNoteTitle.setText("");
        warnLabelWrongFile.setText("");
        warnLabelUnknown.setText("");
        doneLabelEncryption.setText("");
        doneLabelSaveInFile.setText("");
        doneLabelSaveInNotes.setText("");
        warnLabelKey2.setText("");
        warnLabelWrongFile2.setText("");
        warnLabelUnknown2.setText("");
        doneLabelEncryption2.setText("");
        doneLabelSaveInFile2.setText("");
        doneLabelSaveInNotes2.setText("");
    }

    public void cancel() {
        log31.clear();
        name32.clear();
        mail33.clear();
        pass34.clear();
        pass35.clear();
        pass36.clear();
    }

    public static void setSelectionModel(SingleSelectionModel<Tab> s) {
        selectionModel = s;
    }
}


