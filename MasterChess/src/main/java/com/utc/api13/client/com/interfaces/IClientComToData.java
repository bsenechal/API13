package com.utc.api13.client.com.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

public interface IClientComToData {

	public boolean connectAsObserver(UUID game_id);

	public List<PublicUserEntity> getUsers();
	
	public void notifyConnection(PublicUserEntity pubUser);
	
	public boolean validateMove(UUID idPlayer, MoveEntity move);

	public void sendUserUpdates(PublicUserEntity user);

	public boolean pushReplayToServer(PublicUserEntity user, GameEntity game);

	public void sendProposition(PublicUserEntity player);

	public boolean sendAnswer(String answer, PublicUserEntity sender);

	public boolean surrender(UUID uid);

	public void victoryBySurrender(UUID uid);

	public void endGameBySurrender();

	public void endGameByLeaving();

	public void requestPlayerForLeaving(UUID uid, boolean answer);

	public void sendTextChat(String text, UUID idPartie);

	public List<GameEntity> getAllParties();

	public void observerLeave(UUID uid);

	public void sendLeavingMessage(UUID idPartie);

	public ComClientManager getComClientManagerInstance();

	public void setComClientManagerInstance(ComClientManager comClientManagerInstance);

}
