package com.utc.api13.client.com;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * 
 * @author stephane , clemence
 * Initialize the Client's pipeline
 * Using ObjectDecoder and Encoder to manage pojo 
 *
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
ChannelPipeline pipeline = arg0.pipeline();
		
		pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
		pipeline.addLast("encoder", new ObjectEncoder());		
		pipeline.addLast("handler", new ClientHanlder());
		
	}

}
