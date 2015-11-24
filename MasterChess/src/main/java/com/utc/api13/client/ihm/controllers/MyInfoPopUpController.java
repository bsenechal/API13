package com.utc.api13.client.ihm.controllers; 

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientToIHM;
import com.utc.api13.client.ihm.IHMManager;


public class MyInfoPopUpController {
	private IHMManager IHMManager; 
	private AppClient mainApp;
	private IClientToIHM myIClientToIHM; 
	
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
		
	}
		
	public MyInfoPopUpController() { 
		this.IHMManager = new IHMManager(); 
		this.myIClientToIHM=IHMManager.getClientToIHM(); 
		initialize(); 
	}
		
	public void initialize() {
	}
		
	public void setMainApp(AppClient app) {
		this.mainApp=app; 
	      //initialiser avec login de l'user connecté : ATTENTE DATA 
	}
}
	