package com.utc.api13.server.com.interfaces;

import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.AUserEntity;

public interface IServeurToData {

	public void multicastMove(AUserEntity users, UUID idPlayer, MoveEntity move);

	public void multicastFinished(GameEntity game);

	public void multicastNewPlayer(PublicUserEntity pubPlayer);

}
