package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class RequestAnswerMessage extends Message {
	private static final Logger logger = Logger.getLogger(RequestAnswerMessage.class);
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
	public void proceed(ChannelHandlerContext ctx,ComClientManager comClientManager) {
		// TODO Auto-generated method stub

	}
	public UUID getGameId() {
		return gameId;
	}
	public void setGameId(UUID gameId) {
		this.gameId = gameId;
	}
	@Override
	public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
		GameEntity game=new GameEntity();
		game.setId(gameId);
		//TODO ajouter les 2 players dans game.setWhitePlayer();
	}
	

}
