package com.utc.api13.server.com;

import java.io.IOException;
import java.util.Hashtable;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.utc.api13.commun.messages.DisconnectMessage;
import com.utc.api13.commun.messages.HeartBeat;
import com.utc.api13.commun.messages.Message;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 
 * @author stephane, clemence, thomas
 * @see ClientHandler
 *
 */

public class ServerHanlder extends SimpleChannelInboundHandler<Message> {

	private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	private static final Logger logger = Logger.getLogger(ServerHanlder.class);

	// ping_lost_map stores ping lost count for each channel -> unique attribute
	// ping_lost may cause unexpected channel closure
	private static final Hashtable<Channel, AtomicInteger> ping_lost_map = new Hashtable<Channel, AtomicInteger>();
	private ComServerManager comServerManager;

	public ServerHanlder(ComServerManager comServerManager) {
		this.comServerManager = comServerManager;
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		channels.add(incoming);

		// initializing the ping map with incoming channel key and 0 value
		ping_lost_map.put(incoming, new AtomicInteger());

		// TODO Notify new user connection
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		channels.remove(incoming);

		ping_lost_map.remove(incoming);
		// TODO Notify user disconnection
	}

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, Message arg1) throws Exception {
		Channel incoming = arg0.channel();

		arg1.proceedServer(arg0, comServerManager);

		ping_lost_map.get(incoming).set(0); // message received => host is alive
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) { // IdleStateEvent fired when no
												// inbound messages
			IdleStateEvent e = (IdleStateEvent) evt;
			if (e.state() == IdleState.WRITER_IDLE) {
				// logger.info("Channel IDLE : sending Hello");
				ctx.writeAndFlush(new HeartBeat(new UUID(0, 0), new UUID(0, 0), null));

				// incrementing concerned channel's counter
				ping_lost_map.get(ctx.channel()).incrementAndGet();

				// If x pings lost in a row, assuming that host is down
				if (ping_lost_map.get(ctx.channel()).get() > 2) {
					throw (new IOException("Connection timeout"));
				}
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// cause.printStackTrace();
		UUID userId = comServerManager.findUserIdFromChannelHandlerContext(ctx);
		logger.warn("User : " + userId + " quit application, broadcast disconnection");
		comServerManager.unlinkUserToChannelHandlerContext(userId);
		DisconnectMessage msg = new DisconnectMessage(userId, new UUID(0, 0), userId);
		comServerManager.broadcastMessage(msg);
		ctx.close();
	}

	public void reply(Channel incoming, Message msg) throws Exception {
		incoming.writeAndFlush(msg);
	}

	public void replyAll(ChannelHandlerContext arg0, Message message) throws Exception {
		Channel incoming = null;
		if (arg0 != null) {
			incoming = arg0.channel();
		}
		for (Channel channel : channels) {
			if (channel != incoming) {
				channel.writeAndFlush(message);

			}
		}
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
	 * @return the channels
	 */
	public static ChannelGroup getChannels() {
		return channels;
	}

	/**
	 * @return the pingLostMap
	 */
	public static Hashtable<Channel, AtomicInteger> getPingLostMap() {
		return ping_lost_map;
	}

}
