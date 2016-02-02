package com.utc.api13.commun.messages;

import java.net.InetAddress;
import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class HeartBeat extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = -607617759169441509L;
    private InetAddress ping;

    /**
     * @param sender
     * @param receiver
     * @param ping
     */
    public HeartBeat(UUID sender, UUID receiver, InetAddress ping) {
        super(sender, receiver);
        this.ping = ping;
    }

    /**
     * Handles the message when received on the client. Request for ping ->
     * sending ping
     */
    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        ctx.writeAndFlush(new HeartBeat(receiver, sender, ping));
    }

    public InetAddress getPing() {
        return ping;
    }

    public void setPing(InetAddress ping) {
        this.ping = ping;
    }

    /**
     * Handles the message when received on the server. When ping is received,
     * assuming that client is alive
     */
    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        // Proceed HeartBeat on server
    }

}
