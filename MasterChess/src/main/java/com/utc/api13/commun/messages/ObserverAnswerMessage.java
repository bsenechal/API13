package com.utc.api13.commun.messages;

import java.util.UUID;

import com.utc.api13.commun.messages.Message;

import io.netty.channel.ChannelHandlerContext;

public class ObserverAnswerMessage extends Message {
	

	/**
	 * @param sender
	 * @param receiver
	 */
	public ObserverAnswerMessage(UUID sender, UUID receiver) {
		super(sender, receiver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void proceed(ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void proceedServer(ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		
	}



}
