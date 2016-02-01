package com.utc.api13.client.ihm.controllers;

import java.io.IOException;
import java.util.Optional;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GiveUpPopUpController {
    private IHMManager IHMManager;
    private Stage currentStage;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;

    @FXML
    BorderPane mainBorderPane;
    @FXML
    AnchorPane mainAnchorPane;
    @FXML
    Label opponentLabel, messageLabel;
    @FXML
    Button yesButton, noButton;

    public GiveUpPopUpController() {
        initialize();
    }

    @FXML
    public void OnYesClicked() {
        IHMManager.getCurrentStage().close();
        IHMManager.getCurrentGameStage().close();
        myIClientToIHM.sendAnswerForLeaving(true);

    }

    @FXML
    public void OnNoClicked() {
        IHMManager.getCurrentStage().close();
        IHMManager.getCurrentGameStage().close();
        myIClientToIHM.sendAnswerForLeaving(false);
    }

    public IHMManager getIHMManager() {
        return this.IHMManager;
    }

    public void setIHMManager(IHMManager iHMManager) {
        this.IHMManager = iHMManager;
    }

    public void initialize() {
    }

    public void setMainApp(AppClient app, String login) {
        this.mainApp = app;
        Optional.ofNullable(login).ifPresent(u -> messageLabel.setText(u + " " + messageLabel.getText()));
    }

    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null) {
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
        }
    }

    public void setIHMMandClient(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null)
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
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

    public void error(String message) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/errorPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ErrorController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        mainApp.setCurrentStage(stage);
        controller.setMainApp(this.mainApp, message);
        stage.setScene(new Scene(root));
        stage.setTitle("Error");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

}
