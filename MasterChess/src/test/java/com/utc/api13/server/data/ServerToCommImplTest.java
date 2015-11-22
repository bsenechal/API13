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
	@Before
	public void setUp() throws Exception {

		dataServerManager = new DataServerManager();
		
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
		final int nbRandomUser = 5;
		final UUID idUser = UUID.randomUUID(); 
		
		user.setId(idUser);
		
		for (int i = 0 ; i < nbRandomUser ; i++){
			PublicUserEntity randomUser = new PublicUserEntity();
			randomUser.setId(UUID.randomUUID());
			dataServerManager.getCurrentUsers().add(randomUser);
		}
		
		dataServerManager.getCurrentUsers().add(user);
		
		dataServerManager.getServerDataToComImpl().disconnect(idUser);
		
		Assert.assertNotNull("DataServerManager shouldn't be null", dataServerManager);
		Assert.assertNotNull("CurrentUsers shouldn't be null", dataServerManager.getCurrentUsers());
		Assert.assertEquals("CurrentUsers should contain " + nbRandomUser + " items", nbRandomUser, dataServerManager.getCurrentUsers().size());
		Assert.assertFalse("CurrentUsers souldn't contain the user with the id : " + idUser, dataServerManager.getCurrentUsers().contains(idUser));
	}

}
