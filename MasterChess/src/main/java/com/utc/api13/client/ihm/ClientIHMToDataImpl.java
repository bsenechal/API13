package com.utc.api13.client.ihm;

import java.io.IOException;
import java.util.UUID;

import com.utc.api13.client.ihm.controllers.IHMWelcomePageController;
import com.utc.api13.client.ihm.controllers.UserInfoPopUpController;
import com.utc.api13.client.ihm.interfaces.IClientIHMToData;
//github.com/bsenechal/API13.git
import com.utc.api13.commun.entities.PublicUserEntity;

public class ClientIHMToDataImpl implements IClientIHMToData {

    private IHMManager myIHMManager;

    public ClientIHMToDataImpl(IHMManager pIHMManager) {
        myIHMManager = pIHMManager;
    }

    @Override
    public void displayProfile(PublicUserEntity u) {
        // onUserInfoClicked
        IHMWelcomePageController profileController = new IHMWelcomePageController();
        UserInfoPopUpController userDistantController = new UserInfoPopUpController();
        userDistantController.displayProfile(u);

        profileController.setControllerContext(this.myIHMManager);
        try {
            profileController.onUserInfoClicked();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void otherPlayerLeaving() {
        // TODO Auto-generated method stub

    }

    @Override
    public void displayProposition(UUID uidSender, boolean observable, boolean chattable) {
        // TODO Auto-generated method stub

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