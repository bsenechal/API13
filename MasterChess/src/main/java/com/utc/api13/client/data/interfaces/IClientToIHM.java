package com.utc.api13.client.data.interfaces;

public interface IClientToIHM {
    
    public void getUsers(List <IUserEntity> users);
    
    public IUserEntity getUserInfo(UID iduser);
    
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
    
    public IGameEntity getCurrentGame()
    
    public void createProposition(String uidReciever, boolean chattable, boolean observable)
    
    public void surrender();
    
    public void sendChatText(String message);
}
