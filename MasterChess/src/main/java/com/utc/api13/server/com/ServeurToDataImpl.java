package com.utc.api13.server.com;

import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.UserEntity;
import com.utc.api13.commun.messages.ConnectMessage;
import com.utc.api13.commun.messages.GameFinishedMessage;
import com.utc.api13.commun.messages.MoveMessage;
import com.utc.api13.server.com.interfaces.IServeurToData;

public class ServeurToDataImpl implements IServeurToData {

	ComServerManager comServerManagerInstance;
	
	
	
	/**
	 * @param comServerManagerInstance
	 */
	public ServeurToDataImpl(ComServerManager comServerManagerInstance) {
		this.comServerManagerInstance = comServerManagerInstance;
	}

	@Override
	public void multicastMove(UserEntity users, UUID idPlayer, MoveEntity move) {

	}

	@Override
	public void multicastFinished(GameEntity game) {

	}

	@Override
	public void multicastNewPlayer(PublicUserEntity pubPlayer) {

		
	}

	/**
	 * @return the comServerManagerInstance
	 */
	@Override
	public ComServerManager getComServerManagerInstance() {
		return comServerManagerInstance;
	}

	/**
	 * @param comServerManagerInstance the comServerManagerInstance to set
	 */
	@Override
	public void setComServerManagerInstance(ComServerManager comServerManagerInstance) {
		this.comServerManagerInstance = comServerManagerInstance;
	}

	

}
