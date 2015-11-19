package com.utc.api13.server.com;

import org.apache.log4j.Logger;

import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.server.com.interfaces.IServeurToDataImpl;
import com.utc.api13.server.data.interfaces.IServerToComm;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ComServerManager {
	private IServerToComm iServerToComm;
	private IServeurToDataImpl iServerToDataImpl;
	
	public void launchAppCom(){
		try{
			this.run();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private final int port;
	private static final Logger logger = Logger.getLogger(ComServerManager.class);

	public ComServerManager(int port){
		this.port = port;
		this.iServerToDataImpl = new IServeurToDataImpl(this);
	}
	
	public void run() throws InterruptedException{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try{
			
			ServerBootstrap boostrap = new ServerBootstrap()
				.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ServerInitializer());
			
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
	public IServeurToDataImpl getIServerToDataImpl() {
		return iServerToDataImpl;
	}

	/**
	 * @param iServerToDataImpl the iServerToDataImpl to set
	 */
	public void setIServerToDataImpl(IServeurToDataImpl iServerToDataImpl) {
		iServerToDataImpl = iServerToDataImpl;
	}
	
	public void userConnection(PublicUserEntity pue, String pwd){ // handle user connection request
		// Quelle interface pour exécuter cette action ??
//		this.iServerToDataImpl
	}

}
