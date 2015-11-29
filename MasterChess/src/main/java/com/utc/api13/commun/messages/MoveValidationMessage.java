package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class MoveValidationMessage extends Message {
	private static final Logger logger = Logger.getLogger(MoveValidationMessage.class);
	MoveEntity  move;
	

	/**
	 * @param sender
	 * @param receiver
	 * @param move
	 */
	public MoveValidationMessage(UUID sender, UUID receiver, MoveEntity move) {
		super(sender, receiver);
		this.move = move;
	}

	public MoveEntity getMove() {
		return move;
	}

	public void setMove(MoveEntity move) {
		this.move = move;
	}

	@Override
	public void proceed(ChannelHandlerContext ctx,ComClientManager comClientManager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
		// TODO Auto-generated method stub
		
	}

}
