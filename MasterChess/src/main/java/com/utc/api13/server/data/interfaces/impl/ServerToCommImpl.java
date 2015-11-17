package com.utc.api13.server.data.interfaces.impl;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.UserEntity;
import com.utc.api13.commun.services.UserService;
import com.utc.api13.server.data.interfaces.IServerToComm;

public class ServerToCommImpl implements IServerToComm {

	private UserService userService = new UserService();
	
	@Override
	public List<UserEntity> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity getUserInfo(UUID idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GameEntity> getAllGames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyConnections(UserEntity Player) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean computerResult(int idPlayer, MoveEntity move) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFinished(String idGame) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void observerLeave(int idUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserEntity> getListObservers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUserData(PublicUserEntity user) {
		userService.getConnectedUsers().add(user);

	}

	@Override
	public void newObserver(int idGame, UUID idUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createReplay(GameEntity game, UserEntity user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserEntity> getConnectedUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void surrender(UUID idPlayer) {
		// TODO Auto-generated method stub

	}

}
