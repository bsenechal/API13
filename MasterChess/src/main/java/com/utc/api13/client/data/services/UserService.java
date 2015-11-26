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
		PrivateUserEntity user = getByLogin(login);
		if(user == null || !user.getPassword().equals(password)) {
			List<Erreur> erreurs = new ArrayList<>();
			erreurs.add(new Erreur(ErrorTypeEnum.LOGIN_FAILED));
			throw new FunctionalException(erreurs);
		}
		
		PrivateUserEntity privateUser = (PrivateUserEntity) user;
		//Create a public user from the private user
		PublicUserEntity publicUser = new PublicUserEntity(privateUser);
		//notify the com module
		comInterface.notifyConnection(publicUser);
	}
	
	/**
	 * Checks if the login and password are valid
	 * @param login login
	 * @param password password
	 * @throws FunctionalException when login and/or password are not valid
	 * @throws TechnicalException technical exception
	 */
	public PrivateUserEntity getByLogin(String login) throws TechnicalException{
		//TODO check field existence
		return null;
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
	 * Needed validations to do before creation and update
	 * @param entity entity to modify or create
	 * @throws TechnicalException technical exception
	 * @throws FunctionalException functional exception
	 */
	protected void validateInstance(final PrivateUserEntity u) throws TechnicalException, FunctionalException{
		//TODO
	};
	
	

	/**
	 * Delte the given entity in file
	 * @param entity entity to destroy
	 * @throws TechnicalException 
	 */
	public void delete(PrivateUserEntity user) throws TechnicalException{
		userDAO.delete(user.getLogin());
	}

}
