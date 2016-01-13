package com.utc.api13.client.ihm.controllers;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.property.ErrorProperty;
import com.utc.api13.client.ihm.property.ProfilProperty;
import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ErrorController {

    private IHMManager IHMManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private Stage currentStage;

    @FXML
    BorderPane errorBorderPane;
    @FXML
    Label errorLabel;
    @FXML
    AnchorPane errorAnchorPane;
    @FXML
    ImageView errorIcon;

    public ErrorController() {
        initialize();
    }

    public void setMainApp(AppClient app, String s) {
        this.mainApp = app;
        this.errorLabel.setText(s);
    }

    public void initialize() {
        // bindings
    }

    public void setManager(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null)
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
    }

    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null)
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
        setListenersOnLoad();
        setBindingsOnLoad();
    }

    public void setListenersOnLoad() {

    }

    public void setBindingsOnLoad() {

    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void setBindings(ErrorProperty errorProperty) {
        errorLabel.textProperty().bind(errorProperty.errorMessageProperty());
    }

}
