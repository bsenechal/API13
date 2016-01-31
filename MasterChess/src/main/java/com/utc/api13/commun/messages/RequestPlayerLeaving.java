package com.utc.api13.commun.messages;

import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class RequestPlayerLeaving extends Message {

    private static final long serialVersionUID = -4586898422959823860L;
    boolean abandon;

    public RequestPlayerLeaving(UUID sender, UUID receiver, boolean abandon) {
        super(sender, receiver);
        this.abandon = abandon;

    }

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        // Informs the client of a new observer connection
    	if (!abandon) {
    		comClientManager.getIClientDataToCom().requestPlayerForLeaving(sender);
    	}else{
    		comClientManager.getIClientDataToCom().endGameBySurrender();
    	}
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
    	if (!abandon) {
    		comServerManager.sendMessage(comServerManager.findChannelHandlerContextFromUserId(receiver).channel(), this);
    	}else{
    		comServerManager.sendMessage(comServerManager.findChannelHandlerContextFromUserId(receiver).channel(), this);
    	}
    }

}
