package com.utc.api13.commun.messages;

import java.net.InetAddress;
import java.util.UUID;

public class HeartBeat extends Message {

	InetAddress ping;
	
	/**
	 * @param sender
	 * @param receiver
	 * @param ping
	 */
	public HeartBeat(UUID sender, UUID receiver, InetAddress ping) {
		super(sender, receiver);
		this.ping = ping;
	}
	@Override
	public void proceed() {
		// TODO Auto-generated method stub
//		System.out.println("==> ping !");
	}
	public InetAddress getPing() {
		return ping;
	}
	public void setPing(InetAddress ping) {
		this.ping = ping;
	}
	
}
