package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.server.com.ComServerManager;
import com.utc.api13.server.com.ServerHanlder;

import io.netty.channel.ChannelHandlerContext;

public class ChatMessage extends Message {

	/**
	* 
	*/
	private static final long serialVersionUID = -55526017055065873L;
	private static final Logger logger = Logger.getLogger(ChatMessage.class);
	String message;

	/**
	 * @param sender
	 * @param receiver
	 * @param message
	 */
	public ChatMessage(UUID sender, UUID receiver, String message) {
		super(sender, receiver);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void proceed(ChannelHandlerContext ctx,ComClientManager comClientManager) {
		System.out.println(this.getMessage());
		//Trouver le game et ajouter le message

	}

	@Override
	public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
		
//		try {
//			ServerHanlder.getInstance().replyAll(ctx,new ChatMessage(new UUID(0, 0), new UUID(0, 0), message ));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
