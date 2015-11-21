/**
 * 
 */
package com.utc.api13.client.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.utc.api13.client.data.entities.PrivateUserEntity;

import junit.framework.Assert;

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
		dataClientManager.setUserLocal(new PrivateUserEntity());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void disconnectTest() {
		Assert.assertNotNull(dataClientManager.getUserLocal());
		dataClientManager.getClientToIHMImpl().disconnect();
		Assert.assertNull(dataClientManager.getUserLocal());
	}

}
