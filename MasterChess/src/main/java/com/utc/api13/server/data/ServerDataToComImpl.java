/**
 * 
 */
package com.utc.api13.server.data;

import java.util.List;
import java.util.UUID;

//import com.utc.api13.client.data.services.UserService;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.server.data.interfaces.IServerDataToCom;

/**
 * @author Benoît
 *
 */
public class ServerDataToComImpl implements IServerDataToCom {
	private DataServerManager dataServerManager;

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#getUsers()
	 */
	@Override
	public List<PublicUserEntity> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#getUserInfo(java.util.UUID)
	 */
	@Override
	public PublicUserEntity getUserInfo(UUID idUser) {
	    return dataServerManager.getCurrentUsers().stream().filter(u -> u.getId().equals(idUser)).findFirst().get();
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
	public void notifyConnections(PublicUserEntity Player) {
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
	public List<PublicUserEntity> getListObservers() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#saveUserData(com.utc.api13.commun.entities.UserEntity)
	 */
	@Override
	public void saveUserData(final PublicUserEntity User) {
		System.out.println("Ajout de l'utilisateur " + User);
		dataServerManager.getCurrentUsers().add(User);
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
	public void createReplay(GameEntity game, PublicUserEntity user) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#getConnectedUsers()
	 */
	@Override
	public List<PublicUserEntity> getConnectedUsers() {
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
	
	/*
	 * (non-Javadoc)
	 * @see com.utc.api13.server.data.interfaces.IServerToComm#disconnect(java.util.UUID)
	 */
	@Override
	public void disconnect(final UUID idUser){
		dataServerManager.getCurrentUsers().removeIf(user -> user.getId() == idUser);
	}

	/**
	 * @param dataServerManager
	 */
	public ServerDataToComImpl(DataServerManager dataServerManager) {
		super();
		this.dataServerManager = dataServerManager;
	}
}
