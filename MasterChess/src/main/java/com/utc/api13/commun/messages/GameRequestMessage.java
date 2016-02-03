package com.utc.api13.commun.messages;

import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class GameRequestMessage extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = 978459564519511506L;
    private boolean chattable;
    private boolean observable;
    private Integer timerInt;
    private boolean timer;

    /**
     * @param sender
     * @param receiver
     * @param gameId
     */
    public GameRequestMessage(UUID sender, UUID receiver, boolean chattable, boolean observable, boolean timer,
            Integer timerInt) {
        super(sender, receiver);
        this.chattable = chattable;
        this.observable = observable;
        this.timerInt = timerInt;
        this.timer = timer;
    }

    /**
     * Handles the message when received on the client. Calls the
     * 'printProposition' method from Data.
     */
    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        comClientManager.getIClientDataToCom().printProposition(getSender(), observable, chattable, timer, timerInt);
    }

    /**
     * Handles the message when received on the server. Informs the server of a
     * new game request Is sent to the targeted client
     */
    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        if (comServerManager.getIServerDataToCom().isPlaying(receiver)) {
            comServerManager.sendMessage(comServerManager.findChannelHandlerContextFromUserId(sender).channel(),
                    new RequestAnswerMessage(sender, receiver, chattable, observable, false, timer, timerInt));
        } else {
            comServerManager.sendMessage(comServerManager.findChannelHandlerContextFromUserId(receiver).channel(),
                    this);
        }
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

    /**
     * @return the timerInt
     */
    public Integer getTimerInt() {
        return timerInt;
    }

    /**
     * @param timerInt
     *            the timerInt to set
     */
    public void setTimerInt(Integer timerInt) {
        this.timerInt = timerInt;
    }

    /**
     * @return the timer
     */
    public boolean isTimer() {
        return timer;
    }

    /**
     * @param timer
     *            the timer to set
     */
    public void setTimer(boolean timer) {
        this.timer = timer;
    }

}
