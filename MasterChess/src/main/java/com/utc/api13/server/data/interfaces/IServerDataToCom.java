package com.utc.api13.server.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

public interface IServerDataToCom {
    public List<PublicUserEntity> getUsers();
    public PublicUserEntity getUserInfo(final UUID idUser);
    public List<GameEntity> getAllGames();
    public void notifyConnections (final PublicUserEntity Player);
    public boolean computerResult(final int idPlayer, final MoveEntity  move);
    public boolean isFinished (final String idGame);
    public void observerLeave(final UUID idUser);
    public List<PublicUserEntity> getListObservers();
    public void saveUserData(final PublicUserEntity User) throws TechnicalException, FunctionalException;
    public void newObserver(final int idGame, final UUID idUser);
    public void createReplay(final GameEntity game, final PublicUserEntity user);
    public List<PublicUserEntity> getConnectedUsers();
    public void surrender(final UUID idPlayer);
    public void disconnect(final UUID idUser);
}
