package com.utc.api13.commun.entities.pieces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.Assert;

import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.ChessboardEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.enumerations.PieceColorEnum;

public class KingEntity extends APieceEntity {
	private static final int START_COLUMN_KING = 5;
	private static final int START_LINE_BLACK_KING = 8;
	private static final int START_LINE_WHITE_KING = 1;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3721412295018328472L;

	/**
	 * @param color
	 * @param currentGame
	 */
	public KingEntity(PieceColorEnum color) {
		super(color);
		this.setPosition(
				(color.equals(PieceColorEnum.BLACK) ? new PositionEntity(START_COLUMN_KING, START_LINE_BLACK_KING)
						: new PositionEntity(START_COLUMN_KING, START_LINE_WHITE_KING)));
	}

	@Override
	public List<PositionEntity> generateAvailableMoves(GameEntity game) {
		Assert.notNull(getPosition(), "[KingEntity][generateAvailableMoves] Position shouldn't be null");

		List<PositionEntity> result = new ArrayList<PositionEntity>();

		int positionX = getPosition().getPositionX();
		int positionY = getPosition().getPositionX();

		// Calcul des positions possibles autour du roi
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				final PositionEntity positionTemp = new PositionEntity(positionX + x, positionY + y);

				// On vérifie que la position est bien sur le plateau de jeu
				if (ChessboardEntity.getCases().contains(positionTemp)) {

					// Si on est le joueur noir
					if (game.getCurrentPlayer().equals(game.getBlackPlayer())) {

						// On vérifie que la position n'est pas déjà prise
						if (!APieceEntity.getAllPositionsByPieces(game.getBlackPieces()).contains(positionTemp)) {
							// On vérifie que cela ne met pas notre roi en échec
							// :
							this.movePiece(new MoveEntity(new Date(), this.getPosition(), positionTemp, this), game);
							if (!game.isCheck()) {
								result.add(positionTemp);
							}
							this.cancelLastMove(game);

						}
					} else {
						if (!APieceEntity.getAllPositionsByPieces(game.getWhitePieces()).contains(positionTemp)) {
							result.add(positionTemp);
						}
					}
				}
			}
		}
		return result;
	}
}
