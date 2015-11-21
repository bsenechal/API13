/**
 * 
 */
package com.utc.api13.server.data;

import java.util.ArrayList;
import java.util.List;

import com.utc.api13.commun.entities.AUserEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.server.com.interfaces.IServeurToData;

/**
 * @author Benoît
 *
 */
public class DataServerManager {
	// Interface COM
	private IServeurToData iServeurToData;
	
	// Interface DATA
	private ServerToCommImpl serverToCommImpl;
	private List<AUserEntity> currentUsers;
	private List<GameEntity> currentGames;
	

	/**
	 * @return the IServeurToData
	 */
	public IServeurToData getIServeurToData() {
		return iServeurToData;
	}
	/**
	 * @param IServeurToData the IServeurToData to set
	 */
	public void setIServeurToData(IServeurToData IServeurToData) {
		this.iServeurToData = IServeurToData;
	}
	/**
	 * @return the currentUsers
	 */
	public List<AUserEntity> getCurrentUsers() {
		return currentUsers;
	}
	/**
	 * @param currentUsers the currentUsers to set
	 */
	public void setCurrentUsers(final List<AUserEntity> currentUsers) {
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
	
	public DataServerManager() {
		super();
		this.iServeurToData = null;
		this.serverToCommImpl = new ServerToCommImpl(this);
		this.currentGames = new ArrayList<GameEntity>();
		this.currentUsers = new ArrayList<AUserEntity>();
	}
	
	// TODO : Supprimer cette méthode une fois la connexion implémentée
	public void addUsers(){
		
		for (int i = 0 ; i < 10 ; i++){
			PublicUserEntity userEntity = new PublicUserEntity();
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
