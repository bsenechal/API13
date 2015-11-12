package com.utc.api13.commun.entities;

public interface PublicUserEntity extends UserEntity {
    byte getImage();

    void setImage(final byte image);

    void getObservedGames();

    void setObservedGames();

}
