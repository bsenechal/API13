package com.utc.api13.commun.messages;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.AUserEntity;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class AllUserMessage extends Message {
	private static final Logger logger = Logger.getLogger(AllUserMessage.class);
	List<AUserEntity> users = null;

	public AllUserMessage(UUID sender, UUID receiver) {
		super(sender, receiver);
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * @return the users
	 */
	public List<AUserEntity> getUsers() {
		return users;
	}


	/**
	 * @param users the users to set
	 */
	public void setUsers(List<AUserEntity> users) {
		this.users = users;
	}


	@Override
	public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
		if (users != null) {
			comClientManager.getIClientDataToCom().displayUsersList(users);
		}else{
			logger.error("users is null");
		}
	}

	@Override
	public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
		users = comServerManager.getIServerDataToCom().getConnectedUsers();
		comServerManager.sendMessage(ctx.channel(), this);
	}

}
