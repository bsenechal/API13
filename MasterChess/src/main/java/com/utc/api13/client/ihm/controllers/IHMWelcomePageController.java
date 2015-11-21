package com.utc.api13.client.ihm.controllers; 

import com.utc.api13.client.data.interfaces.IClientToIHM;
import com.utc.api13.client.ihm.ClientToIHMImpl;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class IHMWelcomePageController {
	
	//+ tous les listeners
	ClientToIHMImpl clientToIHM ; 
	@FXML
	BorderPane mainBorderPane; 
	@FXML
	SplitPane splitPane, centralSplitPane, rightSplitPane, splitPane3; 
	@FXML
	AnchorPane anchorPane, rightAnchorPane, leftAnchorPane, bottomLeftAnchorPane, topLeftAnchorPane; 
	@FXML
	ImageView iconHelp, iconParam, iconProfile, iconNotif; 
	@FXML
	Label title, currentGamesLabel, savedGamesLabel, connectedUserLabel; 
	@FXML
	Text userLabel; 
	@FXML
	TableView currentGamesTable, savedGamesTable, connectedUserTable; 
	@FXML
	TableColumn currentGamesId, currentGamesPlayer1, currentGamesPlayer2, currentGamesTime, currentGamesObs;
	@FXML
	TableColumn savedGamesId, savedGamesPlayer1, savedGamesPlayer2, savedGamesDate, savedGamesReplay;
	@FXML
	TableColumn connectedUserLogin, connectedUserStatus, connectedUserStat, connectedUserActionIcon;
	@FXML
	SplitMenuButton paramSplitMenuButton; 
	@FXML
	ScrollBar currentGamesScrollbar, savedGamesScrollbar, connectedUserScrollbar; 
	@FXML
	private void onHelpClicked(Event event) {
	}
	@FXML
	private void onParamClicked(Event event) {
	}
	@FXML
	private void onNotifClicked(Event event) {
	}
	@FXML
	public void onModifyProfileClicked() { //demander à Pierre pourquoi annotation fxml
	}
	@FXML
	public void onLogOutClicked() {
	}
	@FXML
	public void onSettingsClicked() {	
	}
	
	
	public IHMWelcomePageController() { 
		clientToIHM = new ClientToIHMImpl(); 
		initialize(); 
	}
	
	public void initialize() {
		//bindings
		// TODO Demande de la liste des users connectés
		//IClientToIHM.getUsers();
		// TODO Demande de la liste des jeux
		//getAllGames();
	}
	
	public void setListConnectedUser() {
		
	}
	
	public void setListCurrentGames() {
		
	}
	
	public void setListSavedGames() {
		
	}

}