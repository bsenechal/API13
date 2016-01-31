package com.utc.api13.commun.messages;

import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class GameFinishedMessage extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = 3446575915066676519L;
    private UUID game;

    private boolean answer;

    /**
     * @param sender
     * @param receiver
     * @param game
     */
    public GameFinishedMessage(UUID sender, UUID receiver, UUID game, boolean answer) {
        super(sender, receiver);
        this.game = game;
        this.answer = answer;

    }

    /**
     * Handles the message when received on the client.
     * Calls the 'sendAnswerForLeaving' method from Data.
     * Inform the client that the game is terminated.
     */
    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        comClientManager.getIClientDataToCom().sendAnswerForLeaving(answer);

    }

    /**
     * Handles the message when received on the server.
     * Informs the server and the other player that the game is terminated
     */
    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {

        comServerManager.getIServerDataToCom().endGame(this.game);
        comServerManager.sendMessage(comServerManager.findChannelHandlerContextFromUserId(receiver).channel(), this);

    }

}
