package com.utc.api13.client.data.services;

import java.util.List;
import java.util.stream.Collectors;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.exceptions.DataAccessException;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

public class GameService{

   
	/**
	 * 
	 * @param user user
	 * @return Returns the list of games observed by a user 
	 * @throws DataAccessException data access exception
	 */
	public List<GameEntity> getObservedGames(PrivateUserEntity user) throws TechnicalException{
		return user.getSavedGames().stream().filter(g -> !(g.getBlackPlayer().getId().equals(user.getId())||g.getWhitePlayer().getId().equals(user.getId()))).collect(Collectors.toList());
	}

	protected void validateInstance(GameEntity entity) throws TechnicalException, FunctionalException {
		// TODO Auto-generated method stub
		
	}

}
