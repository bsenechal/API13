package com.utc.api13.client.ihm.controllers;

import java.io.IOException;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.property.ErrorProperty;
import com.utc.api13.client.ihm.property.PropositionProperty;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SendPropositionController {

    private IHMManager ihmManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private Stage currentStage;
    private static final Logger LOGGER = Logger.getLogger(SendPropositionController.class);
    private String opponentUUID;
    private ErrorProperty error;
    boolean chattable;
    private PropositionProperty proposition;

    @FXML
    BorderPane sendPropositionBorderPane;
    @FXML
    AnchorPane sendPropositionAnchorPane;
    @FXML
    Label playerLabel, opponentLogin, optionLabel;
    @FXML
    CheckBox chatCheckBox, timerCheckBox, observerCheckBox;
    @FXML
    TextField timeTextField;
    @FXML
    Button sendPropositionButton, cancelButton;

    public SendPropositionController() {
        initialize();
    }
    
    public void onCancelClicked() {
        mainApp.getCurrentStage().close();
    }

    public void onSendPropositionClicked() {

        chattable = chatCheckBox.isSelected();
        boolean observable = observerCheckBox.isSelected();
        boolean timer = timerCheckBox.isSelected();
        String time = timeTextField.getText();

        if (time.length() == 0 && timer)
            time = "00:30";

        if (time.length() != 0 && !timer) {
            try {
                error("Please check timer option if you enter a time!");
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        if (timer && !time.matches("[0-9][0-3]:+[0-5][0-9]")) {

            try {
                error("Please use a 00:00 format!");
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        PrivateUserEntity u = this.myIClientToIHM.getLocalUser();
        UUID enquirerUUID = u.getId();
        int timeInt = 0;
        if (timer)
            timeInt = conversionTime(time);

        ihmManager.setProposition(this.proposition);
        this.myIClientToIHM.createProposition(UUID.fromString(this.opponentUUID), enquirerUUID, chattable, observable,
                timer, timeInt);
        this.onCancelClicked();

        try {
            this.confirmation();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
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
        controller.setControllerContext(ihmManager);
        controller.setText(message);
        stage.setScene(new Scene(root));
        stage.setTitle("Error");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void confirmation() throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/confirmationPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ConfirmationController controller = fxmlLoader.getController();
        controller.setControllerContext(ihmManager);
        controller.setMainApp("The game proposition has been sent!");
        stage.setScene(new Scene(root));
        stage.setTitle("Proposition success");
        mainApp.getCurrentStage().close();
        mainApp.setCurrentStage(stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> stage.close());
        delay.play();
    }

    public FXMLLoader loadProposition() {
        return new FXMLLoader(getClass().getResource("/fxml/answerPropositionPopUp.fxml"));
    }

    public void setMainApp(AppClient app, Label oppUUID, Label oppLogin) {
        mainApp = app;
        this.opponentUUID = oppUUID.getText();
        this.opponentLogin.setText(oppLogin.getText());

    }

    public void setControllerContext(IHMManager ihmManager) {
        this.ihmManager = ihmManager;
        if (ihmManager != null) {
            this.myIClientToIHM = ihmManager.getIClientDataToIHM();
        }
        error = new ErrorProperty();
        ihmManager.setError(error);
        this.setListenersOnLoad();
    }

    private static int conversionTime(String time) {
        String secondsString = time.substring(3, 5);
        String minutesString = time.substring(0, 2);
        return Integer.parseInt(secondsString) + (Integer.parseInt(minutesString) * 60);
    }
    
    private void setListenersOnLoad() {
    }

    public void displayPopup() {
    }
}