package com.utc.api13.client.data.services;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.beanutils.PropertyUtils;
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
 * @param <T> le type du bean à gérer
 */
public abstract class DataServiceTest<T extends ADataEntity> extends TestCase{

	/**
	 *
	 * @return Returns an entity without an id <br/> Used in belowing methods to create a new entity ans save to the files
	 */
	protected abstract T getEntityWithoutId();
	
	/**
	 * Modifies the given entity without updating it into storage files
	 * @param entity entity to modify
	 * @return the modified entity
	 */
	protected abstract T modifyEntity(T entity);
	
	protected abstract ADataService<T> getService();
	
	protected abstract List<T> getEntitiesWithoutIds();
	
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
			
		} catch (TechnicalException | FunctionalException e) {
			LOG.error("Error during save test", e);
		} finally {
			//Delete the created entity
			if(newEntity.getId() != null) {
				try {
					getService().delete(newEntity);
				} catch (TechnicalException e) {
					LOG.error(e);
				}
			}
		}
	}
	
	/**
	 * Tests the getById method
	 */
	public void testGetById() {
		T newEntity = getEntityWithoutId();	
		try {
			//Create an entity
			newEntity = getService().save(newEntity);
			//Get by id and check the values
			T foundEntity = getService().getById(newEntity.getId());
			assertNotNull(foundEntity);
			for(PropertyDescriptor prop : PropertyUtils.getPropertyDescriptors(foundEntity.getClass())) {
				if (prop.getReadMethod() != null && !"class".equals(prop.getName())) {
					assertEquals(prop.getReadMethod().invoke(newEntity), prop.getReadMethod().invoke(foundEntity));
				}
			}
			
		} catch (TechnicalException | FunctionalException | IllegalAccessException | InvocationTargetException e) {
			LOG.error("Error", e);
		} finally {
			//Delete the created entity
			if(newEntity.getId() != null) {
				try {
					getService().delete(newEntity);
				} catch (TechnicalException e) {
					LOG.error(e);
				}
			}
		}
				
	}
	/**
	 * Tests the delete method
	 */
	public void testDelete() {
		//Create a new entity
		T newEntity = getEntityWithoutId();
		try {
			newEntity = getService().save(newEntity);
		} catch (TechnicalException | FunctionalException e) {
			LOG.error(e);
		} finally {
			if(newEntity.getId() != null) {
				try {
					//Delete it
					getService().delete(newEntity);
					//Check if the entity has been successfullly deleted
					T foundEntity = getService().getById(newEntity.getId());
					assertNull(foundEntity);
				} catch (TechnicalException e) {
					LOG.error(e);
				}
			}
		}
		
		
		
	}
	
	/**
	 * Tests the deleteAll method
	 */
	public void testDeleteAll() {
		//Create a list of entities
		List<T> entities = getEntitiesWithoutIds();
		//Save all of them
		try {
			for(T entity:entities) {				
				getService().save(entity);				
			}
		} catch (TechnicalException | FunctionalException e) {
			LOG.error("Error while saving", e);
		} finally {
			//Delete all and check if the deletions have gone correctly
			try {
				getService().deleteAll();
				assertEquals(0, getService().getAll().size());
			} catch (TechnicalException e) {
				LOG.error("", e);
			}
		}
		
	}
	
	/**
	 * Tests the getAll method
	 */
	public void testGetAll() {
		//Create a list of entities
		List<T> entities = getEntitiesWithoutIds();
		List<T> createdEntities = new ArrayList<>();
		//Save all of them
		for(T entity:entities) {
			try {
				entity = getService().save(entity);
				createdEntities.add(entity);
			} catch (TechnicalException | FunctionalException e) {
				LOG.error("Error while saving", e);
			} finally{
				if(entity.getId() != null) {
					try {
						getService().delete(entity);
					} catch (TechnicalException e) {
						LOG.error("Error while deleting", e);
					}
				}
			}
		}
		//Get all and check the returned values
		try {
			List<T> foundEntities = getService().getAll();
			if(!(foundEntities.size() == createdEntities.size() && foundEntities.containsAll(createdEntities))){
				LOG.error("getAll test failed");
			}
		} catch (TechnicalException e) {
			LOG.error("Error", e);
		} finally {
			//Delete all created entities
			try {
				getService().deleteAll();
			} catch (TechnicalException e) {
				LOG.error("Error while deleting", e);
			}
		}
		
	}
	

	/**
	 * Tests the update method
	 */
	public void testUpdate() {
		//Save a new entity
		T newEntity = getEntityWithoutId(), modifiedEntity;
		try {
			newEntity = getService().save(newEntity);
			//Modify all of the properties of the object but the id
			modifiedEntity = modifyEntity(newEntity);
			T foundEntity = getService().update(modifiedEntity);
			//Check the returned values
			for(PropertyDescriptor prop : PropertyUtils.getPropertyDescriptors(newEntity.getClass())) {
				if (prop.getReadMethod() != null && !"class".equals(prop.getName())) {
					assertEquals(prop.getReadMethod().invoke(foundEntity), prop.getReadMethod().invoke(modifiedEntity));
				}
			}
		} catch (TechnicalException | FunctionalException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LOG.error(e);
		} finally {
			if(newEntity.getId() != null) {
				try {
					//Delete the created entity
					getService().delete(newEntity);
				} catch (TechnicalException e) {
					LOG.error(e);
				}
			}
		}
		
		
		
	}
	
}
