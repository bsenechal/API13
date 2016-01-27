package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

/**
 * Message that informs players and observers of a new move in a game (after it
 * has been validated by the server)
 * 
 * @author Thomas
 *
 */
public class MoveMessage extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = -5240625223458694460L;
    private static final Logger logger = Logger.getLogger(MoveMessage.class);
    MoveEntity move;

    /**
     * @param sender
     * @param receiver
     * @param move
     */
    public MoveMessage(UUID sender, UUID receiver, MoveEntity move) {
        super(sender, receiver);
        this.move = move;
    }

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        comClientManager.getIClientDataToCom().displayResult(move.getUserID(), move);
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        // Never goes to the server.
    }

    public MoveEntity getMove() {
        return move;
    }

    public void setMove(MoveEntity move) {
        this.move = move;
    }

}
