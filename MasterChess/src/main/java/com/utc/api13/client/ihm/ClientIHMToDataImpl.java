package com.utc.api13.client.ihm;

import java.io.IOException;
import java.util.UUID;

import com.utc.api13.client.ihm.controllers.IHMWelcomePageController;
import com.utc.api13.client.ihm.controllers.UserInfoPopUpController;
import com.utc.api13.client.ihm.interfaces.IClientIHMToData;
//github.com/bsenechal/API13.git
import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
                        
                        }
                    });

    }

    @Override
    public void otherPlayerLeaving() {
        // TODO Auto-generated method stub

    }

    @Override
    public void displayProposition(UUID uidSender, boolean observable, boolean chattable) {
        // TODO Auto-generated method stub
        //à la fin, doit appeler la fonction de l'interface de data en rajoutant observable et chattable 
        //myIHMManager.getIClientDataToIHM().sendResponse(idUser, answer, observable, chattable);
    }

    @Override
    public void displayAnswer(UUID uidSender, boolean observable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void displayChessBoard() {
        // TODO Auto-generated method stub

    }

    @Override
    public void refreshChessBoard() {
        // TODO Auto-generated method stub

    }

    @Override
    public void displayMessage(String newMessage) {
        // TODO Auto-generated method stub

    }

    @Override
    public void refreshObserverList() {
        // TODO Auto-generated method stub

    }

    @Override
    public void displayGameLiveObserver() {
        // TODO Auto-generated method stub

    }
}