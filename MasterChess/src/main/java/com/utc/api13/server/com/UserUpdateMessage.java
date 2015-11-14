package com.utc.api13.server.com;

import java.util.UUID;

public class UserUpdateMessage extends Message {

	
	/**
	 * @param sender
	 * @param receiver
	 */
	public UserUpdateMessage(UUID sender, UUID receiver) {
		super(sender, receiver);
		// TODO Auto-generated constructor stub
	}

	@Override
	void proceed() {
		// TODO Auto-generated method stub

	}

}
