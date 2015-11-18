package com.utc.api13.client;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.client.data.DataClientManager;
import com.utc.api13.client.ihm.IHMManager;

/**
 * Hello world!
 *
 */
public class AppClient {

	// private static final Logger LOGGER = Logger.getLogger(AppClient.class);

	public static void main(String[] args) {
		IHMManager ihmManager = new IHMManager();
		ComClientManager comClientManager = new ComClientManager();

		DataClientManager dataClientManager = new DataClientManager();
		dataClientManager.setiClientToData(comClientManager.getClientToDataImpl());
		dataClientManager.setiIHMFromData(ihmManager.getIHMFromDataImpl());

		ihmManager.setClientToIHM(dataClientManager.getClientToIHMImpl());
		comClientManager.setIClientToComm(dataClientManager.getClientToCommImpl());
		comClientManager.launchAppCom("localhost", 8000);
		ihmManager.launchAppIHM(args);
		// TODO : Faire une vrai gestion d'erreur
		comClientManager.close();

	}
}
