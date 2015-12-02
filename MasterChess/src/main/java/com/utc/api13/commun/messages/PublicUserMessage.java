package com.utc.api13.commun.messages;

import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class PublicUserMessage extends Message {

    UUID target; // UUID of the user of which we require info.
    PublicUserEntity usr; // Object concerning the user

    public PublicUserMessage(UUID sender, UUID receiver, UUID target) {
        super(sender, receiver);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) throws Exception {
        // When receiving answer message on the client.
        // calling method from data with the info received.
        comClientManager.getIClientDataToCom().displayProfile(this.usr);
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        // Get the selected user information by calling method from data :
        PublicUserEntity usr = comServerManager.getIServerDataToCom().getUserInfo(this.target);
        this.setUsr(usr);
        // Send back the message to the sender !!!
        comServerManager.sendMessage(ctx.channel(), this);
    }

    public UUID getTarget() {
        return target;
    }

    public void setTarget(UUID target) {
        this.target = target;
    }

    public PublicUserEntity getUsr() {
        return usr;
    }

    public void setUsr(PublicUserEntity usr) {
        this.usr = usr;
    }

}
