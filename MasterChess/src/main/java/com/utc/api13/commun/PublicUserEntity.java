package com.utc.api13.commun;

public interface PublicUserEntity extends UserEntity {
    byte getImage();

    void setImage(final byte image);

    void getObservedGames();

    void setObservedGames();

}
