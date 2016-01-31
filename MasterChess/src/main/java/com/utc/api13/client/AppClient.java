package com.utc.api13.client;

import java.io.IOException;

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
    public static Stage stage;
    private Stage currentStage;

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

        /**
         * JAVAFX STAGE <<<<<<<<<<<<<<<<<<<<<<<
         */
        ihmManager = new IHMManager();
        comClientManager = new ComClientManager();
        ihmManager.setMainApp(this);

        DataClientManager dataClientManager = new DataClientManager();
        dataClientManager.setIClientComToData(comClientManager.getClientComToDataImpl());
        dataClientManager.setIClientIHMToData(ihmManager.getClientIHMToDataImpl());

        ihmManager.setIClientDataToIHM(dataClientManager.getClientDataToIHMImpl());
        comClientManager.setIClientDataToCom(dataClientManager.getClientDataToComImpl());

        AppClient.stage = stage;
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

        /**
         * >>>>>>>>>>>>>>>>>>>>>>>
         */

        /**
         * MAIN <<<<<<<<<<<<<<<<<<<<<<<
         */

    }

    // private static final Logger LOGGER = Logger.getLogger(AppClient.class);

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

        // comClientManager.launchAppCom(host, port);
        try {
            comClientManager.launchAppCom(host, port);
        } catch (Exception e) {
            succeed = false;
            displayErrorPopup(" wrong server port and server address");
            e.printStackTrace();

        }
        /**
         * >>>>>>>>>>>>>>>>>>>>>>>
         */

        // TODO : Faire une vrai gestion d'erreur
        // comClientManager.close();

    }

    public void displayErrorPopup(String message) {
        Stage stage;
        Parent root = null;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/errorPopUp.fxml"));
        try {
            root = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ErrorController controller = fxmlLoader.getController();
        controller.setControllerContext(ihmManager);
        controller.setMainApp(this, message);
        stage.setScene(new Scene(root));
        stage.setTitle("error");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    public void displayConfirmationPopup(String message) {

        Stage stage;
        Parent root = null;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/confirmationPopUp.fxml"));
        try {
            root = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ConfirmationController controller = fxmlLoader.getController();
        controller.setControllerContext(ihmManager);
        controller.setMainApp(this, message);
        stage.setScene(new Scene(root));
        stage.setTitle("confirmation");

        // myIHMManager.getCurrentStage().close();
        // mainApp.getCurrentStage().close();
        // mainApp.setCurrentStage(stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }
}
