package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class DisconnectMessage extends Message {
	private static final Logger logger = Logger.getLogger(ConnectMessage.class);
	private UUID idPubUser;

	/**
	 * @param sender
	 * @param receiver
	 * @param pubUser
	 */
	public DisconnectMessage(UUID sender, UUID receiver, UUID idPubUser) {
		super(sender, receiver);
		this.idPubUser = sender;
	}

	@Override
	public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
		// comClientManager.getIClientDataToCom().
	}

	@Override
	public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
		comServerManager.unlinkUserToChannelHandlerContext(idPubUser);
		comServerManager.getIServerDataToCom().disconnect(idPubUser);
		AllUserMessage msg = new AllUserMessage(new UUID(0, 0), null);
		comServerManager.broadcastMessage(msg);
	}

}
