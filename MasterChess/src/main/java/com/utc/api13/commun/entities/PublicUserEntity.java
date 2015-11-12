package com.utc.api13.commun.entities;

import java.util.List;

public class PublicUserEntity extends UserEntity {
	
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
