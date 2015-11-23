package com.utc.api13.client.ihm.controllers;

import com.utc.api13.client.data.interfaces.IClientToIHM;
import com.utc.api13.client.ihm.IHMManager;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class IHMConnexionPageController {
	
	private IHMManager IHMManager; 
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
	private void onSignInClicked(Event event) {
	 // TODO Demande des infos sur le user
	 //IClientToIHM.getUserInfo();
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
	
	public void initialize() {
		//bindings
	}
		 
}