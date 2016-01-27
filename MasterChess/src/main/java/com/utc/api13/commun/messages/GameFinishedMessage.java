package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class GameFinishedMessage extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = 3446575915066676519L;
    private static final Logger logger = Logger.getLogger(GameFinishedMessage.class);
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

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        // TODO Auto-generated method stub
        comClientManager.getIClientDataToCom().sendAnswerForLeaving(answer);

    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {

        comServerManager.getIServerDataToCom().endGame(this.game);
        comServerManager.sendMessage(comServerManager.findChannelHandlerContextFromUserId(receiver).channel(), this);

    }

}
