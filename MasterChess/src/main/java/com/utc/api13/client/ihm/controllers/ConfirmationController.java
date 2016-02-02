package com.utc.api13.client.ihm.controllers;

import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.property.ErrorProperty;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConfirmationController {

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

    public void setMainApp(String s) {
        this.confirmationLabel.setText(s);
    }

    public void initialize() {
    }

    public void setManager(IHMManager ihmManager) {
        if (ihmManager != null)
            ihmManager.getIClientDataToIHM();
    }

    public void setControllerContext(IHMManager ihmManager) {
        if (ihmManager != null)
            ihmManager.getIClientDataToIHM();
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
