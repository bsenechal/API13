package com.utc.api13.client.data.services;



import java.util.UUID;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.dao.UserDAO;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

public class UserService{
	private final static UserDAO userDAO = new UserDAO();
	
	
	/**
	 * Returns the found user or null when nothing found
	 * @param login login
	 * @param password password
	 * @throws TechnicalException technical exception
	 */
	public PrivateUserEntity getByLoginAndPassword(String login, String password) throws TechnicalException{
		return userDAO.getByLoginAndPassword(login, password);
	}

	/**
	 * Create a new entity
	 * @param entity entity to create
	 * @return entity created
	 * @throws TechnicalException technical exception
	 * @throws FunctionalException functional exception
	 */
	public void save(PrivateUserEntity user) throws TechnicalException, FunctionalException{
		validateInstance(user);
		userDAO.save(user);
	}
	

	/**
	 * Needed validations to do before creation and update of an user
	 * @param u user to create or update
	 * @throws TechnicalException technical exception
	 * @throws FunctionalException functional exception
	 */
	private void validateInstance(final PrivateUserEntity u) throws TechnicalException, FunctionalException{
		//TODO: needed validation before saving
	};
	
	

	/**
	 * Delete the given user from files
	 * To use only if the user's login hasn't changed since the last update
	 * @param user the user to delete
	 * @throws TechnicalException when deletion fails
	 */
	public void delete(PrivateUserEntity user) throws TechnicalException{
		userDAO.delete(user);
	}

	/**
	 * Delete the user with id userId from the files
	 * Works in any case
	 * @param userId id of user to delete
	 */
	public void deleteById(UUID userId) {
		userDAO.deleteById(userId);
		
	}

}
