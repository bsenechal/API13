package com.utc.api13.client.com;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.utc.api13.commun.messages.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 
 * @author stephane, clemence, thomas Manage User process on a message reception
 * 
 */

public class ClientHandler extends SimpleChannelInboundHandler<Message> {

    private int ping_lost; // number of HertBeat messages lost in a row
    private static final Logger LOGGER = Logger.getLogger(ClientHandler.class);
    private ComClientManager comClientManager = null;

    public ClientHandler(ComClientManager comClientManager) {
        this.comClientManager = comClientManager;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message arg1) throws Exception {
        arg1.proceed(ctx, comClientManager);
        ping_lost = 0; // message received => host is alive

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.WRITER_IDLE) {
                LOGGER.info("No message from server, waiting ...");
                ping_lost++;
                if (ping_lost > 2) { // If x pings lost in a row, assuming that
                                     // server is down
                    throw new IOException("Connection timeout");
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error("Lost connection, check your network connection");
        LOGGER.error(cause);
        ctx.close();
    }
}
