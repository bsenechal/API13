package com.utc.api13.server.com;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 
 * @author stephane, clemence
 * @see ClientHandler
 *
 */

public class ServerHanlder extends SimpleChannelInboundHandler<Object> {

private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		channels.add(incoming);
		
		//TODO Notify new user connection
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		channels.remove(incoming);
		
		//TODO Notify user disconnection
	}

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, Object arg1)
			throws Exception {
		
		Channel incoming = arg0.channel();
		
		((Message) arg1).proceed();
		
	}

}
