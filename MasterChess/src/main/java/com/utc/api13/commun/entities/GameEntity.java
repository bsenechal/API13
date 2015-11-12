package com.utc.api13.commun.entities;

import java.util.Date;

import com.utc.api13.client.ihm.interfaces.IDataEntity;

public class GameEntity extends DataEntity {
    private Date creationDate;
    private Boolean idOservable;
    private Boolean isChattable;
    private Date limit;
    private UserEntity whitePlayer;
    private UserEntity blackPlayer;
    
    
    public Date getCreationDate();
    
    public boolean isObservable();
    
    public boolean isChattable();
    
    public Date getLimit();
    
    public UserEntity getWhitePlayer();
    
    public UserEntity getBlackPlayer();
    
    public boolean isFinished();
    
    public void setCreationDate(final Date creationDate);
    
    public void setObservable(final boolean observable);
    
    public void setChattable(final boolean chattable);
    
    public void setLimit(final Date limit);
    
    public void setWhitePlayer(final UserEntity whitePlayer);
    
    public void setBlackPlayer(final UserEntity blackPlayer);
    
    public void setFinished(final boolean finished);
    
    public ChessboardEntity getChessboard();
    
    public void setChessboard(final ChessboardEntity chessboard);
    
    public void getObservers();

}
