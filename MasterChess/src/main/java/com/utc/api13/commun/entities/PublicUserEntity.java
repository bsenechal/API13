
package com.utc.api13.commun.entities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.exceptions.TechnicalException;

public class PublicUserEntity extends AUserEntity {

    private static final long serialVersionUID = 7421076183167325143L;
    private byte[] image;
    private GameEntity observedGame;
    private boolean allowedToChat;
    private static final Logger LOGGER = Logger.getLogger(PublicUserEntity.class);

    public PublicUserEntity() {
        allowedToChat = false;
    }

    public PublicUserEntity(String login, String password) {
        super(login, password);
        allowedToChat = false;
    }

    /**
     * Creates a public user from a private user
     * 
     * @param privateUser
     *            the private user
     * @throws TechnicalException
     *             technical error
     */
    public PublicUserEntity(PrivateUserEntity privateUser) throws TechnicalException {
        setId(privateUser.getId());
        setLogin(privateUser.getLogin());
        setFirstName(privateUser.getFirstName());
        setLastName(privateUser.getLastName());
        setNbLost(privateUser.getNbLost());
        setNbPlayed(privateUser.getNbPlayed());
        setNbWon(privateUser.getNbWon());

        // extract bytes from image
        BufferedImage bufferedImage;
        if (privateUser.getImagePath() != null) {
            try {

                bufferedImage = ImageIO.read(new File(privateUser.getImagePath()));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", baos);

                byte[] res = baos.toByteArray();
                setImage(res);

            } catch (IOException e) {
                LOGGER.error("[PublicUserEntity][Constructor] " + e.getMessage(), e);
            }
        }
        setAllowedToChat(false);
    }

    /**
     * @return the image
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image
     *            the image to set
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
     * @param observedGame
     *            the observedGame to set
     */
    public void setObservedGames(GameEntity observedGame) {
        this.observedGame = observedGame;
    }

    /**
     * 
     * @return true if and only if the user is allowed to chat during the game
     */
    public boolean isAllowedToChat() {
        return allowedToChat;
    }

    /**
     * @param allowedToChat
     *            true if and only if user is allowed to chat during the game
     */
    public void setAllowedToChat(boolean allowedToChat) {
        this.allowedToChat = allowedToChat;
    }
}
