/**
 * 
 */
package com.utc.api13.client.data;


import com.utc.api13.client.com.interfaces.IClientComToData;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.ihm.interfaces.IClientIHMToData;
import com.utc.api13.commun.entities.AUserEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Benoît
 *
 */
public class DataClientManager {
	private ClientDataToComImpl clientDataToComImpl;
	private ClientDataToIHMImpl clientDataToIHMImpl;
	private IClientComToData iClientComToData;
	private IClientIHMToData iClientIHMToData;
	private PrivateUserEntity userLocal;
	private ObservableList<AUserEntity> currentUsers;
	private ObservableList<GameEntity> currentGames;
	/**
	 * The game the user is playing or observing
	 */
	private GameEntity currentGame;
	/**
	 * 
	 * @return clientDataToComImpl
	 */
	public ClientDataToComImpl getClientDataToComImpl() {
		return clientDataToComImpl;
	}
	/**
	 * @param clientDataToComImpl the clientToCommImpl to set
	 */
	public void setClientDataToComImpl(ClientDataToComImpl clientDataToComImpl) {
		this.clientDataToComImpl = clientDataToComImpl;
	}
	/**
	 * @return the clientToIHMImpl
	 */
	public ClientDataToIHMImpl getClientDataToIHMImpl() {
		return clientDataToIHMImpl;
	}
	/**
	 * @param clientDataToIHMImpl the clientToIHMImpl to set
	 */
	public void setClientDataToIHMImpl(ClientDataToIHMImpl clientDataToIHMImpl) {
		this.clientDataToIHMImpl = clientDataToIHMImpl;
	}
	/**
	 * @return the userLocal
	 */
	public PrivateUserEntity getUserLocal() {
		return userLocal;
	}
	/**
	 * @param PrivateUserEntity the userLocal to set
	 */
	public void setUserLocal(PrivateUserEntity userLocal) {
		this.userLocal = userLocal;
	}
	/**
	 * @return the currentUsers
	 */
	public ObservableList<AUserEntity> getCurrentUsers() {
		return currentUsers;
	}
	/**
	 * @param currentUsers the currentUsers to set
	 */
	public void setCurrentUsers(ObservableList<AUserEntity> currentUsers) {
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
	 * @return the iClientToData
	 */
	public IClientComToData getIClientComToData() {
		return iClientComToData;
	}
	/**
	 * @param iClientToData the iClientToData to set
	 */
	public void setIClientComToData(IClientComToData iClientToData) {
		this.iClientComToData = iClientToData;
	}
	/**
	 * @return the iIHMFromData
	 */
	public IClientIHMToData getIClientIHMToData() {
		return iClientIHMToData;
	}
	/**
	 * @param iClientIHMToData the iIHMFromData to set
	 */
	public void setIClientIHMToData(IClientIHMToData iClientIHMToData) {
		this.iClientIHMToData = iClientIHMToData;
	}
	
	/**
	 * 
	 * @return Returns the current game the local user is playing or observing
	 */
	public GameEntity getCurrentGame() {
		return currentGame;
	}
	
	/**
	 * Set the game the user is playing or observing
	 * @param currentGame current game
	 */
	public void setCurrentGame(GameEntity currentGame) {
		this.currentGame = currentGame;
	}
	/**
	 * @param clientDataToComImpl
	 * @param clientDataToIHMImpl
	 * @param iClientComToData
	 * @param userLocal
	 * @param currentUsers
	 * @param currentGames
	 */
	public DataClientManager() {
		super();
		this.clientDataToComImpl = new ClientDataToComImpl(this);
		this.clientDataToIHMImpl = new ClientDataToIHMImpl(this);
		this.iClientIHMToData = null;
		this.iClientComToData = null;
		this.userLocal = new PrivateUserEntity();
		this.currentUsers = FXCollections.emptyObservableList();
		this.currentGames = FXCollections.emptyObservableList();
		// => Doesn't work :)
		// this.setBouchonPierre();
	}
	
	/*********************************************************
	 * *****************  WARNING  ***************************
	 * *******************************************************
	 * 
	 * To delete asap
	 */
	private void setBouchonPierre(){
	    for (int i = 0 ; i < 10 ; i++){
	    	PublicUserEntity userEntity = new PublicUserEntity();
            userEntity.setFirstName("firstName" + i);
            userEntity.setLastName("lastName" + i);
            userEntity.setLogin("login" + i);
            userEntity.setNbLost((int) Math.random());
            userEntity.setNbPlayed((int) Math.random());
            userEntity.setNbWon((int) Math.random());
            userEntity.setStatus((i % 2 == 0) ? true : false);
            //A pierre : NULL POINT EXCEPTION : currentUsers is null man !
            //this.currentUsers.add(userEntity);
        }
	}

	
}