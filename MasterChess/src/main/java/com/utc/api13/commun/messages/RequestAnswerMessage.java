
package com.utc.api13.commun.messages;

import java.util.List;
import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class RequestAnswerMessage extends Message {

    /**
     * 
     */
    private static final long serialVersionUID = -5244707144483762561L;
    private boolean chattable;
    private boolean observable;
    private Integer timerInt;
    private boolean timer;
    private boolean answer;
    private GameEntity ge;

    private static final String REJECTION_MESSAGE = "Your request has been refused";

    /**
     * @param sender
     * @param receiver
     * @param gameId
     */
    public RequestAnswerMessage(UUID sender, UUID receiver, boolean chattable, boolean observable, boolean answer,
            boolean timer, Integer timerInt) {
        super(sender, receiver);
        this.chattable = chattable;
        this.observable = observable;
        this.answer = answer;
        this.timer = timer;
        this.timerInt = timerInt;
    }
    
    /**
     * Handles the message when received on the client.
     * Gives the answer to the player.
     */
    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        if (answer) {
            comClientManager.getIClientDataToCom().initGame(this.ge);
        } else {
            comClientManager.getIClientDataToCom().notifyRejection(this.sender, REJECTION_MESSAGE);
        }
    }

    /**
     * Handles the message when received on the server.
     * Carries answer of a proposition request.
     * Informs the server and the requesting client.
     */
    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        if (answer) {
            this.ge = comServerManager.getIServerDataToCom().createGame(getSender(), getReceiver(), chattable,
                    observable, timer, timerInt);
            // On retourne l'info a l'utilisateur
            comServerManager.sendMessage(ctx.channel(), this);
            // On recupere la liste des parties mise à jour
            List<GameEntity> games = comServerManager.getIServerDataToCom().getAllGames();
            // On broadcast la nouvelle liste de partie
            comServerManager.broadcastMessage(new AllGameMessage(sender, receiver, games));
        }
        // On envoie aussi le message a l'autre joueur
        comServerManager.sendMessage(comServerManager.findChannelHandlerContextFromUserId(receiver).channel(), this);
    }

    public boolean isChattable() {
        return chattable;
    }

    public void setChattable(boolean chattable) {
        this.chattable = chattable;
    }

    public boolean isObservable() {
        return observable;
    }

    public void setObservable(boolean observable) {
        this.observable = observable;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public Integer getTimer() {
        return timerInt;
    }

    public void setTimer(Integer timerInt) {
        this.timerInt = timerInt;
    }

    public void setTimer(boolean timer) {
        this.timer = timer;
    }

}
