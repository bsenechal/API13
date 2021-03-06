package com.utc.api13.commun.messages;

import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class ObserverAnswerMessage extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = -336121149325334506L;

    private GameEntity game;

    /**
     * @param sender
     * @param receiver
     */
    public ObserverAnswerMessage(UUID sender, UUID receiver, GameEntity g) {
        super(sender, receiver);
        this.game = g;
    }

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        // Appeler InitGame()
        comClientManager.getIClientDataToCom().initGame(game);
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        // Never goes to the server
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }
}
