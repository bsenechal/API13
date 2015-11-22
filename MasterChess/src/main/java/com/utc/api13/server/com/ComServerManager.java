package com.utc.api13.server.com;

import org.apache.log4j.Logger;

import com.utc.api13.commun.messages.Message;
import com.utc.api13.server.data.interfaces.IServerDataToCom;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ComServerManager {
	private final int port;
	ServerInitializer serverInitializer = null;
	private static final Logger logger = Logger.getLogger(ComServerManager.class);
	private IServerDataToCom iServerDataToCom;
	private ServeurComToDataImpl serverToDataImpl;
	
	

	public void launchAppCom(){
		try{
			this.run();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	

	public ComServerManager(int port){
		this.port = port;
		this.serverToDataImpl = new ServeurComToDataImpl(this);

	}
	
	public void run() throws InterruptedException{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try{
			serverInitializer = new ServerInitializer(this);
			ServerBootstrap boostrap = new ServerBootstrap()
				.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(serverInitializer);
			
			if(logger.isDebugEnabled()){
			    logger.debug("Starting server on : " + port);
			}
			
			boostrap.bind(port).sync().channel().closeFuture().sync();
			
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
			logger.debug("Server closed");
		}
		
	}
	
	public void sendMessage(Channel channel, Message msg) throws ExceptionInInitializerError{
		
		if(channel != null){	
			channel.writeAndFlush(msg);
			logger.debug("A message has been sent to : " + msg.getReceiver());
		}else throw new ExceptionInInitializerError();
	}

	/**
	 * @return the iServerToComm
	 */
	public IServerDataToCom getIServerDataToCom() {
		return iServerDataToCom;
	}

	/**
	 * @param iServerDataToCom the iServerToComm to set
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
	 * @param iServerToDataImpl the iServerToDataImpl to set
	 */
	public void setServeurComToDataImpl(ServeurComToDataImpl serverToDataImpl) {
		this.serverToDataImpl = serverToDataImpl;
	}
	

}
