package com.utc.api13.client.ihm.controllers;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ErrorController {

    private IHMManager IHMManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;

    @FXML
    BorderPane errorBorderPane;
    @FXML
    Label errorLabel;
    @FXML
    AnchorPane errorAnchorPane;

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

}
