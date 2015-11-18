package com.utc.api13.server.com.interfaces;

import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.UserEntity;
import com.utc.api13.commun.messages.ConnectMessage;
import com.utc.api13.commun.messages.GameFinishedMessage;
import com.utc.api13.commun.messages.MoveMessage;

public class IServeurToDataImpl implements IServeurToData {

	public void multicastMove(UserEntity users, UUID idPlayer, MoveEntity move) {
		new MoveMessage(users.getId(), null, move).proceedServer(null);
		
	}

	public void multicastFinished(GameEntity game) {
		new GameFinishedMessage(null, null, game).proceedServer(null);
	}

	public void multicastNewPlayer(PublicUserEntity pubPlayer) {
		new ConnectMessage(pubPlayer.getId(), null, pubPlayer).proceedServer(null);
		
	}


}
