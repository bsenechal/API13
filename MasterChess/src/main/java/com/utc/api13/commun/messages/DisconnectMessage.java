package com.utc.api13.commun.messages;

import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class DisconnectMessage extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = -8917480597959281133L;
    private UUID idPubUser;

    /**
     * @param sender
     * @param receiver
     * @param pubUser
     */
    public DisconnectMessage(UUID sender, UUID receiver, UUID idPubUser) {
        super(sender, receiver);
        this.idPubUser = sender;
    }

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        comClientManager.getIClientDataToCom().notifyDisconnection(idPubUser);
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        comServerManager.unlinkUserToChannelHandlerContext(idPubUser);
        comServerManager.getIServerDataToCom().disconnect(idPubUser);
        this.setSender(this.getReceiver());
        comServerManager.broadcastMessage(this);
    }

}
