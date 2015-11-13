package com.utc.api13.server.com;

import java.util.UUID;

public abstract class Message {
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


	abstract void proceed();

}
