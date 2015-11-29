package com.utc.api13.client.ihm.controllers; 

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class MyInfoPopUpController {
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
	@FXML
	Hyperlink modifyLink; 
		
	@FXML
	public void onModifyProfileClicked() {
		//lier avec fenetre de modif
	}
		
	public MyInfoPopUpController() { 
		initialize(); 
	}
		
	public void initialize() {
	}
		
	public void setMainApp(AppClient app) {
		this.mainApp=app; 
		PrivateUserEntity u=this.myIClientToIHM.getLocalUser(); 
	    this.userInfoLogin.setText(u.getLogin()); 
	    this.userInfoFirstName.setText(u.getFirstName()); 
	    this.userInfoLastName.setText(u.getLastName()); 
	    this.userInfoWon.setText(Integer.toString(u.getNbWon()));
	    this.userInfoLost.setText(Integer.toString(u.getNbLost()));
	    this.userInfoPlayed.setText(Integer.toString(u.getNbPlayed()));
	}
	
	public void setManager(IHMManager ihmManager){
		this.IHMManager = ihmManager;
		if(ihmManager!=null) this.myIClientToIHM=IHMManager.getIClientDataToIHM(); 
	}
}
	