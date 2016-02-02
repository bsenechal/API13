package com.utc.api13.server.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.GameStatusEnum;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

public interface IServerDataToCom {
    

    /**
     * Returns the user with the given UUID
     * 
     * @param idUser
     *            UUID of the user
     * @return the found user or null when not founded
     */
    public PublicUserEntity getUserInfo(final UUID idUser); 
    /**
     * 
     * @return Returns the list of current games
     */
    public List<GameEntity> getAllGames(); 

    /**
     * Warn the server that the player gets connected to add it to current users
     * @param player
     *      the new player
     */
    public void notifyConnections(final PublicUserEntity player); 

    /**
     * @author ulyss_000 
     * This method is used to verify if the move is possible
     * @param move
     * @return true if the move is possible, else -> false
     */
    public boolean computerResult(final MoveEntity move);

    /**
     * This method is used to determine if the game is check/checkmate, draw or
     * can simply continue -> it will delete the game entry if the result is
     * CHECKMATE -> consequently you have to save the gameID beforehand.
     * 
     * @author ulyss_000
     * @param idGame
     * @return GameStatusEnum -> 3 possible status : CHECK, CHECKMATE, DRAW or
     *         CONTINUE
     */
    public GameStatusEnum isFinished(final UUID idGame);

    /**
     * erase the observer from all current games
     * 
     * @param idUser
     *            user who is leaving
     */
    public void observerLeave(final UUID idUser); 

    /**
     * 
     * @return the list of observers
     */
    public List<PublicUserEntity> getListObservers(); 

    /**
     * Saves or updates the given user in the list of current users
     * 
     * @param user
     *            user to save or update in
     * @throws TechnicalException
     *             technical exception
     * @throws FunctionalException
     *             functional exception
     */
    public boolean saveUserData(final PublicUserEntity user) throws TechnicalException, FunctionalException;

    /**
     * 
     * Add a new observer to a game and aware others of this new connection
     * 
     * @param idGame
     *            uid game
     * @param idUser
     *            uid user à rajouter
     */
    public void newObserver(final UUID idGame, final UUID idUser);

    /**
     * Create a replay of a saved game
     * @param game
     * @param user
     */
    public void createReplay(final GameEntity game, final PublicUserEntity user);

    /**
     * 
     * @return the list of connected users
     */
    public List<PublicUserEntity> getConnectedUsers(); 

    /**
     * @author ulyss_000 
     * list of the Users (observers and players) connected to a specified Game
     * @param idGame
     * @return the list of found users or null if the game doesn't exist or an
     *         empty List<PublicUserEntity> if there are no players
     */
    public List<PublicUserEntity> getUsersByGame(final UUID idGame); 

    /**
     * Surrend the game in which idplayer is playing
     * @param idPlayer
     */
    public void surrender(final UUID idPlayer);

    /**
     * Delete the user in the liste of connected users
     * @param idUser
     *          the user who wants to be disconnected
     */
    public void disconnect(final UUID idUser); 
    /**
     * create a new game
     * @param j1
     *          first player
     * @param j2
     *          second player
     * @param observable
     *          true if the game is observable
     * @param chattable
     *          true if the game if chattable
     * @param timer
     *          true fi there is a timer between each moves
     * @param timerInt
     *          second of the timer
     * @return
     */
    public GameEntity createGame(final UUID j1, final UUID j2, final boolean observable, final boolean chattable,
            final boolean timer, final Integer timerInt);

    /**
     * Get a game by its ID
     * @param idGame
     * @return the game
     */
    public GameEntity getGameById(final UUID idGame); 

    /**
     * removes the game with the given id on the server
     * 
     * @param idGame
     *            id of game
     */
    public void endGame(UUID idGame);

    /**
     * removes an observer from his/her local game
     * 
     * @param idUser
     *            uid of user to remove
     * @param idGame
     *            uid of game
     */
    public void removeUserFromChat(UUID idUser, UUID idGame);

    /**
     * return true if a user is Playing
     * 
     * @param idUser
     *            targeted user
     */
    public boolean isPlaying(UUID idUser);
}
