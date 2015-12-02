package com.utc.api13.client.ihm.controllers; 

import java.io.IOException;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MyInfoPopUpController {
	private IHMManager IHMManager; 
	private AppClient mainApp;
	private IClientDataToIHM myIClientToIHM; 
	private boolean newProfile=false;
    
    public boolean isNewProfile() {
          return newProfile;
      }

      public void setNewProfile(boolean newProfile) {
          this.newProfile = newProfile;
      }
	
	@FXML
	BorderPane userInfoBorderPane;  
	@FXML
	AnchorPane userInfoAnchorPane; 
	@FXML
	Label userInfoLogin, userInfoFirstName, userInfoLastName; 
	@FXML
	TableView userInfoTableView; 
	@FXML
	TableColumn userInfoWon, userInfoLost, userInfoPlayed; 
	@FXML
	Hyperlink modifyLink; 
		
	@FXML
	public void onModifyProfileClicked() throws IOException {
		
		Stage stage; 
		Parent root;
		stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/createProfilePage.fxml"));
		root = (Pane) fxmlLoader.load();
		IHMManageProfileController controller = fxmlLoader.getController();
		controller.setControllerContext(this.IHMManager);
		if(newProfile){
		    controller.setNewProfile(newProfile);
		}
		    controller.setMainApp(this.mainApp);
		stage.setScene(new Scene(root));
		stage.setTitle("My Profile");
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.showAndWait();
		
	}
	 
	public IHMManager getIHMManager() {
		return IHMManager;
	}

	public void setIHMManager(IHMManager iHMManager) {
		IHMManager = iHMManager;
	}

	public MyInfoPopUpController() { 
		initialize(); 
	}
		
	public void initialize() {
	}
		
	public void setMainApp(AppClient app) {
		this.mainApp=app; 
		
		if(!newProfile){
		PrivateUserEntity u=this.myIClientToIHM.getLocalUser();
		
    	    this.userInfoLogin.setText(u.getLogin()); 
    	    this.userInfoFirstName.setText(u.getFirstName()); 
    	    this.userInfoLastName.setText(u.getLastName()); 
    	    this.userInfoWon.setText(Integer.toString(u.getNbWon()));
    	    this.userInfoLost.setText(Integer.toString(u.getNbLost()));
    	    this.userInfoPlayed.setText(Integer.toString(u.getNbPlayed()));
		}
	    
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
	