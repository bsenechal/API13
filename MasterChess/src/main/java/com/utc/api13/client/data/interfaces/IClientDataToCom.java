package com.utc.api13.client.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.client.data.DataClientManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MessageEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.collections.ObservableList;

/**
 * 
 * This interface will be given to the Communication Client module.
 * @author ulyss_000
 *
 */

//Cette Interface sert à recevoir les réponses de Com
//Com n'appelle pas les méthodes de ces interfaces à l'aide de méthodes, mais à l'aide de proceed.

public interface IClientDataToCom {
    

    /**
     * This method will set the ClientDataManger ObservableList<AUserEntity> currentUsers
     * -> it will not add the localuser to currentUsers if existing in the connectedUserList parameter
     * @param connectedUserList
     */
    void displayUsersList(final List<PublicUserEntity> connectedUserList);          //Affiche (récupère pour affichege IHM) la liste des PublicUserEntity connectés

    /**
     * 
     * @param user
     */
    void displayProfile(final PublicUserEntity user);                               //Affiche un Profil de PublicUserEntity distant

    void print_error(final String error);

    void displayAllGames(final List<GameEntity> games);                             //Affiche (récupère pour affichege IHM) la liste des GameEntity en cours

    void displayResult(final UUID idPlayer, final MoveEntity move);

    void sendMessageToChat(final MessageEntity message);

    void sendAnswerForLeaving(final boolean answer);

    void requestPlayerForLeaving(final UUID uid);

    void endGameByLeaving();

    /**
     * TODO: unnecessary
     * @param message message
     */
    void notify(final String message);

    void initGame(final GameEntity game);                                           //La GameEntity est créé sur le serveur. Cette méthode initialise l'instance de la GameEntity sur le client

    void newObserver(final UUID idObserver);

    void newPlayer(final UUID idPlayer);                                            //Etant donné qu'un GameEntity est créé sur le serveur avec deux users, cette méthode semble inutile

    void newReplay(final GameEntity game);

    void sendProposition(final UUID uidSender, final UUID uidReciever, final boolean observable, final boolean chattable);      //A quoi sert cette méthode dans les méthodes utilisées par le "retour" de com ?

    void printProposition(final UUID uidSender, final boolean observable, final boolean chattable);     //Affiche la proposition de jeu d'un autre joueur. "Voulez vous jouer avec moi ?"

    void victoryBySurrender();

    void endGameBySurrender();

    void displayMessage(final String message);

    DataClientManager getInstanceDataClientManager();

    void setInstanceDataClientManager(DataClientManager instanceDataClientManager);
}