package com.utc.api13.client.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PieceEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

import javafx.collections.ObservableSet;


public interface IClientDataToIHM {
    
    public void getUsers(List <PublicUserEntity> users);
    
    public PublicUserEntity getUserInfo(UUID iduser);
    
    public void getAllGames();
    
    /**
     * Connect a user to the app
     * @param login login of user
     * @param password password of user
     * @throws FunctionalException exception when login or password are incorrect
     * @throws TechnicalException technical exception
     */
    public void connect(String login, String password) throws FunctionalException, TechnicalException;
    
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
    
    /**
     * Sends message for chat
     * @param message message to send
     */
    public void sendChatText(String message);

    public ObservableSet<PublicUserEntity> getUserList();
    
    public void createProfil(String login, String firstName, String lastName) throws FunctionalException, TechnicalException;
}