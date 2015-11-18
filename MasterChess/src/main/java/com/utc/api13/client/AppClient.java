package com.utc.api13.client;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.client.data.DataClientManager;
import com.utc.api13.client.ihm.IHMManager;

/**
 * Hello world!
 *
 */
public class AppClient{
	
//	private static final Logger LOGGER = Logger.getLogger(AppClient.class);

	public static void main(String[] args) {
		ComClientManager comClientManager = null;
		IHMManager ihmManager = new IHMManager();
		
		try {
			comClientManager = new ComClientManager("localhost", 80);
			
			DataClientManager dataClientManager = new DataClientManager();
			dataClientManager.setiClientToData(comClientManager.getClientToDataImpl());
			dataClientManager.setiIHMFromData(ihmManager.getIHMFromDataImpl());

			ihmManager.setClientToIHM(dataClientManager.getClientToIHMImpl());
			comClientManager.setIClientToComm(dataClientManager.getClientToCommImpl());
			
			ihmManager.launchAppIHM(args);
		} catch (InterruptedException e) {
			// TODO : Faire une vrai gestion d'erreur
			System.out.println("erreur"); 
		}
		finally{
			if (comClientManager != null){
				comClientManager.close();
			}
		}
	}
}
