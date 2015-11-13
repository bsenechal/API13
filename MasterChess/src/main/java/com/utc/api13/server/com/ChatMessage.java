package com.utc.api13.server.com;

import java.util.UUID;

public class ChatMessage extends Message {

		/**
	 * 
	 */
	private static final long serialVersionUID = -55526017055065873L;
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
	public void proceed() {
		// TODO Auto-generated method stub

	}

}
