package com.utc.api13.server.com;


import java.io.IOException;
import java.util.UUID;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 
 * @author stephane, clemence, thomas
 * @see ClientHandler
 *
 */

public class ServerHanlder extends SimpleChannelInboundHandler<Object> {

	private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	private int ping_lost;
	
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
		
		if(arg1.getClass().equals(HeartBeat.class)){
//			System.out.println("Hello message received, peer is alive !");
		}
		
		((Message) arg1).proceed();

		this.ping_lost = 0;	// message received => host is alive
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) { // IdleStateEvent fired when no inbound messages
			IdleStateEvent e = (IdleStateEvent) evt;
			if (e.state() == IdleState.WRITER_IDLE) {
//				System.out.println("Channel IDLE : sending Hello");
				ctx.writeAndFlush(new HeartBeat(new UUID(0, 0), new UUID(0, 0), null));	
				ping_lost++;
				if(ping_lost > 2){ // If x pings lost in a row, considering that host is down
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
	
	public void reply(Channel incoming, Message msg) throws Exception{
		incoming.writeAndFlush(msg);			
	}
	
	public void replyAll(ChannelHandlerContext arg0, Message message) throws Exception {
		Channel incoming = arg0.channel();
		for(Channel channel : channels){
			if(channel != incoming){
				channel.writeAndFlush(message);
				
			}
		}
	}
}
