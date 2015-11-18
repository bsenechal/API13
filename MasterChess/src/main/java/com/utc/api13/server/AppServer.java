/**
 * 
 */
package com.utc.api13.server;

import com.utc.api13.server.com.ServerManager;
//import com.utc.api13.server.com.interfaces.ToClientImpl;
import com.utc.api13.server.data.DataServerManager;

public class AppServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerManager serverManager = new ServerManager(80);
			
		DataServerManager dataServerManager = new DataServerManager();
//		dataServerManager.setInterfaceToClient(serverManager.getToClientImpl());
//				
//		serverManager.setIServerToComm(dataServerManager.getServerToCommImpl());
	}

}
