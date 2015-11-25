package com.utc.api13.commun.entities;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.exceptions.TechnicalException;
import com.utc.api13.commun.utils.ImageUtils;

public class PublicUserEntity extends AUserEntity {

    private static final long serialVersionUID = 7421076183167325143L;
    private byte[] image;
	private GameEntity observedGame;
	
	public PublicUserEntity(){
		
	}
	
	/**
	 * Creates a public user from a private user
	 * @param privateUser the private user
	 * @throws TechnicalException technical error
	 */
	public PublicUserEntity(PrivateUserEntity privateUser) throws TechnicalException{
		setId(privateUser.getId());
		setLogin(privateUser.getLogin());
		setFirstName(privateUser.getFirstName());
		setLastName(privateUser.getLastName());
		setNbLost(privateUser.getNbLost());
		setNbPlayed(privateUser.getNbPlayed());
		setNbWon(privateUser.getNbWon());
		//extract bytes from image
		setImage(ImageUtils.extractBytes(privateUser.getImagePath()));
	}
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
