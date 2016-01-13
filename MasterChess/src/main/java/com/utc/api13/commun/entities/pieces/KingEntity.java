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
	private static final int MIN_MOVE_X = -1;
	private static final int MAX_MOVE_X = 1;
	private static final int MIN_MOVE_Y = -1;
	private static final int MAX_MOVE_Y = 1;

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
				(PieceColorEnum.BLACK.equals(color) ? new PositionEntity(START_COLUMN_KING, START_LINE_BLACK_KING)
						: new PositionEntity(START_COLUMN_KING, START_LINE_WHITE_KING)));
	}

	@Override
	public List<PositionEntity> generateAvailableMoves(GameEntity game) {
		return generateAvailableMoves(game, Boolean.TRUE);
	}

	@Override
	public List<PositionEntity> generateAvailableMoves(GameEntity game, boolean verifyCheck) {
		Assert.notNull(getPosition(), "[KingEntity][generateAvailableMoves] Position shouldn't be null");

		List<PositionEntity> result = new ArrayList<PositionEntity>();

		int positionX = getPosition().getPositionX();
		int positionY = getPosition().getPositionY();

		// Calcul des positions possibles autour du roi
		for (int x = MIN_MOVE_X; x <= MAX_MOVE_X; x++) {
			for (int y = MIN_MOVE_Y; y <= MAX_MOVE_Y; y++) {
				addPossibleSolution(game, positionX, positionY, x, y, result, verifyCheck);
			}
		}
		return result;
	}

}
