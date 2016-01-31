package com.utc.api13.client.ihm.controllers;

import java.io.IOException;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.property.PropositionProperty;
import com.utc.api13.commun.exceptions.TechnicalException;

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

public class AnswerPropositionController {

    private IHMManager IHMManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private Stage currentStage;
    private final Logger log = Logger.getLogger(getClass());
    private boolean answer = true;
    private boolean chattable;
    private boolean observable;
    private boolean timer;
    private String timeString;
    private int timeInt;

    @FXML
    BorderPane answerPropositionBorderPane;
    @FXML
    AnchorPane answerPropositionAnchorPane;
    @FXML
    Label invintingPlayerLogin, invitationLabel, optionsLabel, chosenOptionsLabel;
    @FXML
    Button yesButton, noButton;

    public AnswerPropositionController() {
        initialize();
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
        controller.setControllerContext(this.IHMManager);
        controller.setMainApp(this.mainApp, message);
        stage.setScene(new Scene(root));
        stage.setTitle("Error");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void setMainApp(AppClient app, String login, Boolean chattable, Boolean timer, Boolean observable,
            int time) {
        this.mainApp = app;
        this.chattable = chattable;
        this.timer = timer;
        this.timeInt = time;
        int nbSecondes = time % 60;
        int nbMinutes = time / 60;
        this.timeString = Integer.toString(nbMinutes) + ":" + Integer.toString(nbSecondes);
        this.observable = observable;

        String options = new String("");
        if (chattable) {
            options.concat("chattable ; ");
        }
        if (observable) {
            options.concat("observable ; ");
        }
        if (timer) {
            options.concat("timer : " + timeString);
        }
        if (options.length() == 0) {
            options = "None";
        }

        this.chosenOptionsLabel.setText(options);
        this.invintingPlayerLogin.setText(login);
    }

    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null) {
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
        }
    }

    public void onYesClicked() {
        PrivateUserEntity u = this.myIClientToIHM.getLocalUser();
        UUID answeringUser = u.getId();
        try {
            this.IHMManager.getCurrentStage().close();
            this.myIClientToIHM.sendResponse(answeringUser, IHMManager.getUisender(), answer, observable, chattable,
                    timer, timeInt);
        } catch (TechnicalException e) {
            log.error(e.getMessage(), e);
            try {
                error("Error while sending proposition answer : technical exception");
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
        }
    }

    public void onNoClicked() {
        this.answer = false;
        this.onYesClicked();
    }

    public void setBindings(PropositionProperty proposition) {
        invintingPlayerLogin.textProperty().bind(proposition.loginAskingPayerProperty());
        optionsLabel.textProperty().bind(proposition.chattableProperty().asString()); // can
    }
}