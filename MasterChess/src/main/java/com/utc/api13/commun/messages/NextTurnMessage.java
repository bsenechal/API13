package com.utc.api13.commun.messages;

import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.enumerations.GameStatusEnum;
import com.utc.api13.server.com.ComServerManager;

public class NextTurnMessage extends Message {
	
	private GameStatusEnum isfinished;
	private UUID nexttoplay;

	public NextTurnMessage(UUID sender, UUID receiver, GameStatusEnum isfinished, UUID nexttoplay) {
		super(sender, receiver);
		this.isfinished = isfinished;
		this.nexttoplay = nexttoplay;
	}

	@Override
	public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) throws Exception {
//		comClientManager.getIClientDataToCom().nextturn(this.isfinished, this.nexttoplay);
	}

	@Override
	public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
		// Never reaches the server !
	}

}
