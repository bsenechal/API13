/**
 * 
 */
package com.utc.api13.client.data;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

/**
 * @author Benoît
 *
 */
public class ClientToIHMImplTest {
	private DataClientManager dataClientManager;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dataClientManager = new DataClientManager();
		dataClientManager.getCurrentUsers().add(new PublicUserEntity());
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

		dataClientManager.getClientDataToIHMImpl().disconnect();

		Assert.assertNotNull("dataClientManager shouldn't be null", dataClientManager);
		Assert.assertNull("UserLocal should be null", dataClientManager.getUserLocal());
	}
	
	@Test
    public void getUserListTest() {
		Assert.assertNotNull("The list shouldn't be null", dataClientManager.getClientDataToIHMImpl().getUserList());
    }
}
