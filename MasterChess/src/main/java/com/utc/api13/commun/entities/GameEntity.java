package com.utc.api13.commun.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.Assert;

import com.utc.api13.commun.entities.pieces.KingEntity;
import com.utc.api13.commun.enumerations.GameStatusEnum;

public class GameEntity extends ADataEntity {

	private static final long serialVersionUID = -959030856925179648L;
	private Date creationDate;
	private Boolean isOservable;
	private Boolean isChattable;
	private Date limit;
	private PublicUserEntity whitePlayer;
	private PublicUserEntity blackPlayer;
	private PublicUserEntity currentPlayer;
	private Boolean isFinished;
	private ChessboardEntity chessboardEntity;
	private List<PublicUserEntity> observers;
	private List<MoveEntity> movesHistory;
	private List<APieceEntity> whitePieces;
	private List<APieceEntity> blackPieces;

	/**
	 * 
	 */
	public GameEntity() {
		super();
		this.movesHistory = new ArrayList<MoveEntity>();
		this.observers = new ArrayList<PublicUserEntity>();
		this.chessboardEntity = new ChessboardEntity();
		this.creationDate = new Date();
		this.isFinished = Boolean.FALSE;
		this.isOservable = Boolean.FALSE;
		this.isChattable = Boolean.FALSE;
		this.limit = null;
		this.whitePlayer = null;
		this.blackPlayer = null;
		this.currentPlayer = null;
	}

	/**
	 * @param idOservable
	 * @param isChattable
	 * @param limit
	 * @param whitePlayer
	 * @param blackPlayer
	 */
	public GameEntity(Boolean idOservable, Boolean isChattable, Date limit, PublicUserEntity whitePlayer,
			PublicUserEntity blackPlayer) {
		super();
		this.isOservable = idOservable;
		this.isChattable = isChattable;
		this.limit = limit;
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		this.currentPlayer = whitePlayer;
		this.movesHistory = new ArrayList<MoveEntity>();
		this.observers = new ArrayList<PublicUserEntity>();
		this.chessboardEntity = new ChessboardEntity();
		this.creationDate = new Date();
		this.isFinished = Boolean.FALSE;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the idOservable
	 */
	public Boolean getIdOservable() {
		return isOservable;
	}

	/**
	 * @param idOservable
	 *            the idOservable to set
	 */
	public void setIdOservable(Boolean idOservable) {
		this.isOservable = idOservable;
	}

	/**
	 * @return the isChattable
	 */
	public Boolean getIsChattable() {
		return isChattable;
	}

	/**
	 * @param isChattable
	 *            the isChattable to set
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
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(Date limit) {
		this.limit = limit;
	}

	/**
	 * @return the whitePlayer
	 */
	public PublicUserEntity getWhitePlayer() {
		return whitePlayer;
	}

	/**
	 * @param whitePlayer
	 *            the whitePlayer to set
	 */
	public void setWhitePlayer(PublicUserEntity whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	/**
	 * @return the blackPlayer
	 */
	public PublicUserEntity getBlackPlayer() {
		return blackPlayer;
	}

	/**
	 * @param blackPlayer
	 *            the blackPlayer to set
	 */
	public void setBlackPlayer(PublicUserEntity blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	/**
	 * @return the isFinished
	 */
	public Boolean getIsFinished() {
		return isFinished;
	}

	/**
	 * @param isFinished
	 *            the isFinished to set
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
	 * @param chessboardEntity
	 *            the chessboardEntity to set
	 */
	public void setChessboardEntity(ChessboardEntity chessboardEntity) {
		this.chessboardEntity = chessboardEntity;
	}

	/**
	 * @return the observers
	 */
	public List<PublicUserEntity> getObservers() {
		return observers;
	}

	/**
	 * @param observers
	 *            the observers to set
	 */
	public void setObservers(List<PublicUserEntity> observers) {
		this.observers = observers;
	}

	/**
	 * @return the movesHistory
	 */
	public List<MoveEntity> getMovesHistory() {
		return movesHistory;
	}

	/**
	 * @param movesHistory
	 *            the movesHistory to set
	 */
	public void setMovesHistory(List<MoveEntity> movesHistory) {
		this.movesHistory = movesHistory;
	}

	/**
	 * @return the whitePieces
	 */
	public List<APieceEntity> getWhitePieces() {
		return whitePieces;
	}

	/**
	 * @param whitePieces
	 *            the whitePieces to set
	 */
	public void setWhitePieces(List<APieceEntity> whitePieces) {
		this.whitePieces = whitePieces;
	}

	/**
	 * @return the blackPieces
	 */
	public List<APieceEntity> getBlackPieces() {
		return blackPieces;
	}

	/**
	 * @param blackPieces
	 *            the blackPieces to set
	 */
	public void setBlackPieces(List<APieceEntity> blackPieces) {
		this.blackPieces = blackPieces;
	}

	/**
	 * @return the isOservable
	 */
	public Boolean getIsOservable() {
		return isOservable;
	}

	/**
	 * @param isOservable
	 *            the isOservable to set
	 */
	public void setIsOservable(Boolean isOservable) {
		this.isOservable = isOservable;
	}

	/**
	 * @return the currentPlayer
	 */
	public PublicUserEntity getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @param currentPlayer
	 *            the currentPlayer to set
	 */
	public void setCurrentPlayer(PublicUserEntity currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	/**
	 * Will switch current user between user black and user white
	 * @author ulyss_000
	 */
	public void switchCurrentUser(){
    	//switch active user :
    	if(this.getBlackPlayer().getId().equals(this.getCurrentPlayer().getId())){
    		this.setCurrentPlayer(this.getWhitePlayer());
    	}
    	else{
    		this.setCurrentPlayer(this.getBlackPlayer());
    	}
	}

	/**
	 * @author ulyss_000
	 * @return the current player king
	 */
	private KingEntity getKing() {
		if (this.getCurrentPlayer().getId().equals(this.getBlackPlayer().getId())) {
			// ActivePlayer is BlackPlayer
			return (KingEntity) getBlackPieces().stream().filter(bp -> bp.getClass().isInstance(KingEntity.class))
					.findFirst().orElse(null);
		} else {
			// ActivePlayer is WhitePlayer
			return (KingEntity) getWhitePieces().stream().filter(bw -> bw.getClass().isInstance(KingEntity.class))
					.findFirst().orElse(null);
		}
	}

	/**
	 * @author ulyss_000
	 * @return the other player pieces
	 */
	private List<APieceEntity> getOpponentPieces() {
		if (this.getCurrentPlayer().getId().equals(this.getBlackPlayer().getId())) {
			// ActivePlayer is BlackPlayer
			return this.getWhitePieces();
		} else {
			// ActivePlayer is WhitePlayer
			return this.getBlackPieces();

		}
	}

	/**
	 * @author ulyss_000
	 * @return true if the gameentity is in check, false if not
	 */
	public Boolean isCheck() {
		Boolean result = false;
		if (this.getOpponentPieces().stream()
				.filter(op -> op.generateAvailableMoves(this).contains(this.getKing().getPosition())).findFirst()
				.orElse(null) == null) {
			result = true;
		}
		return result;
	}

	/**
	 * Verify the winning status of the game
	 * 
	 * @author ulyss_000
	 * @return GameStatusEnum -> CHECKMATE,MATE,CONTINUE
	 */
	public GameStatusEnum isFinished() {
		Assert.notNull(this.getCurrentPlayer(), "[GameEntity][isFinished] currentPlayer shouldn't be null");
		Assert.notNull(this.getBlackPieces(), "[GameEntity][isFinished] blackPieces shouldn't be null");
		Assert.notNull(this.getWhitePieces(), "[GameEntity][isFinished] whitePieces shouldn't be null");

		// Local variables :
		KingEntity king;
		List<APieceEntity> opponentPieces;
		GameStatusEnum result = GameStatusEnum.CONTINUE;
		Boolean check = false;

		// set local variables according to the local player color :
		if (this.getCurrentPlayer().getId().equals(this.getBlackPlayer().getId())) {
			// ActivePlayer is BlackPlayer
			king = (KingEntity) getBlackPieces().stream().filter(bp -> bp.getClass().isInstance(KingEntity.class))
					.findFirst().orElse(null);
			opponentPieces = this.getWhitePieces();
		} else {
			// ActivePlayer is WhitePlayer
			king = (KingEntity) getWhitePieces().stream().filter(bw -> bw.getClass().isInstance(KingEntity.class))
					.findFirst().orElse(null);
			opponentPieces = this.getBlackPieces();

		}

		// Check check
		if (this.isCheck()) {
			check = true;
			result = GameStatusEnum.CHECK;
		}

		// Checkmate check :
		if (check == true) {
			if (king.generateAvailableMoves(this).isEmpty()) {
				result = GameStatusEnum.CHECKMATE;
			}
		}

		// Draw check :
		// TODO : Ulysse : there are a lot of those...

		return result;
	}

}
