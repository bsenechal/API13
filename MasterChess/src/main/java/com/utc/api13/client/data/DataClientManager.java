/**
 * 
 */
package com.utc.api13.client.data;

import javafx.collections.ObservableSet;

import com.utc.api13.client.com.interfaces.InterfaceFromData;
import com.utc.api13.client.data.interfaces.IClientToComm;
import com.utc.api13.client.data.interfaces.IClientToIHM;
import com.utc.api13.commun.entities.UserEntity;

/**
 * @author Benoît
 *
 */
public class DataClientManager {
	private IClientToComm clientToComm;
	private IClientToIHM clientToIHM;
	private InterfaceFromData interfaceFromData;
	private UserEntity userLocal;
	private ObservableSet<UserEntity> userList;

	/**
	 * @return the clientToComm
	 */
	public IClientToComm getClientToComm() {
		return clientToComm;
	}

	/**
	 * @param clientToComm
	 *            the clientToComm to set
	 */
	public void setClientToComm(final IClientToComm clientToComm) {
		this.clientToComm = clientToComm;
	}

	/**
	 * @return the clientToIHM
	 */
	public IClientToIHM getClientToIHM() {
		return clientToIHM;
	}

	/**
	 * @param clientToIHM
	 *            the clientToIHM to set
	 */
	public void setClientToIHM(final IClientToIHM clientToIHM) {
		this.clientToIHM = clientToIHM;
	}

	/**
	 * @return the interfaceFromData
	 */
	public InterfaceFromData getInterfaceFromData() {
		return interfaceFromData;
	}

	/**
	 * @param interfaceFromData
	 *            the interfaceFromData to set
	 */
	public void setInterfaceFromData(final InterfaceFromData interfaceFromData) {
		this.interfaceFromData = interfaceFromData;
	}

	/**
	 * @return the userLocal
	 */
	public UserEntity getUserLocal() {
		return userLocal;
	}

	/**
	 * @param userLocal
	 *            the userLocal to set
	 */
	public void setUserLocal(final UserEntity userLocal) {
		this.userLocal = userLocal;
	}
	
	/**
     * @return the userList
     */
    public ObservableSet<UserEntity> getUserList() {
        return userList;
    }

    /**
     * @param userLocal
     *            the userLocal to set
     */
    public void setUserList(final ObservableSet<UserEntity> userList) {
        this.userList = userList;
    }

	/**
	 * @param clientToComm
	 * @param clientToIHM
	 * @param interfaceFromData
	 * @param userLocal
	 */
	public DataClientManager(final IClientToComm clientToComm, final IClientToIHM clientToIHM,
			final InterfaceFromData interfaceFromData, final UserEntity userLocal) {
		super();
		this.clientToComm = clientToComm;
		this.clientToIHM = clientToIHM;
		this.interfaceFromData = interfaceFromData;
		this.userLocal = userLocal;
	}

}
