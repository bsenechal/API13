package com.utc.api13.commun.dao;

import com.utc.api13.commun.entities.UserEntity;
import com.utc.api13.commun.exceptions.TechnicalException;

public class UserDAO extends GenericDAOImpl<UserEntity> {

	public UserDAO() throws TechnicalException {
		super("user");
	}

}
