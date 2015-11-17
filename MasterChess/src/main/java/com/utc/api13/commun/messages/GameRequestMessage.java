package com.utc.api13.commun.messages;

import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;

public class GameRequestMessage extends Message {
	UUID gameId;
	
	/**
	 * @param sender
	 * @param receiver
	 * @param gameId
	 */
	public GameRequestMessage(UUID sender, UUID receiver, UUID gameId) {
		super(sender, receiver);
		this.gameId = gameId;
	}
	@Override
	public void proceed(ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub

	}
	public UUID getGameId() {
		return gameId;
	}
	public void setGameId(UUID gameId) {
		this.gameId = gameId;
	}
	@Override
	public void proceedServer(ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		
	}
	

}
