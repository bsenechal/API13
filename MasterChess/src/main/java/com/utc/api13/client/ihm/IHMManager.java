package com.utc.api13.client.ihm;

import com.utc.api13.client.data.interfaces.IClientDataToIHM;

public class IHMManager {

    private IClientDataToIHM myIClientToIHM;
    private ClientIHMToDataImpl myClientIHMToDataImpl;

    public IHMManager() {
        this.myClientIHMToDataImpl = new ClientIHMToDataImpl(this);
    }

    public IClientDataToIHM getIClientDataToIHM() {
        return this.myIClientToIHM;
    }

    public void setIClientDataToIHM(IClientDataToIHM dataInterface) {
        myIClientToIHM = dataInterface;
    }

    public ClientIHMToDataImpl getClientIHMToDataImpl() {
        return myClientIHMToDataImpl;
    }

}