package com.utc.api13.client.data;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

/**
 * @author DATA
 *
 */
public class ClientDataToComImplTest {
    private DataClientManager dataClientManager;

    @Before
    public void setUp() throws Exception {
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
}
