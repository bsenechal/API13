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
	private String password;
	
	/**
	 * @param sender
	 * @param receiver
	 * @param pubUser
	 * @param pwd
	 */
	public ConnectMessage(UUID sender, UUID receiver, PublicUserEntity pubUser, String pwd) {
		super(sender, receiver);
		this.pubUser = pubUser;
		this.password = pwd;
	}
	
	public PublicUserEntity getPubUser() {
		return pubUser;
	}

	public void setPubUser(PublicUserEntity pubUser) {
		this.pubUser = pubUser;
	}

	@Override
	public void proceed(ChannelHandlerContext ctx,ComClientManager comClientManager) {
		// TODO 
		
	}


	@Override
	public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
		//multicast new User

		comServerManager.getIServerToComm().notifyConnections(pubUser);

	}



}
