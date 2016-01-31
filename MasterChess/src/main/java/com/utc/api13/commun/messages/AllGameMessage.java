
package com.utc.api13.commun.messages;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

/**
 * Message used to tranfer the list of all currently played games.
 * 
 * @author Thomas
 *
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

    /**
     * Handles the message when received on the client.
     * Calls the 'displayAllGames' method from Data.
     */
    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        if (games != null) {
            comClientManager.getIClientDataToCom().displayAllGames(games);
        } else {
            logger.error("game list is null");
        }
    }

    /**
     * Handles the message when received on the server.
     * Gets the list of all currently played games and is sent back to the client.
     */
    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        games = comServerManager.getIServerDataToCom().getAllGames();
        comServerManager.sendMessage(ctx.channel(), this);
    }

}
