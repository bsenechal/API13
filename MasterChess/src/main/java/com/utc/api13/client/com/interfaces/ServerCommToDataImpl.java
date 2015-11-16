/**
 * 
 */
package com.utc.api13.client.com.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.UserEntity;

/**
 * @author Thomas
 *
 *Implementation of methods in comm module callable from data module
 */
public class ServerCommToDataImpl implements InterfaceFromData {

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#connectAsObserver(java.util.UUID)
	 */
	public boolean connectAsObserver(UUID game_id) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#getUsers()
	 */
	public List<UserEntity> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#validateMove(java.util.UUID, com.utc.api13.commun.entities.MoveEntity)
	 */
	public boolean validateMove(UUID idPlayer, MoveEntity move) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#sendUserUpdates(com.utc.api13.commun.entities.PublicUserEntity)
	 */
	public void sendUserUpdates(PublicUserEntity user) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#pushReplayToServer(com.utc.api13.commun.entities.UserEntity, com.utc.api13.commun.entities.GameEntity)
	 */
	public boolean pushReplayToServer(UserEntity user, GameEntity game) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#sendProposition(com.utc.api13.commun.entities.UserEntity)
	 */
	public void sendProposition(UserEntity player) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#sendAnswer(java.lang.String, com.utc.api13.commun.entities.UserEntity)
	 */
	public boolean sendAnswer(String answer, UserEntity sender) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#surrender(java.util.UUID)
	 */
	public boolean surrender(UUID uid) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#victoryBySurrender(java.util.UUID)
	 */
	public void victoryBySurrender(UUID uid) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#endGameBySurrender()
	 */
	public void endGameBySurrender() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#endGameByLeaving()
	 */
	public void endGameByLeaving() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#requestPlayerForLeaving(java.util.UUID, boolean)
	 */
	public void requestPlayerForLeaving(UUID uid, boolean answer) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#sendTextChat(java.lang.String, java.util.UUID)
	 */
	public void sendTextChat(String text, UUID idPartie) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#getAllParties()
	 */
	public List<GameEntity> getAllParties() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#observerLeave(java.util.UUID)
	 */
	public void observerLeave(UUID uid) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.client.com.interfaces.InterfaceFromData#sendLeavingMessage(java.util.UUID)
	 */
	public void sendLeavingMessage(UUID idPartie) {
		// TODO Auto-generated method stub

	}

}
