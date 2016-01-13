package com.utc.api13.client.ihm;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.ihm.controllers.ErrorController;
import com.utc.api13.client.ihm.controllers.IHMWelcomePageController;
import com.utc.api13.client.ihm.controllers.SendPropositionController;
import com.utc.api13.client.ihm.interfaces.IClientIHMToData;
import com.utc.api13.commun.entities.GameEntity;
//github.com/bsenechal/API13.git
import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientIHMToDataImpl implements IClientIHMToData {

    private IHMManager myIHMManager;

    public ClientIHMToDataImpl(IHMManager pIHMManager) {
        myIHMManager = pIHMManager;
    }

    @Override
    public void displayProfile(PublicUserEntity u) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                myIHMManager.getProfil().loginProperty().set(u.getLogin());
                myIHMManager.getProfil().firstNameProperty().set(u.getFirstName());
                myIHMManager.getProfil().lastNameProperty().set(u.getLastName());
                myIHMManager.getProfil().statPlayerProperty().setAll(u);
                myIHMManager.getProfil().userUUID().set(u.getId().toString());
            }
        });
    }

    @Override
    public void otherPlayerLeaving() {
        // TODO Auto-generated method stub

    }

    @SuppressWarnings("restriction")
    @Override
    public void displayProposition(UUID uidSender, boolean observable,
            boolean chattable /* , boolean timer, Integer time, String login */) {
        // TODO Auto-generated method stub
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // myIHMManager.getProposition().loginAskingPayerProperty().set(login);
                myIHMManager.getProposition().chattableProperty().set(chattable);
                // myIHMManager.getProposition().timerProperty().set(timer);
                myIHMManager.getProposition().observableProperty().set(observable);
                // myIHMManager.getProposition().timeProperty().set(time);
            }
        });

    }

    @Override
    public void displayAnswer(UUID uidSender, boolean observable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void displayChessBoard(GameEntity g) {
        // TODO Auto-generated method stub
        /*
         * Platform.runLater(new Runnable() {
         * 
         * @Override public void run() { //trouver l'autre player
         * myIHMManager.getGame().otherPlayLoginProperty().set();
         * myIHMManager.getGame().setOtherPlayerUUID(g);
         * myIHMManager.getGame().setChattableProperty(g.getIsChattable());
         * myIHMManager.getGame().setObservableProperty(g.getIsOservable());
         * //myIHMManager.getGame().setTimerProperty(g.getIsTimed());
         * //myIHMManager.getGame().setTimerProperty().set(g.getTime()); //time
         * //myIHMManager.getGame().userUUID().set(u.getId().toString()); } });
         */
    }

    public void refreshChessBoard(int line_from, int col_from, int line_to, int col_to, String pieceType) {
    	// TODO Auto-generated method stub

    }

    @Override
    public void displayError(String errorMessage) {
        // TODO Auto-generated method stub
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                myIHMManager.getError().errorMessageProperty().set(errorMessage);
            }
        });
    }

    @Override
    public void displayConfirmation(String confirmationMessage) {
        // TODO Auto-generated method stub
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                myIHMManager.getConfirmation().confirmationMessageProperty().set(confirmationMessage);
            }
        });
    }

    @Override
    public void refreshObserverList() {
        // TODO Auto-generated method stub

    }

    @Override
    public void displayGameLiveObserver() {
        // TODO Auto-generated method stub

    }

    @Override
    public void displayMessage(String newMessage) {
        // TODO Auto-generated method stub

    }
}