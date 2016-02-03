package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class UserUpdateMessage extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(UserUpdateMessage.class);
    private static final String UPDATE_SUCCESSFUL = "User successfully updated on the server";
    private static final String UPDATE_FAILED = "User updated failed on the server";

    private String resultMessage;
    private PublicUserEntity usr;

    /**
     * Message sent to server & clients when a user updates its profile
     * 
     * @param sender
     * @param receiver
     */
    public UserUpdateMessage(UUID sender, UUID receiver) {
        super(sender, receiver);
    }

    public UserUpdateMessage(UUID sender, UUID receiver, PublicUserEntity u) {
        super(sender, receiver);
        this.usr = u;
    }

    /**
     * Handles the message when received on the client. Gives Data the updted
     * profile.
     */
    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        // When received on the client, calls data to display whether the update
        comClientManager.getIClientDataToCom().updateDistantProfile(usr);
    }

    /**
     * Handles the message when received on the server. Server registers the
     * user profile update.
     */
    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        // On the server, updates the connected users list
        boolean result = false;
        try {
            result = comServerManager.getIServerDataToCom().saveUserData(usr);
            comServerManager.broadcastMessageExceptUser(this, usr.getId());
        } catch (TechnicalException e) {
            LOGGER.error("[UserUpdateMessage][proceedServer] " + e.getMessage(), e);
        } catch (FunctionalException e) {
            LOGGER.error("[UserUpdateMessage][proceedServer] " + e.getMessage(), e);
        } finally {
            if (result) {
                this.resultMessage = UPDATE_SUCCESSFUL;
            } else {
                LOGGER.error(resultMessage);
                this.resultMessage = UPDATE_FAILED;
            }
        }

    }

    public PublicUserEntity getUsr() {
        return usr;
    }

    public void setUsr(PublicUserEntity usr) {
        this.usr = usr;
    }

}
