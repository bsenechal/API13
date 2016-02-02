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
    private IHMManager ihmManager;
    private ProfilProperty profile;
    private Stage currentStage;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private ObservableList<PublicUserEntity> listUserPublic;
    private ObservableList<GameEntity> listCurrentGames;
    private static final Logger LOGGER = Logger.getLogger(IHMWelcomePageController.class);

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
    TableView<GameEntity> currentGamesTable, savedGamesTable;
    @FXML
    TableView<PublicUserEntity> connectedUserTable;
    @FXML
    TableColumn<GameEntity, UUID> currentGamesId;
    @FXML
    TableColumn<GameEntity, String> currentGamesPlayer1, currentGamesPlayer2, currentGamesTime, currentGamesObs;

    @FXML
    TableColumn<PublicUserEntity, String> connectedUserLogin, connectedUserStatus, connectedUserStat,
            connectedUserActionIcon;
    @FXML
    SplitMenuButton paramSplitMenuButton;
    @FXML
    ScrollBar currentGamesScrollbar, savedGamesScrollbar, connectedUserScrollbar;

    public IHMWelcomePageController() {
        initialize();
    }

    public ProfilProperty getProfile() {
        return this.profile;
    }

    public void setProfile(ProfilProperty profile) {
        this.profile = profile;
    }

    public IHMManager getIHMManager() {
        return this.ihmManager;
    }

    public void setIHMManager(IHMManager iHMManager) {
        this.ihmManager = iHMManager;
    }

    @FXML
    private void onHelpClicked(Event event) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/helpPage.fxml"));
        root = (Pane) fxmlLoader.load();
        IHMHelpPageController controller = fxmlLoader.getController();
        controller.setControllerContext(this.ihmManager);
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
        controller.setControllerContext(this.ihmManager);
        mainApp.setCurrentStage(stage);
        controller.setMainApp(this.mainApp, this.userLabel);
        stage.setScene(new Scene(root));
        stage.setTitle("My Profile");
        stage.show();
    }

    @FXML
    public void onLogOutClicked() throws IOException {
        try {
            this.myIClientToIHM.disconnect();
        } catch (TechnicalException e) {
            try {
                error("Log out error : Technical Exception");
            } catch (IOException e1) {
                LOGGER.error(e1.getMessage(), e1);
            }
            LOGGER.error(e.getMessage(), e);
        }

        catch (FunctionalException e) {
            try {
                error("Log out error : Functional Exception");
            } catch (IOException e1) {
                LOGGER.error(e1.getMessage(), e1);
            }
            LOGGER.error(e.getMessage(), e);
        } finally {
            mainApp.getComClientManager().close();
        }

        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/connexionPage.fxml"));
        root = (Pane) fxmlLoader.load();
        IHMConnexionPageController controller = fxmlLoader.getController();
        controller.setControllerContext(ihmManager);
        mainApp.getMainStage().close();
        mainApp.setCurrentStage(stage);
        controller.setMainApp(mainApp);
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Connexion to MasterChess");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onExportClicked() throws IOException {
        File exportFile = null;
        try {
            exportFile = this.myIClientToIHM.exportProfile();
        } catch (TechnicalException e) {
            try {
                error("Export error : Technical Exception");
            } catch (IOException e1) {
                LOGGER.error(e1.getMessage(), e1);
            }
            LOGGER.error(e.getMessage(), e);
        }

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Export to ...");
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        if (selectedDirectory != null) {
            File newFile = new File(selectedDirectory.getAbsolutePath() + File.separator + exportFile.getName());
            if (selectedDirectory != null) {
                try {
                    Files.copy(exportFile.toPath(), newFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING,
                            java.nio.file.StandardCopyOption.COPY_ATTRIBUTES);
                    exportOK();
                } catch (IOException e) {
                    try {
                        exportNOK();
                    } catch (IOException e1) {
                        LOGGER.error(e1.getMessage(), e1);
                    }
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    @FXML
    public void onUserInfoClicked() throws IOException {

        Stage stage;
        Parent root = null;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/userInfoPopUp.fxml"));

        try {
            root = (Pane) fxmlLoader.load();
            UserInfoPopUpController controller = fxmlLoader.getController();
            controller.setControllerContext(this.ihmManager);
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
                LOGGER.error(e1.getMessage(), e1);
            }
            LOGGER.error(e.getMessage(), e);
        }
    }

    @FXML
    public void onMyInfoClicked() throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/myInfoPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        MyInfoPopUpController controller = fxmlLoader.getController();
        controller.setControllerContext(this.ihmManager);
        mainApp.setCurrentStage(stage);
        controller.setMainApp(this.mainApp, this.userLabel);
        stage.setScene(new Scene(root));
        stage.setTitle("My Information");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void initialize() {
    }

    public void setMainApp(AppClient app) {
        this.mainApp = app;
        PrivateUserEntity u = this.myIClientToIHM.getLocalUser();
        this.userLabel.setText(u.getLogin());
    }

    public void exportOK() throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/confirmationPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ConfirmationController controller = fxmlLoader.getController();
        controller.setControllerContext(this.ihmManager);
        mainApp.setCurrentStage(stage);
        controller.setMainApp("Successful export!");
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
        controller.setControllerContext(this.ihmManager);
        mainApp.setCurrentStage(stage);
        controller.setText("Export error!");
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
        this.ihmManager = ihmManager;
        if (ihmManager != null) {
            this.myIClientToIHM = ihmManager.getIClientDataToIHM();
            profile = new ProfilProperty();

            this.ihmManager.setProfil(profile);
            setListenersOnLoad();
            setBindingsOnLoad();
        }
    }

    public void setIHMMandClient(IHMManager ihmManager) {
        this.ihmManager = ihmManager;
        if (ihmManager != null)
            this.myIClientToIHM = ihmManager.getIClientDataToIHM();
    }

    public void setListenersOnLoad() {
        this.listUserPublic = myIClientToIHM.getUserList();
        this.listUserPublic.addListener(new ListChangeListener<PublicUserEntity>() {
            @Override
            public void onChanged(javafx.collections.ListChangeListener.Change<? extends PublicUserEntity> c) {
                connectedUserTable.setItems(myIClientToIHM.getUserList());
            }
        });

        connectedUserTable.setItems(this.listUserPublic);
        myIClientToIHM.getUsers();

        this.listCurrentGames = myIClientToIHM.getGamesList();
        this.listCurrentGames.addListener(new ListChangeListener<GameEntity>() {

            @Override
            public void onChanged(javafx.collections.ListChangeListener.Change<? extends GameEntity> c) {
                currentGamesTable.setItems(myIClientToIHM.getGamesList());
            }
        });

        currentGamesTable.setItems(this.listCurrentGames);
        myIClientToIHM.getAllGames();
        connectedUserTable.setOnMouseClicked(event -> connectedUserTable.getSelectionModel().clearSelection());
        connectedUserTable.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Object>() {

            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {

                if (connectedUserTable.getSelectionModel().getSelectedIndex() > -1) {
                    Optional.ofNullable(listUserPublic.get((int) newValue))
                            .ifPresent(user -> myIClientToIHM.getUserInfo(user.getId()));

                    Stage stage;
                    Parent root = null;
                    stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/userInfoPopUp.fxml"));
                    try {
                        root = (Pane) fxmlLoader.load();
                        UserInfoPopUpController controller = fxmlLoader.getController();
                        controller.setControllerContext(ihmManager);
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
                            LOGGER.error(e1.getMessage(), e1);
                        }
                        LOGGER.error(e.getMessage(), e);
                    }

                }

            }
        });

    }

    public void setBindingsOnLoad() {
        connectedUserLogin.setCellValueFactory(new PropertyValueFactory<PublicUserEntity, String>("Login"));
        connectedUserStatus.setCellValueFactory(new PropertyValueFactory<PublicUserEntity, String>("Status"));
        connectedUserStat.setCellValueFactory(new PropertyValueFactory<PublicUserEntity, String>("NbWon"));

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
        controller.setControllerContext(this.ihmManager);
        mainApp.setCurrentStage(stage);
        controller.setText(message);
        stage.setScene(new Scene(root));
        stage.setTitle("Error");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void setDisconnectUserByClosingWindow() {
        mainApp.getMainStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
                    onLogOutClicked();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        });
    }

}
