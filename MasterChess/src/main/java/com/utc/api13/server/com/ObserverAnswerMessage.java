package com.utc.api13.server.com;

import java.util.UUID;

import com.utc.api13.commun.messages.Message;

public class ObserverAnswerMessage extends Message {
	

	/**
	 * @param sender
	 * @param receiver
	 */
	public ObserverAnswerMessage(UUID sender, UUID receiver) {
		super(sender, receiver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void proceed() {
		// TODO Auto-generated method stub

	}



}