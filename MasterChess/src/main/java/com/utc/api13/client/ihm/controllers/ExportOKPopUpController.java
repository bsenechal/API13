package com.utc.api13.client.ihm.controllers;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.ihm.IHMManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ExportOKPopUpController {
    private IHMManager IHMManager;

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
    }

    public void setMainApp(AppClient app, String path) {
        this.popUpLabel3.setText(path);
    }

    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null)
            this.IHMManager.getIClientDataToIHM();
    }
}
