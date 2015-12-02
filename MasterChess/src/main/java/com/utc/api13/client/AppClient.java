package com.utc.api13.client;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.client.data.DataClientManager;
import com.utc.api13.client.ihm.IHMManager;

import com.utc.api13.client.ihm.controllers.IHMConnexionPageController;

import com.utc.api13.client.ihm.controllers.IHMWelcomePageController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class AppClient extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        /**
         * MAIN <<<<<<<<<<<<<<<<<<<<<<<
         */

        IHMManager ihmManager = new IHMManager();
        ComClientManager comClientManager = new ComClientManager();

        DataClientManager dataClientManager = new DataClientManager();
        dataClientManager.setIClientComToData(comClientManager.getClientComToDataImpl());
        dataClientManager.setIClientIHMToData(ihmManager.getClientIHMToDataImpl());

        ihmManager.setIClientDataToIHM(dataClientManager.getClientDataToIHMImpl());
        comClientManager.setIClientDataToCom(dataClientManager.getClientDataToComImpl());
        comClientManager.launchAppCom("localhost", 8000);

        /**
         * >>>>>>>>>>>>>>>>>>>>>>>
         */

        /**
         * JAVAFX STAGE <<<<<<<<<<<<<<<<<<<<<<<
         */
        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/welcomePage.fxml"));

        Pane root = (Pane) fxmlLoader.load();
        IHMWelcomePageController controller = fxmlLoader.getController();
        controller.setControllerContext(ihmManager);
        controller.setMainApp(this);
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/css/masterCSS.css").toExternalForm());
        stage.setTitle("Connexion");
        stage.setScene(scene);
        stage.show();

        /**
         * >>>>>>>>>>>>>>>>>>>>>>>
         */

        // TODO : Faire une vrai gestion d'erreur
        // comClientManager.close();

    }

    // private static final Logger LOGGER = Logger.getLogger(AppClient.class);

    public static void main(String[] args) {
        launch(args);

    }
}
