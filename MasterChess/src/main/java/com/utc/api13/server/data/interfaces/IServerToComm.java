package com.utc.api13.server.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.UserEntity;

public interface IServerToComm {
    public List<UserEntity> getUsers();
    public UserEntity getUserInfo(UUID idUser);
    public List<GameEntity> getAllGames();
    public void notifyConnections (UserEntity Player);
    public boolean computerResult(int idPlayer, MoveEntity  move);
    public boolean isFinished (String idGame);
    public void observerLeave(int idUser);
    public List<UserEntity> getListObservers();
    public void saveUserData(PublicUserEntity User);
    public void newObserver(int idGame, UUID idUser);
    public void createReplay(GameEntity game, UserEntity user);
    public List<UserEntity> getConnectedUsers();
    public void surrender(UUID idPlayer);
}
