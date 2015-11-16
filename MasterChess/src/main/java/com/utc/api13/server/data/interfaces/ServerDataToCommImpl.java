/**
 * 
 */
package com.utc.api13.server.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.UserEntity;

/**
 * @author Thomas
 * Implementation of methods from data module, callable from comm module to transfer objects. 
 *
 */
public class ServerDataToCommImpl implements IServerToComm {

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#getUsers()
	 */
	public List<UserEntity> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#getUserInfo(java.util.UUID)
	 */
	public UserEntity getUserInfo(UUID idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#getAllGames()
	 */
	public List<GameEntity> getAllGames() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#notifyConnections(com.utc.api13.commun.entities.UserEntity)
	 */
	public void notifyConnections(UserEntity Player) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#computerResult(int, com.utc.api13.commun.entities.MoveEntity)
	 */
	public boolean computerResult(int idPlayer, MoveEntity move) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#isFinished(java.lang.String)
	 */
	public boolean isFinished(String idGame) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#observerLeave(int)
	 */
	public void observerLeave(int idUser) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#getListObservers()
	 */
	public List<UserEntity> getListObservers() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#saveUserData(com.utc.api13.commun.entities.UserEntity)
	 */
	public void saveUserData(UserEntity User) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#newObserver(int, java.util.UUID)
	 */
	public void newObserver(int idGame, UUID idUser) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#createReplay(com.utc.api13.commun.entities.GameEntity, com.utc.api13.commun.entities.UserEntity)
	 */
	public void createReplay(GameEntity game, UserEntity user) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#getConnectedUsers()
	 */
	public List<UserEntity> getConnectedUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#surrender(java.util.UUID)
	 */
	public void surrender(UUID idPlayer) {
		// TODO Auto-generated method stub

	}

}
