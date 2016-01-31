/**
 * 
 */
package com.utc.api13.server.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

/**
 * @author Benoît
 *
 */
public class DataServerManager {

	// Interface DATA
	private ServerDataToComImpl serverDataToComImpl;
	private List<PublicUserEntity> currentUsers;
	private List<GameEntity> currentGames;

	public DataServerManager() {
		super();
		this.serverDataToComImpl = new ServerDataToComImpl(this);
		this.currentGames = new ArrayList<GameEntity>();
		this.currentUsers = new ArrayList<PublicUserEntity>();
	}

	/**
	 * @return the currentUsers
	 */
	public List<PublicUserEntity> getCurrentUsers() {
		return currentUsers;
	}

	/**
	 * @param currentUsers
	 *            the currentUsers to set
	 */
	public void setCurrentUsers(final List<PublicUserEntity> currentUsers) {
		this.currentUsers = currentUsers;
	}

	/**
	 * @return the currentGames
	 */
	public List<GameEntity> getCurrentGames() {
		return currentGames;
	}

	/**
	 * @param currentGames
	 *            the currentGames to set
	 */
	public void setCurrentGames(final List<GameEntity> currentGames) {
		this.currentGames = currentGames;
	}

	/**
	 * @return the serverToCommImpl
	 */
	public ServerDataToComImpl getServerDataToComImpl() {
		return serverDataToComImpl;
	}

	/**
	 * @param serverDataToComImpl
	 *            the serverToCommImpl to set
	 */
	public void setServerDataToComImpl(ServerDataToComImpl serverDataToComImpl) {
		this.serverDataToComImpl = serverDataToComImpl;
	}

	/**
	 * Return Game By ID
	 * 
	 * @author ulyss_000
	 * @param gameID
	 * @return
	 */
	public GameEntity getGameById(UUID gameID) {
		return this.getCurrentGames().stream().filter(g -> gameID.equals(g.getId())).findFirst().orElse(null);
	}
}
