package com.utc.api13.client.com.interfaces;

import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

public interface IClientComToData {

    /**
     * to request observation connection to a replay from server
     */
    public void connectAsObserver(UUID game_id);

    /**
     * to request all public users connected from server
     */
    public void getUsers();

    /**
     * to request a user profile from server
     */
    public void getUserInfo(UUID iduser);

    /**
     * to request connection to server
     */
    public void notifyConnection(PublicUserEntity pubUser);

    /**
     * to request disconnection of application to server
     */
    public void disconnect(UUID sender);

    /**
     * to send a proposition to a another connected user
     */
    public void sendProposition(UUID sender, UUID reciever, boolean chattable, boolean observable, boolean timer,
            Integer timerInt);

    /**
     * to answer a proposition to a another connected user
     */
    public void answerProposition(UUID sender, UUID reciever, boolean chattable, boolean observable, boolean answer,
            boolean timer, Integer timerInt);

    /**
     * to request a validation of move.
     */
    public void validateMove(UUID idPlayer, MoveEntity move);

    /**
     * Sends new user profile when it has bee updated.
     * 
     * @param user
     *            Local user public profile object
     */
    public void sendUserUpdates(PublicUserEntity user);

    /**
     * Sends the game replay on the server
     * 
     * @param user
     *            Sender
     * @param game
     *            Game object
     */
    public void pushReplayToServer(PublicUserEntity user, GameEntity game);

    // DEPRECATED
    public void sendProposition(PublicUserEntity player);

    // public void sendAnswer(String answer, PublicUserEntity sender);

    /**
     * Informs the server the current player surrenders
     * 
     * @param uid
     *            Id of the player
     */
    public void surrender(UUID uid);

    /**
     * Give victory to the opponent by surrending
     * 
     * @param uid
     *            ID of the opponent
     */
    public void victoryBySurrender(UUID uid);

    /**
     * 
     */
    public void endGameBySurrender();

    /**
     * Ends the game when the player exits the game window
     */
    public void endGameByLeaving(UUID sender, UUID receiver, UUID gameId, boolean answer);

    /**
     * Sends leaving request to the opponent
     * 
     * @param uid
     *            ID of the opponent
     * @param answer
     */
    public void requestPlayerForLeaving(UUID sender, UUID receiver);

    /**
     * Sends chat message to a game
     * 
     * @param texte
     *            Message
     * @param partie
     *            Game ID
     */
    public void sendTextChat(String texte, UUID partie);

    /**
     * to request all games
     */
    public void getAllParties();

    /**
     * Notifies that the local observer leaves the game
     * 
     * @param uid
     */
    public void observerLeave(UUID uid);

    public void sendLeavingMessage(UUID idPartie);

    /**
     * Getter for manager instance
     * 
     * @return Manager instance
     */
    public ComClientManager getComClientManagerInstance();

    /**
     * Setter for manager instance
     * 
     * @param comClientManagerInstance
     *            Manager to set
     */
    public void setComClientManagerInstance(ComClientManager comClientManagerInstance);

    public void removeUserFromChat(UUID idUser, UUID game);

}
