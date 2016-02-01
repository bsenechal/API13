package com.utc.api13.commun.messages;

import java.util.UUID;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class ObserverRequestMessage extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = -4586898422959823860L;

    private UUID game; // Game on which we request to observe

    public ObserverRequestMessage(UUID sender, UUID receiver, UUID gameId) {
        super(sender, receiver);
        this.game = gameId;
    }

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        // Informs the client of a new observer connection
        comClientManager.getIClientDataToCom().newObserver(sender);
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        this.sender = comServerManager.findUserIdFromChannelHandlerContext(ctx);
        comServerManager.getIServerDataToCom().newObserver(this.game, this.sender);
        // récupérer le gameEntity
        GameEntity gameE = comServerManager.getIServerDataToCom().getGameById(game);
        // Envoyer la réponse au demandeur !
        comServerManager.sendMessage(ctx.channel(), new ObserverAnswerMessage(new UUID(0, 0), new UUID(0, 0), gameE));

        // A tous les participants à la partie : new Observer
        comServerManager.multicastMessageByUsers(gameE.getAllParticipants(), this);

    }

}
