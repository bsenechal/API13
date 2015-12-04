package com.utc.api13.client.ihm;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.property.ProfilProperty;

public class IHMManager {

    private IClientDataToIHM myIClientToIHM;
    private ClientIHMToDataImpl myClientIHMToDataImpl;

    private ProfilProperty profil;



    public ProfilProperty getProfil() {
        return profil;
    }

    public void setProfil(ProfilProperty profil) {
        this.profil = profil;
    }



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