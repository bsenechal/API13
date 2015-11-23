package com.utc.api13.client.ihm.controllers;

import java.io.IOException;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientToIHM;
import com.utc.api13.client.ihm.IHMManager;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class IHMConnexionPageController {
	
	private IHMManager IHMManager; 
	private AppClient mainApp;
	private IClientToIHM myIClientToIHM;
	
	@FXML
	BorderPane connexionBorderPane;
	@FXML
	Label connexionLabel, loginLabel, passwordLabel, serverAddressLabel ; 
	@FXML
	AnchorPane connexionAnchorPane; 
	@FXML
	Button connexionButton; 
	@FXML
	TextField loginTextView, passwordTextView, serverAddressTextView; 
	@FXML
	Hyperlink importLink, exportLink, signUpLink; 
	@FXML
	private void onSignInClicked(Event event) throws IOException { 
		String login=loginTextView.getText();  
		System.out.println(login); 
		String pw=passwordTextView.getText(); 
		System.out.println(pw); 
		myIClientToIHM.connect(login, pw); //à tester à l'intégration
		
		Stage stage; 
		Parent root;
		stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/welcomePage.fxml"));
		root = (Pane) fxmlLoader.load();
		IHMWelcomePageController controller = fxmlLoader.getController();
        controller.setMainApp(mainApp);
		Scene scene = new Scene(root, 800, 600);
		stage.setTitle("Connexion to MasterChess");
		stage.setScene(scene);
		
		mainApp.stage.close(); 
		stage.show();
	}
	@FXML
	private void onImportClicked(Event event) {
	    
	}
	@FXML
	private void onExportClicked(Event event) {
	    
	}
	@FXML
	private void onSignUpClicked(Event event) {
	    
	}
	
	
	public IHMConnexionPageController() { 
		IHMManager = new IHMManager(); 
		myIClientToIHM=IHMManager.getClientToIHM();
		initialize(); 
	}
	
	public void setMainApp(AppClient app) {
		this.mainApp=app; 
	}
	
	public void initialize() {
		//bindings
	}
		 
}