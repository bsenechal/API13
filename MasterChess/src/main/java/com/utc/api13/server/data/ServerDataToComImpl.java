/**
 * 
 */
package com.utc.api13.server.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.GameStatusEnum;
import com.utc.api13.server.data.interfaces.IServerDataToCom;

/**
 * @author DATA
 *
 */
public class ServerDataToComImpl implements IServerDataToCom {
    private DataServerManager dataServerManager;

    /**
     * @param dataServerManager
     */
    public ServerDataToComImpl(DataServerManager dataServerManager) {
        super();
        Assert.notNull(dataServerManager, "[ServerDataToComImpl][Constructor] dataServerManager shouldn't be null");
        this.dataServerManager = dataServerManager;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerDataToCom#getUserInfo(java.
     * util.UUID)
     */
    @Override
    public PublicUserEntity getUserInfo(final UUID idUser) {
        Assert.notNull(idUser, "[ServerDataToComImpl][getUserInfo] idUser shouldn't be null");
        Assert.notNull(dataServerManager.getCurrentUsers(),
                "[ServerDataToComImpl][getUserInfo] currentUsers shouldn't be null");
        return dataServerManager.getCurrentUsers().stream().filter(u -> u.getId().equals(idUser)).findFirst()
                .orElse(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.server.data.interfaces.IServerDataToCom#getAllGames()
     */
    @Override
    public List<GameEntity> getAllGames() {
        return dataServerManager.getCurrentGames();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerToComm#notifyConnections(com.
     * utc.api13.commun.entities.UserEntity)
     */
    @Override
    public void notifyConnections(final PublicUserEntity player) {
        Assert.notNull(dataServerManager.getCurrentUsers(),
                "[ServerDataToComImpl][notifyConnections] currentUsers shouldn't be null");
        dataServerManager.getCurrentUsers().add(player);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerToComm#computerResult(int,
     * com.utc.api13.commun.entities.MoveEntity)
     */
    @Override
    public boolean computerResult(MoveEntity move) {

        GameEntity game = dataServerManager.getGameById(move.getGameID());
        // On récupere la piece
        APieceEntity mypiece = game.getPieceFromPosition(move.getFromPosition());
        Boolean result = mypiece.isMovePossible(move, game);

        if (result) {
            // Si le déplacement est possible
            game.removePieceFromPosition(move.getToPosition());

            // Transforme le pion en reine s'il atteint le camp ennemi
            game.transformPawnToQueen(move);
            // bouge la piece
            game.movePiece(move);

        }

        return result;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerToComm#isFinished(java.lang.
     * String)
     */
    @Override
    public GameStatusEnum isFinished(UUID idGame) {
        Assert.notNull(dataServerManager.getGameById(idGame),
                "[ServerDataToComImpl][isFinished] The specified idGame doesn't have a linked game");

        GameEntity game = dataServerManager.getGameById(idGame);

        // On echange les users
        game.switchCurrentUser();

        // On verifie le statut du jeu
        return game.isFinished();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerDataToCom#observerLeave(java.
     * util.UUID)
     */
    @Override
    public void observerLeave(final UUID idUser) {
        Assert.notNull(dataServerManager.getCurrentUsers(),
                "[ServerDataToComImpl][observerLeave] currentUsers shouldn't be null");
        dataServerManager.getCurrentGames().stream()
                .forEach(game -> game.getObservers().removeIf(u -> idUser.equals(u.getId())));

        // A faire lorsque l'on implemente les observateurs
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerToComm#getListObservers()
     */
    @Override
    public List<PublicUserEntity> getListObservers() {
        // A faire lorsque l'on implemente les observateurs
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerDataToCom#saveUserData(com.
     * utc.api13.commun.entities.PublicUserEntity)
     */
    @Override
    public boolean saveUserData(final PublicUserEntity user) {
        Assert.notNull(dataServerManager.getCurrentUsers(),
                "[ServerDataToComImpl][saveUserData] currentUsers shouldn't be null");
        Map<Boolean, List<PublicUserEntity>> map = dataServerManager.getCurrentUsers().stream()
                .collect(Collectors.partitioningBy(u -> u.getId().equals(user.getId())));
        List<PublicUserEntity> currentUsers = map.get(false);
        currentUsers.add(user);
        dataServerManager.setCurrentUsers(currentUsers);
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.server.data.interfaces.IServerToComm#newObserver(int,
     * java.util.UUID)
     */
    @Override
    public void newObserver(UUID idGame, UUID idUser) {
        // On recupere le jeu
        GameEntity game = getGameById(idGame);
        // On ajouter un observeur
        PublicUserEntity userToAdd = getUserInfo(idUser);
        if (game != null && userToAdd != null) {
            game.getObservers().add(userToAdd);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerToComm#createReplay(com.utc.
     * api13.commun.entities.GameEntity,
     * com.utc.api13.commun.entities.UserEntity)
     */
    @Override
    public void createReplay(GameEntity game, PublicUserEntity user) {
        // A faire lorsque l'on implémente les replays

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerToComm#getConnectedUsers()
     */
    @Override
    public List<PublicUserEntity> getConnectedUsers() {
        return dataServerManager.getCurrentUsers();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerDataToCom#getUsersByGame(java
     * .util.UUID)
     */
    @Override
    public List<PublicUserEntity> getUsersByGame(final UUID idGame) {
        Assert.notNull(dataServerManager.getCurrentGames(),
                "[ServerDataToComImpl][getUsersByGame] currentGames shouldn't be null");
        List<PublicUserEntity> listUsersByGame = new ArrayList<PublicUserEntity>();

        // On récupere le jeu
        final GameEntity gameFound = getGameById(idGame);

        // Si le jeu existe sur le serveur
        if (gameFound != null) {
            // On recupere les observateurs + les joueurs
            if (gameFound.getObservers() != null) {
                listUsersByGame.addAll(gameFound.getObservers());
            }
            if (gameFound.getBlackPlayer() != null) {
                listUsersByGame.add(gameFound.getBlackPlayer());
            }

            if (gameFound.getWhitePlayer() != null) {
                listUsersByGame.add(gameFound.getWhitePlayer());
            }
            // on retourne la liste
            return listUsersByGame;
        }

        // On retourne null si le jeu n'existe pas
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerToComm#surrender(java.util.
     * UUID)
     */
    @Override
    public void surrender(UUID idPlayer) {
        // On retrouve le jeu dans lequel le player joue
        GameEntity playedGame = dataServerManager.getCurrentGames().stream()
                .filter(game -> game.getBlackPlayer().getId().equals(idPlayer)
                        || game.getWhitePlayer().getId().equals(idPlayer))
                .findFirst().orElse(null);
        // On finit le jeu
        endGame(playedGame.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerToComm#disconnect(java.util.
     * UUID)
     */
    @Override
    public void disconnect(final UUID idUser) {
        Assert.notNull(dataServerManager.getCurrentUsers(),
                "[ServerDataToComImpl][disconnect] currentUsers shouldn't be null");
        dataServerManager.getCurrentUsers().removeIf(user -> user.getId().equals(idUser));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerDataToCom#createGame(java.
     * util.UUID, java.util.UUID, boolean, boolean, boolean, java.lang.Integer)
     */
    @Override
    public GameEntity createGame(UUID j1, UUID j2, boolean observable, boolean chattable, boolean timer,
            Integer timerInt) {
        PublicUserEntity whitePlayer;
        PublicUserEntity blackPlayer;

        // on genere un nombre aléatoire pour définir si le joueur est blanc ou
        // noir
        Random r = new Random();
        int valeur = 0 + r.nextInt(2 - 0);

        if (valeur == 1) {
            whitePlayer = getUserInfo(j1);
            blackPlayer = getUserInfo(j2);
        } else {
            whitePlayer = getUserInfo(j2);
            blackPlayer = getUserInfo(j1);
        }

        Assert.notNull(whitePlayer, "[ServerDataToComImpl][createGame] player 1 is not online");
        Assert.notNull(blackPlayer, "[ServerDataToComImpl][createGame] player 2 is not online");
        // On modifie les paramètres des jeux pour chacun des jeux
        whitePlayer.setAllowedToChat(true);
        blackPlayer.setAllowedToChat(true);
        // On crée un nouveau jeu
        GameEntity newGame = new GameEntity();
        newGame.setBlackPlayer(blackPlayer);
        newGame.setWhitePlayer(whitePlayer);
        newGame.setCurrentPlayer(whitePlayer);
        newGame.setIsOservable(observable);
        newGame.setIsChattable(chattable);
        newGame.setTimer(timer);
        newGame.setTimerInt(timerInt);
        // On ajoute le nouveau jeu
        dataServerManager.getCurrentGames().add(newGame);
        return newGame;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerDataToCom#getGameById(java.
     * util.UUID)
     */
    @Override
    public GameEntity getGameById(UUID idGame) {
        return dataServerManager.getCurrentGames().stream().filter(u -> u.getId().equals(idGame)).findFirst()
                .orElse(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerDataToCom#endGame(java.util.
     * UUID)
     */
    @Override
    public void endGame(UUID idGame) {
        dataServerManager.getCurrentGames().removeIf(g -> idGame.equals(g.getId()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerDataToCom#removeUserFromChat(
     * java.util.UUID, java.util.UUID)
     */
    @Override
    public void removeUserFromChat(UUID idUser, UUID idGame) {
        // On désactive le user du chat
        GameEntity game = getGameById(idGame);
        if (game != null) {
            PublicUserEntity userToRemove = getUserInfo(idUser);
            if (userToRemove != null) {
                userToRemove.setAllowedToChat(false);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerDataToCom#isPlaying(java.util
     * .UUID)
     */
    @Override
    public boolean isPlaying(UUID idUser) {
        // TODO Auto-generated method stub
        return false;
    }
}
