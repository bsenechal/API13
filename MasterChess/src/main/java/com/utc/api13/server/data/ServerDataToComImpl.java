/**
 * 
 */
package com.utc.api13.server.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

//import com.utc.api13.client.data.services.UserService;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
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
    public boolean computerResult(int idPlayer, MoveEntity move) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerToComm#isFinished(java.lang.
     * String)
     */
    @Override
    public boolean isFinished(String idGame) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void observerLeave(final UUID idUser) {
        Assert.notNull(dataServerManager.getCurrentUsers(),
                "[ServerDataToComImpl][observerLeave] currentUsers shouldn't be null");
        dataServerManager.getCurrentGames().stream().forEach(game -> {
            game.getObservers().removeIf(u -> idUser.equals(u.getId()));
        });
        dataServerManager.getCurrentUsers().removeIf(u -> idUser.equals(u.getId()));
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
    public void saveUserData(final PublicUserEntity user) {
        Assert.notNull(dataServerManager.getCurrentUsers(),
                "[ServerDataToComImpl][saveUserData] currentUsers shouldn't be null");
        Map<Boolean, List<PublicUserEntity>> map = dataServerManager.getCurrentUsers().stream()
                .collect(Collectors.partitioningBy(u -> u.getId().equals(user.getId())));
        List<PublicUserEntity> currentUsers = map.get(false);
        currentUsers.add(user);
        dataServerManager.setCurrentUsers(currentUsers);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.server.data.interfaces.IServerToComm#newObserver(int,
     * java.util.UUID)
     */
    @Override
    public void newObserver(int idGame, UUID idUser) {
        // TODO Auto-generated method stub

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
        final GameEntity gameFound = dataServerManager.getCurrentGames().stream().filter(u -> u.getId().equals(idGame))
                .findFirst().orElse(null);

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
    };

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.server.data.interfaces.IServerToComm#surrender(java.util.
     * UUID)
     */
    @Override
    public void surrender(UUID idPlayer) {
        // TODO Auto-generated method stub

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
    public GameEntity createGame(UUID j1, UUID j2, boolean observable, boolean chattable) {
        // TODO Auto-generated method stub
        return null;
    }
}