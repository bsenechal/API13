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
	public List<PublicUserEntity> getUsers(); // Appelée par com.getUsers().
												// Renvoie la liste des
												// PublicUserEntity connectés

	/**
	 * Returns the user with the given UUID
	 * 
	 * @param idUser
	 *            UUID of the user
	 * @return the found user or null when not founded
	 */
	public PublicUserEntity getUserInfo(final UUID idUser); // Appelée par
															// com.getUserInfo(final
															// UUID idUser).
															// Renvoie les
															// informations sur
															// un PublicUser

	/**
	 * 
	 * @return Returns the list of current games
	 */
	public List<GameEntity> getAllGames(); // Appelée par com.getUsers().
											// Renvoie la liste des GameEntity
											// en
											// cours

	public void notifyConnections(final PublicUserEntity Player); // Appelée par
																	// by
																	// com.notifyConnections
																	// (PublicUserEntity
																	// Player).
																	// Préviens
																	// le
																	// serveur
																	// lorsqu'un
																	// User se
																	// connecte,
																	// de façon
																	// à
																	// mettre à
																	// jour la
																	// liste de
																	// PublicUserEntity
																	// sur le
																	// serveur

	/**
	 * @author ulyss_000 This method is used to verify if the move is possible
	 * @param idPlayer
	 * @param move
	 * @return true if the move is possible, else -> false
	 */
	// TODO : Ulysse : do we need idPlayer since move as the players infos ?
	public boolean computerResult(final int idPlayer, final MoveEntity move);

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
	public void observerLeave(final UUID idUser); // Signaler qu'un observeur
													// quitte la partie.

	public List<PublicUserEntity> getListObservers(); // Récupérer la liste des
														// observeurs

	/**
	 * Saves or updates the given user in the list of current users
	 * 
	 * @param User
	 *            user to save or update in
	 * @throws TechnicalException
	 *             technical exception
	 * @throws FunctionalException
	 *             functional exception
	 */
	public boolean saveUserData(final PublicUserEntity User) throws TechnicalException, FunctionalException;

	public void newObserver(final UUID idGame, final UUID idUser); // Un nouvel
																	// observeur
																	// se
																	// connecte
																	// à la
																	// partie.
																	// Mettre à
																	// jour la
																	// List<PublicUserEntity>
																	// d'observeurs

	public void createReplay(final GameEntity game, final PublicUserEntity user);

	public List<PublicUserEntity> getConnectedUsers(); // Différence avec
														// getUsers() ?

	/**
	 * @author ulyss_000 list of the Users (observers and players) connected to
	 *         a specified Game
	 * @param idGame
	 * @return the list of found users or null if the game doesn't exist or an
	 *         empty List<PublicUserEntity> if there are no players
	 */
	public List<PublicUserEntity> getUsersByGame(final UUID idGame); // Liste
																		// des
																		// utilisateurs
																		// connectés
																		// à une
																		// partie
																		// spécifique

	public void surrender(final UUID idPlayer);

	public void disconnect(final UUID idUser); // Appelée par
												// com.disconnect(UUID idUser)
												// pour supprimer un User de la
												// liste des Users connectés

	// TODO
	// Méthode pour créer une nouvelle partie :
	public GameEntity createGame(final UUID j1, final UUID j2, final boolean observable, final boolean chattable);

	public GameEntity getGameById(final UUID IdGame); // Renvoie une GameEntity
														// par l'UID de la Game

	/**
	 * removes the game with the given id on the server
	 * 
	 * @param idGame
	 *            id of game
	 */
	void endGame(UUID idGame);
}
