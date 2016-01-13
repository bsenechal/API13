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
    private int timer;

    /**
     * @param sender
     * @param receiver
     * @param gameId
     */
    public GameRequestMessage(UUID sender, UUID receiver, boolean chattable, boolean observable, int timer) {
        super(sender, receiver);
        this.chattable = chattable;
        this.observable = observable;
        this.timer = timer;
    }

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        comClientManager.getIClientDataToCom().printProposition(getSender(), observable, chattable, timer);
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
    
    public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}
}
