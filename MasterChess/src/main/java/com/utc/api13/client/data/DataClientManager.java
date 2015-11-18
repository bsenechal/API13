/**
 * 
 */
package com.utc.api13.client.data;

import com.utc.api13.client.com.interfaces.IClientToDataImpl;
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
	private IClientToDataImpl iClientToDataImpl;
	private UserEntity userLocal;

	
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
	 * @param clientToComm
	 * @param clientToIHM
	 * @param interfaceFromData
	 * @param userLocal
	 */
	public DataClientManager(final IClientToComm clientToComm, final IClientToIHM clientToIHM,
			final IClientToDataImpl iClientToDataImpl, final UserEntity userLocal) {
		super();
		this.clientToComm = clientToComm;
		this.clientToIHM = clientToIHM;
		this.iClientToDataImpl = iClientToDataImpl;
		this.userLocal = userLocal;
	}

	/**
	 * @return the iClientToDataImpl
	 */
	public IClientToDataImpl getiClientToDataImpl() {
		return iClientToDataImpl;
	}

	/**
	 * @param iClientToDataImpl the iClientToDataImpl to set
	 */
	public void setiClientToDataImpl(IClientToDataImpl iClientToDataImpl) {
		this.iClientToDataImpl = iClientToDataImpl;
	}

}
