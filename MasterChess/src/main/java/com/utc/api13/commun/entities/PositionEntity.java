package com.utc.api13.commun.entities;

public interface PositionEntity extends DataEntity {
    String getX();

    String getY();

    void setY(final String y);

    void setX(final String x);

}
