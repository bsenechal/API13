package com.utc.api13.client.ihm.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.exceptions.TechnicalException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IHMWelcomePageController {
    private IHMManager IHMManager;

    public IHMManager getIHMManager() {
        return IHMManager;
    }

    public void setIHMManager(IHMManager iHMManager) {
        IHMManager = iHMManager;
    }

    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private ObservableList<PublicUserEntity> listUserPublic;
    private ObservableList<GameEntity> listCurrentGames;

    @FXML
    BorderPane mainBorderPane;
    @FXML
    SplitPane splitPane, centralSplitPane, rightSplitPane, splitPane3;
    @FXML
    AnchorPane anchorPane, rightAnchorPane, leftAnchorPane, bottomLeftAnchorPane, topLeftAnchorPane;
    @FXML
    ImageView iconHelp, iconParam, iconProfile, iconNotif, infoTest;
    @FXML
    Label title, currentGamesLabel, savedGamesLabel, connectedUsersLabel;
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
    public void onModifyProfileClicked() throws IOException {
        MyInfoPopUpController profileController = new MyInfoPopUpController();
        profileController.setIHMManager(this.IHMManager);
        profileController.onModifyProfileClicked();

    }

    @FXML
    public void onLogOutClicked() {
        // NB : pas d'exception prévu par data = normal ??
        this.myIClientToIHM.disconnect();
    }

    @FXML
    public void onExportClicked() throws IOException {
        File exportFile = null;
        try {
            exportFile = this.myIClientToIHM.exportProfile();
        } catch (TechnicalException e1) {
            // TODO Faire la gestion d'erreur
            e1.printStackTrace();
        }
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Export to ...");
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        if (selectedDirectory != null) {
            try {
                // Attention : si la gestion d'erreur n'est pas faite,
                // exportFile est null et on a une belle NullPointerException
                Files.copy(exportFile.toPath(), selectedDirectory.toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING,
                        java.nio.file.StandardCopyOption.COPY_ATTRIBUTES);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // pop up de confirmation
            this.exportOK(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    public void onUserInfoClicked() throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/userInfoPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        UserInfoPopUpController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        controller.setMainApp(this.mainApp);
        stage.setScene(new Scene(root));
        stage.setTitle("User Information");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    public void onMyInfoClicked() throws IOException {
        /*
         * clic => appel de myIClientToIHM.getUserInfo(uuid) rien d'autre dans
         * cette fonction dans l'interface displayProfile, appel d'une autre
         * fonction qui fait lancement de l'écran pop up qui set les bonnes
         * infos
         */

        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/myInfoPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        MyInfoPopUpController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
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
        // bindings
        // TODO Demande de la liste des users connectés
        // IClientToIHM.getUsers();
        // TODO Demande de la liste des jeux
        // getAllGames();
    }

    public void setMainApp(AppClient app) {
        this.mainApp = app;
        PrivateUserEntity u = this.myIClientToIHM.getLocalUser();
        this.connectedUserLabel.setText(u.getLogin());
        setListenersOnLoad();
        setBindingsOnLoad();
    }

    public void exportOK(String path) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/exportOKPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ExportOKPopUpController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        controller.setMainApp(this.mainApp, path);
        stage.setScene(new Scene(root));
        stage.setTitle("Export success");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void setListConnectedUser() {

    }

    public void setListCurrentGames() {

    }

    public void setListSavedGames() {

    }

    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null)
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
        setListenersOnLoad();
        setBindingsOnLoad();
    }

    public void setListenersOnLoad() {
        // Demande de la liste des users
        // -------------------------------
        this.listUserPublic = myIClientToIHM.getUserList();
        this.listUserPublic.addListener // add listener on observableList in
                                        // DATA
        (new ListChangeListener<PublicUserEntity>() {
            @Override
            public void onChanged(javafx.collections.ListChangeListener.Change<? extends PublicUserEntity> c) {
                connectedUserTable.setItems(myIClientToIHM.getUserList());
            }
        });

        connectedUserTable.setItems(this.listUserPublic);
        myIClientToIHM.getUsers(); // ask for list of user to DATA

        // Demande de la liste des jeux
        // -------------------------------
        /*
         * this.listCurrentGames= myIClientToIHM.getGameList();
         * this.listCurrentGames.addListener // add listener on observableList
         * in DATA ( new ListChangeListener<GameEntity>() {
         * 
         * @Override public void
         * onChanged(javafx.collections.ListChangeListener.Change<? extends
         * GameEntity> c) {
         * currentGamesTable.setItems(myIClientToIHM.getGameList()); } } );
         * 
         * listCurrentGames.setItems(this.listCurrentGames);
         * myIClientToIHM.getCurrentGame(); // ask for list of game to DATA
         */

        // Demande de la liste des parties sauvegardées
        // -------------------------------
        connectedUserTable.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Object>() {

            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                // TODO Auto-generated method stub

                Optional.ofNullable(listUserPublic.get((int) newValue))
                        .ifPresent(user -> myIClientToIHM.getUserInfo(user.getId()));
            }

        });
    }

    public void setBindingsOnLoad() {
        // liste des users
        // ---------------
        connectedUserLogin.setCellValueFactory(new PropertyValueFactory<PublicUserEntity, String>("Login"));
        connectedUserStatus.setCellValueFactory(new PropertyValueFactory<PublicUserEntity, String>("Status"));
        connectedUserStat.setCellValueFactory(new PropertyValueFactory<PublicUserEntity, String>("NbWon"));

        // liste des jeux en cours
        // ---------------
        /*
         * currentGamesId.setCellValueFactory(new
         * PropertyValueFactory<PublicUserEntity, String>("Login")); ID????
         * currentGamesPlayer1.setCellValueFactory(new
         * PropertyValueFactory<PublicUserEntity, String>("whitePlayer"));
         * currentGamesPlayer2.setCellValueFactory(new
         * PropertyValueFactory<PublicUserEntity, String>("blackPlayer"));
         * currentGamesTime.setCellValueFactory(new
         * PropertyValueFactory<PublicUserEntity, Date>("creationDate"));
         */

    }

}
