package com.utc.api13.commun;

public interface PositionEntity extends DataEntity {
    String getX();

    String getY();

    void setY(final String y);

    void setX(final String x);

}
