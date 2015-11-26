package com.utc.api13.server.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

public interface IServerDataToCom {
    public List<PublicUserEntity> getUsers();               //Called by com.getUsers()
    /**
     * Returns the user with the given UUID
     * @param idUser UUID of the user
     * @return the found user or null when not founded
     */
    public PublicUserEntity getUserInfo(final UUID idUser);     //Called by com.getUserInfo(final UUID idUser)
    public List<GameEntity> getAllGames();
    public void notifyConnections (final PublicUserEntity Player);      // Called by com.notifyConnections (PublicUserEntity Player)
    public boolean computerResult(final int idPlayer, final MoveEntity  move);
    public boolean isFinished (final String idGame);
    public void observerLeave(final UUID idUser);
    public List<PublicUserEntity> getListObservers();
    public void saveUserData(final PublicUserEntity User) throws TechnicalException, FunctionalException;
    public void newObserver(final int idGame, final UUID idUser);
    public void createReplay(final GameEntity game, final PublicUserEntity user);
    public List<PublicUserEntity> getConnectedUsers();
    /**
     * @author ulyss_000
     * list of the Users (observers and players) connected to a specified Game
     * @param idGame
     * @return the list of found users or null if the game doesn't exist or an empty List<PublicUserEntity> if there are no players
     */
    public List<PublicUserEntity> getUsersByGame(final UUID idGame);
    public void surrender(final UUID idPlayer);
    public void disconnect(final UUID idUser);               //Called by disconnect(UUID idUser)
}
