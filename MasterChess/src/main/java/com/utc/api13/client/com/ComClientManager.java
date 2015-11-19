package com.utc.api13.client.com;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.utc.api13.client.com.interfaces.IClientToDataImpl;
import com.utc.api13.client.data.interfaces.IClientToComm;
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
public class ComClientManager {	
	
	private Channel channel;
	private EventLoopGroup group;
	
	// TODO : Modifier le nom de la classe Impl
	private IClientToDataImpl clientToDataImpl;
	private IClientToComm IClientToComm;
	
	private static final Logger logger = Logger.getLogger(ComClientManager.class);
	public ComClientManager(){
		this.clientToDataImpl = new IClientToDataImpl(this);
		
	}
	public void launchAppCom(String host, int port) {
		
		group = new NioEventLoopGroup();
		
		Bootstrap boostrap = new Bootstrap()
		.group(group)
		.channel(NioSocketChannel.class)
		.handler(new ClientInitializer());	
		
		try {
			this.channel = boostrap.connect(host,port).sync().channel();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.log(Level.DEBUG, "Message Manager is initialized for : " + host + ":" + port);
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

	/**
	 * @return the clientToDataImpl
	 */
	public IClientToDataImpl getClientToDataImpl() {
		return clientToDataImpl;
	}

	/**
	 * @param clientToDataImpl the clientToDataImpl to set
	 */
	public void setClientToDataImpl(IClientToDataImpl clientToDataImpl) {
		this.clientToDataImpl = clientToDataImpl;
	}

	/**
	 * @return the iClientToComm
	 */
	public IClientToComm getIClientToComm() {
		return IClientToComm;
	}

	/**
	 * @param iClientToComm the iClientToComm to set
	 */
	public void setIClientToComm(IClientToComm iClientToComm) {
		IClientToComm = iClientToComm;
	}
	

}
