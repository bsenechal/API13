package com.utc.api13.server.com.interfaces;

import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.UserEntity;
import com.utc.api13.server.com.ComServerManager;

public interface IServeurToData {

	public void multicastMove(UserEntity users, UUID idPlayer, MoveEntity move);

	public void multicastFinished(GameEntity game);

	public void multicastNewPlayer(PublicUserEntity pubPlayer);

	public ComServerManager getComServerManagerInstance();

	public void setComServerManagerInstance(ComServerManager comServerManagerInstance);

}
