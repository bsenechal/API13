package com.utc.api13.server;

import com.utc.api13.server.com.ComServerManager;
import com.utc.api13.server.data.DataServerManager;

public class AppServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ComServerManager comServerManager = new ComServerManager(8000);
			
		DataServerManager dataServerManager = new DataServerManager();
		
		dataServerManager.setIServeurToData(comServerManager.getServerToDataImpl());
		
		//comServerManager.setIServerToComm(dataServerManager.getServerToCommImpl());
		
		comServerManager.launchAppCom();

		// TODO : a rajouter
//		comServerManager.close();
	}

}
