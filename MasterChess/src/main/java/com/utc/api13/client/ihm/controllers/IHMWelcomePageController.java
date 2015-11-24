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

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
	public void onUserInfoClicked() throws IOException {
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
	public void onMyInfoClicked() throws IOException {
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
	}

	public void setMainApp(AppClient app) {
		this.mainApp = app;
		// this.currentGamesLabel.setText("");
		// initialiser avec login de l'user connecté : ATTENTE DATA
	}

	private ObservableList<AUserEntity> observableListConnectedUser = FXCollections.observableArrayList(); 

	public void setControllerContext(IHMManager ihmManager) {
		this.IHMManager = ihmManager;
		if (ihmManager != null)
			this.myIClientToIHM = IHMManager.getClientToIHM();
		setListenersOnLoad();
		setBindingsOnLoad();
	}

	public void setListenersOnLoad() {
		// Demande de la liste des users
		// -------------------------------
		observableListConnectedUser.addListener
		(
				new ListChangeListener<AUserEntity>() 
				{
					@Override
					public void onChanged(javafx.collections.ListChangeListener.Change<? extends AUserEntity> c) 
					{
						connectedUserTable.setItems(observableListConnectedUser);
					}
				}
		 );

		setBouchon(); // a retirer lorsque data OK et remplacer l'objet du listener
		
		// Demande de la liste des jeux
		// -------------------------------
		/*SetChangeListener<GameEntity> currentGamesListListener;
		currentGamesListListener = change -> {
			
			 * System.out.println("OK LISTENER Game"); bindings =>
			 * setListCurrentGames(list); + implémenter méthode
			 
		};*/
		// myIClientToIHM.getAllGames().addListener(currentGamesListListener);

		// Demande de la liste des parties sauvegardées
		// -------------------------------
		/*SetChangeListener<GameEntity> savedGamesListListener;
		savedGamesListListener = change -> {
			
			 * System.out.println("OK LISTENER Saved Game"); bindings =>
			 * setListSavedGames(list); + implémenter méthode
			 
		};*/
		// myIClientToIHM.getSavedGames().addListener(savedGamesListListener);

	}
	
	public void setBindingsOnLoad() 
	{
		//liste des users
		//---------------
		connectedUserLogin.setCellValueFactory(new PropertyValueFactory<AUserEntity, String>("Login"));
		connectedUserStatus.setCellValueFactory(new PropertyValueFactory<AUserEntity, String>("Status"));
		connectedUserStat.setCellValueFactory(new PropertyValueFactory<AUserEntity, String>("NbWon"));
	}
	
	public void setBouchon() {
		/*bouchon => ajouter setListConnectedUser() sur un listener de bouton pour test temporaire*/
		AUserEntity userEntity1 = new PublicUserEntity();
		AUserEntity userEntity2= new PublicUserEntity();
		userEntity1.setLogin("login1");
		userEntity1.setStatus(true);
		userEntity1.setNbPlayed(20);
        userEntity1.setNbWon(12);
		userEntity2.setLogin("login2");
		userEntity2.setStatus(false);
		userEntity2.setNbPlayed(267);
        userEntity2.setNbWon(123);
        observableListConnectedUser.add(userEntity1);
        observableListConnectedUser.add(userEntity2);
		/*END Bouchon*/
	}
}
