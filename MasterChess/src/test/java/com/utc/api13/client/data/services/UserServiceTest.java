
package com.utc.api13.client.data.services;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.services.UserService;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

/**
 * Tests the methods of the UserService class
 * @author Amstrong
 *
 */

public class UserServiceTest {

	/**
	 * UserService instance
	 */
	private final UserService userService = new UserService();
	
	/**
	 * Class logger
	 */
	private final Logger LOG = Logger.getLogger(getClass());
	
	protected PrivateUserEntity getEntityWithoutId() {
		PrivateUserEntity user = new PrivateUserEntity();
		user.setLogin("login");
		user.setFirstName("first");
		user.setLastName("Last");
		user.setNbPlayed(2);
		user.setNbLost(1);
		user.setNbWon(1);
		user.setStatus(true);
		return user;
	}

	@Test
	public void testGetByLoginAndPassword() {
		PrivateUserEntity foundUser = null;
		try{
			//Test without registering the user
			foundUser = userService.getByLoginAndPassword("login", "password");
			Assert.assertNull(foundUser);
			//Register the user
			PrivateUserEntity newUser = new PrivateUserEntity();
			newUser.setLogin("login");
			newUser.setPassword("password");
			userService.save(newUser);
			//Test after registring
			foundUser = userService.getByLoginAndPassword("login", "password");
			Assert.assertNotNull(foundUser);
			Assert.assertEquals("login", foundUser.getLogin());
			Assert.assertEquals("password", foundUser.getPassword());
		} catch (TechnicalException | FunctionalException e) {
			LOG.error("Error", e);
			Assert.fail("test has failed. Check the logs for more information");
		} finally {
			if(foundUser != null) {
				try {
					userService.delete(foundUser);
				} catch (TechnicalException e) {
					LOG.error("Error while deleting", e);
				}
			}
		}
	}

}
