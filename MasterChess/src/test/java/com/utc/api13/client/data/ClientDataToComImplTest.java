package com.utc.api13.client.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.ihm.ClientIHMToDataImpl;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.GameStatusEnum;

/**
 * @author DATA
 *
 */
public class ClientDataToComImplTest {
    private DataClientManager dataClientManager;

    @Mock
    private ClientIHMToDataImpl clientIHMToDataImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        dataClientManager = new DataClientManager();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void displayUsersListTest() {
        List<PublicUserEntity> connectedUserList = new ArrayList<PublicUserEntity>();
        final int nbUser = 5;
        final PublicUserEntity specificUser = new PublicUserEntity();

        dataClientManager.setUserLocal(new PrivateUserEntity());
        connectedUserList.add(specificUser);

        for (int i = 0; i < nbUser - 1; i++) {
            connectedUserList.add(new PublicUserEntity());
        }

        Assert.assertTrue("CurrentUsers should be empty", dataClientManager.getCurrentUsers().isEmpty());

        dataClientManager.getClientDataToComImpl().displayUsersList(connectedUserList);

        Assert.assertNotNull("dataClientManager shouldn't be null", dataClientManager);
        Assert.assertEquals("CurrentUsers should contain " + nbUser + " users", nbUser,
                dataClientManager.getCurrentUsers().size());
        Assert.assertTrue("CurrentUsers should contain the user " + specificUser.toString(),
                dataClientManager.getCurrentUsers().contains(specificUser));
    }

    @Test
    public void displayAllGamesTest() {
        List<GameEntity> games = new ArrayList<GameEntity>();
        final int nbGame = 5;
        final GameEntity specificGame = new GameEntity();

        games.add(specificGame);

        for (int i = 0; i < nbGame - 1; i++) {
            games.add(new GameEntity());
        }

        Assert.assertTrue("CurrentGames should be empty", dataClientManager.getCurrentUsers().isEmpty());

        dataClientManager.getClientDataToComImpl().displayAllGames(games);

        Assert.assertNotNull("dataClientManager shouldn't be null", dataClientManager);
        Assert.assertEquals("CurrentGames should contain " + nbGame + " games", nbGame,
                dataClientManager.getCurrentGames().size());
        Assert.assertTrue("CurrentGames should contain the game " + specificGame.toString(),
                dataClientManager.getCurrentGames().contains(specificGame));
    }

    @Test
    public void notifyConnection() {
        final PublicUserEntity user = new PublicUserEntity();
        final int nbUsers = dataClientManager.getCurrentUsers().size();
        final int nbUsersExpected = nbUsers + 1;

        dataClientManager.getClientDataToComImpl().notifyConnection(user);

        Assert.assertNotNull("dataClientManager shouldn't be null", dataClientManager);
        Assert.assertTrue("CurrentUsers should contain the user " + user.toString(),
                dataClientManager.getCurrentUsers().contains(user));
        Assert.assertEquals("CurrentUsers should contain " + nbUsersExpected + " users", nbUsersExpected,
                dataClientManager.getCurrentUsers().size());
    }

    @Test
    public void notifyDisconnection() {
        final PublicUserEntity user = new PublicUserEntity();

        dataClientManager.getCurrentUsers().add(user);

        final int nbUsers = dataClientManager.getCurrentUsers().size();
        final int nbUsersExpected = nbUsers - 1;

        dataClientManager.getClientDataToComImpl().notifyDisconnection(user.getId());

        Assert.assertNotNull("dataClientManager shouldn't be null", dataClientManager);
        Assert.assertFalse("CurrentUsers shouldn't contain the user " + user.toString(),
                dataClientManager.getCurrentUsers().contains(user));
        Assert.assertEquals("CurrentUsers should contain " + nbUsersExpected + " users", nbUsersExpected,
                dataClientManager.getCurrentUsers().size());
    }

    @Test
    public void initGame() {
        GameEntity testGame = new GameEntity();

        dataClientManager.setIClientIHMToData(clientIHMToDataImpl);
        Mockito.doNothing().when(clientIHMToDataImpl).displayChessBoard(testGame);

        dataClientManager.getClientDataToComImpl().initGame(testGame);

        Assert.assertNotNull("CurrentGame shouldn't be null", dataClientManager.getCurrentGame());
        Assert.assertEquals("CurrentGame and testGame should be equals", dataClientManager.getCurrentGame(), testGame);
    }

    @Test
    public void nextTurnTest() {
        PublicUserEntity user1 = new PublicUserEntity();
        PublicUserEntity user2 = new PublicUserEntity();
        GameEntity game = new GameEntity(Boolean.FALSE, Boolean.FALSE, new Date(), user1, user2);

        dataClientManager.setCurrentGame(game);

        // Test si le currentUser est bien le joueur noir
        Assert.assertEquals("Current player should be user1",
                dataClientManager.getCurrentGame().getCurrentPlayer().getId(), user1.getId());

        dataClientManager.getClientDataToComImpl().nextTurn(GameStatusEnum.CONTINUE, user2.getId());

        // Status CONTINUE : On vérifie qu'on a bien changé de currentPlayer
        Assert.assertEquals("Current player should be user2",
                dataClientManager.getCurrentGame().getCurrentPlayer().getId(), user2.getId());

        dataClientManager.getClientDataToComImpl().nextTurn(GameStatusEnum.CHECKMATE, user2.getId());

        // Status CHECKMATE : On vérifie qu'on a pas changé de joueur
        Assert.assertEquals("Current player should be user2",
                dataClientManager.getCurrentGame().getCurrentPlayer().getId(), user2.getId());
    }
}
