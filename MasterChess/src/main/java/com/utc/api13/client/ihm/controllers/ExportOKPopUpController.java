package com.utc.api13.client.ihm.controllers;

import com.utc.api13.client.ihm.IHMManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ExportOKPopUpController {
    @FXML
    BorderPane popUpBorderPane;
    @FXML
    AnchorPane popUpAnchorPane;
    @FXML
    Label popUpLabel1, popUpLabel2, popUpLabel3;

    public ExportOKPopUpController() {
        initialize();
    }

    public void initialize() {
        // Initialisation JavaFX
    }

    public void setText(String path) {
        this.popUpLabel3.setText(path);
    }

    public void setControllerContext(IHMManager ihmManager) {
        if (ihmManager != null)
            ihmManager.getIClientDataToIHM();
    }
}
