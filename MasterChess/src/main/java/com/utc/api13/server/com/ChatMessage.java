package com.utc.api13.server.com;

import java.util.UUID;

public class ChatMessage extends Message {

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
	void proceed() {
		// TODO Auto-generated method stub

	}

}
