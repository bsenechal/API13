package com.utc.api13.commun.messages;

import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class ExcludeObserverMessage extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = -5882266964398566864L;

    private UUID game; // Game on which we request to observe
    private UUID userId;

    /**
     * @param sender
     * @param receiver
     */

    public ExcludeObserverMessage(UUID userId, UUID gameId) {
        super(new UUID(0, 0), new UUID(0, 0));
        this.game = gameId;
        this.userId = sender;
    }

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {

        // nothing to do here
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {

        comServerManager.getIServerDataToCom().removeUserFromChat(this.userId, this.game);

    }
}
