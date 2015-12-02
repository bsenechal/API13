/**
 * 
 */
package com.utc.api13.client.data;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

/**
 * @author DATA
 *
 */
public class ClientDataToIHMImplTest {
    private DataClientManager dataClientManager;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        dataClientManager = new DataClientManager();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void disconnectTest() {
        dataClientManager.setUserLocal(new PrivateUserEntity());

        try {
			dataClientManager.getClientDataToIHMImpl().disconnect();
		} catch (TechnicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FunctionalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Assert.assertNotNull("dataClientManager shouldn't be null", dataClientManager);
        Assert.assertNull("UserLocal should be null", dataClientManager.getUserLocal());
    }

    @Test
    public void getUserListTest() {
        final PublicUserEntity user = new PublicUserEntity();

        dataClientManager.getCurrentUsers().add(user);
        Assert.assertNotNull("The list shouldn't be null", dataClientManager.getClientDataToIHMImpl().getUserList());
        Assert.assertTrue("The list should contain the user " + user.toString(),
                dataClientManager.getClientDataToIHMImpl().getUserList().contains(user));
    }

    @Test
    public void getCurrentGameTest() {
        final GameEntity game = new GameEntity();

        dataClientManager.setCurrentGame(game);
        Assert.assertNotNull("The current game shouldn't be null",
                dataClientManager.getClientDataToIHMImpl().getCurrentGame());
        Assert.assertEquals("Current game should be equal to " + game.toString(),
                dataClientManager.getClientDataToIHMImpl().getCurrentGame(), game);
    }

    @Test
    public void getLocalUserTest() {
        PrivateUserEntity localUser = new PrivateUserEntity();
        dataClientManager.setUserLocal(localUser);

        Assert.assertNotNull("The local user shouldn't be null",
                dataClientManager.getClientDataToIHMImpl().getLocalUser());
        Assert.assertEquals("Current game should be equal to " + localUser.toString(),
                dataClientManager.getClientDataToIHMImpl().getLocalUser(), localUser);
    }
}
