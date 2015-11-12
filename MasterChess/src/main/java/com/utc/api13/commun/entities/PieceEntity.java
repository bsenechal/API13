package com.utc.api13.commun.entities;

public interface PieceEntity extends DataEntity {
    PieceColorEnum getColor();

    void setColor(final PieceColorEnum color);

}
