package com.utc.api13.client.ihm.controllers; 

import java.io.File;


import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class IHMCreateProfileController {
	private IHMManager IHMManager; 
	private AppClient mainApp;
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

    	String login=loginTextView.getText();  
		String pw=passwordTextView.getText(); 
		String firstName=firstNameTextView.getText();  
		String lastName=lastNameTextView.getText(); 

    	//appel de this.myIClientToIHM.createProfile(PrivateUserEntity u) et catch des exceptions
    	//modifier : idem avec this.myIClientToIHM.updateProfile(PrivateUserEntity u)

	}
    @FXML
    public void onChangePictureClicked() {
    	
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Ouvrir le document");
		fileChooser.setInitialDirectory(new File("/"));
		File f = fileChooser.showOpenDialog(new Stage());
		System.out.println(" le chemin est :"+f.getAbsolutePath());
		Image newProfil = null;
		try{
			
			String chemin=f.getAbsolutePath();
				 newProfil = new Image("file:///"+chemin);
			
		}catch(Exception e){
			System.out.println("erreur");
			e.printStackTrace();
		}
		if (newProfil!=null){
			changeProfilePicture.setImage(newProfil);
		}
		else{
			System.out.println("erreur with newProfil");
		}
	}
    
    
	public IHMCreateProfileController() { 
		
		// [DATA] : Le code ci-dessous Ne peut pas fonctionner émoticône unsure
//		IHMManager = new IHMManager(); 
//		myIClientToIHM=IHMManager.getIClientDataToIHM(); 

		initialize();  
	}
	
	public void initialize() {
		
	}
	
	public void setMainApp(AppClient app) {
		this.mainApp=app; 
	}
	
	public void setControllerContext(IHMManager ihmManager) 
    {
        this.IHMManager = ihmManager;
        if (ihmManager != null)
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
        setListenersOnLoad();
        setBindingsOnLoad();
    }

    public void setListenersOnLoad() 
    {

    }

    public void setBindingsOnLoad() 
    {
    }
	
}