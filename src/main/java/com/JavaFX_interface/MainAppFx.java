package com.JavaFX_interface;

import com.JavaFX_interface.view.EnterController;
import com.JavaFX_interface.view.MenuController;
import com.JavaFX_interface.view.RegController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainAppFx extends Application {
    private static Stage primaryStage;
    @FXML
    private static Button button3;



    @Override
      public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CryptoApp");

        try {
            Parent root = FXMLLoader.load(MainAppFx.class.getResource("view/Main.fxml"));
            primaryStage.setScene(new Scene(root, null));
            primaryStage.setTitle("CryptoApp");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

       public static void  initShowReg(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppFx.class.getResource("view/Reg.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage reg = new Stage();
            reg.setTitle("Registration");
            reg.initModality(Modality.WINDOW_MODAL);
            reg.initOwner(primaryStage);
            Scene scene = new Scene(page);
            reg.setScene(scene);

            RegController regController = loader.getController();
            regController.setDialogStage(reg);


            reg.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initShowEnter(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppFx.class.getResource("view/Enter.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage enter = new Stage();
            enter.setTitle("Enter");
            enter.initModality(Modality.WINDOW_MODAL);
            enter.initOwner(primaryStage);
            Scene scene = new Scene(page);
            enter.setScene(scene);

            EnterController enterController = loader.getController();
            enterController.setDialogStage(enter);


            enter.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void initMenu() throws Exception{



        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainAppFx.class.getResource("view/Menu.fxml"));
        TabPane page = (TabPane) loader.load();
        Stage enter = new Stage();
        enter.setTitle("Enter");

        Scene scene = new Scene(page);

        enter.setScene(scene);

        MenuController menucontroller = loader.getController();
        menucontroller.setDialogStage(enter);

        enter.show();
    }
    public static void CloseMethod() {

       primaryStage.close();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

