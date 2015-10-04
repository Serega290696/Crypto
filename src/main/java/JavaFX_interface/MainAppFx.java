package JavaFX_interface;

import JavaFX_interface.view.MenuController;
import JavaFX_interface.view.RegController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainAppFx extends Application {

    private static Stage primaryStage;

    private static Stage additionalStage;

    @FXML
    private static Button button3;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStageT) throws Exception {
        primaryStage = primaryStageT;
        primaryStage.setTitle("Crypto");

        try {
            Parent root = FXMLLoader.load(MainAppFx.class.getResource("/fxml/Main.fxml"));
            primaryStage.setScene(new Scene(root, null));
            primaryStage.setTitle("Crypto");
            primaryStage.setResizable(false);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void initShowReg() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppFx.class.getResource("/fxml/Reg.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage reg = new Stage();
            reg.setTitle("Registration");
            reg.initModality(Modality.WINDOW_MODAL);
            reg.initOwner(primaryStage);
            Scene scene = new Scene(page);
            reg.setScene(scene);

            RegController regController = loader.getController();
            regController.setDialogStage(reg);

            additionalStage = reg;
            additionalStage.setResizable(false);
            additionalStage.setOnCloseRequest((event) ->
                            additionalStage = null
            );
            reg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initShowEnter() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppFx.class.getResource("/fxml/Enter.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage enter = new Stage();
            enter.setTitle("Enter");
            enter.initModality(Modality.WINDOW_MODAL);
            enter.initOwner(primaryStage);
            Scene scene = new Scene(page);
            enter.setScene(scene);

            additionalStage = enter;
            additionalStage.setResizable(false);
            additionalStage.setOnCloseRequest((event) ->
                            additionalStage = null
            );
            enter.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initMenu() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainAppFx.class.getResource("/fxml/Menu.fxml"));
        TabPane page = (TabPane) loader.load();
        Stage menu = new Stage();
        menu.setTitle("Enter");

        Scene scene = new Scene(page);

        menu.setScene(scene);

        MenuController menucontroller = loader.getController();
        MenuController.setSelectionModel(page.getSelectionModel());
        menucontroller.setDialogStage(menu);
        CloseMethod();
        primaryStage.setResizable(false);
        primaryStage = menu;
        primaryStage.setOnCloseRequest((e) ->
                        primaryStage = null
        );
        menu.show();
    }

    public static void initMainMenu() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainAppFx.class.getResource("/fxml/Main.fxml"));
        GridPane page = (GridPane) loader.load();
        Stage mainMenu = new Stage();
        mainMenu.setTitle("Crypto");

        Scene scene = new Scene(page);

        mainMenu.setScene(scene);

        primaryStage.setResizable(false);
        primaryStage = mainMenu;
        primaryStage.setOnCloseRequest((e) ->
                        primaryStage = null
        );
        mainMenu.show();
//        primaryStage = primaryStageT;
//        primaryStage.setTitle("Crypto");

    }

    public static void CloseMethod() {
        primaryStage.close();
    }

    public static void setPrimaryStage(Stage primaryStage) {
        MainAppFx.primaryStage = primaryStage;
    }

    public static void setAdditionalStage(Stage additionalStage) {
        MainAppFx.additionalStage = additionalStage;
    }

    public static Stage getAdditionalStage() {
        return additionalStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}



