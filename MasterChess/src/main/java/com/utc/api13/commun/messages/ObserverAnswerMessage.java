package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.commun.messages.Message;

import io.netty.channel.ChannelHandlerContext;

public class ObserverAnswerMessage extends Message {
	private static final Logger logger = Logger.getLogger(ObserverAnswerMessage.class);

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
