package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class ConnectMessage extends Message {
	private static final Logger logger = Logger.getLogger(ConnectMessage.class);
	private PublicUserEntity pubUser;
	
	/**
	 * @param sender
	 * @param receiver
	 * @param pubUser
	 */
	public ConnectMessage(UUID sender, UUID receiver, PublicUserEntity pubUser) {
		super(sender, receiver);
		this.pubUser = pubUser;
	}
	
	public PublicUserEntity getPubUser() {
		return pubUser;
	}

	public void setPubUser(PublicUserEntity pubUser) {
		this.pubUser = pubUser;
	}

	@Override
	public void proceed(ChannelHandlerContext ctx,ComClientManager comClientManager) {
		// TODO manque méthide dans interfaceDataToComm
		//comClientManager.getIClientDataToCom().
	}


	@Override
	public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
		comServerManager.linkUserToChannelHandlerContext(pubUser.getId(), ctx);
		comServerManager.getIServerDataToCom().notifyConnections(pubUser);
		AllUserMessage msg = new AllUserMessage(new UUID(0, 0), null);
		comServerManager.broadcastMessage(msg);
	}



}
