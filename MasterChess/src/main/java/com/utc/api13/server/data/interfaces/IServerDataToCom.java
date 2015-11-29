package com.utc.api13.server.data.interfaces;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

//Cette intercace est à disposition de Com sur le serveur.
//Ce sont les méthodes de traitement de Data.
//Ces méthodes sont synchrones, et doivent "return" une information à Com, si necessaire

public interface IServerDataToCom {
    
    public List<PublicUserEntity> getUsers();       //Appelée par com.getUsers(). Renvoie la liste des PublicUserEntity connectés
    
    public PublicUserEntity getUserInfo(final UUID idUser);     //Appelée par com.getUserInfo(final UUID idUser). Renvoie les informations sur un PublicUser
    
    public List<GameEntity> getAllGames();          //Appelée par com.getUsers(). Renvoie la liste des GameEntity en cours
    
    public void notifyConnections (final PublicUserEntity Player);      // Appelée par by com.notifyConnections (PublicUserEntity Player). Préviens le serveur lorsqu'un User se connecte, de façon à mettre à jour la liste de PublicUserEntity sur le serveur
    
    public boolean computerResult(final int idPlayer, final MoveEntity  move);
    
    public boolean isFinished (final String idGame);                    //Signale la fin d'une partie pour supprimer la GameEntity
    
    public void observerLeave(final UUID idUser);                       //Signaler qu'un observeur quitte la partie.
    
    public List<PublicUserEntity> getListObservers();                   //Récupérer la liste des observeurs
    
    public void saveUserData(final PublicUserEntity User);
    
    public void newObserver(final int idGame, final UUID idUser);      //Un nouvel observeur se connecte à la partie. Mettre à jour la List<PublicUserEntity> d'observeurs
    
    public void createReplay(final GameEntity game, final PublicUserEntity user);
    
    public List<PublicUserEntity> getConnectedUsers();              //Différence avec getUsers() ?
    /**
     * list of the Users (observers and players) connected to a specified Game
     * @param idGame
     * @return the list of found users or null if the game doesn't exist or an empty List<PublicUserEntity> if there are no players
     */
    public List<PublicUserEntity> getUsersByGame(final UUID idGame);    //Liste des utilisateurs connectés à une partie spécifique
    
    public void surrender(final UUID idPlayer);
    
    public void disconnect(final UUID idUser);                          //Appelée par com.disconnect(UUID idUser) pour supprimer un User de la liste des Users connectés

    
    //TODO
    //Méthode pour créer une nouvelle partie :
    //public GameEntity CreateGame(final UUID j1, final UUID j2, final boolean observable, final boolean chattable);
    
}
