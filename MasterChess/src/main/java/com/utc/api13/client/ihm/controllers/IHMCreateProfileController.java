package com.utc.api13.client.ihm.controllers; 
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class IHMCreateProfileController {
	private IHMManager IHMManager; 
	private IClientDataToIHM myIClientToIHM; 
	
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
    
	public IHMCreateProfileController() { 
		
		// [DATA] : Le code ci-dessous Ne peut pas fonctionner :/
//		IHMManager = new IHMManager(); 
//		myIClientToIHM=IHMManager.getIClientDataToIHM(); 
		initialize();  
	}
	
	public void initialize() {
		
	}
	
}