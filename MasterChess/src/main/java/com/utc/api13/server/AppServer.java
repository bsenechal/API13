/**
 * 
 */
package com.utc.api13.server;

import com.utc.api13.server.com.ComServerManager;
import com.utc.api13.server.data.DataServerManager;

public class AppServer {

    private AppServer() {

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ComServerManager comServerManager = new ComServerManager(8000);

        DataServerManager dataServerManager = new DataServerManager();

        comServerManager.setIServerDataToCom(dataServerManager.getServerDataToComImpl());

        comServerManager.launchAppCom();
    }

}
