package com.utc.api13.server.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.AUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

public interface IServerDataToCom {
    public List<AUserEntity> getUsers();
    public AUserEntity getUserInfo(final UUID idUser);
    public List<GameEntity> getAllGames();
    public void notifyConnections (final AUserEntity Player);
    public boolean computerResult(final int idPlayer, final MoveEntity  move);
    public boolean isFinished (final String idGame);
    public void observerLeave(final UUID idUser);
    public List<AUserEntity> getListObservers();
    public void saveUserData(final AUserEntity User) throws TechnicalException, FunctionalException;
    public void newObserver(final int idGame, final UUID idUser);
    public void createReplay(final GameEntity game, final AUserEntity user);
    public List<AUserEntity> getConnectedUsers();
    public void surrender(final UUID idPlayer);
    public void disconnect(final UUID idUser);
}
