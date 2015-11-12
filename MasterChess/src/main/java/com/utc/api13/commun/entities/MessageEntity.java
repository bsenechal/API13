package com.utc.api13.commun.entities;

import java.util.Date;

import com.utc.api13.client.ihm.interfaces.IDataEntity;

public class MessageEntity extends IDataEntity {
    private Date d
    
    public String getUid();
    
    public Date getDate();
    
    public String getText();
    
    public void setUid(final String uid);
    
    public void setDate(final Date date);
    
    public void setText(final String text);
    
    public void setGame(final GameEntity game);
    
    public GameEntity getGame();
    
    public void IMessageEntity();

}
