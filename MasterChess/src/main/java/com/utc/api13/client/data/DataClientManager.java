/**
 * 
 */
package com.utc.api13.client.data;

import com.utc.api13.client.data.interfaces.IClientToComm;
import com.utc.api13.client.data.interfaces.IClientToIHM;
import com.utc.api13.client.ihm.interfaces.IIHMFromData;
import com.utc.api13.commun.entities.UserEntity;

/**
 * @author Benoît
 *
 */
public class DataClientManager {
    private IClientToComm clientToComm;
    private IClientToIHM clientToIHM;
    private IIHMFromData ihmFromData;
    private UserEntity userLocal;
    
    /**
     * @return the clientToComm
     */
    public IClientToComm getClientToComm() {
        return clientToComm;
    }
    /**
     * @param clientToComm the clientToComm to set
     */
    public void setClientToComm(IClientToComm clientToComm) {
        this.clientToComm = clientToComm;
    }
    /**
     * @return the clientToIHM
     */
    public IClientToIHM getClientToIHM() {
        return clientToIHM;
    }
    /**
     * @param clientToIHM the clientToIHM to set
     */
    public void setClientToIHM(IClientToIHM clientToIHM) {
        this.clientToIHM = clientToIHM;
    }
    /**
     * @return the ihmFromData
     */
    public IIHMFromData getIhmFromData() {
        return ihmFromData;
    }
    /**
     * @param ihmFromData the ihmFromData to set
     */
    public void setIhmFromData(IIHMFromData ihmFromData) {
        this.ihmFromData = ihmFromData;
    }
    /**
     * @return the userLocal
     */
    public UserEntity getUserLocal() {
        return userLocal;
    }
    /**
     * @param userLocal the userLocal to set
     */
    public void setUserLocal(UserEntity userLocal) {
        this.userLocal = userLocal;
    }
    /**
     * @param clientToComm
     * @param clientToIHM
     * @param ihmFromData
     * @param userLocal
     */
    public DataClientManager(IClientToComm clientToComm, IClientToIHM clientToIHM, IIHMFromData ihmFromData,
            UserEntity userLocal) {
        super();
        this.clientToComm = clientToComm;
        this.clientToIHM = clientToIHM;
        this.ihmFromData = ihmFromData;
        this.userLocal = userLocal;
    }
}
