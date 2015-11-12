package com.utc.api13.server.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.IGameEntity;
import com.utc.api13.commun.IMoveEntity;
import com.utc.api13.commun.IUserEntity;

public interface IServerToComm {
    public List<IUserEntity> getUsers();
    public IUserEntity getUserInfo(UUID idUser);
    public List<IGameEntity> getAllGames();
    public void notifyConnections (IUserEntity Player);
    public boolean computerResult(int idPlayer, IMoveEntity  move);
    public boolean isFinished (String idGame);
    public void observerLeave(int idUser);
    public List<IUserEntity> getListObservers();
    public void saveUserData(IUserEntity User);
    public void newObserver(int idGame, UUID idUser);
    public void createReplay(IGameEntity game, IUserEntity user);
    public List<IUserEntity> getConnectedUsers();
    public void surrender(UUID idPlayer);
}
