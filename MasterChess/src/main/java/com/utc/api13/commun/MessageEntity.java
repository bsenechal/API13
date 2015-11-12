package com.utc.api13.commun;

import java.util.Date;

public interface MessageEntity extends DataEntity {
    String getUid();

    Date getDate();

    String getText();

    void setUid(final String uid);

    void setDate(final Date date);

    void setText(final String text);

    void setGame(final GameEntity game);

    GameEntity getGame();

    void IMessageEntity();

}
