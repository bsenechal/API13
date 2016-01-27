
package com.utc.api13.client.ihm.controllers;

import org.apache.log4j.Logger;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class IHMHelpPageController {

    private IHMManager IHMManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private Stage currentStage;
    private final Logger log = Logger.getLogger(getClass());

    @FXML
    BorderPane helpBorderPane;
    @FXML
    AnchorPane helpAnchorPane;
    @FXML
    Button closeButton;
    @FXML
    ImageView helpImage;
    @FXML
    TabPane helpTabPane;
    @FXML
    Hyperlink rulesHyperlink;

    @FXML
    public void onBackClicked(Event event) {
        this.getCurrentStage().close();
    }

    @FXML
    public void onRulesClicked(Event event) {
        this.mainApp.getHostServices().showDocument("https://en.wikipedia.org/wiki/Rules_of_chess");
    }

    public IHMHelpPageController() {
        initialize();
    }

    public void setMainApp(AppClient app) {
        this.mainApp = app;
    }

    public void initialize() {
    }

    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null)
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

}
