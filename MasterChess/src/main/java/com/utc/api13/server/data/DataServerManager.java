/**
 * 
 */
package com.utc.api13.server.data;

import java.util.List;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.UserEntity;
import com.utc.api13.server.data.interfaces.IServerToComm;

/**
 * @author Benoît
 *
 */
public class DataServerManager {
    private IServerToComm serverToComm;
    // Interface comm to data
    private List<UserEntity> connectedUsers;
    private List<GameEntity> currentGames;

    /**
     * @return the serverToComm
     */
    public IServerToComm getServerToComm() {
        return serverToComm;
    }

    /**
     * @param serverToComm
     *            the serverToComm to set
     */
    public void setServerToComm(IServerToComm serverToComm) {
        this.serverToComm = serverToComm;
    }

    /**
     * @return the connectedUsers
     */
    public List<UserEntity> getConnectedUsers() {
        return connectedUsers;
    }

    /**
     * @param connectedUsers
     *            the connectedUsers to set
     */
    public void setConnectedUsers(List<UserEntity> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    /**
     * @return the currentGames
     */
    public List<GameEntity> getCurrentGames() {
        return currentGames;
    }

    /**
     * @param currentGames
     *            the currentGames to set
     */
    public void setCurrentGames(List<GameEntity> currentGames) {
        this.currentGames = currentGames;
    }

    /**
     * @param serverToComm
     * @param connectedUsers
     * @param currentGames
     */
    public DataServerManager(IServerToComm serverToComm, List<UserEntity> connectedUsers,
            List<GameEntity> currentGames) {
        super();
        this.serverToComm = serverToComm;
        this.connectedUsers = connectedUsers;
        this.currentGames = currentGames;
    }
}
