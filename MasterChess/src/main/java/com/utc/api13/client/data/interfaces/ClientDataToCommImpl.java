/**
 * 
 */
package com.utc.api13.client.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MessageEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

/**
 * @author Thomas
 *
 */
public class ClientDataToCommImpl implements IClientToComm {

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#displayUsersList()
	 */
	public void displayUsersList() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#displayProfile(com.utc.api13.commun.entities.PublicUserEntity)
	 */
	public void displayProfile(PublicUserEntity user) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#print_error(java.lang.String)
	 */
	public void print_error(String error) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#displayAllGames(java.util.List)
	 */
	public void displayAllGames(List<GameEntity> games) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#displayResult(java.util.UUID, com.utc.api13.commun.entities.MoveEntity)
	 */
	public void displayResult(UUID idPlayer, MoveEntity move) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#sendMessageToChat(com.utc.api13.commun.entities.MessageEntity)
	 */
	public void sendMessageToChat(MessageEntity message) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#sendAnswerForLeaving(boolean)
	 */
	public void sendAnswerForLeaving(boolean answer) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#requestPlayerForLeaving(java.util.UUID)
	 */
	public void requestPlayerForLeaving(UUID uid) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#endGameByLeaving()
	 */
	public void endGameByLeaving() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#notify(java.lang.String)
	 */
	public void notify(String message) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#initGame(com.utc.api13.commun.entities.GameEntity)
	 */
	public void initGame(GameEntity game) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#newObserver(java.util.UUID)
	 */
	public void newObserver(UUID idObserver) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#newPlayer(java.util.UUID)
	 */
	public void newPlayer(UUID idPlayer) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#newReplay(com.utc.api13.commun.entities.GameEntity)
	 */
	public void newReplay(GameEntity game) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#sendProposition(java.util.UUID, java.util.UUID, boolean, boolean)
	 */
	public void sendProposition(UUID uidSender, UUID uidReciever,
			boolean observable, boolean chattable) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#printProposition(java.util.UUID, boolean, boolean)
	 */
	public void printProposition(UUID uidSender, boolean observable,
			boolean chattable) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#victoryBySurrender()
	 */
	public void victoryBySurrender() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#endGameBySurrender()
	 */
	public void endGameBySurrender() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#displayMessage(java.lang.String)
	 */
	public void displayMessage(String message) {
		// TODO Auto-generated method stub

	}

}