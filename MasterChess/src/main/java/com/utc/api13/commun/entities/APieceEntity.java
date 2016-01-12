package com.utc.api13.commun.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.util.Assert;

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
	 * Check if is position is available from a list of pieces
	 * 
	 * @param pieces
	 * @param position
	 * @return
	 */
	public static boolean isPositionAvailableFromPieces(final List<APieceEntity> pieces,
			final PositionEntity position) {
		for (APieceEntity piece : pieces) {
			if (piece.getPosition().equals(position)) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	/**
	 * Check if is position is available with a list of positions
	 * 
	 * @param pieces
	 * @param position
	 * @return
	 */
	public static boolean isPositionAvailable(final List<PositionEntity> positions,
			final PositionEntity positionToCheck) {
		for (PositionEntity position : positions) {
			if (position.equals(positionToCheck)) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
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

	public void deleteDestinationPiece(final MoveEntity move, GameEntity game) {
		Assert.notNull(game, "[APieceEntity][move] current game shouldn't be null");
		Assert.notNull(game.getCurrentPlayer(), "[APieceEntity][move] current player shouldn't be null");
		Assert.notNull(game.getWhitePieces(), "[APieceEntity][move] WhitePieces shouldn't be null");
		Assert.notNull(game.getBlackPieces(), "[APieceEntity][move] BlackPieces shouldn't be null");
		Assert.notNull(move, "[APieceEntity][move] move shouldn't be null");

		game.removePiece(move.getPiece());

	}

	public void movePiece(final MoveEntity move, GameEntity game) {
		Assert.notNull(game, "[APieceEntity][move] current game shouldn't be null");
		Assert.notNull(move, "[APieceEntity][move] move shouldn't be null");
		Assert.notNull(game.getMovesHistory(), "[APieceEntity][move] MovesHistory shouldn't be null");

		// Déplacement de la pièce
		setPosition(move.getToPosition());

		// TODO : Delete adversary pond if needed !!! (managed deleted pond to
		// allow canceling a move ?)

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

	/**
	 * This method allow us to add a possible position to the result depending
	 * on a offset (x & y).
	 * 
	 * @return boolean -> if true -> the solution implies a kill.
	 * @param game
	 * @param positionX
	 * @param positionY
	 * @param x
	 * @param y
	 * @param result
	 * @author ulyss_000
	 */
	protected boolean addPossibleSolution(final GameEntity game, final int positionX, final int positionY, int x, int y,
			List<PositionEntity> result) {
		PositionEntity positionTemp = new PositionEntity(positionX + x, positionY + y);
		boolean haskilledAnother = false;

		// On vérifie que la position est bien sur le plateau de jeu
		if (ChessboardEntity.isCaseOnChessboard(positionTemp)) {
			// On vérifie que la position n'est pas déjà prise par nos pionts
			if (APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTemp)) {
				// On vérifie que cela ne met pas notre roi en échec
				// :
				this.movePiece(new MoveEntity(new Date(), this.getPosition(), positionTemp, this), game);
				// on supprime le piont adverse s'il y en a un a destination
				APieceEntity tmpOpponentPiece = null;
				if (!APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp)) {
					tmpOpponentPiece = game.getOpponentPieces().stream()
							.filter(piece -> piece.getPosition().equals(positionTemp)).findFirst().orElse(null);
					game.removePiece(tmpOpponentPiece);
					haskilledAnother = true;
				}
				if (!game.isCheck()) {
					result.add(positionTemp);
				}
				if (haskilledAnother) {
					game.addPiece(tmpOpponentPiece);
				}
				this.cancelLastMove(game);
			}
		}
		return haskilledAnother;
	}
}
