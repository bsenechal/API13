package com.utc.api13.client.com.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.UserEntity;

public class IClientToDataImpl implements IClientToData {

	public boolean connectAsObserver(UUID game_id) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<UserEntity> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean validateMove(UUID idPlayer, MoveEntity move) {
		// TODO Auto-generated method stub
		return false;
	}

	public void multicastMove(UserEntity users, UUID idPlayer, MoveEntity move) {
		// TODO Auto-generated method stub
		
	}

	public void multicastFinished(UserEntity users) {
		// TODO Auto-generated method stub
		
	}

	public void sendUserUpdates(PublicUserEntity user) {
		// TODO Auto-generated method stub
		
	}

	public boolean pushReplayToServer(UserEntity user, GameEntity game) {
		// TODO Auto-generated method stub
		return false;
	}

	public void multicastNewPlayer(UUID idPlayer) {
		// TODO Auto-generated method stub
		
	}

	public void sendProposition(UserEntity player) {
		// TODO Auto-generated method stub
		
	}

	public boolean sendAnswer(String answer, UserEntity sender) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean surrender(UUID uid) {
		// TODO Auto-generated method stub
		return false;
	}

	public void victoryBySurrender(UUID uid) {
		// TODO Auto-generated method stub
		
	}

	public void endGameBySurrender() {
		// TODO Auto-generated method stub
		
	}

	public void endGameByLeaving() {
		// TODO Auto-generated method stub
		
	}

	public void requestPlayerForLeaving(UUID uid, boolean answer) {
		// TODO Auto-generated method stub
		
	}

	public void sendTextChat(String text, UUID idPartie) {
		// TODO Auto-generated method stub
		
	}

	public List<GameEntity> getAllParties() {
		// TODO Auto-generated method stub
		return null;
	}

	public void observerLeave(UUID uid) {
		// TODO Auto-generated method stub
		
	}

	public void sendLeavingMessage(UUID idPartie) {
		// TODO Auto-generated method stub
		
	}

	

}
