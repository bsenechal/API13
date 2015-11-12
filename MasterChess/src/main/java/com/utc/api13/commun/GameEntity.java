package com.utc.api13.commun;

import java.util.Date;

public interface GameEntity extends DataEntity {
    Date getCreationDate();

    boolean isObservable();

    boolean isChattable();

    Date getLimit();

    UserEntity getWhitePlayer();

    UserEntity getBlackPlayer();

    boolean isFinished();

    void setCreationDate(final Date creationDate);

    void setObservable(final boolean observable);

    void setChattable(final boolean chattable);

    void setLimit(final Date limit);

    void setWhitePlayer(final UserEntity whitePlayer);

    void setBlackPlayer(final UserEntity blackPlayer);

    void setFinished(final boolean finished);

    ChessboardEntity getChessboard();

    void setChessboard(final ChessboardEntity chessboard);

    void getObservers();

}
