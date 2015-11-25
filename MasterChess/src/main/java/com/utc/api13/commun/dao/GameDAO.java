package com.utc.api13.commun.dao;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.exceptions.TechnicalException;

public class GameDAO extends GenericDAOImpl<GameEntity> {

	public GameDAO() throws TechnicalException {
		super("game");
	}

}
