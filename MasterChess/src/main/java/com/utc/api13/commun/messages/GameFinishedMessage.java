package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.server.com.ComServerManager;
import com.utc.api13.server.com.ServerHanlder;

import io.netty.channel.ChannelHandlerContext;

public class GameFinishedMessage extends Message {
	private static final Logger logger = Logger.getLogger(GameFinishedMessage.class);
	GameEntity game;

	/**
	 * @param sender
	 * @param receiver
	 * @param game
	 */
	public GameFinishedMessage(UUID sender, UUID receiver, GameEntity game) {
		super(sender, receiver);
		this.game = game;
	}

	@Override
	public void proceed(ChannelHandlerContext ctx,ComClientManager comClientManager) {
		// TODO Auto-generated method stub

	}


	public GameEntity getGame() {
		return game;
	}

	public void setGame(GameEntity game) {
		this.game = game;
	}

	@Override
	public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
//		try {
//			ServerHanlder.getInstance().replyAll(ctx,new GameFinishedMessage(new UUID(0, 0), new UUID(0, 0), game ));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
