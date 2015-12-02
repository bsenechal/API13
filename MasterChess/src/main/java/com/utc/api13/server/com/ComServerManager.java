package com.utc.api13.server.com;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.messages.Message;
import com.utc.api13.server.data.interfaces.IServerDataToCom;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ComServerManager {
    private final int port;
    ServerInitializer serverInitializer = null;
    private static final Logger logger = Logger.getLogger(ComServerManager.class);
    private IServerDataToCom iServerDataToCom;
    private ServeurComToDataImpl serverToDataImpl;
    private final ConcurrentHashMap<UUID, ChannelHandlerContext> channelHandlerContextMap = new ConcurrentHashMap<UUID, ChannelHandlerContext>();

    public void launchAppCom() {
        try {
            this.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ComServerManager(int port) {
        this.port = port;
        this.serverToDataImpl = new ServeurComToDataImpl(this);

    }

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            serverInitializer = new ServerInitializer(this);
            ServerBootstrap boostrap = new ServerBootstrap().group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class).childHandler(serverInitializer);

            if (logger.isDebugEnabled()) {
                logger.debug("Starting server on : " + port);
            }

            boostrap.bind(port).sync().channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            logger.debug("Server closed");
        }

    }

    public void sendMessage(Channel channel, Message msg) throws ExceptionInInitializerError {

        if (channel != null) {
            channel.writeAndFlush(msg);
            logger.debug("A " + msg.getClass().getSimpleName() + " has been sent to : " + msg.getReceiver());
        } else
            throw new ExceptionInInitializerError();
    }

    /**
     * @return the iServerToComm
     */
    public IServerDataToCom getIServerDataToCom() {
        return iServerDataToCom;
    }

    /**
     * @param iServerDataToCom
     *            the iServerToComm to set
     */
    public void setIServerDataToCom(IServerDataToCom iServerDataToCom) {
        this.iServerDataToCom = iServerDataToCom;
    }

    /**
     * @return the iServerToDataImpl
     */
    public ServeurComToDataImpl getServeurComToDataImpl() {
        return serverToDataImpl;
    }

    /**
     * @param iServerToDataImpl
     *            the iServerToDataImpl to set
     */
    public void setServeurComToDataImpl(ServeurComToDataImpl serverToDataImpl) {
        this.serverToDataImpl = serverToDataImpl;
    }

    /**
     * 
     */
    public void linkUserToChannelHandlerContext(UUID idUser, ChannelHandlerContext ctx) {
        synchronized (this) {
            if (channelHandlerContextMap.get(idUser) == null) {
                channelHandlerContextMap.put(idUser, ctx);
            } else {
                logger.error("User " + idUser.toString() + " is alreay connected, please disconnect before reconnect");
            }
        }
    }

    /**
     * 
     */
    public void unlinkUserToChannelHandlerContext(UUID idUser) {
        synchronized (this) {
            channelHandlerContextMap.remove(idUser);
        }
    }

    /**
     * 
     */
    public ChannelHandlerContext findChannelHandlerContextFromUserId(UUID idUser) {
        synchronized (this) {
            return channelHandlerContextMap.get(idUser);
        }
    }

    /**
     * 
     */
    public UUID findUserIdFromChannelHandlerContext(ChannelHandlerContext ctx) {
        synchronized (this) {
            for (UUID idUser : channelHandlerContextMap.keySet()) {
                if (channelHandlerContextMap.get(idUser).equals(ctx))
                    return idUser;
            }
            return null;
        }
    }

    /**
     * 
     */
    public void broadcastMessage(Message msg) {
        synchronized (this) {
            for (UUID idUser : channelHandlerContextMap.keySet()) {
                msg.setReceiver(idUser);
                sendMessage(channelHandlerContextMap.get(idUser).channel(), msg);
            }
        }
    }

    /**
     * 
     */
    public void multicastMessageByIds(List<UUID> userIds, Message msg) {
        synchronized (this) {
            for (UUID idUser : userIds) {
                msg.setReceiver(idUser);
                if (channelHandlerContextMap.get(idUser) != null) {
                    sendMessage(channelHandlerContextMap.get(idUser).channel(), msg);
                } else {
                    logger.warn("User " + idUser.toString() + " is disconnected. ");
                }
            }
        }
    }

    /**
     * 
     */
    public void multicastMessageByUsers(List<PublicUserEntity> users, Message msg) {
        synchronized (this) {
            for (PublicUserEntity user : users) {
                msg.setReceiver(user.getId());
                if (channelHandlerContextMap.get(user.getId()) != null) {
                    sendMessage(channelHandlerContextMap.get(user.getId()).channel(), msg);
                } else {
                    logger.warn("User " + user.getId().toString() + " is disconnected. ");
                }
            }
        }
    }
}
