package com.utc.api13.client.com;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.utc.api13.commun.messages.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 
 * @author stephane, clemence
 * This class allow message transmission through Netty
 * It establishes the connection on the given port number.
 * When the connection is ready you can use the method sendMessage
 * Don't forget to close the connection.
 */
public class MessageManager {	
	
	private Channel channel;
	private EventLoopGroup group;
	
	private static final Logger logger = Logger.getLogger(MessageManager.class);
	
	public MessageManager(String host, int port) throws InterruptedException{
		
		group = new NioEventLoopGroup();
		
		Bootstrap boostrap = new Bootstrap()
		.group(group)
		.channel(NioSocketChannel.class)
		.handler(new ClientInitializer());	
		
		this.channel = boostrap.connect(host,port).sync().channel();
		
		logger.log(Level.DEBUG, "Message Manager is initialize for : " + host + ":" + port);
	}
	
	public void sendMessage(Message msg) throws ExceptionInInitializerError{
		
		if(channel != null){	
			channel.writeAndFlush(msg);
			logger.debug("A message has been sent to : " + msg.getReceiver());
		}else throw new ExceptionInInitializerError();
	}
	
	public void close(){
		group.shutdownGracefully();
	}

}
