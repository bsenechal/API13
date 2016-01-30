package com.utc.api13.server.com;

import com.utc.api13.client.com.ClientInitializer;

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

public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    private ComServerManager comServerManager;
    private ServerHanlder serverHanlder;

    public ServerInitializer(ComServerManager comServerManager) {
        this.comServerManager = comServerManager;
    }

    @Override
    protected void initChannel(SocketChannel arg0) throws Exception {
        ChannelPipeline pipeline = arg0.pipeline();
        serverHanlder = new ServerHanlder(comServerManager);
        pipeline.addLast("idlestatehandler", new IdleStateHandler(0, 5, 0));
        pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
        pipeline.addLast("encoder", new ObjectEncoder());
        pipeline.addLast("handler", serverHanlder);

    }

    /**
     * @return the comServerManager
     */
    public ComServerManager getComServerManager() {
        return comServerManager;
    }

    /**
     * @param comServerManager
     *            the comServerManager to set
     */
    public void setComServerManager(ComServerManager comServerManager) {
        this.comServerManager = comServerManager;
    }

    /**
     * @return the serverHanlder
     */
    public ServerHanlder getServerHanlder() {
        return serverHanlder;
    }

    /**
     * @param serverHanlder
     *            the serverHanlder to set
     */
    public void setServerHanlder(ServerHanlder serverHanlder) {
        this.serverHanlder = serverHanlder;
    }

}
