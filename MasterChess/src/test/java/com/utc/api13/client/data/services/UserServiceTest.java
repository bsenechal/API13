package com.utc.api13.client.data.services;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.services.ADataService;
import com.utc.api13.client.data.services.UserService;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

public class UserServiceTest extends DataServiceTest<PrivateUserEntity> {

	@Override
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

	@Override
	protected ADataService<PrivateUserEntity> getService() {
		return new UserService();
	}
	
	public void testGetByLoginAndPassword() {
		
		try{
			//Test without registering the user
			PrivateUserEntity foundUser = ((UserService) getService()).getByLoginAndPassword("login", "password");
			assertNull(foundUser);
			//Register the user
			PrivateUserEntity newUser = new PrivateUserEntity();
			newUser.setLogin("login");
			newUser.setPassword("password");
			getService().save(newUser);
			//Test after registring
			foundUser = ((UserService) getService()).getByLoginAndPassword("login", "password");
			assertNotNull(foundUser);
			assertEquals("login", foundUser.getLogin());
			assertEquals("password", ((PrivateUserEntity) foundUser).getPassword());
			//Delete the created entity
			getService().delete(foundUser);
		} catch (TechnicalException | FunctionalException e) {
			getLOG().error("Error", e);
		}
	}

}
