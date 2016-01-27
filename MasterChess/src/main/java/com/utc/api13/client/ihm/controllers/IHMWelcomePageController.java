package com.utc.api13.client.ihm.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.property.ProfilProperty;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class IHMWelcomePageController {
    private IHMManager IHMManager;
    private ProfilProperty profile;
    public static Stage stageI;
    private Stage currentStage;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private ObservableList<PublicUserEntity> listUserPublic;
    private ObservableList<GameEntity> listCurrentGames;
    private final Logger log = Logger.getLogger(getClass());

    @FXML
    BorderPane mainBorderPane;
    @FXML
    SplitPane splitPane, centralSplitPane, rightSplitPane, splitPane3;
    @FXML
    AnchorPane anchorPane, rightAnchorPane, leftAnchorPane, bottomLeftAnchorPane, topLeftAnchorPane;
    @FXML
    ImageView iconHelp, iconParam, iconProfile, infoTest;
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

    public ProfilProperty getProfile() {
        return this.profile;
    }

    public void setProfile(ProfilProperty profile) {
        this.profile = profile;
    }

    public IHMManager getIHMManager() {
        return this.IHMManager;
    }

    public void setIHMManager(IHMManager iHMManager) {
        this.IHMManager = iHMManager;
    }

    @SuppressWarnings("restriction")
	@FXML
    private void onHelpClicked(Event event) throws IOException {
    	Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/helpPage.fxml"));
        root = (Pane) fxmlLoader.load();
        IHMHelpPageController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        controller.setCurrentStage(stage);
        controller.setMainApp(this.mainApp);
        stage.setScene(new Scene(root));
        stage.setTitle("Help page");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void onParamClicked(Event event) {
    }

    @FXML
    public void onModifyProfileClicked() throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/updateProfilePage.fxml"));
        root = (Pane) fxmlLoader.load();
        ModifyProfileController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        mainApp.setCurrentStage(stage);
        controller.setMainApp(this.mainApp);
        stage.setScene(new Scene(root));
        stage.setTitle("My Profile");
        stage.show();
    }

    @SuppressWarnings("restriction")
    @FXML
    public void onLogOutClicked() throws IOException {
        try {
            this.myIClientToIHM.disconnect();
        } catch (TechnicalException e) {
            try {
                error("Log out error : Technical Exception");
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
            log.error(e.getMessage(), e);
        }
           
        catch (FunctionalException e) {
            try {
                error("Log out error : Functional Exception");
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
            log.error(e.getMessage(), e);
        }
        finally {
            mainApp.getComClientManager().close();
        }
        
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/connexionPage.fxml"));
        root = (Pane) fxmlLoader.load();
        IHMConnexionPageController controller = fxmlLoader.getController();
        controller.setControllerContext(IHMManager);
        mainApp.getMainStage().close();
        mainApp.setCurrentStage(stage);
        controller.setMainApp(mainApp);
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Connexion to MasterChess");
        stage.setScene(scene);
        stage.show();
    }

    @SuppressWarnings("restriction")
    @FXML
    public void onExportClicked() throws IOException {
        File exportFile = null;
        try {
            exportFile = this.myIClientToIHM.exportProfile();
        } catch (TechnicalException e) {
            try {
                error("Export error : Technical Exception");
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
            log.error(e.getMessage(), e);
        }

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Export to ...");
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        File newFile = new File(selectedDirectory.getAbsolutePath() + File.separator + exportFile.getName());
        if (selectedDirectory != null) {
            try {
                // Attention : si la gestion d'erreur n'est pas faite,
                // exportFile est null et on a une belle NullPointerException
                Files.copy(exportFile.toPath(), newFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING,
                        java.nio.file.StandardCopyOption.COPY_ATTRIBUTES);
                exportOK(selectedDirectory.getAbsolutePath());
            } catch (IOException e) {
                try {
                    exportNOK();
                } catch (IOException e1) {
                    log.error(e1.getMessage(), e1);
                }
                log.error(e.getMessage(), e);
            }
        }
    }

    @SuppressWarnings("restriction")
    @FXML
    public void onUserInfoClicked() throws IOException {

        Stage stage;
        Parent root = null;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/userInfoPopUp.fxml"));

        try {
            root = (Pane) fxmlLoader.load();
            UserInfoPopUpController controller = fxmlLoader.getController();
            controller.setControllerContext(this.IHMManager);
            mainApp.setCurrentStage(stage);
            mainApp.getCurrentStage().close();
            mainApp.setCurrentStage(stage);
            controller.setMainApp(this.mainApp);
            stage.setScene(new Scene(root));
            stage.setTitle("User Information");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            try {
                error("Error when loading user info : IOException");
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
            log.error(e.getMessage(), e);
        }
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
        mainApp.setCurrentStage(stage);
        controller.setMainApp(this.mainApp);
        stage.setScene(new Scene(root));
        stage.setTitle("My Information");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void initialize() {
    }

    public IHMWelcomePageController() {
        initialize();
    }

    public void setMainApp(AppClient app) {
        this.mainApp = app;
        PrivateUserEntity u = this.myIClientToIHM.getLocalUser();
        this.userLabel.setText(u.getLogin());
    }

    public void exportOK(String path) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/confirmationPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ConfirmationController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        mainApp.setCurrentStage(stage);
        controller.setMainApp(this.mainApp, "Successful export!");
        stage.setScene(new Scene(root));
        stage.setTitle("Export success");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> stage.close());
        delay.play();
    }

    public void exportNOK() throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/errorPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ErrorController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        mainApp.setCurrentStage(stage);
        controller.setMainApp(this.mainApp, "Export error!");
        stage.setScene(new Scene(root));
        stage.setTitle("Export error");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void setListConnectedUser() {

    }

    public void setListCurrentGames() {

    }

    public void setListSavedGames() {

    }

    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null) {
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
            profile = new ProfilProperty();

            this.IHMManager.setProfil(profile);
            setListenersOnLoad();
            setBindingsOnLoad();
        }
    }

    public void setIHMMandClient(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null)
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
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

        this.listCurrentGames = myIClientToIHM.getGamesList();
        this.listCurrentGames.addListener // add listener on observableList in
                                          // DATA
        (new ListChangeListener<GameEntity>() {

            @Override
            public void onChanged(javafx.collections.ListChangeListener.Change<? extends GameEntity> c) {
                currentGamesTable.setItems(myIClientToIHM.getGamesList());
            }
        });

        currentGamesTable.setItems(this.listCurrentGames);
        myIClientToIHM.getAllGames(); // ask for list of game to DATA

        // Demande de la liste des parties sauvegardées
        // -------------------------------
        connectedUserTable.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Object>() {

            @SuppressWarnings("unchecked")
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                // TODO Auto-generated method stub

                Optional.ofNullable(listUserPublic.get((int) newValue))
                        .ifPresent(user -> myIClientToIHM.getUserInfo(user.getId()));

                Stage stage;
                Parent root = null;
                stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/userInfoPopUp.fxml"));
                try {
                    root = (Pane) fxmlLoader.load();
                    UserInfoPopUpController controller = fxmlLoader.getController();
                    controller.setControllerContext(IHMManager);
                    mainApp.setCurrentStage(stage);
                    controller.setMainApp(mainApp);
                    controller.setBindings(profile);
                    stage.setScene(new Scene(root));
                    stage.setTitle("User Information");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    try {
                        error("Error when loading user info : IOException");
                    } catch (IOException e1) {
                        log.error(e1.getMessage(), e1);
                    }
                    log.error(e.getMessage(), e);
                }
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

        currentGamesId.setCellValueFactory(new PropertyValueFactory<GameEntity, UUID>("id"));
        currentGamesPlayer1.setCellValueFactory(new PropertyValueFactory<GameEntity, String>("whitePlayerLogin"));
        currentGamesPlayer2.setCellValueFactory(new PropertyValueFactory<GameEntity, String>("blackPlayerLogin"));
        currentGamesTime.setCellValueFactory(new PropertyValueFactory<GameEntity, String>("creationDateDrawable"));

        currentGamesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        savedGamesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        connectedUserTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void displayProfile() {

    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void error(String message) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/errorPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ErrorController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        mainApp.setCurrentStage(stage);
        controller.setMainApp(this.mainApp, message);
        stage.setScene(new Scene(root));
        stage.setTitle("Error");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    // Function to make people disconnect when they click the cross to close the
    // window of Welcome Page
    public void setDisconnectUserByClosingWindow() {
        // catch event close window
        mainApp.getMainStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
                    onLogOutClicked();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
