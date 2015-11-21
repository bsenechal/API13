package com.utc.api13.server.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.UserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

public interface IServerToComm {
    public List<UserEntity> getUsers();
    public UserEntity getUserInfo(final UUID idUser);
    public List<GameEntity> getAllGames();
    public void notifyConnections (final UserEntity Player);
    public boolean computerResult(final int idPlayer, final MoveEntity  move);
    public boolean isFinished (final String idGame);
    public void observerLeave(final UUID idUser);
    public List<UserEntity> getListObservers();
    public void saveUserData(final UserEntity User) throws TechnicalException, FunctionalException;
    public void newObserver(final int idGame, final UUID idUser);
    public void createReplay(final GameEntity game, final UserEntity user);
    public List<UserEntity> getConnectedUsers();
    public void surrender(final UUID idPlayer);
}
