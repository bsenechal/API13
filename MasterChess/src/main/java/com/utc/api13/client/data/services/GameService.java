package com.utc.api13.client.data.services;

import java.util.List;
import java.util.UUID;
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

	/**
	 * Checks wheter or not the user is an observer of this game
	 * @param game game
	 * @param userId id of the user to check
	 * @return true if and only if the user is an observer of the game
	 */
	public boolean isObserver(GameEntity game, UUID userId) {
		return game.getObservers().stream().anyMatch(u -> userId.equals(u.getId()));
	}
}

