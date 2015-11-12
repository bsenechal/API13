package com.utc.api13.commun;

public interface PieceEntity extends DataEntity {
    PieceColorEnum getColor();

    void setColor(final PieceColorEnum color);

}
