package com.utc.api13.server.com;

import java.util.UUID;

import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.UserEntity;
import com.utc.api13.server.com.interfaces.InterfaceToClient;

// TODO @COM : Vraiment écrire cette classe :) Bisous, Data
public class ToClientImpl implements InterfaceToClient {

	@Override
	public void multicastMove(UserEntity users, UUID idPlayer, MoveEntity move) {
		// TODO Auto-generated method stub

	}

	@Override
	public void multicastFinished(UserEntity users) {
		// TODO Auto-generated method stub

	}

	@Override
	public void multicastNewPlayer(UUID idPlayer) {
		// TODO Auto-generated method stub

	}

}
