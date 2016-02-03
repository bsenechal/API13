package com.utc.api13.client.ihm.controllers;

import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.property.ErrorProperty;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ErrorController {

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

    public void setText(String s) {
        this.errorLabel.setText(s);
    }

    public void initialize() {
        // Initialisation JavaFX
    }

    public void setManager(IHMManager ihmManager) {
        if (ihmManager != null)
            ihmManager.getIClientDataToIHM();
    }

    public void setControllerContext(IHMManager ihmManager) {
        if (ihmManager != null)
            ihmManager.getIClientDataToIHM();
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
