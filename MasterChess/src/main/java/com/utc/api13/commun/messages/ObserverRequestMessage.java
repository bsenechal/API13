package com.utc.api13.commun.messages;

import io.netty.channel.ChannelHandlerContext;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.server.com.ComServerManager;

public class ObserverRequestMessage extends Message {
    private static final Logger logger = Logger.getLogger(ObserverRequestMessage.class);

    private UUID game; // Game on which we request to observe

    /**
     * @param sender
     * @param receiver
     */
//    public ObserverRequestMessage(UUID sender, UUID receiver) {
//        super(sender, receiver);
//        // TODO Auto-generated constructor stub
//    }

    public ObserverRequestMessage(UUID sender, UUID receiver, UUID game_id) {
		super(sender,receiver);
		this.game = game_id;
	}

	@Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        // Informs the client of a new observer connection
        comClientManager.getIClientDataToCom().newObserver(sender);
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        // TODO Auto-generated method stub
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
