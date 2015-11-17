package com.utc.api13.commun.entities;

public class PublicUserEntity extends UserEntity {

    private static final long serialVersionUID = 7421076183167325143L;
    private byte Image;
	private GameEntity ObservedGames;
	
	/**
	 * @return the image
	 */
	public byte getImage() {
		return Image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(byte image) {
		Image = image;
	}
	/**
	 * @return the observedGames
	 */
	public GameEntity getObservedGames() {
		return ObservedGames;
	}
	/**
	 * @param observedGames the observedGames to set
	 */
	public void setObservedGames(GameEntity observedGames) {
		ObservedGames = observedGames;
	}
	


}
