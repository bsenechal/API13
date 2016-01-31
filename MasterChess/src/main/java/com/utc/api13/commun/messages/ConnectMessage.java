package com.utc.api13.commun.messages;

import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class ConnectMessage extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = 4221107628906920944L;
    private PublicUserEntity pubUser;

    /**
     * @param sender
     * @param receiver
     * @param pubUser
     */
    public ConnectMessage(UUID sender, UUID receiver, PublicUserEntity pubUser) {
        super(sender, receiver);
        this.pubUser = pubUser;
    }

    public PublicUserEntity getPubUser() {
        return pubUser;
    }

    public void setPubUser(PublicUserEntity pubUser) {
        this.pubUser = pubUser;
    }

    /**
     * Handles the message when received on the client.
     * Calls the 'notifyConnection' method from Data for the client to display the 
     * newly connected user.
     */
    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        comClientManager.getIClientDataToCom().notifyConnection(pubUser);
    }

    /**
     * Handles the message when received on the server.
     * Registers the new user's communication channel object
     * Notifies Data of a new player connection
     * Multicasts the new connection to all currently connected players
     */
    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        comServerManager.linkUserToChannelHandlerContext(pubUser.getId(), ctx);
        comServerManager.getIServerDataToCom().notifyConnections(pubUser);
        this.setSender(this.getReceiver());
        comServerManager.broadcastMessage(this);
    }

}
