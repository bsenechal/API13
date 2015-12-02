package com.utc.api13.commun.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.util.Assert;

import com.utc.api13.client.data.services.GameService;
import com.utc.api13.commun.enumerations.PieceColorEnum;

public abstract class APieceEntity extends ADataEntity {

	private static final long serialVersionUID = 6842864968035495956L;
	private PieceColorEnum color;
	private PositionEntity position;

	/**
	 * get the list of positions of a specified list of pieces
	 * 
	 * @param List<APieceEntity>
	 *            pieces
	 * @return static List<PositionEntity>
	 */
	public static List<PositionEntity> getAllPositionsByPieces(final List<APieceEntity> pieces) {
		List<PositionEntity> positions = new ArrayList<PositionEntity>();
		for (APieceEntity piece : pieces) {
			positions.add(piece.getPosition());
		}
		return positions;
	}

	/**
	 * remove a piece by its postion in a list of pieces
	 * 
	 * @param List<APieceEntity>
	 *            pieces
	 * @param final
	 *            PositionEntity position
	 */
	public static void removePieceByPosition(List<APieceEntity> pieces, final PositionEntity position) {
		pieces.removeIf(piece -> piece.getPosition().equals(position));
	}

	/**
	 * @param color
	 * @param currentGame
	 */
	public APieceEntity(PieceColorEnum color) {
		super();
		this.color = color;
	}
	
	public void deleteDestinationPiece(final MoveEntity move, GameEntity game){
		Assert.notNull(game, "[APieceEntity][move] current game shouldn't be null");
		Assert.notNull(game.getCurrentPlayer(), "[APieceEntity][move] current player shouldn't be null");
		Assert.notNull(game.getWhitePieces(), "[APieceEntity][move] WhitePieces shouldn't be null");
		Assert.notNull(game.getBlackPieces(), "[APieceEntity][move] BlackPieces shouldn't be null");
		Assert.notNull(move, "[APieceEntity][move] move shouldn't be null");
		
		if (game.getCurrentPlayer().equals(game.getBlackPlayer())) {
			if (APieceEntity.getAllPositionsByPieces(game.getWhitePieces()).contains(move.getToPosition())) {
				// Suppression de la pièce dans le jeu de l'adversaire
				APieceEntity.removePieceByPosition(game.getWhitePieces(), move.getToPosition());
			}
		} else {
			if (APieceEntity.getAllPositionsByPieces(game.getBlackPieces()).contains(move.getToPosition())) {
				// Suppression de la pièce dans le jeu de l'adversaire
				APieceEntity.removePieceByPosition(game.getBlackPieces(), move.getToPosition());
			}
		}
	}

	public void movePiece(final MoveEntity move, GameEntity game) {
		Assert.notNull(game, "[APieceEntity][move] current game shouldn't be null");
		Assert.notNull(move, "[APieceEntity][move] move shouldn't be null");
		Assert.notNull(game.getMovesHistory(), "[APieceEntity][move] MovesHistory shouldn't be null");

		// Déplacement de la pièce
		setPosition(move.getToPosition());

		// Ajout dans l'historique des coups
		game.getMovesHistory().add(move);
	}
	
	public void cancelLastMove(GameEntity game) {
		Assert.notNull(game, "[APieceEntity][move] current game shouldn't be null");
		Assert.notNull(game.getMovesHistory(), "[APieceEntity][move] MovesHistory shouldn't be null");
		
		MoveEntity lastMove = game.getMovesHistory().stream().max(new Comparator<MoveEntity>() {
			@Override
			public int compare(MoveEntity o1, MoveEntity o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
			
		}).get();
		
		// annulation du dernier mouvement
		setPosition(lastMove.getFromPosition());

		// Enlever de l'historique
		game.getMovesHistory().remove(lastMove);
	}

	public boolean isMovePossible(final MoveEntity move, final GameEntity game) {
		Assert.notNull(move, "[APieceEntity][isMovePossible] move shouldn't be null");
		Assert.notNull(game, "[APieceEntity][isMovePossible] game shouldn't be null");

		return (generateAvailableMoves(game).contains(move.getToPosition())) ? true : false;
	}

	public abstract List<PositionEntity> generateAvailableMoves(GameEntity game);

	/**
	 * @return the position
	 */
	public PositionEntity getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(PositionEntity position) {
		this.position = position;
	}

	/**
	 * @return the color
	 */
	public PieceColorEnum getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(PieceColorEnum color) {
		this.color = color;
	}
}
