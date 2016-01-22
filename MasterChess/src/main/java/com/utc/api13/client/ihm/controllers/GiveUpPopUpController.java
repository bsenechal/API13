package com.utc.api13.client.ihm.controllers;

import java.awt.Button;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.property.ProfilProperty;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GiveUpPopUpController {
    private IHMManager IHMManager;
    private ProfilProperty profile;
    public static Stage stageI;
    private Stage currentStage;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private ObservableList<PublicUserEntity> listUserPublic;
    private ObservableList<GameEntity> listCurrentGames;
    private final Logger log = Logger.getLogger(getClass());
    private UUID opponentUUID; 

    @FXML
    BorderPane mainBorderPane;
    @FXML
    AnchorPane mainAnchorPane;
    @FXML
    Label opponentLabel, messageLabel;
    @FXML
    Button yesButton, noButton; 
    
    public void OnYesClicked() {
    	
    }
    
    public void OnNoClicked() {
    	
    }

    public IHMManager getIHMManager() {
        return this.IHMManager;
    }

    public void setIHMManager(IHMManager iHMManager) {
        this.IHMManager = iHMManager;
    }

    public void initialize() {
    }

    public GiveUpPopUpController() {
        initialize();
    }

    public void setMainApp(AppClient app, UUID opponent) {
        this.mainApp = app;
        this.opponentUUID=opponent; 
    }

    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null) {
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
            profile = new ProfilProperty();

            this.IHMManager.setProfil(profile);
            setListenersOnLoad();
            setBindingsOnLoad();
        }
    }

    public void setIHMMandClient(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null)
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
    }

    public void setListenersOnLoad() {
        
    }

    public void setBindingsOnLoad() {

    }


    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void error(String message) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/errorPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ErrorController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        mainApp.setCurrentStage(stage);
        controller.setMainApp(this.mainApp, message);
        stage.setScene(new Scene(root));
        stage.setTitle("Error");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

}
