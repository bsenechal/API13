package com.utc.api13.commun.messages;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class ErrorMessage extends Message {
    private static final Logger logger = Logger.getLogger(ErrorMessage.class);
    private Exception exception;

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) throws Exception {
        throw this.exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    /**
     * @param sender
     * @param receiver
     * @param exception
     */
    public ErrorMessage(UUID sender, UUID receiver, Exception exception) {
        super(sender, receiver);
        this.exception = exception;
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        // TODO Auto-generated method stub

    }

}
