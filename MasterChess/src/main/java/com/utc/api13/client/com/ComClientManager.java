package com.utc.api13.client.com;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.utc.api13.client.data.interfaces.IClientDataToCom;
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
	
	private ClientComToDataImpl clientComToDataImpl;
	private IClientDataToCom iClientDataToCom;
	
	private static final Logger logger = Logger.getLogger(ComClientManager.class);


	public ComClientManager() {
		this.clientComToDataImpl = new ClientComToDataImpl(this);
	}

	public void launchAppCom(String host, int port) throws InterruptedException {
		
		group = new NioEventLoopGroup();
		
		Bootstrap boostrap = new Bootstrap()
		.group(group)
		.channel(NioSocketChannel.class)
		.handler(new ClientInitializer(this));	
		
		try {
			this.channel = boostrap.connect(host,port).sync().channel();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error("Lost connection, check your network connection");
			e.printStackTrace();
			throw(e);
		} catch (Throwable e){
			logger.error("Can't connect to server, please check your connection and server statuts");
			e.printStackTrace();
			throw(e);
		}
		
		logger.log(Level.DEBUG, "Message Manager is initialized for : " + host + ":" + port);
	}
	
	public void sendMessage(Message msg) throws ExceptionInInitializerError{
		
		if(channel != null){	
			channel.writeAndFlush(msg);
			logger.debug("A " + msg.getClass().getSimpleName() + " has been sent to server :" );
		}else throw new ExceptionInInitializerError();
	}
	
	public void close(){
		group.shutdownGracefully();
	}

	/**
	 * @return the clientToDataImpl
	 */
	public ClientComToDataImpl getClientComToDataImpl() {
		return clientComToDataImpl;
	}

	/**
	 * @param clientComToDataImpl the clientToDataImpl to set
	 */
	public void setClientComToDataImpl(ClientComToDataImpl clientComToDataImpl) {
		this.clientComToDataImpl = clientComToDataImpl;
	}

	/**
	 * @return the iClientToComm
	 */
	public IClientDataToCom getIClientDataToCom() {
		return iClientDataToCom;
	}

	/**
	 * @param iClientToComm the iClientToComm to set
	 */
	public void setIClientDataToCom(IClientDataToCom iClientToComm) {
		iClientDataToCom = iClientToComm;
	}
	

}
