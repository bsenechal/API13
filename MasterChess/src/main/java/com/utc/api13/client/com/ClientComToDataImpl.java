package com.utc.api13.client.com;

import java.util.UUID;

import com.utc.api13.client.com.interfaces.IClientComToData;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.messages.AllGameMessage;
import com.utc.api13.commun.messages.AllUserMessage;
import com.utc.api13.commun.messages.ChatMessage;
import com.utc.api13.commun.messages.ConnectMessage;
import com.utc.api13.commun.messages.DisconnectMessage;
import com.utc.api13.commun.messages.ExcludeObserverMessage;
import com.utc.api13.commun.messages.GameFinishedMessage;
import com.utc.api13.commun.messages.GameRequestMessage;
import com.utc.api13.commun.messages.MoveValidationMessage;
import com.utc.api13.commun.messages.ObserverLeaveMessage;
import com.utc.api13.commun.messages.ObserverRequestMessage;
import com.utc.api13.commun.messages.PublicUserMessage;
import com.utc.api13.commun.messages.RequestAnswerMessage;
import com.utc.api13.commun.messages.RequestPlayerLeaving;
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
        comClientManagerInstance.sendMessage(new ObserverRequestMessage(null, new UUID(0, 0), game_id));
        return;
    }

    @Override
    public void getUsers() {
        comClientManagerInstance.sendMessage(new AllUserMessage(new UUID(0, 0), new UUID(0, 0)));
        return;
    }

    @Override
    public void validateMove(UUID idPlayer, MoveEntity move) {
        comClientManagerInstance.sendMessage(new MoveValidationMessage(idPlayer, new UUID(0, 0), move));
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
    public void endGameBySurrender(UUID idPlayer, UUID receiver, UUID gameId) {
        comClientManagerInstance.sendMessage(new RequestPlayerLeaving(idPlayer, receiver,gameId, true));

    }

    @Override
    public void requestPlayerForLeaving(UUID idPlayer, UUID idOtherPlayer) {
        comClientManagerInstance.sendMessage(new RequestPlayerLeaving(idPlayer, idOtherPlayer, false));

    }

    @Override
    public void sendTextChat(String text, UUID idPartie) {
        comClientManagerInstance.sendMessage(new ChatMessage(null, null, idPartie, text));
    }

    @Override
    public void getAllParties() {
        comClientManagerInstance.sendMessage(new AllGameMessage(new UUID(0, 0), new UUID(0, 0), null));
    }

    @Override
    public void observerLeave(UUID uid) {
        comClientManagerInstance.sendMessage(new ObserverLeaveMessage(uid, new UUID(0, 0)));
    }

    @Override
    public void sendLeavingMessage(UUID idPartie) {
        // TODO Auto-generated method stub

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
    public void sendProposition(UUID sender, UUID reciever, boolean chattable, boolean observable, boolean timer,
            Integer timerInt) {
        comClientManagerInstance
                .sendMessage(new GameRequestMessage(sender, reciever, chattable, observable, timer, timerInt));
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
    public void answerProposition(UUID sender, UUID reciever, boolean chattable, boolean observable, boolean answer,
            boolean timer, Integer timerInt) {
        comClientManagerInstance.sendMessage(
                new RequestAnswerMessage(sender, reciever, chattable, observable, answer, timer, timerInt));
    }

    /**
     * removing a user from the tchat
     * 
     * @param userId
     *            : the id of the user which will be deleted to the tchat
     * @param gameId
     *            : the game which the user watching
     */

    @Override
    public void removeUserFromChat(UUID userId, UUID gameId) {
        comClientManagerInstance.sendMessage(new ExcludeObserverMessage(userId, gameId));

    }

    @Override
    public void endGameByLeaving(UUID sender, UUID receiver, UUID gameId, boolean answer) {
        comClientManagerInstance.sendMessage(new GameFinishedMessage(sender, receiver, gameId, answer));
    }
}
