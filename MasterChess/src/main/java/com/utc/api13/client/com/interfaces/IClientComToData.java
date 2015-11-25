package com.utc.api13.client.com.interfaces;

import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

public interface IClientComToData {

	public void connectAsObserver(UUID game_id);

	public void getUsers();
	
	public void getUserInfo(UUID iduser);
	
	public void notifyConnection(PublicUserEntity pubUser);
	
	public void disconnect(UUID sender);
	
	public void sendProposition(UUID sender, UUID reciever, boolean chattable, boolean observable, PublicUserEntity user);
	
	public void answerProposition(UUID sender, UUID reciever, boolean chattable, boolean observable, boolean answer);
	
	public void validateMove(UUID idPlayer, MoveEntity move);

	public void sendUserUpdates(PublicUserEntity user);

	public void pushReplayToServer(PublicUserEntity user, GameEntity game);

	public void sendProposition(PublicUserEntity player);

	public void sendAnswer(String answer, PublicUserEntity sender);

	public void surrender(UUID uid);

	public void victoryBySurrender(UUID uid);

	public void endGameBySurrender();

	public void endGameByLeaving();

	public void requestPlayerForLeaving(UUID uid, boolean answer);

	public void sendTextChat(String texte , UUID partie);

	public void getAllParties();

	public void observerLeave(UUID uid);

	public void sendLeavingMessage(UUID idPartie);

	public ComClientManager getComClientManagerInstance();

	public void setComClientManagerInstance(ComClientManager comClientManagerInstance);

	

}
