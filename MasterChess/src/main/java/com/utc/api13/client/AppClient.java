package com.utc.api13.client;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.client.data.DataClientManager;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.services.UserService;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.controllers.IHMConnexionPageController;
import com.utc.api13.client.ihm.controllers.IHMWelcomePageController;
import com.utc.api13.commun.entities.PublicUserEntity;

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
    private Stage currentStage;
    public Stage newStage;
    

    public Stage getNewStage() {
        return new Stage();
    }

    public void setNewStage(Stage newStage) {
        this.newStage = newStage;
    }

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
        PrivateUserEntity user = new PrivateUserEntity("diallo", "diallo");
        dataClientManager.setUserLocal(user);
        dataClientManager.getUserService().save(user);
        
        /**
         * >>>>>>>>>>>>>>>>>>>>>>>
         */

        /**
         * JAVAFX STAGE <<<<<<<<<<<<<<<<<<<<<<<
         */
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/connexionPage.fxml"));
        Pane root = (Pane) fxmlLoader.load();
        IHMConnexionPageController controller = fxmlLoader.getController();
       // ihmManager.setAppClient(this);
        controller.setControllerContext(ihmManager);
        controller.setMainApp(this);
        this.setCurrentStage(stage);
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

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public static void main(String[] args) {
        launch(args);

    }

    
}