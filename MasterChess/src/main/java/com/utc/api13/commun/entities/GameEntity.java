package com.utc.api13.commun.entities;

import java.util.Date;
import java.util.List;

public class GameEntity extends ADataEntity {

    private static final long serialVersionUID = -959030856925179648L;
    private Date creationDate;
    private Boolean idOservable;
    private Boolean isChattable;
    private Date limit;
    private UserEntity whitePlayer;
    private UserEntity blackPlayer;
    private Boolean isFinished;
    private ChessboardEntity chessboardEntity;
    private List<UserEntity> observers;
    
    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }
    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    /**
     * @return the idOservable
     */
    public Boolean getIdOservable() {
        return idOservable;
    }
    /**
     * @param idOservable the idOservable to set
     */
    public void setIdOservable(Boolean idOservable) {
        this.idOservable = idOservable;
    }
    /**
     * @return the isChattable
     */
    public Boolean getIsChattable() {
        return isChattable;
    }
    /**
     * @param isChattable the isChattable to set
     */
    public void setIsChattable(Boolean isChattable) {
        this.isChattable = isChattable;
    }
    /**
     * @return the limit
     */
    public Date getLimit() {
        return limit;
    }
    /**
     * @param limit the limit to set
     */
    public void setLimit(Date limit) {
        this.limit = limit;
    }
    /**
     * @return the whitePlayer
     */
    public UserEntity getWhitePlayer() {
        return whitePlayer;
    }
    /**
     * @param whitePlayer the whitePlayer to set
     */
    public void setWhitePlayer(UserEntity whitePlayer) {
        this.whitePlayer = whitePlayer;
    }
    /**
     * @return the blackPlayer
     */
    public UserEntity getBlackPlayer() {
        return blackPlayer;
    }
    /**
     * @param blackPlayer the blackPlayer to set
     */
    public void setBlackPlayer(UserEntity blackPlayer) {
        this.blackPlayer = blackPlayer;
    }
    /**
     * @return the isFinished
     */
    public Boolean getIsFinished() {
        return isFinished;
    }
    /**
     * @param isFinished the isFinished to set
     */
    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }
    /**
     * @return the chessboardEntity
     */
    public ChessboardEntity getChessboardEntity() {
        return chessboardEntity;
    }
    /**
     * @param chessboardEntity the chessboardEntity to set
     */
    public void setChessboardEntity(ChessboardEntity chessboardEntity) {
        this.chessboardEntity = chessboardEntity;
    }
    /**
     * @return the observers
     */
    public List<UserEntity> getObservers() {
        return observers;
    }
    /**
     * @param observers the observers to set
     */
    public void setObservers(List<UserEntity> observers) {
        this.observers = observers;
    }
}
