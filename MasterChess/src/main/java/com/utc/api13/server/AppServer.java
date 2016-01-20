/**
 * 
 */
package com.utc.api13.server;

import com.utc.api13.server.com.ComServerManager;
import com.utc.api13.server.data.DataServerManager;

public class AppServer {

    /**
     * @param args
     */
    public static void main(String[] args) {
    	int port = 8000;
    	if (args.length > 0){
    		port = Integer.parseInt(args[0]);
    	}
        ComServerManager comServerManager = new ComServerManager(port);

        DataServerManager dataServerManager = new DataServerManager();

        dataServerManager.setIServeurComToData(comServerManager.getServeurComToDataImpl());

        comServerManager.setIServerDataToCom(dataServerManager.getServerDataToComImpl());

        comServerManager.launchAppCom();
    }

}
