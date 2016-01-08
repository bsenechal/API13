package com.utc.api13.client.ihm.controllers;

import java.io.IOException;
import java.util.UUID;

import javafx.collections.ListChangeListener;
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
import javafx.scene.control.cell.PropertyValueFactory;

import org.apache.log4j.Logger;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.property.ProfilProperty;
import com.utc.api13.client.ihm.property.PropositionProperty;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.exceptions.TechnicalException;

public class AnswerPropositionController {

    private IHMManager IHMManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private Stage currentStage;
    private final Logger log = Logger.getLogger(getClass());
    private UUID enquirerUUID; 
    private boolean answer=true; 
    private boolean chattable; 
    private boolean observable; 
    private boolean timer; 
    private String time; 
    
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
    
    public void setMainApp(AppClient app, String login, boolean chattable, boolean timer, boolean observable, String time) {
        this.mainApp = app;
        this.chattable=chattable; 
        this.timer=timer; 
        this.time=time; 
        this.observable=observable; 
        
        String options= new String("") ; 
        if (chattable) options=options+"chattable ; "; 
        if (observable) options=options+"observable ; "; 
        if (timer) options=options+"timer : "+time; 
        if (options.length()==0) options="None"; 
        
        this.chosenOptionsLabel.setText(options);
        this.invintingPlayerLogin.setText(login); 
    }
    
    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null) {
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
        }
    }
    
    public void onYesClicked() {
    	PrivateUserEntity u=this.myIClientToIHM.getLocalUser(); 
		UUID answeringUser = u.getId(); 
		try {
			this.myIClientToIHM.sendResponse(answeringUser/*, enquirerUUID*/, answer, observable, chattable/*, timer, time*/);
		} catch (TechnicalException e) {
			log.error(e.getMessage(), e);
			try { 
				error("Error while sending proposition answer : technical exception"); 
			}
			catch (IOException e1) {
				log.error(e1.getMessage(), e1);
			}
		}
    }
    
    public void onNoClicked() {
    	this.answer=false; 
    	this.onYesClicked(); 
    }
    
    public void setBindings(PropositionProperty proposition) {
    	invintingPlayerLogin.textProperty().bind(proposition.loginAskingPayerProperty());
    	//et pour ce qui n'est pas StringProperty ?
        /*userInfoFirstName.textProperty().bind(profile.firstNameProperty());
        userInfoLastName.textProperty().bind(profile.lastNameProperty());
        userUUID.textProperty().bind(profile.userUUID());  

        userInfoWon.setCellValueFactory(new PropertyValueFactory<PrivateUserEntity, Integer>("nbWon"));
        userInfoLost.setCellValueFactory(new PropertyValueFactory<PrivateUserEntity, Integer>("nbLost"));
        userInfoPlayed.setCellValueFactory(new PropertyValueFactory<PrivateUserEntity, Integer>("nbPlayed"));

        profile.statPlayerProperty().addListener((ListChangeListener.Change<? extends PublicUserEntity> el) -> {
            userInfoTableView.setItems(profile.statPlayerProperty());
        });*/

    }
}