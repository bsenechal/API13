package com.utc.api13.client.ihm.controllers; 
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import com.utc.api13.client.data.interfaces.IClientDataToIHM;
//TODO: UME : why ?
//import com.utc.api13.client.ihm.ClientToIHMImpl;

public class IHMCreateProfileController {
    //ClientToIHMImpl clientToIHM ;
    @FXML
    BorderPane createProfileBorderPane; 
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
    @FXML
    public void onSaveProfileClicked() {
	}
    @FXML
    public void onChangePictureClicked() {
	}
    
    //methodes à relier au FXML 
    
	public IHMCreateProfileController() { 
		//TODO: UME : ??
		//clientToIHM = new ClientToIHMImpl(); 
		initialize(); 
	}
	
	public void initialize() {
		
	}
	
}