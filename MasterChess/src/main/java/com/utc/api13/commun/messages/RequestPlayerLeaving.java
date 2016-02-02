package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class RequestPlayerLeaving extends Message {

    private static final long serialVersionUID = -4586898422959823860L;
    private static final Logger LOGGER = Logger.getLogger(RequestPlayerLeaving.class);

    private boolean abandon;
    private UUID gameId;

    public RequestPlayerLeaving(UUID sender, UUID receiver, UUID gmaeId, boolean abandon) {
        super(sender, receiver);
        this.abandon = abandon;
        this.gameId = gmaeId;

    }

    public RequestPlayerLeaving(UUID sender, UUID receiver, boolean abandon) {
        super(sender, receiver);
        this.abandon = abandon;

    }

    /**
     * Handles the message when received on the client. Informt the player tht
     * the opponent wants to leave the game.
     */
    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        // Informs the client of a new observer connection
        if (!abandon) {
            comClientManager.getIClientDataToCom().requestPlayerForLeaving(sender);
        } else {
            comClientManager.getIClientDataToCom().victoryBySurrender();
        }
    }

    /**
     * Handles the message when received on the server. Server sends the message
     * to the receiver.
     */
    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        if (!abandon) {
            LOGGER.info("requeste leaving");
            comServerManager.sendMessage(comServerManager.findChannelHandlerContextFromUserId(receiver).channel(),
                    this);
        } else {
            LOGGER.info("player abandons game");
            comServerManager.getIServerDataToCom().endGame(gameId);
            comServerManager.sendMessage(comServerManager.findChannelHandlerContextFromUserId(receiver).channel(),
                    this);
            comServerManager.broadcastMessage(
                    new AllGameMessage(new UUID(0, 0), null, comServerManager.getIServerDataToCom().getAllGames()));

        }
    }

}
