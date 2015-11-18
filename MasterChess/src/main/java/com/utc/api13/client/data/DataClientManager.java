/**
 * 
 */
package com.utc.api13.client.data;

import com.utc.api13.client.com.interfaces.IClientToData;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.UserEntity;

import javafx.collections.ObservableList;

/**
 * @author Benoît
 *
 */
public class DataClientManager {
	private ClientToCommImpl clientToCommImpl;
	private ClientToIHMImpl clientToIHMImpl;
	private IClientToData iClientToData;
	private UserEntity userLocal;
	private ObservableList<UserEntity> currentUsers;
	private ObservableList<GameEntity> currentGames;
	/**
	 * @return the clientToCommImpl
	 */
	public ClientToCommImpl getClientToCommImpl() {
		return clientToCommImpl;
	}
	/**
	 * @param clientToCommImpl the clientToCommImpl to set
	 */
	public void setClientToCommImpl(ClientToCommImpl clientToCommImpl) {
		this.clientToCommImpl = clientToCommImpl;
	}
	/**
	 * @return the clientToIHMImpl
	 */
	public ClientToIHMImpl getClientToIHMImpl() {
		return clientToIHMImpl;
	}
	/**
	 * @param clientToIHMImpl the clientToIHMImpl to set
	 */
	public void setClientToIHMImpl(ClientToIHMImpl clientToIHMImpl) {
		this.clientToIHMImpl = clientToIHMImpl;
	}
	/**
	 * @return the iClientToData
	 */
	public IClientToData getIClientToData() {
		return iClientToData;
	}
	/**
	 * @param iClientToData the iClientToData to set
	 */
	public void setIClientToData(IClientToData iClientToData) {
		iClientToData = iClientToData;
	}
	/**
	 * @return the userLocal
	 */
	public UserEntity getUserLocal() {
		return userLocal;
	}
	/**
	 * @param userLocal the userLocal to set
	 */
	public void setUserLocal(UserEntity userLocal) {
		this.userLocal = userLocal;
	}
	/**
	 * @return the currentUsers
	 */
	public ObservableList<UserEntity> getCurrentUsers() {
		return currentUsers;
	}
	/**
	 * @param currentUsers the currentUsers to set
	 */
	public void setCurrentUsers(ObservableList<UserEntity> currentUsers) {
		this.currentUsers = currentUsers;
	}
	/**
	 * @return the currentGames
	 */
	public ObservableList<GameEntity> getCurrentGames() {
		return currentGames;
	}
	/**
	 * @param currentGames the currentGames to set
	 */
	public void setCurrentGames(ObservableList<GameEntity> currentGames) {
		this.currentGames = currentGames;
	}
	/**
	 * @param clientToCommImpl
	 * @param clientToIHMImpl
	 * @param iClientToData
	 * @param userLocal
	 * @param currentUsers
	 * @param currentGames
	 */
	public DataClientManager() {
		super();
		this.clientToCommImpl = new ClientToCommImpl(this);
		this.clientToIHMImpl = new ClientToIHMImpl(this);
		this.iClientToData = null;
		this.userLocal = new UserEntity();
	}

	
}
