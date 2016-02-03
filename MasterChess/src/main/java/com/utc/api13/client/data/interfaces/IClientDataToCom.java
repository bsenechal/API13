package com.utc.api13.client.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.client.data.DataClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.GameStatusEnum;

/**
 * 
 * This interface will be given to the Communication Client module. The
 * Communication module won't call this interface through methods but through
 * proceed
 * 
 * @author ulyss_000
 *
 */

public interface IClientDataToCom {

    /**
     * This method will set the ClientDataManger ObservableList
     * <AUserEntity> currentUsers -> it will not add the localuser to
     * currentUsers if existing in the connectedUserList parameter
     * 
     * @param connectedUserList
     */
    public void displayUsersList(final List<PublicUserEntity> connectedUserList);

    /**
     * Display a distant user's profile ( PublicUserEntity)
     * 
     * @param user
     *            user to be display
     */
    public void displayProfile(final PublicUserEntity user);

    /**
     * Display the list of all pending games
     * 
     * @param games
     *            list of all current games
     */
    public void displayAllGames(final List<GameEntity> games);

    /**
     * This method will update the local currentGame with the server validated
     * move & it will trigger an board update on IHM
     * 
     * @author ulyss_000
     * @param idPlayer
     * @param move
     */
    public void displayResult(final UUID idPlayer, final MoveEntity move);

    /**
     * Send the answer for the other player's request leaving
     * 
     * @param answer
     *            true if the user agree to end the game
     */
    public void sendAnswerForLeaving(final boolean answer);

    /**
     * Ask the opponent to end the game
     * 
     * @param uid
     *            The opponent's uid
     */
    public void requestPlayerForLeaving(final UUID uid);

    /**
     * Ends the local game for the observer when one of the player has left<br/>
     * Method to call only for observer users
     */
    public void endGameByLeaving();

    /**
     * 
     * notify the rejection
     * 
     * @param uidSender
     *            the sender's uid
     * @param rejectionMessage
     *            the message display for rejection
     */
    public void notifyRejection(final UUID uidSender, String rejectionMessage);

    /**
     * Create the GameEntity on the serveur, initialise and instantiate the game
     * on the Client
     * 
     * @param game
     *            game créé sur le serveur
     */
    public void initGame(final GameEntity game);

    /**
     * add an observer to the observer list of a game
     * 
     * @param idObserver
     *            the uid of the new observer
     */
    public void newObserver(final UUID idObserver);

    /**
     * add a replay to the current games
     * 
     * @param game
     *            the game to be replayed
     */
    public void newReplay(final GameEntity game);

    /**
     * Display the game proposition of the opponent
     * 
     * @param uidSender
     *            The opponent : sending a game request
     * @param observable
     *            true if the game is observable
     * @param chattable
     *            true if the chat is available during the game
     * @param timer
     *            true if a timer is set between each move
     * @param timerInt
     *            Timer in sec
     */
    public void printProposition(final UUID uidSender, final boolean observable, final boolean chattable,
            final boolean timer, final Integer timerInt);

    /**
     * The opponent is leaving, the payer win the game
     */
    public void victoryBySurrender();

    /**
     * lose game by surrender
     * 
     * @param idPlayer
     */
    public void endGameBySurrender(UUID idPlayer);

    /**
     * Display a message
     * 
     * @param message
     */
    public void displayMessage(final String message);

    /**
     * @return an instance of DataClientmanager
     */
    public DataClientManager getInstanceDataClientManager();

    /**
     * Define the instance of DataClientManager
     * 
     * @param instanceDataClientManager
     */
    public void setInstanceDataClientManager(DataClientManager instanceDataClientManager);

    /**
     * Adds a new user to the list of local users*
     * 
     * @param user
     *            user to add
     */
    public void notifyConnection(PublicUserEntity user);

    /**
     * Delete a user from the list of local users
     * 
     * @param idUser
     *            id of user to delete
     */
    public void notifyDisconnection(UUID idUser);

    /**
     * Com can use this method to set the next turn
     * 
     * @author ulyss_000
     * @param isFinished
     * @param nextPlayer
     */
    public void nextTurn(GameStatusEnum isFinished, UUID nextPlayer);

    /**
     * Updates the info about the given user<br/>
     * Method called when the given user updates its local profile
     * 
     * @param userToUpdate
     *            user to update
     */
    void updateDistantProfile(PublicUserEntity userToUpdate);
}
