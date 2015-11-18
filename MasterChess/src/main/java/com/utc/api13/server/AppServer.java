/**
 * 
 */
package com.utc.api13.server;

import com.utc.api13.server.com.interfaces.IServeurToDataImpl;
import com.utc.api13.server.data.DataServerManager;
import com.utc.api13.server.data.ServerToCommImpl;

public class AppServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataServerManager dataServerManager = new DataServerManager(new IServeurToDataImpl(), new ServerToCommImpl());
		dataServerManager.addUsers();
		
		
	}

}
