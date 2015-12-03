package com.utc.api13.client.com;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 
 * @author stephane , clemence Initialize the Client's pipeline Using
 *         ObjectDecoder and Encoder to manage pojo Using IdleStateHandler to
 *         keep connection alive
 *
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    ComClientManager comClientManager = null;

    public ClientInitializer(ComClientManager comClientManager) {
        this.comClientManager = comClientManager;
    }

    @Override
    protected void initChannel(SocketChannel arg0) throws Exception {
        ChannelPipeline pipeline = arg0.pipeline();

        pipeline.addLast("idlestatehandler", new IdleStateHandler(0, 5, 0));
        pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
        pipeline.addLast("encoder", new ObjectEncoder());
        pipeline.addLast("handler", new ClientHanlder(comClientManager));

    }

}
