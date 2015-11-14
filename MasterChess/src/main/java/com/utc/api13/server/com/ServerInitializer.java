package com.utc.api13.server.com;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 
 * @author stephane, clemence
 * @see ClientInitializer
 *
 */

public class ServerInitializer extends ChannelInitializer<SocketChannel>{
	
	@Override
	protected void initChannel(SocketChannel  arg0) throws Exception {
		ChannelPipeline pipeline = arg0.pipeline();
		 
		pipeline.addLast("idlestatehandler", new IdleStateHandler(0, 5, 0));
		pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
		pipeline.addLast("encoder", new ObjectEncoder());
		pipeline.addLast("handler", new ServerHanlder());

	}

}
