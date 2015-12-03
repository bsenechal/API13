package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;
import com.utc.api13.commun.messages.Message;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class UserUpdateMessage extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(UserUpdateMessage.class);
    private static final String UPDATE_SUCCESSFUL = "User successfully updated on the server";
    private static final String UPDATE_FAILED = "User updated failed on the server";

    private String result_message;
    private PublicUserEntity usr;

    /**
     * Message sent to server & clients when a user updates its profile
     * 
     * @param sender
     * @param receiver
     */
    public UserUpdateMessage(UUID sender, UUID receiver) {
        super(sender, receiver);
        // TODO Auto-generated constructor stub
    }

    public UserUpdateMessage(UUID sender, UUID receiver, PublicUserEntity u) {
        super(sender, receiver);
        this.usr = u;
    }

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        // When received on the client, calls data to display whether the update
        comClientManager.getIClientDataToCom().notify(result_message);
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        // On the server, updates the connected users list
        boolean result = false;
        try {
            result = comServerManager.getIServerDataToCom().saveUserData(usr);
        } catch (TechnicalException e) {
            e.printStackTrace();
        } catch (FunctionalException e) {
            e.printStackTrace();
        }
        if (result) {
            this.result_message = UPDATE_SUCCESSFUL;
        } else {
            this.result_message = UPDATE_FAILED;
        }
        comServerManager.sendMessage(ctx.channel(), this);
    }

    public PublicUserEntity getUsr() {
        return usr;
    }

    public void setUsr(PublicUserEntity usr) {
        this.usr = usr;
    }

}
