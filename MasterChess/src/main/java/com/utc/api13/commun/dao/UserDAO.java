package com.utc.api13.commun.dao;

import com.utc.api13.commun.entities.AUserEntity;
import com.utc.api13.commun.exceptions.TechnicalException;

public class UserDAO extends GenericDAOImpl<AUserEntity> {

	public UserDAO() throws TechnicalException {
		super("user");
	}

}
