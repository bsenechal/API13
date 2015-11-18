package com.utc.api13.commun.services;

import com.utc.api13.commun.entities.UserEntity;

public class UserServiceTest extends DataServiceTest<UserEntity> {

	@Override
	protected UserEntity getEntityWithoutId() {
		UserEntity user = new UserEntity();
		user.setLogin("login");
		user.setFirstName("first");
		user.setLastName("Last");
		user.setNbPlayed(2);
		user.setNbLost(1);
		user.setNbWon(1);
		user.setStatus(true);
		return user;
	}
	
//	@Test
//	public void testGetByLoginAndPassword(){
//		
//		try {
//			//Test without registering the user
//			Optional<UserEntity> foundUser = ((UserService) getService()).getByLoginAndPassword("login", "password");
//			Assert.assertNull(foundUser);
//			//Register the user
//			PrivateUserEntity newUser = new PrivateUserEntity();
//			newUser.setLogin("login");
//			newUser.setPassword("password");
//			getService().save(newUser);
//			//Test after registering the user
//			foundUser = ((UserService) getService()).getByLoginAndPassword("login", "password");
//			Assert.assertNotNull(foundUser);
//			Assert.assertEquals("login", foundUser.get().getLogin());
//			Assert.assertEquals("password", ((PrivateUserEntity) foundUser.get()).getPassword());
//		} catch (TechnicalException | FunctionalException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
//	}

}
