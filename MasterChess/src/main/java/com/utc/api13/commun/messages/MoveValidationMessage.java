package com.utc.api13.commun.messages;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.utc.api13.client.com.ComClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.GameStatusEnum;
import com.utc.api13.server.com.ComServerManager;

import io.netty.channel.ChannelHandlerContext;

public class MoveValidationMessage extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = 6100960055537315611L;
    private static final Logger logger = Logger.getLogger(MoveValidationMessage.class);
    MoveEntity move;

    /**
     * @param sender
     * @param receiver
     * @param move
     */
    public MoveValidationMessage(UUID sender, UUID receiver, MoveEntity move) {
        super(sender, receiver);
        this.move = move;
    }

    public MoveEntity getMove() {
        return move;
    }

    public void setMove(MoveEntity move) {
        this.move = move;
    }

    @Override
    public void proceed(ChannelHandlerContext ctx, ComClientManager comClientManager) {
        // Never goes on client side.
    }

    @Override
    public void proceedServer(ChannelHandlerContext ctx, ComServerManager comServerManager) {
        // STEP 1 : Validate the move
        boolean ok = comServerManager.getIServerDataToCom().computerResult(sender, move); // Is
                                                                                          // the
                                                                                          // move
                                                                                          // valid
                                                                                          // ?
        if (ok) { // If yes,
            // STEP 2 : inform the other players
            GameEntity game = comServerManager.getIServerDataToCom().getGameById(move.getGameID()); // get
                                                                                                    // the
                                                                                                    // game
                                                                                                    // entity
                                                                                                    // with
                                                                                                    // the
                                                                                                    // gameID
            List<PublicUserEntity> list = game.getAllParticipants(); // get the
                                                                     // list of
                                                                     // all
                                                                     // players
            MoveMessage movemsg = new MoveMessage(new UUID(0, 0), null, move); // build
                                                                               // the
                                                                               // message
            comServerManager.multicastMessageByUsers(list, movemsg); // send the
                                                                     // move to
                                                                     // all game
                                                                     // participants
                                                                     // (players
                                                                     // &
                                                                     // observers!)

            // STEP 3 : inform the other players of the next step ( game
            // finished, next player)
            GameStatusEnum status = comServerManager.getIServerDataToCom().isFinished(move.getGameID());
            game = comServerManager.getIServerDataToCom().getGameById(move.getGameID());
            UUID nexttoplay = game.getCurrentPlayer().getId();
            // UUID nexttoplay =
            // comServerManager.getIServerDataToCom().getNextPlayer(move.getGameID());
            // // A vérifier, mais ne pas utiliser
            NextTurnMessage ntm = new NextTurnMessage(new UUID(0, 0), null, status, nexttoplay);
            comServerManager.multicastMessageByUsers(list, ntm);
        } else {
            // TODO : Error !
        }
    }

}
