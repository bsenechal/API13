package com.utc.api13.commun.messages;

import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;

public class AllUserMessage extends Message {

	public AllUserMessage(UUID sender, UUID receiver) {
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
