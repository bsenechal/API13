package com.utc.api13.commun.messages;

import java.net.InetAddress;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class HeartBeat extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = -607617759169441509L;
    private static final Logger logger = Logger.getLogger(HeartBeat.class);
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

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        // logger.info("Hello message received from server, answering Hello");
        ctx.writeAndFlush(new HeartBeat(receiver, sender, ping));
    }

    public InetAddress getPing() {
        return ping;
    }

    public void setPing(InetAddress ping) {
        this.ping = ping;
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        // TODO Auto-generated method stub
        // logger.info("Hello message received, peer is alive !");
    }

}
