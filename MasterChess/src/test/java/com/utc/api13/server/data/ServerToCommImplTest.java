/**
 * 
 */
package com.utc.api13.server.data;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.utc.api13.commun.entities.PublicUserEntity;

import junit.framework.Assert;

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
		final UUID idUser = UUID.randomUUID(); 
		user.setId(idUser);
		
		for (int i = 0 ; i < 5 ; i++){
			PublicUserEntity randomUser = new PublicUserEntity();
			randomUser.setId(UUID.randomUUID());
			dataServerManager.getCurrentUsers().add(randomUser);
		}
		
		dataServerManager.getCurrentUsers().add(user);
		
		dataServerManager.getServerToCommImpl().disconnect(idUser);
		
		Assert.assertNotNull(dataServerManager.getCurrentUsers());
		Assert.assertEquals(5, dataServerManager.getCurrentUsers().size());
		Assert.assertFalse(dataServerManager.getCurrentUsers().contains(idUser));
	}

}
