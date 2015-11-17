package com.utc.api13.commun.entities;

import java.util.Date;

public class MoveEntity extends DataEntity {

    private static final long serialVersionUID = -2280973208742426855L;
    private Date date;
    private GameEntity game;
    private PositionEntity fromPosition;
    private PositionEntity toPosition;
    private PieceEntity piece;
    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }
    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * @return the game
     */
    public GameEntity getGame() {
        return game;
    }
    /**
     * @param game the game to set
     */
    public void setGame(GameEntity game) {
        this.game = game;
    }
    /**
     * @return the fromPosition
     */
    public PositionEntity getFromPosition() {
        return fromPosition;
    }
    /**
     * @param fromPosition the fromPosition to set
     */
    public void setFromPosition(PositionEntity fromPosition) {
        this.fromPosition = fromPosition;
    }
    /**
     * @return the toPosition
     */
    public PositionEntity getToPosition() {
        return toPosition;
    }
    /**
     * @param toPosition the toPosition to set
     */
    public void setToPosition(PositionEntity toPosition) {
        this.toPosition = toPosition;
    }
    /**
     * @return the piece
     */
    public PieceEntity getPiece() {
        return piece;
    }
    /**
     * @param piece the piece to set
     */
    public void setPiece(PieceEntity piece) {
        this.piece = piece;
    }
}
