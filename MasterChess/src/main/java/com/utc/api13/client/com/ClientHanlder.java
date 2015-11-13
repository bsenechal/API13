package com.utc.api13.client.com;

import com.utc.api13.server.com.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 
 * @author stephane, clemence
 * Manage User process on a message reception
 *
 */

public class ClientHanlder extends SimpleChannelInboundHandler<Message>{

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, Message arg1)
			throws Exception {		
		arg1.proceed();
		
	}

}
