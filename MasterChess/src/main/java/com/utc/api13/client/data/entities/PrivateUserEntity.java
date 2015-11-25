package com.utc.api13.client.data.entities;

import java.util.List;

import com.utc.api13.commun.entities.AUserEntity;
import com.utc.api13.commun.entities.GameEntity;

public class PrivateUserEntity extends AUserEntity {
	
    private static final long serialVersionUID = -5408789931031693916L;
    private String password;
	private String imagePath;
	private List<GameEntity> observedGames;
	private GameEntity game;
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	/**
	 * @return the observedGame
	 */
	public List<GameEntity> getObservedGames() {
		return observedGames;
	}
	/**
	 * @param observedGame the observedGame to set
	 */
	public void setObservedGames(List<GameEntity> observedGames) {
		this.observedGames = observedGames;
	}
	/**
	 * @return the game
	 */
	public GameEntity getGame() {
		return game;
	}
	/**
	 * @param game the game to set
	 */
	public void setGame(GameEntity game) {
		this.game = game;
	}
	
}
