package com.utc.api13.commun.entities;

import com.utc.api13.client.ihm.interfaces.IDataEntity;

public interface PositionEntity extends DataEntity {
    public String getX();

    public String getY();

    public void setY(final String y);

    public void setX(final String x);

}
