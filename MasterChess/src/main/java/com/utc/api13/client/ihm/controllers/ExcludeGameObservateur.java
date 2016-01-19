package com.utc.api13.client.ihm.controllers;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExcludeGameObservateur {

    private IHMManager IHMManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private Stage currentStage;
    private final Logger log = Logger.getLogger(getClass());
    private UUID enquirerUUID;
    private ObservableList gameObserver;

    @FXML
    TableView observateurUserTable;
    @FXML
    TableColumn observateurUserLogin, observateurUserFirstName, observateurLastName;

    public ExcludeGameObservateur() {
        initialize();
    }

    public void initialize() {
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
        controller.setMainApp(this.mainApp, message);
        stage.setScene(new Scene(root));
        stage.setTitle("Error");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @SuppressWarnings("restriction")
    public void setMainApp(
            AppClient app /*
                           * , String login, Boolean chattable, Boolean timer,
                           * Boolean observable, int time
                           */) {
        this.mainApp = app;
    }

    @SuppressWarnings("unchecked")
    private void loadDataFromTable() {
        // TODO Auto-generated method stub

        gameObserver = FXCollections.observableList(myIClientToIHM.getCurrentGame().getObservers());

        observateurUserTable.setItems(gameObserver);

    }

    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null) {
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
        }
        setListenersOnLoad();
        setBindingsOnLoad();
    }

    public void setBindings() {

    }

    public void setListenersOnLoad() {

        loadDataFromTable();
        // selecting a line this user will be deleted from the Tchat

        observateurUserTable.getSelectionModel().selectedIndexProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    int newValue = newSelection.intValue();
                    Optional.ofNullable(gameObserver.get(newValue))
                            .ifPresent(user -> myIClientToIHM.removeUserFromChat(((PublicUserEntity) user).getId()));

                    getCurrentStage().close();// closing the current Stage

                });
    }

    public void setBindingsOnLoad() {

        observateurUserLogin.setCellValueFactory(new PropertyValueFactory<PublicUserEntity, String>("login"));
        observateurUserFirstName.setCellValueFactory(new PropertyValueFactory<PublicUserEntity, String>("firstName"));
        observateurLastName.setCellValueFactory(new PropertyValueFactory<PublicUserEntity, String>("lastName"));
    }

}