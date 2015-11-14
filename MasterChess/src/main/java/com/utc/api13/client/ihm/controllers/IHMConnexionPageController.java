package com.utc.api13.client.ihm.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import com.utc.api13.client.ihm.ClientToIHMImpl;

public class IHMConnexionPageController {
	
	ClientToIHMImpl clientToIHM ; 
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
	
	public IHMConnexionPageController() { 
		clientToIHM = new ClientToIHMImpl(); 
		//+ listeners
		initialize(); 
	}
	
	public void initialize() {
		//bindings
	}
	
	public void onSignInClicked() {
		
	}
	
	public void onSignUpClicked() {
		
	}
	
	public void onImportClicked() {
		
	}
	
	public void onExportClicked() {
		
	}
}