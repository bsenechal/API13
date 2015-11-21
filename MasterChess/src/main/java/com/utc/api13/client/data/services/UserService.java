package com.utc.api13.client.data.services;

import java.util.ArrayList;
import java.util.List;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.Erreur;
import com.utc.api13.commun.dao.UserDAO;
import com.utc.api13.commun.dao.interfaces.IGenericDAO;
import com.utc.api13.commun.entities.AUserEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.ErrorTypeEnum;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;
import com.utc.api13.commun.utils.ImageUtils;

public class UserService extends ADataService<PrivateUserEntity> {

	
	public void connect(String login, String password) throws FunctionalException, TechnicalException{
		PrivateUserEntity user = getByLoginAndPassword(login, password);
		if(user == null) {
			List<Erreur> erreurs = new ArrayList<>();
			erreurs.add(new Erreur(ErrorTypeEnum.LOGIN_FAILED));
			throw new FunctionalException(erreurs);
		}
		
		PrivateUserEntity privateUser = (PrivateUserEntity) user;
		//Create a public user from the private user
		PublicUserEntity publicUser = (PublicUserEntity) (AUserEntity)user;
		//extract bytes from image
		publicUser.setImage(ImageUtils.extractBytes(privateUser.getImagePath()));
		//notify the com module
		//TODO: interfaceFromCom.notifyConnection(userPublic)
	}
	
	/**
	 * Checks if the login and password are valid
	 * @param login login
	 * @param password password
	 * @throws FunctionalException when login and/or password are not valid
	 * @throws TechnicalException technical exception
	 */
	public PrivateUserEntity getByLoginAndPassword(String login, String password) throws TechnicalException{
		return getAll().stream().filter(entity -> entity instanceof PrivateUserEntity).filter(entity -> entity.getLogin().equals(login) && ((PrivateUserEntity)entity).getPassword().equals(password)).findFirst().orElse(null);
	}


	@Override
	protected IGenericDAO<PrivateUserEntity> getDao() throws TechnicalException {
		return new UserDAO();
	}

	@Override
	protected void validateInstance(PrivateUserEntity entity) throws TechnicalException, FunctionalException {
	
		
	}
}
