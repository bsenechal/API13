package com.utc.api13.client.ihm.controllers; 

import java.io.IOException;

import org.controlsfx.control.PopOver;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientToIHM;
import com.utc.api13.client.ihm.IHMFromDataImpl;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.commun.entities.AUserEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
	private IHMManager IHMManager; 
	private AppClient mainApp;
	private IClientToIHM myIClientToIHM; 
	
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
		myIClientToIHM.disconnect();  
	}
	@FXML
	public void onSettingsClicked() {
	}
	@FXML
	public void onUserInfoClicked() throws IOException  {
		Stage stage; 
		Parent root;
		stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/userInfoPopUp.fxml"));
		root = (Pane) fxmlLoader.load();
		UserInfoPopUpController controller = fxmlLoader.getController();
        controller.setMainApp(this.mainApp);
		stage.setScene(new Scene(root));
		stage.setTitle("User Information");
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.showAndWait();
	}
	
	@FXML
	public void onMyInfoClicked() throws IOException  {
		Stage stage; 
		Parent root;
		stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/myInfoPopUp.fxml"));
		root = (Pane) fxmlLoader.load();
		MyInfoPopUpController controller = fxmlLoader.getController();
        controller.setMainApp(this.mainApp);
		stage.setScene(new Scene(root));
		stage.setTitle("My Information");
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.showAndWait();
	}
	
	public IHMWelcomePageController() { 
		initialize(); 
	}
	
	public void initialize() {
	}
	
	public void setMainApp(AppClient app) {
		this.mainApp=app; 
		//this.currentGamesLabel.setText(""); 
        //initialiser avec login de l'user connecté : ATTENTE DATA 
	}
	
	public void setListConnectedUser() {
		
	}
	
	public void setListCurrentGames() {
		
	}
	
	public void setListSavedGames() {
		
	}
	
	public void setControllerContext(IHMManager ihmManager){
		this.IHMManager = ihmManager;
		if(ihmManager!=null) this.myIClientToIHM=IHMManager.getClientToIHM(); 
		setListenersOnLoad();
	}
	
	public void setListenersOnLoad()
	{
		// Demande de la liste des users
		//-------------------------------
		SetChangeListener<AUserEntity> connectedUserListListener;
		connectedUserListListener = change -> 
		{
			/*System.out.println("OK LISTENER Users");
			bindings => setListConnectedUser(list); + implémenter méthode*/
		};
		//myIClientToIHM.getUserList().addListener(connectedUserListListener);
		
		
		// Demande de la liste des jeux
		//-------------------------------
		SetChangeListener<GameEntity> currentGamesListListener;
		currentGamesListListener = change -> 
		{
			/*System.out.println("OK LISTENER Game");
			bindings => setListCurrentGames(list); + implémenter méthode*/
		};
		//myIClientToIHM.getAllGames().addListener(currentGamesListListener);
		
		
		// Demande de la liste des parties sauvegardées
		//-------------------------------
		SetChangeListener<GameEntity> savedGamesListListener;
		savedGamesListListener = change -> 
		{
			/*System.out.println("OK LISTENER Saved Game");
			bindings => setListSavedGames(list); + implémenter méthode*/
		};
		//myIClientToIHM.getSavedGames().addListener(savedGamesListListener);
		
	}
}
