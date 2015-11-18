package com.utc.api13.server.com.interfaces;

import java.util.UUID;

import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.UserEntity;

public interface InterfaceToClient {

	public void multicastMove(UserEntity users, UUID idPlayer, MoveEntity move);

	public void multicastFinished(UserEntity users);

	public void multicastNewPlayer(UUID idPlayer);

}
