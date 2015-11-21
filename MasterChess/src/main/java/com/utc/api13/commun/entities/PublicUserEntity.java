package com.utc.api13.commun.entities;

public class PublicUserEntity extends AUserEntity {

    private static final long serialVersionUID = 7421076183167325143L;
    private byte[] Image;
	private GameEntity ObservedGame;
	
	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return Image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		Image = image;
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
	public void setObservedGames(GameEntity observedGame) {
		ObservedGame = observedGame;
	}
	


}
