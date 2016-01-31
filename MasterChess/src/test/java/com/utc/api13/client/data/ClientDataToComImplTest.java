package com.utc.api13.client.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        // PublicUserEntity user1 = new PublicUserEntity();
        // PublicUserEntity user2 = new PublicUserEntity();
        // GameEntity game = new GameEntity(Boolean.FALSE, Boolean.FALSE, new
        // Date(), user1, user2);
        //
        // dataClientManager.setCurrentGame(game);
        //
        // // Test si le currentUser est bien le joueur noir
        // Assert.assertEquals("Current player should be user1",
        // dataClientManager.getCurrentGame().getCurrentPlayer().getId(),
        // user1.getId());
        //
        // dataClientManager.getClientDataToComImpl().nextTurn(GameStatusEnum.CONTINUE,
        // user2.getId());
        //
        // // Status CONTINUE : On vérifie qu'on a bien changé de currentPlayer
        // Assert.assertEquals("Current player should be user2",
        // dataClientManager.getCurrentGame().getCurrentPlayer().getId(),
        // user2.getId());
        //
        // dataClientManager.getClientDataToComImpl().nextTurn(GameStatusEnum.CHECKMATE,
        // user2.getId());
        //
        // // Status CHECKMATE : On vérifie qu'on a pas changé de joueur
        // Assert.assertEquals("Current player should be user2",
        // dataClientManager.getCurrentGame().getCurrentPlayer().getId(),
        // user2.getId());
    }

    @Test
    public void sendAnswerForLeavingTest() {
        GameEntity game = new GameEntity();
        PrivateUserEntity userPrivate = new PrivateUserEntity();
        PublicUserEntity whiteUser = new PublicUserEntity();
        PublicUserEntity blackUser = new PublicUserEntity();
        final UUID idWhite = UUID.randomUUID();
        final UUID idBlack = UUID.randomUUID();
        userPrivate.setId(idWhite);
        whiteUser.setId(idWhite);
        blackUser.setId(idBlack);
        game.setBlackPlayer(blackUser);
        game.setWhitePlayer(whiteUser);
        dataClientManager.setIClientIHMToData(clientIHMToDataImpl);
        dataClientManager.setUserLocal(userPrivate);
        dataClientManager.setCurrentGame(game);
        Assert.assertNotNull(dataClientManager.getCurrentGame());
        // If false then nothing -> game continue

        dataClientManager.getClientDataToComImpl().sendAnswerForLeaving(false);
        Assert.assertNotNull(dataClientManager.getCurrentGame());

        // If true then quit game without changing stats
        dataClientManager.getUserLocal().setSavedGames(new ArrayList<GameEntity>());
        dataClientManager.getClientDataToComImpl().sendAnswerForLeaving(true);
        Assert.assertNull(dataClientManager.getCurrentGame());

    }

    @Test
    public void endGameBySurrednerTest() {
        GameEntity game = new GameEntity();
        PrivateUserEntity userPrivate = new PrivateUserEntity();
        PublicUserEntity whiteUser = new PublicUserEntity();
        PublicUserEntity blackUser = new PublicUserEntity();
        userPrivate.setNbLost(0);
        userPrivate.setNbPlayed(0);
        userPrivate.setNbWon(0);
        game.setBlackPlayer(blackUser);
        game.setWhitePlayer(whiteUser);

        dataClientManager.setIClientIHMToData(clientIHMToDataImpl);
        dataClientManager.setUserLocal(userPrivate);
        dataClientManager.getUserLocal().setSavedGames(new ArrayList<GameEntity>());
        dataClientManager.setCurrentGame(game);
        Assert.assertNotNull(dataClientManager.getCurrentGame());

        // endGameBySurrend
        dataClientManager.getClientDataToComImpl().endGameBySurrender();
        Assert.assertNull(dataClientManager.getCurrentGame());
        Assert.assertEquals(0, dataClientManager.getUserLocal().getNbWon());
        Assert.assertEquals(1, dataClientManager.getUserLocal().getNbPlayed());
        Assert.assertEquals(1, dataClientManager.getUserLocal().getNbLost());

    }

    @Test
    public void victoryBySurrenderTest() {
        GameEntity game = new GameEntity();
        PrivateUserEntity userPrivate = new PrivateUserEntity();
        PublicUserEntity whiteUser = new PublicUserEntity();
        PublicUserEntity blackUser = new PublicUserEntity();
        userPrivate.setNbLost(0);
        userPrivate.setNbPlayed(0);
        userPrivate.setNbWon(0);
        game.setBlackPlayer(blackUser);
        game.setWhitePlayer(whiteUser);

        dataClientManager.setIClientIHMToData(clientIHMToDataImpl);
        dataClientManager.setUserLocal(userPrivate);
        dataClientManager.getUserLocal().setSavedGames(new ArrayList<GameEntity>());
        dataClientManager.setCurrentGame(game);
        Assert.assertNotNull(dataClientManager.getCurrentGame());

        // endGameBySurrend
        dataClientManager.getClientDataToComImpl().victoryBySurrender();
        Assert.assertNull(dataClientManager.getCurrentGame());
        Assert.assertEquals(1, dataClientManager.getUserLocal().getNbWon());
        Assert.assertEquals(1, dataClientManager.getUserLocal().getNbPlayed());
        Assert.assertEquals(0, dataClientManager.getUserLocal().getNbLost());

    }

}
