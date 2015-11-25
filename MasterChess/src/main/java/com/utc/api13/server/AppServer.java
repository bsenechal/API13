/**
 * 
 */
package com.utc.api13.server;

import java.util.UUID;

import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.server.com.ComServerManager;
import com.utc.api13.server.data.DataServerManager;

public class AppServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ComServerManager comServerManager = new ComServerManager(8000);
		PublicUserEntity user1 = new PublicUserEntity();
		user1.setId(UUID.randomUUID());
		user1.setLogin("login 1");
		user1.setStatus(true);
		
		PublicUserEntity user2 = new PublicUserEntity();
		user2.setId(UUID.randomUUID());
		user2.setLogin("login 2");
		user2.setStatus(false);
		
		DataServerManager dataServerManager = new DataServerManager();
		
		dataServerManager.setIServeurComToData(comServerManager.getServeurComToDataImpl());
		
		comServerManager.setIServerDataToCom(dataServerManager.getServerDataToComImpl());
		
		dataServerManager.getServerDataToComImpl().saveUserData(user1);
		dataServerManager.getServerDataToComImpl().saveUserData(user2);
		
		comServerManager.launchAppCom();
	}

}
