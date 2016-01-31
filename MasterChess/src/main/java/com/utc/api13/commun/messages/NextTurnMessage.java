
package com.utc.api13.commun.messages;

import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.enumerations.GameStatusEnum;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class NextTurnMessage extends Message {

    /**
     * 
     */
    private static final long serialVersionUID = 8872346061808985890L;
    private GameStatusEnum isfinished;
    private UUID nexttoplay;

    public NextTurnMessage(UUID sender, UUID receiver, GameStatusEnum isfinished, UUID nexttoplay) {
        super(sender, receiver);
        this.isfinished = isfinished;
        this.nexttoplay = nexttoplay;
    }

    /**
     * Handles the message when received on the client.
     * Calls the 'nextTurn' method from Data.
     * Starts next turn
     */
    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) throws Exception {
        comClientManager.getIClientDataToCom().nextTurn(this.isfinished, this.nexttoplay);
        
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        // Never reaches the server !
    }

}
