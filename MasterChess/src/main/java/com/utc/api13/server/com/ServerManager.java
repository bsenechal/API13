package com.utc.api13.server.com;

import com.utc.api13.server.com.ServerInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerManager {
	
	public static void main(String args[]) throws InterruptedException{
		try{
			new ServerManager(8000).run();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private final int port;

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
			
			boostrap.bind(port).sync().channel().closeFuture().sync();
			
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
	}

}
