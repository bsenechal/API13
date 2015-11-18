/**
 * 
 */
package com.utc.api13.server.data;

import java.util.ArrayList;
import java.util.List;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.UserEntity;
import com.utc.api13.server.com.interfaces.IServeurToDataImpl;
import com.utc.api13.server.data.interfaces.IServerToComm;

/**
 * @author Benoît
 *
 */
public class DataServerManager {
	private IServeurToDataImpl iServeurToDataImpl;
	private IServerToComm serverToComm;
	private List<UserEntity> currentUsers;
	private List<GameEntity> currentGames;
	
	

	/**
	 * @return the iServeurToDataImpl
	 */
	public IServeurToDataImpl getiServeurToDataImpl() {
		return iServeurToDataImpl;
	}
	/**
	 * @param iServeurToDataImpl the iServeurToDataImpl to set
	 */
	public void setiServeurToDataImpl(IServeurToDataImpl iServeurToDataImpl) {
		this.iServeurToDataImpl = iServeurToDataImpl;
	}
	/**
	 * @return the serverToComm
	 */
	public IServerToComm getServerToComm() {
		return serverToComm;
	}
	/**
	 * @param serverToComm the serverToComm to set
	 */
	public void setServerToComm(final IServerToComm serverToComm) {
		this.serverToComm = serverToComm;
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
	 * @param interfaceToClient
	 * @param serverToComm
	 */
	public DataServerManager(final IServeurToDataImpl iServeurToDataImpl, final IServerToComm serverToComm) {
		super();
		this.iServeurToDataImpl = iServeurToDataImpl;
		this.serverToComm = serverToComm;
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
