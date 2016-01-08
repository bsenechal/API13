package com.utc.api13.client.ihm;

import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.property.ConfirmationProperty;
import com.utc.api13.client.ihm.property.ErrorProperty;
import com.utc.api13.client.ihm.property.GameProperty;
import com.utc.api13.client.ihm.property.ProfilProperty;

public class IHMManager {

    private IClientDataToIHM myIClientToIHM;
    private ClientIHMToDataImpl myClientIHMToDataImpl;

    private ProfilProperty profil;
    private GameProperty game; 
    private ErrorProperty error;
	private ConfirmationProperty confirmation; 

    public ProfilProperty getProfil() {
        return profil;
    }

    public void setProfil(ProfilProperty profil) {
        this.profil = profil;
    }
    
    public GameProperty getGame() {
        return game;
    }

    public void setGame(GameProperty game) {
        this.game = game;
    }
    
    public ErrorProperty getError() {
        return this.error;
    }

    public void setError(ErrorProperty error) {
        this.error = error;
    }
    
    public ConfirmationProperty getConfirmation() {
        return this.confirmation;
    }

    public void setConfirmation(ConfirmationProperty confirmation) {
        this.confirmation = confirmation;
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