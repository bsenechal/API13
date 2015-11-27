package com.utc.api13.client.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.client.data.DataClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MessageEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;


/**
 * 
 * This interface will be given to the Communication Client module.
 * @author ulyss_000
 *
 */
public interface IClientDataToCom {
	

	/**
	 * @author ulyss_000
	 * This method will set the ClientDataManger ObservableList<AUserEntity> currentUsers
	 * -> it will not add the localuser to currentUsers if existing in the connectedUserList parameter
	 * @param connectedUserList
	 */
    void displayUsersList(final List<PublicUserEntity> connectedUserList);

    /**
     * 
     * @param user
     */
    void displayProfile(final PublicUserEntity user);

    void print_error(final String error);
    /*
     * Displays list of users
     */

    void displayAllGames(final List<GameEntity> games);

    void displayResult(final UUID idPlayer, final MoveEntity move);
    
    /**
     * Add a new message to the chat
     * @param message message to add to the chat
     */

    void sendMessageToChat(final MessageEntity message);

    /**
     * Sends the decision of opponent user after leaving request of the local user
     * @param answer true if ok to leave and false if not
     */
    void sendAnswerForLeaving(final boolean answer);
    
    /**
     * Leaving Request sent by the opponent player 
     * @param uid oppinent player uid
     */

    void requestPlayerForLeaving(final UUID uid);

    void endGameByLeaving();

    /**
     * notifies the local user that his (her) information have been updated successfully
     * @param message message
     */
    void notify(final String message);

    void initGame(final GameEntity game);

    void newObserver(final UUID idObserver);

    void newPlayer(final UUID idPlayer);

    void newReplay(final GameEntity game);

    void sendProposition(final UUID uidSender, final UUID uidReciever, final boolean observable, final boolean chattable);

    void printProposition(final UUID uidSender, final boolean observable, final boolean chattable);

    void victoryBySurrender();

    void endGameBySurrender();

    /**
     * Calls the IHM module displayMessage method to display a message on the chat
     * @param message message to display
     */
    void displayMessage(final String message);

    DataClientManager getInstanceDataClientManager();

    void setInstanceDataClientManager(DataClientManager instanceDataClientManager);
	
}