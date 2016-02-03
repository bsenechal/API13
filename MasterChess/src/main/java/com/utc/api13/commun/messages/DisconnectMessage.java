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
    public DisconnectMessage(UUID sender, UUID receiver) {
        super(sender, receiver);
        this.idPubUser = sender;
    }

    /**
     * Handles the message when received on the client. Calls the
     * 'notifyDiconnection' method from Data. Registers new client disconnection
     * on the client side.
     */
    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        comClientManager.getIClientDataToCom().notifyDisconnection(idPubUser);
    }

    /**
     * Handles the message when received on the server. Removes the user chanel
     * from registered channels Notifies disconection to Data Notifies
     * disconnection to the connected users
     */
    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        comServerManager.unlinkUserToChannelHandlerContext(idPubUser);
        comServerManager.getIServerDataToCom().disconnect(idPubUser);
        this.setSender(this.getReceiver());
        comServerManager.broadcastMessage(this);
    }

}
