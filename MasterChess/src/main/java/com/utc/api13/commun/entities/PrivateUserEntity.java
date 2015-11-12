package com.utc.api13.commun.entities;

public interface PrivateUserEntity extends UserEntity {
    String getPassword();

    void setPpassword(final String password);

    String getImagePath();

    void setImagePath(final String imagePath);

    GameEntity getObservedGame();

    void setObservedGame(final GameEntity observedGame);

    GameEntity getGame();

}
