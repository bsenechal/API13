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

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        comClientManager.getIClientDataToCom().printProposition(getSender(), observable, chattable, timer, timerInt);
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
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
