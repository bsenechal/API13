package com.utc.api13.client.ihm.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.beans.EventHandler;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;



import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;



import org.apache.log4j.Logger;



import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.property.ErrorProperty;
import com.utc.api13.client.ihm.property.ProfilProperty;
import com.utc.api13.client.ihm.property.PropositionProperty;

public class SendPropositionController {

    private static IHMManager IHMManager;
    private static AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private Stage currentStage;
    private final Logger log = Logger.getLogger(getClass());
    private String opponentUUID; 
    private ErrorProperty error;  
    boolean chattable; 
    private PropositionProperty proposition; 
    
    
    @FXML
    BorderPane sendPropositionBorderPane;
    @FXML
    AnchorPane sendPropositionAnchorPane;
    @FXML
    Label playerLabel, opponentLogin, optionLabel; 
    @FXML
    CheckBox chatCheckBox, timerCheckBox, observerCheckBox; 
    @FXML
    TextField timeTextField; 
    @FXML
    Button sendPropositionButton, cancelButton; 
    
    public void onCancelClicked() {
    	mainApp.getCurrentStage().close();
    	//pb car le stage davant nest pas svg = pb à la déconnexion à prévoir
    }
    
    public void onSendPropositionClicked() {
    	
    	chattable = chatCheckBox.isSelected(); 
    	boolean observable = observerCheckBox.isSelected(); 
    	boolean timer = timerCheckBox.isSelected(); 
    	String time = timeTextField.getText(); 
    	
    	if (time.length()==0 && timer) time="00:30"; 
    	else if (time.length()!=0 && !timer) {
    		try {
    			error("Please check timer option if you enter a time!"); 
    		} catch (IOException e) {
    			log.error(e.getMessage(), e);
    		}
    	}
    	else {
    		if (!time.matches("[0-9][0-3]:+[0-5][0-9]")) {
    			try {
        			error("Please use a 00:00 format!"); 
        		} catch (IOException e) {
        			log.error(e.getMessage(), e);
        		}
    		}
    		
    		else {
	    		PrivateUserEntity u=this.myIClientToIHM.getLocalUser(); 
	    		UUID enquirerUUID = u.getId(); 
	    		int timeInt=0; 
	    		if (timer) timeInt = this.conversionTime(time); 
	    		this.proposition = new PropositionProperty(u.getLogin(), timeInt, chattable, timer, observable); 
	    		this.IHMManager.setProposition(this.proposition);
	    		this.myIClientToIHM.createProposition(UUID.fromString(this.opponentUUID), /*enquirerUUID,*/ chattable,/*timer, timeInt,*/ observable);
	    		this.onCancelClicked(); 
	    		
		    	try {
		    		this.confirmation(); 
		    	} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
    		}
    	}	
    }
    
    private int conversionTime(String time) {
		String secondsString=time.substring(3, 5); 
		String minutesString=time.substring(0, 2); 
		int seconds=Integer.parseInt(secondsString)+(Integer.parseInt(minutesString)*60);  
		return seconds;
	}

	public SendPropositionController() {
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
    
    public void confirmation() throws IOException {
            Stage stage;
            Parent root;
            stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/confirmationPopUp.fxml"));
            root = (Pane) fxmlLoader.load();
            ConfirmationController controller = fxmlLoader.getController();
            controller.setControllerContext(this.IHMManager);
            controller.setMainApp(this.mainApp, "The game proposition has been sent!");
            stage.setScene(new Scene(root));
            stage.setTitle("Proposition success");
            mainApp.getCurrentStage().close();
            mainApp.setCurrentStage(stage);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
    }
    
    public FXMLLoader loadProposition() {
    	return new FXMLLoader(getClass().getResource("/fxml/answerPropositionPopUp.fxml"));
    }
    
    public void setMainApp(AppClient app, Label oppUUID, Label oppLogin ) {
        this.mainApp = app;
        this.opponentUUID=oppUUID.getText(); 
        this.opponentLogin.setText(oppLogin.getText());; 
    }
    
    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null) {
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
        }
        error = new ErrorProperty();
        this.IHMManager.setError(error);
        this.setListenersOnLoad(); 
    }

	@SuppressWarnings("restriction")
private void setListenersOnLoad() {
			this.sendPropositionButton.setOnAction(evt->{ //ou quand proposition change ?? pour que ça soit apres 
				 Stage stage;
	             Parent root = null;
	             stage = new Stage();
	             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AnswerPropositionPopUp.fxml"));
	             try {
	                    root = (Pane) fxmlLoader.load();
	                    AnswerPropositionController controller = fxmlLoader.getController();
	                    controller.setControllerContext(IHMManager);
	                    controller.setMainApp(mainApp, this.proposition.loginAskingPayerProperty().toString(), 
	                    		Boolean.valueOf(this.proposition.chattableProperty().toString()), Boolean.valueOf(this.proposition.timerProperty().toString()), 
	                    		Boolean.valueOf(this.proposition.observableProperty().toString()), Integer.parseInt(this.proposition.timeProperty().toString()));
	                    stage.setScene(new Scene(root));
	                    stage.setTitle("User Information");
	                    stage.initModality(Modality.APPLICATION_MODAL);
	                    stage.showAndWait();
	                } catch (IOException e) {
	                    log.error(e.getMessage(), e);
	                   }
	            }); 			
	    }
}