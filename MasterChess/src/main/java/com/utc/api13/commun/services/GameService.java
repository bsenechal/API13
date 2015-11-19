package com.utc.api13.commun.services;

import java.util.List;
import java.util.stream.Collectors;

import com.utc.api13.commun.dao.GameDAO;
import com.utc.api13.commun.dao.UserDAO;
import com.utc.api13.commun.dao.interfaces.IGenericDAO;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.UserEntity;
import com.utc.api13.commun.exceptions.DataAccessException;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

public class GameService extends ADataService<GameEntity> {

	/**
	 * 
	 * @param user user
	 * @return Returns the list of games observed by a user 
	 * @throws DataAccessException data access exception
	 */
	public List<GameEntity> getByUser(UserEntity user) throws TechnicalException{
		return getAll().stream().filter(game -> game.getObservers().contains(user)).collect(Collectors.toList());
	}

	@Override
	protected void validateInstance(GameEntity entity) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected IGenericDAO<GameEntity> getDao() throws TechnicalException {
		return new GameDAO();
	}
}
