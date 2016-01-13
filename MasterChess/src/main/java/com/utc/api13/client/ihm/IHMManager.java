package com.utc.api13.client.ihm;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.property.ConfirmationProperty;
import com.utc.api13.client.ihm.property.ErrorProperty;
import com.utc.api13.client.ihm.property.GameProperty;
import com.utc.api13.client.ihm.property.ProfilProperty;
import com.utc.api13.client.ihm.property.PropositionProperty;

public class IHMManager {

    private IClientDataToIHM myIClientToIHM;
    private ClientIHMToDataImpl myClientIHMToDataImpl;
    private AppClient mainApp;
    
  

    private ProfilProperty profil;
    private PropositionProperty proposition;
    private GameProperty game;
    private ErrorProperty error;
    private ChatProperty chat;
    
    public ChatProperty getChat() {
        return chat;
    }

    public void setChat(ChatProperty chat) {
        this.chat = chat;
    }

    private ConfirmationProperty confirmation;

    public ProfilProperty getProfil() {
        return profil;
    }

    public void setProfil(ProfilProperty profil) {
        this.profil = profil;
    }

    public PropositionProperty getProposition() {
        return this.proposition;
    }

    public void setProposition(PropositionProperty proposition) {
        this.proposition = proposition;
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
//    public PublicUserEntity findUserByUUID(UUID  id){
//        PublicUserEntity user =null;
//        ObservableList<PublicUserEntity>users =this.getIClientDataToIHM().getUserList();
//        boolean end=true;
//        for (int i=0,len=users.size();i<len && end;i++){
//            if (users.get(i).getId()==id){
//                user=users.get(i);
//                end=!end;
//            }
//            
//        }
//        return user;
//    }
    
    public AppClient getMainApp() {
        return mainApp;
    }

    public void setMainApp(AppClient mainApp) {
        this.mainApp = mainApp;
    }
}