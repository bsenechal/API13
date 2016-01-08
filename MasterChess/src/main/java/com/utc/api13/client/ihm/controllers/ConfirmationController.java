package com.utc.api13.client.ihm.controllers;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.property.ErrorProperty;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConfirmationController {

    private IHMManager IHMManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private Stage currentStage;

    @FXML
    BorderPane confirmationBorderPane;
    @FXML
    Label confirmationLabel;
    @FXML
    AnchorPane confirmationAnchorPane;
    @FXML
    ImageView confirmationIcon; 

    public ConfirmationController() {
        initialize();
    }

    public void setMainApp(AppClient app, String s) {
        this.mainApp = app;
        this.confirmationLabel.setText(s);
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
        confirmationLabel.textProperty().bind(errorProperty.errorMessageProperty());
    }
}
