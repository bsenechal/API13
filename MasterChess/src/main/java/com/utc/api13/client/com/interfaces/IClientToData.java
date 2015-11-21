package com.utc.api13.client.com.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.AUserEntity;

public interface IClientToData {

	public boolean connectAsObserver(UUID game_id);

	public List<AUserEntity> getUsers();
	
	public boolean validateMove(UUID idPlayer, MoveEntity move);

	public void sendUserUpdates(PublicUserEntity user);

	public boolean pushReplayToServer(AUserEntity user, GameEntity game);

	public void sendProposition(AUserEntity player);

	public boolean sendAnswer(String answer, AUserEntity sender);

	public boolean surrender(UUID uid);

	public void victoryBySurrender(UUID uid);

	public void endGameBySurrender();

	public void endGameByLeaving();

	public void requestPlayerForLeaving(UUID uid, boolean answer);

	public void sendTextChat(String text, UUID idPartie);

	public List<GameEntity> getAllParties();

	public void observerLeave(UUID uid);

	public void sendLeavingMessage(UUID idPartie);

}
