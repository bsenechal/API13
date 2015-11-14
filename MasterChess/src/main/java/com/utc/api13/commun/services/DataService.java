package com.utc.api13.commun.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.utc.api13.commun.dao.GenericDAO;
import com.utc.api13.commun.dao.impl.GenericDAOImpl;
import com.utc.api13.commun.entities.DataEntity;
import com.utc.api13.commun.exceptions.DataAccessException;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

/**
 * Classe abstraite qui définit les méthodes de base des services
 * @author Amstrong
 *
 * @param <T> un entity
 */
public abstract class DataService<T extends DataEntity> {

	/**
	 * 
	 * @return the data access object of the Service
	 */
	protected GenericDAO<T> getDao(){
		return new GenericDAOImpl<T>();
	}
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
		if(map.get(true) == null) {
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
	 * @throws DataAccessException error when accessing to data
	 */
	public Optional<T> getById(UUID id) throws DataAccessException {
		return getAll().stream().filter(entity -> entity.getId().equals(id)).findFirst();
	}
	
	/**
	 * Delte the given entity in file
	 * @param entity entity to destroy
	 * @throws DataAccessException error when accessing to data
	 */
	public void delete(T entity) throws DataAccessException{
		//TODO: Do we erase the entity from all files???
		List<T> entities = getAll();
		entities.removeIf(e -> e.getId().equals(entity.getId()));
		getDao().updateAll(entities);
	}
	
	/**
	 * Delete all instances in file
	 * @throws DataAccessException error when accessing to data
	 */
	public void deleteAll() throws DataAccessException {
		getDao().deleteAll();
	}
	
	/**
	 * get all instances in file
	 * @return List of found objects
	 * @throws DataAccessException error when accessing to data
	 */
	public List<T> getAll() throws DataAccessException{
		return getDao().findAll();
	}
}
