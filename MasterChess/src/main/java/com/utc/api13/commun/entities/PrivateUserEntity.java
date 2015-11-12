package com.utc.api13.commun.entities;

public class PrivateUserEntity extends UserEntity {
	
	private String Password;
	private String ImagePath;
	private GameEntity ObservedGame;
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
	public GameEntity getObservedGame() {
		return ObservedGame;
	}
	/**
	 * @param observedGame the observedGame to set
	 */
	public void setObservedGame(GameEntity observedGame) {
		ObservedGame = observedGame;
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
