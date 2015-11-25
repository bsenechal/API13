/**
 * 
 */
package com.utc.api13.server.data;

import java.util.ArrayList;
import java.util.List;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.server.com.interfaces.IServeurComToData;

/**
 * @author Benoît
 *
 */
public class DataServerManager {
	// Interface COM
	private IServeurComToData iServeurComToData;
	
	// Interface DATA
	private ServerDataToComImpl serverDataToComImpl;
	private List<PublicUserEntity> currentUsers;
	private List<GameEntity> currentGames;
	

	/**
	 * @return the IServeurToData
	 */
	public IServeurComToData getIServeurComToData() {
		return iServeurComToData;
	}
	/**
	 * @param IServeurComToData the IServeurToData to set
	 */
	public void setIServeurComToData(IServeurComToData IServeurComToData) {
		this.iServeurComToData = IServeurComToData;
	}
	/**
	 * @return the currentUsers
	 */
	public List<PublicUserEntity> getCurrentUsers() {
		return currentUsers;
	}
	/**
	 * @param currentUsers the currentUsers to set
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
	 * @param currentGames the currentGames to set
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
	 * @param serverDataToComImpl the serverToCommImpl to set
	 */
	public void setServerDataToComImpl(ServerDataToComImpl serverDataToComImpl) {
		this.serverDataToComImpl = serverDataToComImpl;
	}
	
	public DataServerManager() {
		super();
		this.iServeurComToData = null;
		this.serverDataToComImpl = new ServerDataToComImpl(this);
		this.currentGames = new ArrayList<GameEntity>();
		this.currentUsers = new ArrayList<PublicUserEntity>();
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
