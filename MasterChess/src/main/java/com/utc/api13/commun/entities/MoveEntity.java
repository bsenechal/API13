package com.utc.api13.commun.entities;

import java.util.Date;

import com.utc.api13.client.ihm.interfaces.IDataEntity;

public interface MoveEntity extends DataEntity {
    public Date getDate();
    
    public String getUid();
    
    public GameEntity getGame();
    
    public PositionEntity getFromPosition();
    
    public PositionEntity getToPositino();
    
    public PieceEntity getPiece();

}
