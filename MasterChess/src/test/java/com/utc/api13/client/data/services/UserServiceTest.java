package com.utc.api13.client.data.services;

public class UserServiceTest {
//extends DataServiceTest<PrivateUserEntity> {
//
//	@Override
//	protected PrivateUserEntity getEntityWithoutId() {
//		PrivateUserEntity user = new PrivateUserEntity();
//		user.setLogin("login");
//		user.setFirstName("first");
//		user.setLastName("Last");
//		user.setNbPlayed(2);
//		user.setNbLost(1);
//		user.setNbWon(1);
//		user.setStatus(true);
//		return user;
//	}
//
//	@Override
//	protected ADataService<PrivateUserEntity> getService() {
//		return new UserService(new ClientComToDataImpl(new ComClientManager()));
//	}
//	
//	public void testGetByLoginAndPassword() {
//		
//		try{
//			//Test without registering the user
//			PrivateUserEntity foundUser = ((UserService) getService()).getByLoginAndPassword("login", "password");
//			assertNull(foundUser);
//			//Register the user
//			PrivateUserEntity newUser = new PrivateUserEntity();
//			newUser.setLogin("login");
//			newUser.setPassword("password");
//			getService().save(newUser);
//			//Test after registring
//			foundUser = ((UserService) getService()).getByLoginAndPassword("login", "password");
//			assertNotNull(foundUser);
//			assertEquals("login", foundUser.getLogin());
//			assertEquals("password", ((PrivateUserEntity) foundUser).getPassword());
//			//Delete the created entity
//			getService().delete(foundUser);
//		} catch (TechnicalException | FunctionalException e) {
//			getLOG().error("Error", e);
//		}
//	}
//	
//	public void testConnect() {
//		/**
//		 * Try to sign in whithout registering<br/>Retourne une exception normalement
//		 **/
//		try {
//			((UserService) getService()).connect("login", "password");
//			getLOG().error("Connection failed: the user should not be able to sign in");
//		} catch (FunctionalException | TechnicalException e) {
////			getLOG().info("Incorrect login or password", e);
//		}
//		//save the user
//		PrivateUserEntity newuser = new PrivateUserEntity();
//		newuser.setLogin("login");
//		newuser.setPassword("password");
//		try {
//			getService().save(newuser);
//		} catch (TechnicalException | FunctionalException e) {
//			getLOG().error(e);
//		}
//		//Test connect again
//		try {
//			((UserService) getService()).connect("login", "password");
//			//TODO: voir s'il est possible de demander au serveur si le user est online
////			getLOG().info("Connection successfull");
//		} catch (FunctionalException | TechnicalException e) {
//			getLOG().error("Connection failed: the user should be able to sign in");
//		} finally {
//			//Delete the entity
//			try {
//				getService().delete(newuser);
//			} catch (TechnicalException e) {
//				getLOG().error(e);
//			}
//		}
//		
//		
//	}

}
