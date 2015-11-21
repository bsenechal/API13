package com.utc.api13.client.data.entities;

import java.util.List;

import com.utc.api13.commun.entities.AUserEntity;
import com.utc.api13.commun.entities.GameEntity;

public class PrivateUserEntity extends AUserEntity {
	
    private static final long serialVersionUID = -5408789931031693916L;
    private String Password;
	private String ImagePath;
	private List<GameEntity> ObservedGames;
	private GameEntity Game;
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}
	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return ImagePath;
	}
	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}
	/**
	 * @return the observedGame
	 */
	public List<GameEntity> getObservedGames() {
		return ObservedGames;
	}
	/**
	 * @param observedGame the observedGame to set
	 */
	public void setObservedGames(List<GameEntity> observedGames) {
		ObservedGames = observedGames;
	}
	/**
	 * @return the game
	 */
	public GameEntity getGame() {
		return Game;
	}
	/**
	 * @param game the game to set
	 */
	public void setGame(GameEntity game) {
		Game = game;
	}
	
}
