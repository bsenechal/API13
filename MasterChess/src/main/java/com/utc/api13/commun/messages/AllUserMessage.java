package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class AllUserMessage extends Message {
	private static final Logger logger = Logger.getLogger(AllUserMessage.class);
	public AllUserMessage(UUID sender, UUID receiver) {
		super(sender, receiver);
		// TODO Auto-generated constructor stub
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
