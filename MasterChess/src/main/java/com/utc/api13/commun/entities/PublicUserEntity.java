package com.utc.api13.commun.entities;

public class PublicUserEntity extends AUserEntity {

    private static final long serialVersionUID = 7421076183167325143L;
    private byte[] image;
	private GameEntity observedGame;
	
	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}
	/**
	 * @return the observedGame
	 */
	public GameEntity getObservedGame() {
		return observedGame;
	}
	/**
	 * @param observedGame the observedGame to set
	 */
	public void setObservedGames(GameEntity observedGame) {
		this.observedGame = observedGame;
	}
	


}
