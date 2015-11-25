package com.utc.api13.client.com;

import java.util.List;
import java.util.UUID;

import com.utc.api13.client.com.interfaces.IClientComToData;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.messages.ConnectMessage;

public class ClientComToDataImpl implements IClientComToData {

	ComClientManager comClientManagerInstance;
	
	
	/**
	 * @param comClientManagerInstance
	 */
	public ClientComToDataImpl(ComClientManager comClientManagerInstance) {
		this.comClientManagerInstance = comClientManagerInstance;
	}
	

	@Override
	public void notifyConnection(PublicUserEntity pubUser) {
		comClientManagerInstance.sendMessage(new ConnectMessage(pubUser.getId(), new UUID(0, 0), pubUser));
	}

	@Override
	public boolean connectAsObserver(UUID game_id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<PublicUserEntity> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean validateMove(UUID idPlayer, MoveEntity move) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendUserUpdates(PublicUserEntity user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean pushReplayToServer(PublicUserEntity user, GameEntity game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendProposition(PublicUserEntity player) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean sendAnswer(String answer, PublicUserEntity sender) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean surrender(UUID uid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void victoryBySurrender(UUID uid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endGameBySurrender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endGameByLeaving() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestPlayerForLeaving(UUID uid, boolean answer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendTextChat(String text, UUID idPartie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GameEntity> getAllParties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void observerLeave(UUID uid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendLeavingMessage(UUID idPartie) {
		// TODO Auto-generated method stub
		
	}

	//TODO: UME : Euh why do you want to give access to the manager using the interface ? -> seems wrong !
	/**
	 * @return the comClientManagerInstance
	 */
	@Override
	public ComClientManager getComClientManagerInstance() {
		return comClientManagerInstance;
	}

	/**
	 * @param comClientManagerInstance the comClientManagerInstance to set
	 */
	@Override
	public void setComClientManagerInstance(ComClientManager comClientManagerInstance) {
		this.comClientManagerInstance = comClientManagerInstance;
	}

	

}