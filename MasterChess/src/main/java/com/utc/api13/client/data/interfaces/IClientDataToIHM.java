package com.utc.api13.client.data.interfaces;

import java.util.UUID;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PieceEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

import javafx.collections.ObservableList;


public interface IClientDataToIHM {
    
    public void getUsers();
    
    public void getUserInfo(final UUID iduser);
    
    public void getAllGames();
    
    /**
     * Connect a user to the app
     * @param login login of user
     * @param password password of user
     * @throws FunctionalException exception when login or password are incorrect
     * @throws TechnicalException technical exception
     */
    public void connect(final String login, final String password) throws FunctionalException, TechnicalException;
    
    public void disconnect();
    
    public void move(PieceEntity piece, PositionEntity position);
    
    public void observerLeave();
    
    public void requestPlayerForLeaving();
    
    public void sendAnserForLeaving(boolean answer);
    
    public void updateProfile(PrivateUserEntity user);
    
    public void sendUserUpdates(PublicUserEntity user);
    
    public void notify (String message);
    
    public void updateProfil (PublicUserEntity user);
    
    public void watchGame (String idGame);
    
    public void chargeReplayFromFile(String file);
    
    public void beginReplay();
    
    public void saveGame();
    
    public GameEntity getCurrentGame();
    
    public void createProposition(UUID uidReciever, boolean chattable, boolean observable);
    
    public void surrender();
    
    public void sendChatText(String message);

    public ObservableList<PublicUserEntity> getUserList();
    
    public void createProfil(String login, String firstName, String lastName) throws FunctionalException, TechnicalException;
}