package com.utc.api13.commun.messages;

import java.io.Serializable;
import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;

public abstract class Message implements Serializable{
	
	private static final long serialVersionUID = -7165675510182836034L;
	UUID sender;
	UUID receiver; 
	
	/**
	 * @param sender
	 * @param receiver
	 */
	public Message(UUID sender, UUID receiver) {
		super();
		this.sender = sender;
		this.receiver = receiver;
	}

	
	public UUID getSender() {
		return sender;
	}


	public void setSender(UUID sender) {
		this.sender = sender;
	}


	public UUID getReceiver() {
		return receiver;
	}


	public void setReceiver(UUID receiver) {
		this.receiver = receiver;
	}


	public abstract void proceed(ChannelHandlerContext ctx) throws Exception;
	
	public abstract void proceedServer(ChannelHandlerContext ctx);

}
