package com.utc.api13.client.data.services;

import java.util.ArrayList;
import java.util.List;

import com.utc.api13.client.com.interfaces.IClientComToData;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.Erreur;
import com.utc.api13.commun.dao.UserDAO;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.ErrorTypeEnum;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

public class UserService{

	private IClientComToData comInterface;
	private final static UserDAO userDAO = new UserDAO();
	
	public UserService(IClientComToData comInterface){
		this.comInterface = comInterface;
	}
	
	public void connect(String login, String password) throws FunctionalException, TechnicalException{
		PrivateUserEntity privateUser = getByLoginAndPassword(login, password);
		if(privateUser == null) {
			List<Erreur> erreurs = new ArrayList<>();
			erreurs.add(new Erreur(ErrorTypeEnum.LOGIN_FAILED));
			throw new FunctionalException(erreurs);
		}
		//Create a public user from the private user
		PublicUserEntity publicUser = new PublicUserEntity(privateUser);
		//notify the com module
		comInterface.notifyConnection(publicUser);
	}
	
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
	 * Delete the given user in files
	 * @param user the user to delete
	 * @throws TechnicalException when deletion fails
	 */
	public void delete(PrivateUserEntity user) throws TechnicalException{
		userDAO.delete(user);
	}

}
