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

//Cette interface est mise à disposition de IHM. Elle permet d'appeler les méthodes chez Data pour réaliser les actions correspondants aux actions sur l'IHM

public interface IClientDataToIHM {
    
    public void getUsers();                         //Appelle com.getUsers();
    
    public void getUserInfo(final UUID iduser);     //Appelle com.getUserInfo(UUID iduser);
    
    public void getAllGames();
    
    /**
     * Connect a user to the app
     * @param login login of user
     * @param password password of user
     * @throws FunctionalException exception when login or password are incorrect
     * @throws TechnicalException technical exception
     */
    public void connect(final String login, final String password) throws FunctionalException, TechnicalException;  //Appelle com.notifyConnection(PublicUserEntity publicUser)
    
    public void disconnect();                       //Appelle com.disconnect(UUID idUser)
    
    public void move(PieceEntity piece, PositionEntity position);
    
    public void observerLeave();
    
    public void requestPlayerForLeaving();
    
    public void sendAnserForLeaving(boolean answer);
    
    public void updateProfile(PrivateUserEntity user) throws TechnicalException, FunctionalException;
    
    public void sendUserUpdates(PublicUserEntity user);
    
    public void notify (String message);
    
    public void watchGame (String idGame);
    
    public void chargeReplayFromFile(String file);
    
    public void beginReplay();
    
    /**
     * Saves the current game into storage
     * @throws TechnicalException technical error
     * @throws FunctionalException validation exception
     */
    public void saveGame() throws TechnicalException, FunctionalException;
    
    
    public GameEntity getCurrentGame();
    
    public void createProposition(UUID uidReciever, boolean chattable, boolean observable);     //Appelle com.sendProposition(UUID idUser, boolean chattable, boolean observable) pour demander à com de faire une proposition à l'user;
    
    public void surrender();
    
    /**
     * Sends message for chat
     * @param message message to send
     */
    public void sendChatText(String message);

    public ObservableList<PublicUserEntity> getUserList();
    
    public void createProfile(PrivateUserEntity user) throws FunctionalException, TechnicalException;
    
    
  //TODO
  //Si le  (IClientDataToCom.)printProposition()  ne renvoie pas directement la réponse à com pour dire si un User à accepté la partie, il faudra créer la méthode de réponse 
  //answerProposition(final UUID uidSender, final UUID uidReciever, final boolean observable, final boolean chattable, boolean answer); qui va appeler com.answerProposition(...)
}