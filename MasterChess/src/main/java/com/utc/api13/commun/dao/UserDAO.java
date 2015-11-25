package com.utc.api13.commun.dao;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.exceptions.TechnicalException;

public class UserDAO extends GenericDAOImpl<PrivateUserEntity> {

	public UserDAO() throws TechnicalException {
		super("user");
	}

}
