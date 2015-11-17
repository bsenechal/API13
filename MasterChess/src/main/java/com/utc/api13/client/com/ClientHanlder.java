package com.utc.api13.client.com;

import java.io.IOException;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.commun.messages.HeartBeat;
import com.utc.api13.commun.messages.Message;
import com.utc.api13.server.com.ServerManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 
 * @author stephane, clemence, thomas
 * Manage User process on a message reception
 *
 */

public class ClientHanlder extends SimpleChannelInboundHandler<Message>{

	private int ping_lost; // number of HertBeat messages lost in a row
	private static final Logger logger = Logger.getLogger(ClientHanlder.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message arg1)
			throws Exception {	
		
		if(arg1.getClass().equals(HeartBeat.class)){
        	logger.info("Hello message received from server, answering Hello");
        	ctx.writeAndFlush(new HeartBeat(new UUID(0, 0), new UUID(0, 0), null));
        }
		
		arg1.proceed();
		ping_lost = 0; // message received => host is alive
		
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent e = (IdleStateEvent) evt;
			if (e.state() == IdleState.WRITER_IDLE) {
				logger.info("No message from server, waiting ...");
				ping_lost++;
				if(ping_lost > 2){ // If x pings lost in a row.
					throw(new IOException("Connection timeout"));
				}
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
