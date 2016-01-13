package com.utc.api13.commun.messages;

import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.server.com.ComServerManager;

public class ObserverLeaveMessage extends Message {

    private UUID user; // ID of the user leaving the game


	public ObserverLeaveMessage(UUID sender, UUID receiver) {
        super(sender, receiver);
        this.user = sender; // Utile ?
    }
	
	@Override
	public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) throws Exception {
		//Pas de traitement possible côté client actuellement
		//Message de départ d'un observateur ?
	}


	@Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        // Received on the server : unsubscribe from the game and send to
        // participants
        comServerManager.getIServerDataToCom().observerLeave(user);

        // Utile de transmettre d'info aux participants ?
        // Besion du gameId pour ça !
    }

}
