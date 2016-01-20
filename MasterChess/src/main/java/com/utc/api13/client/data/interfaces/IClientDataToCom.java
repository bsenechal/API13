package com.utc.api13.client.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.client.data.DataClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MessageEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.GameStatusEnum;

/**
 * 
 * This interface will be given to the Communication Client module.
 * 
 * @author ulyss_000
 *
 */

// Cette Interface sert à recevoir les réponses de Com
// Com n'appelle pas les méthodes de ces interfaces à l'aide de méthodes, mais à
// l'aide de proceed.

public interface IClientDataToCom {

    /**
     * This method will set the ClientDataManger ObservableList
     * <AUserEntity> currentUsers -> it will not add the localuser to
     * currentUsers if existing in the connectedUserList parameter
     * 
     * @param connectedUserList
     */
    public void displayUsersList(final List<PublicUserEntity> connectedUserList); // Affiche
    // (récupère
    // pour
    // affichege
    // IHM)
    // la
    // liste
    // des
    // PublicUserEntity
    // connectés

    /**
     * Affiche un Profil de PublicUserEntity distant
     * 
     * @param user
     *            utilisateur à afficher
     */
    public void displayProfile(final PublicUserEntity user);

    public void print_error(final String error);

    /**
     * Affiche (récupère pour affichege IHM) la liste des GameEntity en cours
     * 
     * @param games
     *            liste des games en cours
     */
    public void displayAllGames(final List<GameEntity> games);

    /**
     * This method will update the local currentGame with the server validated
     * move & it will trigger an board update on IHM
     * 
     * @author ulyss_000
     * @param idPlayer
     * @param move
     */
    public void displayResult(final UUID idPlayer, final MoveEntity move);

    public void sendMessageToChat(final MessageEntity message);

    public void sendAnswerForLeaving(final boolean answer);

    public void requestPlayerForLeaving(final UUID uid);

    /**
     * Ends the local game for the observer when one of the player has left<br/>
     * Method to call only for observer users
     */
    public void endGameByLeaving();

    /**
     * TODO: unnecessary
     * 
     * @param message
     *            message
     */
    public void notify(final String message);

    public void notifyRejection(final UUID uidSender, String REJECTION_MESSAGE);

    
    /**
     * La GameEntity est créé sur le serveur.<br/>
     * Cette méthode initialise l'instance de la GameEntity sur le client
     * 
     * @param game
     *            game créé sur le serveur
     */
    public void initGame(final GameEntity game);

    public void newObserver(final UUID idObserver);

    /**
     * Etant donné qu'un GameEntity est créé sur le serveur avec deux users<br/>
     * cette méthode semble inutile
     * 
     * @param idPlayer
     */
    public void newPlayer(final UUID idPlayer);

    public void newReplay(final GameEntity game);

    public void sendProposition(final UUID uidSender, final UUID uidReciever, final boolean observable,
            final boolean chattable); // A quoi sert cette méthode dans les
                                      // méthodes utilisées par le "retour" de
                                      // com ?

    /**
     * Affiche la proposition de jeu d'un autre joueur.
     * "Voulez vous jouer avec moi ?"
     * 
     * @param uidSender
     * @param observable
     * @param chattable
     * @param timer
     */
    public void printProposition(final UUID uidSender, final boolean observable, final boolean chattable,
            final boolean timer, final Integer timerInt); // Affiche
    // la
    // proposition
    // de
    // jeu
    // d'un
    // autre
    // joueur.
    // "Voulez
    // vous
    // jouer
    // avec
    // moi
    // ?"

    public void victoryBySurrender();

    public void endGameBySurrender();

    public void displayMessage(final String message);

    public DataClientManager getInstanceDataClientManager();

    public void setInstanceDataClientManager(DataClientManager instanceDataClientManager);

    /**
     * Adds a new user to the list of local users*
     * 
     * @param user
     *            user to add
     */
    public void notifyConnection(PublicUserEntity user);

    /**
     * Delete a user from the list of local users
     * 
     * @param idUser
     *            id of user to delete
     */
    public void notifyDisconnection(UUID idUser);

    /**
     * This method will set the status of the game / switch active players /
     * give points if necessary
     * 
     * @author ulyss_000
     * @param status
     *            -> the game status (CHECK/CHECKMATE/CONTINUE/DRAW) send by the
     *            server
     */
    public void setFinishedStatus(GameStatusEnum status);

    public void nextTurn(GameStatusEnum isFinished, UUID nextPlayer);
}
