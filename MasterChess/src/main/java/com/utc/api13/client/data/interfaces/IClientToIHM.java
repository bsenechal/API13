package com.utc.api13.client.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.IGameEntity;
import com.utc.api13.IMoveEntity;
import com.utc.api13.IUserEntity;
import com.utc.api13.IPieceEntity;
import com.utc.api13.IPositionEntity;

public interface IClientToIHM {
    
    public void getUsers(List <IUserEntity> users);
    
    public IUserEntity getUserInfo(UUID iduser);
    
    public void getAllGames();
    
    public void connect(String login, String password);
    
    public void disconnect();
    
    public void move(IPieceEntity piece, IPositionEntity position);
    
    public void observerLeave();
    
    public void requestPlayerForLeaving();
    
    public void sendAnserForLeaving(boolean answer);
    
    public void updateProfile(IPrivateUserEntity user);
    
    public void sendUserUpdates(IPublicUserEntity user);
    
    public void notify (String message);
    
    public void updateProfil (IUserEntity user);
    
    public void watchGame (String idGame);
    
    public void chargeReplayFromFile(String file);
    
    public void beginReplay();
    
    public void saveGame();
    
    public IGameEntity getCurrentGame();
    
    public void createProposition(String uidReciever, boolean chattable, boolean observable);
    
    public void surrender();
    
    public void sendChatText(String message);
}
