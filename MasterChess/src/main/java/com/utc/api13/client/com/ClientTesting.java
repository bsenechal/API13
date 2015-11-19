package com.utc.api13.client.com;

import java.util.UUID;

import com.utc.api13.commun.messages.ChatMessage;


/**
 * 
 * @author stephane, clemence
 * Use to test client configuration
 *
 */

public class ClientTesting {
	
	public static void main(String args[]){
		ComClientManager manager =null;
		try{
			manager = new ComClientManager();
			manager.launchAppCom("localhost",8000);
			ChatMessage msg = new ChatMessage(new UUID(0, 0),new UUID(0, 0),"Hello World");
			manager.sendMessage(msg);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//manager.close();
		}
	}

}
