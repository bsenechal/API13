
package com.utc.api13.commun.messages;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class AllUserMessage extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = -3425696469122464793L;
    private static final Logger LOGGER = Logger.getLogger(AllUserMessage.class);
    List<PublicUserEntity> users = null;

    public AllUserMessage(UUID sender, UUID receiver) {
        super(sender, receiver);
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the users
     */
    public List<PublicUserEntity> getUsers() {
        return users;
    }

    /**
     * @param users
     *            the users to set
     */
    public void setUsers(List<PublicUserEntity> users) {
        this.users = users;
    }

    /**
     * Handles the message when received on the client. Calls the
     * 'displayUsersList' method from Data.
     */
    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        if (users != null) {
            comClientManager.getIClientDataToCom().displayUsersList(users);
        } else {
            LOGGER.error("users is null");
        }
    }

    /**
     * Handles the message when received on the server. Gets the list of all
     * currently connected players and is sent back to the client.
     */
    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        users = comServerManager.getIServerDataToCom().getConnectedUsers();
        this.receiver = this.sender;
        this.sender = new UUID(0, 0);
        comServerManager.sendMessage(ctx.channel(), this);
    }

}
