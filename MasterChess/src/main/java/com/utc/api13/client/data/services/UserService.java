package com.utc.api13.client.data.services;



import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.Erreur;
import com.utc.api13.commun.dao.UserDAO;
import com.utc.api13.commun.enumerations.ErrorTypeEnum;
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
	public PrivateUserEntity getByLoginAndPassword(final String login, final String password) throws TechnicalException{
		return userDAO.getByLoginAndPassword(login, password);
	}

	/**
	 * Create a new entity
	 * @param entity entity to create
	 * @return entity created
	 * @throws TechnicalException technical exception
	 * @throws FunctionalException functional exception
	 */
	public void save(final PrivateUserEntity user) throws TechnicalException, FunctionalException{
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
	public void delete(final PrivateUserEntity user) throws TechnicalException{
		userDAO.delete(user);
	}

	/**
	 * Delete the user with id userId from the files
	 * Works in any case
	 * @param userId id of user to delete
	 */
	public void deleteById(final UUID userId) {
		userDAO.deleteById(userId);		
	}
	
	/**
	 * Reads the user from file and saves it
	 * @param file file
	 * @throws FunctionalException when the stored object is not a private user
	 * @throws TechnicalException exception when reading file
	 */
	public void importProfile(File file, boolean force) throws FunctionalException, TechnicalException {
		//Read object in file and check if it is a private user with an id
		PrivateUserEntity privateUser = userDAO.getUserFromFile(file);
		//Check if the user already exists
		//throw an exception when the user exists already and the force mode is not activated
		if(userDAO.getById(privateUser.getId()) != null && !force) {
			List<Erreur> erreurs = new ArrayList<>();
			erreurs.add(new Erreur(ErrorTypeEnum.DUPLICATED_USER));
			throw new FunctionalException(erreurs);
		}
		//save user
		save(privateUser);
	}
	
	/**
	 * Gets the storage file of given user
	 * @param user user
	 * @return file
	 */
	public File exportProfile(PrivateUserEntity user) {
		URL url = UserService.class.getClassLoader().getResource("user" + File.separator + user.getLogin() + "_" + user.getId());
		return new File(url.getFile());
	}

}
