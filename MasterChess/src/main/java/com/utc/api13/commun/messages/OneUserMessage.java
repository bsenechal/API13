package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class OneUserMessage extends Message {
	
	private PublicUserEntity usr;
	boolean request;

    /**
     * 
     */
    private static final long serialVersionUID = 3911005325138154087L;
    private static final Logger logger = Logger.getLogger(OneUserMessage.class);

    /**
     * @param sender
     * @param receiver
     */
    public OneUserMessage(UUID sender, UUID receiver) {
        super(sender, receiver);
        // TODO Auto-generated constructor stub
    }

    public OneUserMessage(UUID sender, UUID receiver, PublicUserEntity usr, boolean request) {
		super(sender, receiver);
		this.usr = usr;
		this.request = request;
	}



	@Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        if(request){ // si c'est requête, on renvoie le PublicUserEntity local
        	// TODO : getUserInfo n'existe pas !!!!
//        	this.usr = comClientManager.getIClientDataToCom().getUserInfo(receiver);
        }
        else{ // si c'est réponse, on affiche le PublicUserEntity reçu
        	comClientManager.getIClientDataToCom().displayProfile(usr);
        }

    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        if(request){ // si c'est requête, on transfère au destinataire
        	ChannelHandlerContext ch = comServerManager.findChannelHandlerContextFromUserId(receiver);
        	ch.writeAndFlush(this);
        }
        else{ // si c'est réponse, on tranfère au demandeur
        	ChannelHandlerContext che = comServerManager.findChannelHandlerContextFromUserId(sender);
        	che.writeAndFlush(this);
        }

    }

	/**
	 * @return the usr
	 */
	public PublicUserEntity getUsr() {
		return usr;
	}

	/**
	 * @param usr the usr to set
	 */
	public void setUsr(PublicUserEntity usr) {
		this.usr = usr;
	}

}
