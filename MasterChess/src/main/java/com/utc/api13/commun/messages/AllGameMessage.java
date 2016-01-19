package com.utc.api13.commun.messages;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * TODO à vérifier si la liste de games est trop lourde ou pas
 */
public class AllGameMessage extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = -5240625223458694460L;
    private static final Logger logger = Logger.getLogger(AllGameMessage.class);
    List<GameEntity> games;

    /**
     * @param sender
     * @param receiver
     * @param games
     */
    public AllGameMessage(UUID sender, UUID receiver, List<GameEntity> games) {
        super(sender, receiver);
        this.games = games;
    }

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
    }


}
