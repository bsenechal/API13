package com.utc.api13.client.com;

import java.util.UUID;

import com.utc.api13.client.com.interfaces.IClientComToData;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.messages.AllUserMessage;
import com.utc.api13.commun.messages.ChatMessage;
import com.utc.api13.commun.messages.ConnectMessage;
import com.utc.api13.commun.messages.DisconnectMessage;
import com.utc.api13.commun.messages.GameRequestMessage;
import com.utc.api13.commun.messages.PublicUserMessage;
import com.utc.api13.commun.messages.RequestAnswerMessage;
import com.utc.api13.commun.messages.UserUpdateMessage;

public class ClientComToDataImpl implements IClientComToData {

    ComClientManager comClientManagerInstance;

    /**
     * @param comClientManagerInstance
     */
    public ClientComToDataImpl(ComClientManager comClientManagerInstance) {
        this.comClientManagerInstance = comClientManagerInstance;
    }

    @Override
    public void notifyConnection(PublicUserEntity pubUser) {
        comClientManagerInstance.sendMessage(new ConnectMessage(pubUser.getId(), new UUID(0, 0), pubUser));
    }

    @Override
    public void connectAsObserver(UUID game_id) {
        // TODO Auto-generated method stub
        return;
    }

    @Override
    public void getUsers() {
        comClientManagerInstance.sendMessage(new AllUserMessage(new UUID(0, 0), new UUID(0, 0)));
        return;
    }

    @Override
    public void validateMove(UUID idPlayer, MoveEntity move) {
        // TODO Auto-generated method stub
        return;
    }

    @Override
    public void sendUserUpdates(PublicUserEntity user) {
        // Called when user updates its profile
        UserUpdateMessage msg = new UserUpdateMessage(null, new UUID(0, 0));
        msg.setUsr(user);
        comClientManagerInstance.sendMessage(msg);
    }

    @Override
    public void pushReplayToServer(PublicUserEntity user, GameEntity game) {
        // TODO Auto-generated method stub
        return;
    }

    @Override
    public void sendProposition(PublicUserEntity player) {
        // TODO Auto-generated method stub

    }

    /* DEPRECATED !!! */
    @Override
    public void sendAnswer(String answer, PublicUserEntity sender) {
        // TODO Auto-generated method stub
        return;
    }

    @Override
    public void surrender(UUID uid) {
        // TODO Auto-generated method stub
        return;
    }

    @Override
    public void victoryBySurrender(UUID uid) {
        // TODO Auto-generated method stub

    }

    @Override
    public void endGameBySurrender() {
        // TODO Auto-generated method stub

    }

    @Override
    public void endGameByLeaving() {
        // TODO Auto-generated method stub

    }

    @Override
    public void requestPlayerForLeaving(UUID uid, boolean answer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendTextChat(String text, UUID idPartie) {
        // TODO Auto-generated method stub
        comClientManagerInstance.sendMessage(new ChatMessage(null, null, idPartie, text));
    }

    @Override
    public void getAllParties() {
        // TODO Auto-generated method stub
        return;
    }

    @Override
    public void observerLeave(UUID uid) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendLeavingMessage(UUID idPartie) {
        // TODO Auto-generated method stub

    }

    // TODO: UME : Euh why do you want to give access to the manager using the
    // interface ? -> seems wrong !
    /**
     * @return the comClientManagerInstance
     */
    @Override
    public ComClientManager getComClientManagerInstance() {
        return comClientManagerInstance;
    }

    /**
     * @param comClientManagerInstance
     *            the comClientManagerInstance to set
     */
    @Override
    public void setComClientManagerInstance(ComClientManager comClientManagerInstance) {
        this.comClientManagerInstance = comClientManagerInstance;
    }

    @Override
    public void getUserInfo(UUID iduser) {

        comClientManagerInstance.sendMessage(new PublicUserMessage(null, new UUID(0, 0), iduser));

    }

    @Override
    public void disconnect(UUID pubUser) {
        comClientManagerInstance.sendMessage(new DisconnectMessage(pubUser, new UUID(0, 0), pubUser));

    }

    @Override
    /**
     * Envoie une proposition de sender à receiver
     * 
     * @param chattable
     *            : peut-on utiliser le chat pendant la partie
     * @param observable
     *            : peut-on observer la partie
     * 
     * @see com.utc.api13.client.com.interfaces.IClientComToData#sendProposition(java.util.UUID,
     *      java.util.UUID, boolean, boolean,
     *      com.utc.api13.commun.entities.PublicUserEntity)
     */
    public void sendProposition(UUID sender, UUID reciever, boolean chattable, boolean observable,
            PublicUserEntity user) {
        comClientManagerInstance.sendMessage(new GameRequestMessage(sender, reciever, chattable, observable));
    }

    /**
     * Repond a une proposition de partie
     * 
     * @param chattable
     *            : peut-on utiliser le chat pendant la partie
     * @param observable
     *            : peut-on observer la partie
     * @param answer
     *            : reponse du joueur
     * 
     * @see com.utc.api13.client.com.interfaces.IClientComToData#answerProposition(java.util.UUID,
     *      java.util.UUID, boolean, boolean, boolean)
     */
    @Override
    public void answerProposition(UUID sender, UUID reciever, boolean chattable, boolean observable, boolean answer) {
        comClientManagerInstance.sendMessage(new RequestAnswerMessage(sender, reciever, chattable, observable, answer));
    }
}