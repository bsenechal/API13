package com.utc.api13.client.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.client.data.DataClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MessageEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.AUserEntity;

/**
 * 
 * This interface will be given to the Communication Client module.
 * @author ulyss_000
 *
 */
public interface IClientToComm {
	

	
	public void displayUsersList(final List<AUserEntity> connectedUserList);

    /**
     * 
     * @param user
     */
	public void displayProfile(final PublicUserEntity user);

	public void print_error(final String error);

	public void displayAllGames(final List<GameEntity> games);

	public void displayResult(final UUID idPlayer, final MoveEntity move);

	public void sendMessageToChat(final MessageEntity message);

	public void sendAnswerForLeaving(final boolean answer);

	public void requestPlayerForLeaving(final UUID uid);

	public void endGameByLeaving();

	public void notify(final String message);

	public void initGame(final GameEntity game);

	public void newObserver(final UUID idObserver);

	public void newPlayer(final UUID idPlayer);

	public void newReplay(final GameEntity game);

	public void sendProposition(final UUID uidSender, final UUID uidReciever, final boolean observable, final boolean chattable);

	public void printProposition(final UUID uidSender, final boolean observable, final boolean chattable);

	public void victoryBySurrender();

	public void endGameBySurrender();

	public void displayMessage(final String message);

	public DataClientManager getInstanceDataClientManager();

	public void setInstanceDataClientManager(DataClientManager instanceDataClientManager);
	
}
