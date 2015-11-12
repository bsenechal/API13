package com.utc.api13.commun;

import java.util.Date;

public interface MoveEntity extends DataEntity {
    Date getDate();

    String getUid();

    GameEntity getGame();

    PositionEntity getFromPosition();

    PositionEntity getToPositino();

    PieceEntity getPiece();

}
