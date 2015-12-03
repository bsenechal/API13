package com.utc.api13.client.com.interfaces;

import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

public interface IClientComToData {

	/**
	 * to request observation connection to a replay from server
	 */
	public void connectAsObserver(UUID game_id);

	/**
	 * to request all public users connected from server
	 */
	public void getUsers();

	/**
	 * to request a user profile from server
	 */
	public void getUserInfo(UUID iduser);

	/**
	 * to request connection to server
	 */
	public void notifyConnection(PublicUserEntity pubUser);

	/**
	 * to request disconnection of application to server
	 */
	public void disconnect(UUID sender);

	/**
	 * to send a proposition to a another connected user
	 */
	public void sendProposition(UUID sender, UUID reciever, boolean chattable, boolean observable,
			PublicUserEntity user);

	/**
	 * to answer a proposition to a another connected user
	 */
	public void answerProposition(UUID sender, UUID reciever, boolean chattable, boolean observable, boolean answer);

	/**
	 * to request a validation of move.
	 */
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

	public void sendTextChat(String texte, UUID partie);

	/**
	 * to request all games
	 */
	public void getAllParties();

	public void observerLeave(UUID uid);

	public void sendLeavingMessage(UUID idPartie);

	public ComClientManager getComClientManagerInstance();

	public void setComClientManagerInstance(ComClientManager comClientManagerInstance);

}
