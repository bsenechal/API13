package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.server.com.ComServerManager;
import com.utc.api13.server.com.ServerHanlder;

import io.netty.channel.ChannelHandlerContext;

public class MoveMessage extends Message {
	private static final Logger logger = Logger.getLogger(MoveMessage.class);
	MoveEntity move;

	/**
	 * @param sender
	 * @param receiver
	 * @param move
	 */
	public MoveMessage(UUID sender, UUID receiver, MoveEntity move) {
		super(sender, receiver);
		this.move = move;
	}

	@Override
	public void proceed(ChannelHandlerContext ctx,ComClientManager comClientManager) {
		// TODO Auto-generated method stub

	}

	public MoveEntity getMove() {
		return move;
	}

	public void setMove(MoveEntity move) {
		this.move = move;
	}

	@Override
	public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
//		try {
//			ServerHanlder.getInstance().replyAll(ctx,new MoveMessage(new UUID(0, 0), new UUID(0, 0), move ));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
