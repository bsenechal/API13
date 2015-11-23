package com.utc.api13.client.ihm.controllers; 

import java.io.IOException;

import org.controlsfx.control.PopOver;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientToIHM;
import com.utc.api13.client.ihm.ClientToIHMImpl;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IHMWelcomePageController {
	
	//+ tous les listeners
	private ClientToIHMImpl clientToIHM ; 
	private AppClient mainApp;
	//popup user info
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
	
	//main window
	@FXML
	BorderPane mainBorderPane; 
	@FXML
	SplitPane splitPane, centralSplitPane, rightSplitPane, splitPane3; 
	@FXML
	AnchorPane anchorPane, rightAnchorPane, leftAnchorPane, bottomLeftAnchorPane, topLeftAnchorPane; 
	@FXML
	ImageView iconHelp, iconParam, iconProfile, iconNotif, infoTest; 
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
	public void onModifyProfileClicked() { 
	}
	@FXML
	public void onLogOutClicked() {
		//appeler methode deconnexion
	}
	@FXML
	public void onSettingsClicked() {
	}
	@FXML
	public void onUserInfoClicked() throws IOException  {
		Stage stage; 
		Parent root;
		stage = new Stage();
		root = FXMLLoader.load(getClass().getResource("/fxml/userInfoPopUp.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("User Information");
		stage.initModality(Modality.APPLICATION_MODAL);
		setPopUp(); 
		stage.showAndWait();
	}
	
	@FXML
	public void onMyInfoClicked() throws IOException  {
		Stage stage; 
		Parent root;
		stage = new Stage();
		root = FXMLLoader.load(getClass().getResource("/fxml/userInfoPopUp.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("My Profile");
		stage.initModality(Modality.APPLICATION_MODAL);
		//setPopUp(); 
		stage.showAndWait();
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
	
	public void setMainApp(AppClient mainApp) {
        this.mainApp = mainApp;
        //initialiser avec login de l'user connecté 
	}
	
	public void setPopUp() {
        //initialiser tous les labels 
	}
	
	public void setListConnectedUser() {
		
	}
	
	public void setListCurrentGames() {
		
	}
	
	public void setListSavedGames() {
		
	}
	
	public void onTestClicked() {	
	}

}
