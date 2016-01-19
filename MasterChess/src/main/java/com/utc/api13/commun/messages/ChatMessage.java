package com.utc.api13.commun.messages;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class ChatMessage extends Message {

    /**
    * 
    */
    private static final long serialVersionUID = -55526017055065873L;
    private static final Logger logger = Logger.getLogger(ChatMessage.class);
    UUID partie; // game in which the message will be sent
    String message; // message to be sent

    /**
     * @param sender
     * @param receiver
     * @param message
     */
    public ChatMessage(UUID sender, UUID receiver, UUID game, String message) {
        super(sender, receiver);
        this.message = message;
        this.partie = game;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        // Display the message received
        comClientManager.getIClientDataToCom().displayMessage(message);
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        // Get users attending the game !
        // filter the user having allowedTchat to true;

        // List<PublicUserEntity> list =
        // comServerManager.getIServerDataToCom().getUsersByGame(partie);
        List<PublicUserEntity> list = comServerManager.getIServerDataToCom().getUsersByGame(partie).stream()
                .filter(user -> user.getAllowedToChat() == true).collect(Collectors.toList());

        // Send the message to all allowed listed users
        comServerManager.multicastMessageByUsers(list, this);
    }

}
