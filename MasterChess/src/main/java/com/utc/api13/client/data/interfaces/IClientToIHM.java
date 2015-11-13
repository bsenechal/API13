package com.utc.api13.client.data.interfaces;

import java.util.List;
import java.util.UUID;
/*
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PieceEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.entities.PrivateUserEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.UserEntity; */

//FICHIER A SUPPRIMER AVANT LINTEGRATION
public interface IClientToIHM {
    
    //public void getUsers(List <UserEntity> users);
    
    //public UserEntity getUserInfo(UUID iduser);
    
    public void getAllGames();
    
    public void connect(String login, String password);
    
    public void disconnect();
    
    //public void move(PieceEntity piece, PositionEntity position);
    
    public void observerLeave();
    
    public void requestPlayerForLeaving();
    
    public void sendAnserForLeaving(boolean answer);
    
    //public void updateProfile(PrivateUserEntity user);
    
    //public void sendUserUpdates(PublicUserEntity user);
    
    public void notify (String message);
    
    //public void updateProfil (UserEntity user);
    
    public void watchGame (String idGame);
    
    public void chargeReplayFromFile(String file);
    
    public void beginReplay();
    
    public void saveGame();
    
    //public GameEntity getCurrentGame();
    
    public void createProposition(UUID uidReciever, boolean chattable, boolean observable);
    
    public void surrender();
    
    public void sendChatText(String message);
}