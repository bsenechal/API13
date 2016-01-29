/**
 * 
 */
package com.utc.api13.server.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

import com.utc.api13.commun.entities.APieceEntity;
//import com.utc.api13.client.data.services.UserService;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.pieces.KingEntity;
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
     * @see com.utc.api13.server.data.interfaces.IServerToComm#getUsers()
     */
    @Override
    public List<PublicUserEntity> getUsers() {
        return null;
    }

    @Override
    public PublicUserEntity getUserInfo(final UUID idUser) {
        Assert.notNull(idUser, "[ServerDataToComImpl][getUserInfo] idUser shouldn't be null");
        Assert.notNull(dataServerManager.getCurrentUsers(),
                "[ServerDataToComImpl][getUserInfo] currentUsers shouldn't be null");
        return dataServerManager.getCurrentUsers().stream().filter(u -> u.getId().equals(idUser)).findFirst()
                .orElse(null);
    }

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
    public boolean computerResult(UUID idPlayer, MoveEntity move) {

//        System.out.println("computerResult : move : " + move.getFromPosition().getPositionX() + ","
//                + +move.getFromPosition().getPositionY() + ";" + move.getToPosition().getPositionX() + ","
//                + move.getToPosition().getPositionY() + ";" + move.getPiece().toString());

        GameEntity game = dataServerManager.getGameById(move.getGameID());
        // !!! Il est nécessaire de récupérer la pièce locale et non celle du
        // move récupéré côté client -> ce n'est pas le même object
        APieceEntity mypiece = game.getPieceFromPosition(move.getFromPosition());
        Boolean result = mypiece.isMovePossible(move, game);
        System.out.println("isMovePossible " + result);
        if (result) {
            
            game.removePieceFromPosition(move.getToPosition());
            game.movePiece(move);

            System.out.println("computerResult : move : " + move.getFromPosition().getPositionX() + ","
                    + +move.getFromPosition().getPositionY() + ";" + move.getToPosition().getPositionX() + ","
                    + move.getToPosition().getPositionY() + ";" + move.getPiece().toString());

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

        // verify game :
        GameEntity game = dataServerManager.getGameById(idGame);
        
        //first : switch user to verify that he is not checked !
        game.switchCurrentUser();
        
        //then verify the game status :
        GameStatusEnum result = game.isFinished();
        System.out.println("serverdatatocomimpt : isfinished : status : " + result);

        

        // TODO : Ulysse : isn't it a tad brutal ? -> C'est plutôt à Com de le faire dans le proceed
        if (result.equals(GameStatusEnum.CHECKMATE) || result.equals(GameStatusEnum.DRAW)) {
            // Clean the serveur game-entity :
//            dataServerManager.getCurrentGames().remove(game);
        }
        return result;
    }

    @Override
    public void observerLeave(final UUID idUser) {
        Assert.notNull(dataServerManager.getCurrentUsers(),
                "[ServerDataToComImpl][observerLeave] currentUsers shouldn't be null");
        dataServerManager.getCurrentGames().stream().forEach(game -> {
            game.getObservers().removeIf(u -> idUser.equals(u.getId()));
        });

        // TODO: dataServerManager.getIServeurComToData().sendMessageToChat()

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerToComm#getListObservers()
     */
    @Override
    public List<PublicUserEntity> getListObservers() {
        // TODO Auto-generated method stub
        return null;
    }

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
        GameEntity game = getGameById(idGame);
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
        // TODO Auto-generated method stub

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

    public List<PublicUserEntity> getUsersByGame(final UUID idGame) {
        Assert.notNull(dataServerManager.getCurrentGames(),
                "[ServerDataToComImpl][getUsersByGame] currentGames shouldn't be null");
        List<PublicUserEntity> listUsersByGame = new ArrayList<PublicUserEntity>();

        // variable containing the corresponding idGame Game.
        final GameEntity gameFound = getGameById(idGame);

        // If the idGame exist on the server
        if (gameFound != null) {
            // Else get all observer + two players
            if (gameFound.getObservers() != null) {
                listUsersByGame.addAll(gameFound.getObservers());
            }
            if (gameFound.getBlackPlayer() != null) {
                listUsersByGame.add(gameFound.getBlackPlayer());
            }

            if (gameFound.getWhitePlayer() != null) {
                listUsersByGame.add(gameFound.getWhitePlayer());
            }
            return listUsersByGame;
        }

        // If the idGame doesn't exist on the server, the method sends back
        // 'null'
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
        // Get the game played by the user
        GameEntity playedGame = dataServerManager.getCurrentGames().stream()
                .filter(game -> game.getBlackPlayer().getId().equals(idPlayer)
                        || game.getWhitePlayer().getId().equals(idPlayer))
                .findFirst().orElse(null);
        // Erase the game from server
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

    @Override
    public GameEntity createGame(UUID j1, UUID j2, boolean observable, boolean chattable, boolean timer,
            Integer timerInt) {
        PublicUserEntity whitePlayer;
        PublicUserEntity blackPlayer;

        /*
         * generate a random number to choose between 0 and 1 to choose who will
         * be the white player and who will be the black player
         */
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
        // Enable chat for the two players
        whitePlayer.setAllowedToChat(true);
        blackPlayer.setAllowedToChat(true);
        // Create a game
        GameEntity newGame = new GameEntity();
        newGame.setBlackPlayer(blackPlayer);
        newGame.setWhitePlayer(whitePlayer);
        newGame.setCurrentPlayer(whitePlayer);
        newGame.setIsOservable(observable);
        newGame.setIsChattable(chattable);
        newGame.setTimer(timer);
        newGame.setTimerInt(timerInt);
        // Add to the list of current games
        dataServerManager.getCurrentGames().add(newGame);
        return newGame;
    }

    @Override
    public GameEntity getGameById(UUID idGame) {
        return dataServerManager.getCurrentGames().stream().filter(u -> u.getId().equals(idGame)).findFirst()
                .orElse(null);
    }

    @Override
    public void endGame(UUID idGame) {
        dataServerManager.getCurrentGames().removeIf(g -> idGame.equals(g.getId()));
    }

    @Override
    public void removeUserFromChat(UUID idUser, UUID idGame) {
        GameEntity game = getGameById(idGame);
        if (game != null) {
            PublicUserEntity userToRemove = getUserInfo(idUser);
            if (userToRemove != null) {
                userToRemove.setAllowedToChat(false);
            }
        }
    }

    @Override
    public boolean isPlaying(UUID idUser) {
        // TODO Auto-generated method stub
        return false;
    }
}
