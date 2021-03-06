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
    private ServerHandler serverHanlder;

    public ServerInitializer(ComServerManager comServerManager) {
        this.comServerManager = comServerManager;
    }

    @Override
    protected void initChannel(SocketChannel arg0) throws Exception {
        ChannelPipeline pipeline = arg0.pipeline();
        serverHanlder = new ServerHandler(comServerManager);

        pipeline.addLast("idlestatehandler", new IdleStateHandler(0, 5, 0));
        pipeline.addLast("decoder", new ObjectDecoder(1024 * 1024 * 100, ClassResolvers.cacheDisabled(null)));
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
    public ServerHandler getServerHanlder() {
        return serverHanlder;
    }

    /**
     * @param serverHanlder
     *            the serverHanlder to set
     */
    public void setServerHanlder(ServerHandler serverHanlder) {
        this.serverHanlder = serverHanlder;
    }

}
