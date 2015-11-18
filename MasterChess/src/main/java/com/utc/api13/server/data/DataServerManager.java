/**
 * 
 */
package com.utc.api13.server.data;

import java.util.ArrayList;
import java.util.List;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.UserEntity;
import com.utc.api13.server.com.interfaces.InterfaceToClient;

/**
 * @author Benoît
 *
 */
public class DataServerManager {
	// Interface COM
	private InterfaceToClient interfaceToClient;
	
	// Interface DATA
	private ServerToCommImpl serverToCommImpl;
	private List<UserEntity> currentUsers;
	private List<GameEntity> currentGames;
	
	/**
	 * @return the interfaceToClient
	 */
	public InterfaceToClient getInterfaceToClient() {
		return interfaceToClient;
	}
	/**
	 * @param interfaceToClient the interfaceToClient to set
	 */
	public void setInterfaceToClient(final InterfaceToClient interfaceToClient) {
		this.interfaceToClient = interfaceToClient;
	}

	/**
	 * @return the currentUsers
	 */
	public List<UserEntity> getCurrentUsers() {
		return currentUsers;
	}
	/**
	 * @param currentUsers the currentUsers to set
	 */
	public void setCurrentUsers(final List<UserEntity> currentUsers) {
		this.currentUsers = currentUsers;
	}
	/**
	 * @return the currentGames
	 */
	public List<GameEntity> getCurrentGames() {
		return currentGames;
	}
	/**
	 * @param currentGames the currentGames to set
	 */
	public void setCurrentGames(final List<GameEntity> currentGames) {
		this.currentGames = currentGames;
	}
	
	
	/**
	 * @return the serverToCommImpl
	 */
	public ServerToCommImpl getServerToCommImpl() {
		return serverToCommImpl;
	}
	/**
	 * @param serverToCommImpl the serverToCommImpl to set
	 */
	public void setServerToCommImpl(ServerToCommImpl serverToCommImpl) {
		this.serverToCommImpl = serverToCommImpl;
	}
	
	/**
	 * @param interfaceToClient
	 * @param serverToComm
	 */
	public DataServerManager() {
		super();
		this.interfaceToClient = null;
		this.serverToCommImpl = new ServerToCommImpl();
		this.currentGames = new ArrayList<GameEntity>();
		this.currentUsers = new ArrayList<UserEntity>();
	}
	
	// TODO : Supprimer cette méthode une fois la connexion implémentée
	public void addUsers(){
		
		for (int i = 0 ; i < 10 ; i++){
			UserEntity userEntity = new UserEntity();
			userEntity.setFirstName("firstName" + i);
			userEntity.setLastName("lastName" + i);
			userEntity.setLogin("login" + i);
			userEntity.setNbLost((int) Math.random());
			userEntity.setNbPlayed((int) Math.random());
			userEntity.setNbWon((int) Math.random());
			userEntity.setStatus((i % 2 == 0) ? true : false);
			this.currentUsers.add(userEntity);
		}
	}
	
}
