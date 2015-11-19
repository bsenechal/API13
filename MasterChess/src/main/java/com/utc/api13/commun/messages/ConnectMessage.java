package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.client.com.interfaces.IClientToData;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.server.com.ComServerManager;
import com.utc.api13.server.com.ServerHanlder;

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

	public void proceed(ChannelHandlerContext ctx, ComClientManager clientManager) {
		// 
		
	}
	
	public void proceed(ChannelHandlerContext ctx, ComServerManager serverManager){
		// Connection request from client
		serverManager.userConnection(this.pubUser, this.password);
	}

	@Override
	public void proceedServer(ChannelHandlerContext ctx) {
		//multicast new User
		try {
			ServerHanlder.getInstance().replyAll(ctx,new ConnectMessage(new UUID(0, 0), new UUID(0, 0), pubUser, "" ));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void proceed(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
