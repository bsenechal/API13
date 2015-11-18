package com.utc.api13.server.com;

import java.util.UUID;

public class RequestAnswerMessage extends Message {
	UUID gameId;

	
	/**
	 * @param sender
	 * @param receiver
	 * @param gameId
	 */
	public RequestAnswerMessage(UUID sender, UUID receiver, UUID gameId) {
		super(sender, receiver);
		this.gameId = gameId;
	}
	@Override
	void proceed() {
		// TODO Auto-generated method stub

	}
	public UUID getGameId() {
		return gameId;
	}
	public void setGameId(UUID gameId) {
		this.gameId = gameId;
	}
	

}
