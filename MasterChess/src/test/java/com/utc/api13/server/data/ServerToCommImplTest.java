/**
 * 
 */
package com.utc.api13.server.data;

import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.utc.api13.commun.entities.PublicUserEntity;


/**
 * @author DATA
 *
 */
public class ServerToCommImplTest {
	private DataServerManager dataServerManager;
	
	/**
	 * @throws java.lang.Exception
	 */
	
    private final int nbRandomUser = 5;

	@Before
	public void setUp() throws Exception {

		dataServerManager = new DataServerManager();
		

		for (int i = 0 ; i < nbRandomUser ; i++){
            PublicUserEntity randomUser = new PublicUserEntity();
            randomUser.setId(UUID.randomUUID());
            dataServerManager.getCurrentUsers().add(randomUser);
        }
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test if the disconnect function remove a PublicUserEntity from currentUsers correctly
	 */
	@Test
	public void disconnectTest() {
        PublicUserEntity user = new PublicUserEntity();
        final UUID idUser = UUID.randomUUID(); 
        
        user.setId(idUser);
    
        dataServerManager.getCurrentUsers().add(user);
        
        dataServerManager.getServerDataToComImpl().disconnect(idUser);
        
        Assert.assertNotNull("DataServerManager shouldn't be null", dataServerManager);
        Assert.assertNotNull("CurrentUsers shouldn't be null", dataServerManager.getCurrentUsers());
        Assert.assertEquals("CurrentUsers should contain " + nbRandomUser + " items", nbRandomUser, dataServerManager.getCurrentUsers().size());
        Assert.assertFalse("CurrentUsers shouldn't contain the user with the id : " + idUser, dataServerManager.getCurrentUsers().contains(idUser));
    }
	
	@Test
	public void getUserInfoTest() {
        PublicUserEntity user = new PublicUserEntity();
        final UUID idUser = UUID.randomUUID(); 
        final String login="Zbarbadjan";
        final String lastname="Jeannot";
        
        user.setId(idUser);
        user.setLogin(login);
        user.setLastName(lastname);
    
        dataServerManager.getCurrentUsers().add(user);
        
        dataServerManager.getServerDataToComImpl().getUserInfo(idUser);
        
        Assert.assertNotNull("DataServerManager shouldn't be null", dataServerManager);
        Assert.assertNotNull("CurrentUsers shouldn't be null", dataServerManager.getCurrentUsers());
        Assert.assertEquals("CurrentUsers should contain " + (nbRandomUser+1) + " items", (nbRandomUser+1), dataServerManager.getCurrentUsers().size());
        Assert.assertNotNull("getUserInfo shouldn't be null for user : " + idUser, dataServerManager.getServerDataToComImpl().getUserInfo(idUser));
        Assert.assertEquals("getUserInfo().Login should be " + login, login, dataServerManager.getServerDataToComImpl().getUserInfo(idUser).getLogin());
        Assert.assertEquals("getUserInfo().LastName should be " + lastname, lastname, dataServerManager.getServerDataToComImpl().getUserInfo(idUser).getLastName());
        Assert.assertFalse("CurrentUsers should contain the user with the id : " + idUser, dataServerManager.getCurrentUsers().contains(idUser));
	}
	

}