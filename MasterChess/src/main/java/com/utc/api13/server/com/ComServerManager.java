package com.utc.api13.server.com;

import org.apache.log4j.Logger;

import com.utc.api13.server.data.interfaces.IServerToComm;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ComServerManager {
	private final int port;
	private static final Logger logger = Logger.getLogger(ComServerManager.class);
	private IServerToComm iServerToComm;
	private ServeurToDataImpl serverToDataImpl;
	
	

	public void launchAppCom(){
		try{
			this.run();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	

	public ComServerManager(int port){
		this.port = port;
		this.serverToDataImpl = new ServeurToDataImpl(this);
	}
	
	public void run() throws InterruptedException{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try{
			
			ServerBootstrap boostrap = new ServerBootstrap()
				.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ServerInitializer(this));
			
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

	/**
	 * @return the iServerToComm
	 */
	public IServerToComm getIServerToComm() {
		return iServerToComm;
	}

	/**
	 * @param iServerToComm the iServerToComm to set
	 */
	public void setIServerToComm(IServerToComm iServerToComm) {
		iServerToComm = iServerToComm;
	}

	/**
	 * @return the iServerToDataImpl
	 */
	public ServeurToDataImpl getServerToDataImpl() {
		return serverToDataImpl;
	}

	/**
	 * @param iServerToDataImpl the iServerToDataImpl to set
	 */
	public void setIServerToDataImpl(ServeurToDataImpl serverToDataImpl) {
		serverToDataImpl = serverToDataImpl;
	}

}
