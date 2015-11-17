package com.utc.api13.commun.messages;

import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;

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
	public void proceed(ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		System.out.println(this.getMessage());
		
	}


	@Override
	public void proceedServer(ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		
	}

}
