/**
 * 
 */
package com.utc.api13.client.data;

import com.utc.api13.client.com.interfaces.IClientToData;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.ihm.interfaces.IIHMFromData;
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
	private ClientToCommImpl clientToCommImpl;
	private ClientToIHMImpl clientToIHMImpl;
	private IClientToData iClientToData;
	private IIHMFromData iIHMFromData;
	private PrivateUserEntity userLocal;
	private ObservableList<AUserEntity> currentUsers;
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
	 * @return the userLocal
	 */
	public AUserEntity getUserLocal() {
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
	public IClientToData getiClientToData() {
		return iClientToData;
	}
	/**
	 * @param iClientToData the iClientToData to set
	 */
	public void setiClientToData(IClientToData iClientToData) {
		this.iClientToData = iClientToData;
	}
	/**
	 * @return the iIHMFromData
	 */
	public IIHMFromData getiIHMFromData() {
		return iIHMFromData;
	}
	/**
	 * @param iIHMFromData the iIHMFromData to set
	 */
	public void setiIHMFromData(IIHMFromData iIHMFromData) {
		this.iIHMFromData = iIHMFromData;
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
		this.iIHMFromData = null;
		this.iClientToData = null;
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
            this.currentUsers.add(userEntity);
        }
	}

	
}
