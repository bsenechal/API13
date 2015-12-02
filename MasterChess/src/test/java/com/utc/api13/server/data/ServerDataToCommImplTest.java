/**
 * 
 */
package com.utc.api13.server.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;


/**
 * @author DATA
 *
 */
public class ServerDataToCommImplTest {
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
	
	@Test
	public void notifyConnectionsTest() {
	    final PublicUserEntity user = new PublicUserEntity();
	    
	    dataServerManager.getServerDataToComImpl().notifyConnections(user);
	    
	    
	    Assert.assertNotNull("CurrentUsers list shouldn't be null", dataServerManager.getCurrentUsers());
        Assert.assertTrue("CurrentUsers list should contains " + user.toString(), dataServerManager.getCurrentUsers().contains(user));
	}
	
	@Test
	public void saveUserDataTest() {
	    final PublicUserEntity user = new PublicUserEntity();
        
        dataServerManager.getServerDataToComImpl().saveUserData(user);
        
        Assert.assertNotNull("CurrentUsers list shouldn't be null", dataServerManager.getCurrentUsers());
        Assert.assertTrue("CurrentUsers list should contains " + user.toString(), dataServerManager.getCurrentUsers().contains(user));
	}
	
	@Test
	public void getConnectedUsersTest(){
	   
	    Assert.assertNotNull("DataServerManager shouldn't be null", dataServerManager);
        Assert.assertNotNull("CurrentUsers shouldn't be null", dataServerManager.getServerDataToComImpl().getConnectedUsers());
        Assert.assertEquals("CurrentUsers should contain " + nbRandomUser + " items", nbRandomUser, dataServerManager.getServerDataToComImpl().getConnectedUsers().size());
	}
	
	@Test
	public void getUsersByGameTest() {
	  GameEntity game = new GameEntity();
	  final UUID idGame = UUID.randomUUID();
	  final PublicUserEntity whitePlayer = new PublicUserEntity();
	  final PublicUserEntity blackPlayer = new PublicUserEntity();
	  final PublicUserEntity observer1 = new PublicUserEntity();
	  final PublicUserEntity observer2 = new PublicUserEntity();
	  List<PublicUserEntity> observers = new ArrayList<PublicUserEntity>();
	  observers.add(observer1);
	  observers.add(observer2);
	  
	  game.setId(idGame);
	  
	  Assert.assertNull("The result should be null", dataServerManager.getServerDataToComImpl().getUsersByGame(idGame));

	  dataServerManager.getCurrentGames().add(game);
	  
	  game.setWhitePlayer(whitePlayer);
	  Assert.assertNotNull("The result shouldn't be null", dataServerManager.getServerDataToComImpl().getUsersByGame(idGame));
	  Assert.assertEquals("The list size should be equals to 1", 1, dataServerManager.getServerDataToComImpl().getUsersByGame(idGame).size());
	  Assert.assertTrue("The list should contain the user " + whitePlayer.toString(), dataServerManager.getServerDataToComImpl().getUsersByGame(idGame).contains(whitePlayer));
      
	  game.setBlackPlayer(blackPlayer);
	  Assert.assertNotNull("The result shouldn't be null", dataServerManager.getServerDataToComImpl().getUsersByGame(idGame));
      Assert.assertEquals("The list size should be equals to 2", 2, dataServerManager.getServerDataToComImpl().getUsersByGame(idGame).size());
      Assert.assertTrue("The list should contain the user " + blackPlayer.toString(), dataServerManager.getServerDataToComImpl().getUsersByGame(idGame).contains(blackPlayer));
      Assert.assertTrue("The list should contain the user " + whitePlayer.toString(), dataServerManager.getServerDataToComImpl().getUsersByGame(idGame).contains(whitePlayer));
           
	  game.setObservers(observers);
	  
	  Assert.assertNotNull("The result shouldn't be null", dataServerManager.getServerDataToComImpl().getUsersByGame(idGame));
      Assert.assertEquals("The list size should be equals to 4", 4, dataServerManager.getServerDataToComImpl().getUsersByGame(idGame).size());
      Assert.assertTrue("The list should contain the user " + blackPlayer.toString(), dataServerManager.getServerDataToComImpl().getUsersByGame(idGame).contains(blackPlayer));
      Assert.assertTrue("The list should contain the user " + whitePlayer.toString(), dataServerManager.getServerDataToComImpl().getUsersByGame(idGame).contains(whitePlayer));
      Assert.assertTrue("The list should contain the user " + observer1.toString(), dataServerManager.getServerDataToComImpl().getUsersByGame(idGame).contains(observer1));
      Assert.assertTrue("The list should contain the user " + observer2.toString(), dataServerManager.getServerDataToComImpl().getUsersByGame(idGame).contains(observer2));
	}
}