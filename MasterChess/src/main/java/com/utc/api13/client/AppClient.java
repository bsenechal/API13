package com.utc.api13.client;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.client.data.DataClientManager;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.controllers.ConfirmationController;
import com.utc.api13.client.ihm.controllers.ErrorController;
import com.utc.api13.client.ihm.controllers.IHMConnexionPageController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class AppClient extends Application {
    private Stage stage;
    private Stage currentStage;
    private static final Logger LOGGER = Logger.getLogger(AppClient.class);
    private ComClientManager comClientManager;
    private IHMManager ihmManager;
    private boolean succeed = true;

    public ComClientManager getComClientManager() {
        return comClientManager;
    }

    public void setComClientManager(ComClientManager comClientManager) {
        this.comClientManager = comClientManager;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    @Override
    public void start(Stage stage) throws Exception {

        ihmManager = new IHMManager();
        comClientManager = new ComClientManager();
        ihmManager.setMainApp(this);

        DataClientManager dataClientManager = new DataClientManager();
        dataClientManager.setIClientComToData(comClientManager.getClientComToDataImpl());
        dataClientManager.setIClientIHMToData(ihmManager.getClientIHMToDataImpl());

        ihmManager.setIClientDataToIHM(dataClientManager.getClientDataToIHMImpl());
        comClientManager.setIClientDataToCom(dataClientManager.getClientDataToComImpl());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/connexionPage.fxml"));
        Pane root = (Pane) fxmlLoader.load();
        IHMConnexionPageController controller = fxmlLoader.getController();
        controller.setControllerContext(ihmManager);
        this.setCurrentStage(stage);
        controller.setMainApp(this);
        controller.setCurrentStage(stage);
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/css/masterCSS.css").toExternalForm());
        stage.setTitle("Connexion");
        stage.setScene(scene);
        stage.show();

    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public Stage getMainStage() {
        return stage;
    }

    public void setMainStage(Stage mainStage) {
        stage = mainStage;
    }

    public static void main(String[] args) {
        launch(args);

    }

    public void launchAppCom(String host, int port) {

        try {
            comClientManager.launchAppCom(host, port);
        } catch (InterruptedException e) {
            succeed = false;
            displayErrorPopup(" wrong server port and server address");
            LOGGER.error("[AppClient][launchAppCom] " + e.getMessage(), e);
        }
    }

    public void displayErrorPopup(String message) {
        Stage popupStage;
        Parent root = null;
        popupStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/errorPopUp.fxml"));
        try {
            root = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.error("[AppClient][displayErrorPopup] " + e.getMessage(), e);
        }
        ErrorController controller = fxmlLoader.getController();
        controller.setControllerContext(ihmManager);
        controller.setText(message);
        popupStage.setScene(new Scene(root));
        popupStage.setTitle("error");
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.show();

    }

    public void displayConfirmationPopup(String message) {

        Stage popupStage;
        Parent root = null;
        popupStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/confirmationPopUp.fxml"));
        try {
            root = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.error("[AppClient][displayConfirmationPopup] " + e.getMessage(), e);
        }
        ConfirmationController controller = fxmlLoader.getController();
        controller.setControllerContext(ihmManager);
        controller.setMainApp(message);
        popupStage.setScene(new Scene(root));
        popupStage.setTitle("confirmation");

        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.show();
    }
}
