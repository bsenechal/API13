package com.utc.api13.client.data.services;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.utc.api13.commun.dao.interfaces.IGenericDAO;
import com.utc.api13.commun.entities.ADataEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

/**
 * Classe abstraite qui définit les méthodes de base des services
 * @author Amstrong
 *
 * @param <T> un entity
 */
public abstract class ADataService<T extends ADataEntity> {

	/**
	 * 
	 * @return the data access object of the Service
	 * @throws TechnicalException 
	 */
	protected abstract IGenericDAO<T> getDao() throws TechnicalException;
	/**
	 * Create a new entity
	 * @param entity entity to create
	 * @return entity created
	 * @throws TechnicalException technical exception
	 * @throws FunctionalException functional exception
	 */
	public T save(T entity) throws TechnicalException, FunctionalException{
		validateInstance(entity);
		validateInstanceForCreation(entity);
		return getDao().save(entity);
	}
	
	/**
	 * Update an entity
	 * @param entity entity to create
	 * @return entity created
	 * @throws TechnicalException technical exception
	 * @throws FunctionalException functional exception
	 */
	public T update(final T entity) throws FunctionalException, TechnicalException{
		validateInstance(entity);
		validateInstanceForUpdate(entity);
		Map<Boolean, List<T>> map = getAll().stream().collect(Collectors.partitioningBy(e -> e.getId().equals(entity.getId())));
		if(map.get(true) == null || map.get(true).isEmpty()) {
			throw new TechnicalException("Entity cannot be updated: It has not been created yet");
		}		
		entity.setId(map.get(true).get(0).getId());
		List<T> entities = map.get(false);
		entities.add(entity);
		getDao().updateAll(entities);
		return entity;
	}
	
	/**
	 * Needed validations to do before creation and update
	 * @param entity entity to modify or create
	 * @throws TechnicalException technical exception
	 * @throws FunctionalException functional exception
	 */
	protected abstract void validateInstance(final T entity) throws TechnicalException, FunctionalException;
	
	/**
	 * Needed validations to do before creation
	 * @param entity entity to create
	 * @throws TechnicalException technical exception
	 * @throws FunctionalException functional exception
	 */
	protected void validateInstanceForCreation(final T entity) throws TechnicalException, FunctionalException {
		//To override when needed
	}
	
	/**
	 * Needed validations to do before update
	 * @param entity entity to create
	 * @throws TechnicalException technical exception
	 * @throws FunctionalException functional exception
	 */
	protected void validateInstanceForUpdate(final T entity) throws TechnicalException, FunctionalException {
		//To override when needed
	}
	
	/**
	 * Return instance with the given UID
	 * @param id UID of instance
	 * @return found UID or null if not found
	 * @throws TechnicalException 
	 */
	public T getById(UUID id) throws TechnicalException {
		return getAll().stream().filter(entity -> entity.getId().equals(id)).findFirst().orElse(null);
	}
	
	/**
	 * Delte the given entity in file
	 * @param entity entity to destroy
	 * @throws TechnicalException 
	 */
	public void delete(T entity) throws TechnicalException{
		List<T> entities = getAll();
		entities.removeIf(e -> e.getId().equals(entity.getId()));
		getDao().updateAll(entities);
	}
	
	/**
	 * Delete all instances in file
	 * @throws TechnicalException 
	 */
	public void deleteAll() throws TechnicalException {
		getDao().deleteAll();
	}
	
	/**
	 * get all instances in file
	 * @return List of found objects
	 * @throws TechnicalException 
	 */
	public List<T> getAll() throws TechnicalException{
		return getDao().findAll();
	}
}
