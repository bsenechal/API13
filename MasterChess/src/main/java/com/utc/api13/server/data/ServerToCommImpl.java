/**
 * 
 */
package com.utc.api13.server.data;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.UserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;
import com.utc.api13.commun.services.UserService;
import com.utc.api13.server.data.interfaces.IServerToComm;

/**
 * @author Benoît
 *
 */
public class ServerToCommImpl implements IServerToComm {

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#getUsers()
	 */
	@Override
	public List<UserEntity> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#getUserInfo(java.util.UUID)
	 */
	@Override
	public UserEntity getUserInfo(UUID idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#getAllGames()
	 */
	@Override
	public List<GameEntity> getAllGames() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#notifyConnections(com.utc.api13.commun.entities.UserEntity)
	 */
	@Override
	public void notifyConnections(UserEntity Player) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#computerResult(int, com.utc.api13.commun.entities.MoveEntity)
	 */
	@Override
	public boolean computerResult(int idPlayer, MoveEntity move) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#isFinished(java.lang.String)
	 */
	@Override
	public boolean isFinished(String idGame) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#observerLeave(java.util.UUID)
	 */
	@Override
	public void observerLeave(UUID idUser) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#getListObservers()
	 */
	@Override
	public List<UserEntity> getListObservers() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#saveUserData(com.utc.api13.commun.entities.UserEntity)
	 */
	@Override
	public void saveUserData(UserEntity User) throws TechnicalException, FunctionalException {
		getUserService().save(User);

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#newObserver(int, java.util.UUID)
	 */
	@Override
	public void newObserver(int idGame, UUID idUser) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#createReplay(com.utc.api13.commun.entities.GameEntity, com.utc.api13.commun.entities.UserEntity)
	 */
	@Override
	public void createReplay(GameEntity game, UserEntity user) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#getConnectedUsers()
	 */
	@Override
	public List<UserEntity> getConnectedUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#surrender(java.util.UUID)
	 */
	@Override
	public void surrender(UUID idPlayer) {
		// TODO Auto-generated method stub

	}

	private UserService getUserService() {
		return new UserService();
	}
}
