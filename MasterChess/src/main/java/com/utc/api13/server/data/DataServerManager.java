/**
 * 
 */
package com.utc.api13.server.data;

import java.util.ArrayList;
import java.util.List;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.UserEntity;
import com.utc.api13.server.com.interfaces.InterfaceToClient;
import com.utc.api13.server.data.interfaces.IServerToComm;

/**
 * @author Benoît
 *
 */
public class DataServerManager {
	private InterfaceToClient interfaceToClient;
	private IServerToComm serverToComm;
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
	public void setInterfaceToClient(InterfaceToClient interfaceToClient) {
		this.interfaceToClient = interfaceToClient;
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
	public void setServerToComm(IServerToComm serverToComm) {
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
	public void setCurrentUsers(List<UserEntity> currentUsers) {
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
	public void setCurrentGames(List<GameEntity> currentGames) {
		this.currentGames = currentGames;
	}
	/**
	 * @param interfaceToClient
	 * @param serverToComm
	 */
	public DataServerManager(InterfaceToClient interfaceToClient, IServerToComm serverToComm) {
		super();
		this.interfaceToClient = interfaceToClient;
		this.serverToComm = serverToComm;
		this.currentGames = new ArrayList<GameEntity>();
		this.currentUsers = new ArrayList<UserEntity>();
	}
	
	
}
