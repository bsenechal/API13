package com.utc.api13.commun.messages;

import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class RequestPlayerLeaving extends Message {

    private static final long serialVersionUID = -4586898422959823860L;

    public RequestPlayerLeaving(UUID sender, UUID receiver) {
        super(sender, receiver);

    }

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        // Informs the client of a new observer connection
        comClientManager.getIClientDataToCom().requestPlayerForLeaving(sender);
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        comServerManager.sendMessage(comServerManager.findChannelHandlerContextFromUserId(receiver).channel(), this);
    }

}
