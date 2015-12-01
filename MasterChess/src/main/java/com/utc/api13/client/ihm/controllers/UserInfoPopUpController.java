package com.utc.api13.client.ihm.controllers; 

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class UserInfoPopUpController {
	private IHMManager IHMManager; 
	private AppClient mainApp;
	private IClientDataToIHM myIClientToIHM; 
	
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
		
	public UserInfoPopUpController() { 
		this.IHMManager = new IHMManager(); 
		this.myIClientToIHM=IHMManager.getIClientDataToIHM(); 
		initialize(); 
	}
		
	public void initialize() {
	}
		
	public void setMainApp(AppClient app /*,UUID idUser*/) {
		this.mainApp=app; 
		
		/*l'interface ne renvoie pas le bon type, il nous faut un user. ATTENTE DATA 
		 
		 PrivateUserEntity u = this.myIClientToIHM.getUserInfo(idUser); 
		 this.userInfoLogin.setText(u.getLogin()); 
	    this.userInfoFirstName.setText(u.getFirstName()); 
	    this.userInfoLastName.setText(u.getLastName()); 
	    this.userInfoWon.setText(Integer.toString(u.getNbWon()));
	    this.userInfoLost.setText(Integer.toString(u.getNbLost()));
	    this.userInfoPlayed.setText(Integer.toString(u.getNbPlayed()));
		 */ 
	}
	
	public void setControllerContext(IHMManager ihmManager){
		this.IHMManager = ihmManager;
		if(ihmManager!=null) this.myIClientToIHM=IHMManager.getIClientDataToIHM(); 
	}
}
	