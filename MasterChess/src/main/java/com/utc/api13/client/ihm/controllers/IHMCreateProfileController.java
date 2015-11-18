package com.utc.api13.client.ihm.controllers; 
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import com.utc.api13.client.data.interfaces.IClientToIHM;
import com.utc.api13.client.ihm.ClientToIHMImpl;

public class IHMCreateProfileController {
    ClientToIHMImpl clientToIHM ;
    @FXML
    Label createProfileLabel, loginLabel, passwordLabel, firstNameLabel, lastNameLabel; 
    @FXML
    TextField loginTextView, passwordTextView, firstNameTextView, lastNameTextView; 
    @FXML
    Button saveProfileButton; 
    @FXML
    ImageView changeProfilePicture; 
    @FXML
    AnchorPane createProfileAnchorPane; 
    
    //methodes à relier au FXML 
    
	public IHMCreateProfileController() { 
		clientToIHM = new ClientToIHMImpl(); 
		initialize(); 
	}
	
	public void initialize() {
		
	}
	
}