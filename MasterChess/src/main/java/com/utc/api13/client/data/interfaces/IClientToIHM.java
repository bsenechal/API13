package com.utc.api13.client.data.interfaces;

import java.util.List;
import java.util.UUID;

import javafx.collections.ObservableSet;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PieceEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.entities.AUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;


public interface IClientToIHM {
    
    public void getUsers(List <AUserEntity> users);
    
    public AUserEntity getUserInfo(UUID iduser);
    
    public void getAllGames();
    
    public void connect(String login, String password);
    
    public void disconnect();
    
    public void move(PieceEntity piece, PositionEntity position);
    
    public void observerLeave();
    
    public void requestPlayerForLeaving();
    
    public void sendAnserForLeaving(boolean answer);
    
    public void updateProfile(PrivateUserEntity user);
    
    public void sendUserUpdates(PublicUserEntity user);
    
    public void notify (String message);
    
    public void updateProfil (AUserEntity user);
    
    public void watchGame (String idGame);
    
    public void chargeReplayFromFile(String file);
    
    public void beginReplay();
    
    public void saveGame();
    
    public GameEntity getCurrentGame();
    
    public void createProposition(UUID uidReciever, boolean chattable, boolean observable);
    
    public void surrender();
    
    public void sendChatText(String message);

    public ObservableSet<AUserEntity> getUserList();
    
    public void createProfil(String login, String firstName, String lastName) throws FunctionalException, TechnicalException;
}
