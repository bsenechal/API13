package com.utc.api13.client.data.services;

import org.apache.log4j.Logger;

import com.utc.api13.client.data.services.ADataService;
import com.utc.api13.commun.entities.ADataEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

import junit.framework.TestCase;

/**
 * test class for the the abstract class DataService
 * @author Amstrong
 *
 * @param <T>
 */
public abstract class DataServiceTest<T extends ADataEntity> extends TestCase{

	protected abstract T getEntityWithoutId();
	
	protected abstract ADataService<T> getService();
	
	private final Logger LOG = Logger.getLogger(getClass());
    
	protected Logger getLOG() {
		return LOG;
	}

	/**
	 * Tests the save method
	 */
	public void testSave() {
		T newEntity = getEntityWithoutId();
		try {
			//Save into file
			newEntity = getService().save(newEntity);
			//Check the return value of method
			assertNotNull(newEntity);
			assertNotNull(newEntity.getId());
			//Get from file the stored entity
			T foundEntity = getService().getById(newEntity.getId());
			//Check values
			assertNotNull(foundEntity);
			assertEquals(foundEntity.getId(), newEntity.getId());
			//Erase from file to prevent problems for the other unit tests
			getService().delete(foundEntity);
			
		} catch (TechnicalException | FunctionalException e) {
			LOG.error("Error during save test", e);
		} 
	}
	
	/**
	 * Tests the getById method
	 */
	public void testGetById() {
		//Create an entity
		//Get by id and check the values
		//Delete the created entity
	}
	/**
	 * Tests the delete method
	 */
	public void testDelete() {
		//Create a new entity
		//Delete it
		//Check if the entity has been deleted successfullly
	}
	
	/**
	 * Tests the deleteAll method
	 */
	public void testDeleteAll() {
		//Create a list of entities
		//Save all of them
		//Delete all and check if the deletions have gone correctly
	}
	
	/**
	 * Tests the getAll method
	 */
	public void testGetAll() {
		//Create a list of entities
		//Save all of them
		//Get all and check the returned values
		//Delete all created entities
	}
	/**
	 * Tests the update method
	 */
	public void testUpdate() {
		//Save a new entity
		//Modify all of the properties of the object but the id
		//Find the entity by id
		//Check the returned values
		//Delete the created entity
		
	}
	
}
