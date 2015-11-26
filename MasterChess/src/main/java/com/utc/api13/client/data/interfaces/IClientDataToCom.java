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

    void displayAllGames(final List<GameEntity> games);

    void displayResult(final UUID idPlayer, final MoveEntity move);

    void sendMessageToChat(final MessageEntity message);

    void sendAnswerForLeaving(final boolean answer);

    void requestPlayerForLeaving(final UUID uid);

    void endGameByLeaving();

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