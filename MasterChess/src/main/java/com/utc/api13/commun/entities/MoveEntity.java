package com.utc.api13.commun.entities;

import java.util.Date;
import java.util.UUID;

public class MoveEntity extends ADataEntity {

	private static final long serialVersionUID = -2280973208742426855L;
	private Date date;
	private PositionEntity fromPosition;
	private PositionEntity toPosition;
	private APieceEntity piece;
	private UUID userID;
	private UUID gameID;

	/**
	 * @author ulyss_000
	 * @param date
	 * @param game
	 * @param fromPosition
	 * @param toPosition
	 * @param piece
	 * @param activeUser
	 */
	public MoveEntity(Date date, PositionEntity fromPosition, PositionEntity toPosition, APieceEntity piece,
			UUID userID, UUID gameID) {
		super();
		this.date = date;
		this.fromPosition = fromPosition;
		this.toPosition = toPosition;
		this.piece = piece;
		this.userID = userID;
		this.gameID = gameID;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the fromPosition
	 */
	public PositionEntity getFromPosition() {
		return fromPosition;
	}

	/**
	 * @param fromPosition
	 *            the fromPosition to set
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
	 * @param toPosition
	 *            the toPosition to set
	 */
	public void setToPosition(PositionEntity toPosition) {
		this.toPosition = toPosition;
	}

	/**
	 * @return the piece
	 */
	public APieceEntity getPiece() {
		return piece;
	}

	/**
	 * @param piece
	 *            the piece to set
	 */
	public void setPiece(APieceEntity piece) {
		this.piece = piece;
	}

	/**
	 * @return the userID
	 */
	public UUID getUserID() {
		return userID;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	public void setUserID(UUID userID) {
		this.userID = userID;
	}

	/**
	 * @return the gameID
	 */
	public UUID getGameID() {
		return gameID;
	}

	/**
	 * @param gameID
	 *            the gameID to set
	 */
	public void setGameID(UUID gameID) {
		this.gameID = gameID;
	}

}
