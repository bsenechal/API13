package com.utc.api13.client.ihm.controllers;

import java.io.IOException;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import org.apache.log4j.Logger;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;

public class AnswerPropositionController {

    private IHMManager IHMManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private Stage currentStage;
    private final Logger log = Logger.getLogger(getClass());
    private UUID enquirerUUID; 
    
    @FXML
    BorderPane answerPropositionBorderPane;
    @FXML
    AnchorPane answerPropositionAnchorPane;
    @FXML
    Label invintingPlayerLogin, invitationLabel, optionsLabel, chosenOptionsLabel; 
    @FXML
    Button yesButton, noButton; 
    
    public AnswerPropositionController() {
        initialize();
    }
    public void initialize() {
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
        controller.setMainApp(this.mainApp, message);
        stage.setScene(new Scene(root));
        stage.setTitle("Error");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    public void setMainApp(AppClient app, String login, String options) {
        this.mainApp = app;
        this.invintingPlayerLogin.setText(login); 
        this.chosenOptionsLabel.setText(options);
    }
    
    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null) {
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
        }
    }
    
    public void onYesClicked() {
    	//lancer le jeu
    }
    
    public void onNoClicked() { 
    	PrivateUserEntity u=this.myIClientToIHM.getLocalUser(); 
		UUID answeringUser = u.getId(); 
		//pour l'interface : on se fiche de renvoyer les options !
		//this.myIClientToIHM.sendResponse(answeringUser/*, enquirerUUID*/);
    }

}