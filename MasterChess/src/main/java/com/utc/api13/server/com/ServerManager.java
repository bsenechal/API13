package com.utc.api13.server.com;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.utc.api13.client.com.interfaces.ServerCommToDataImpl;
import com.utc.api13.server.com.ServerInitializer;
import com.utc.api13.server.data.interfaces.ServerDataToCommImpl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerManager {
	
	private ServerCommToDataImpl commtodata;
	private ServerDataToCommImpl datatocomm;
	
	public static void main(String args[]) throws InterruptedException{
		try{
			new ServerManager(8000).run();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private final int port;
	private static final Logger logger = Logger.getLogger(ServerManager.class);

	public ServerManager(int port){
		this.port = port;
		
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

}
