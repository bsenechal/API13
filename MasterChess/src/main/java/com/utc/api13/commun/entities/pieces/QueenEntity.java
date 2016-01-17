/**
 * 
 */
package com.utc.api13.commun.entities.pieces;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.enumerations.PieceColorEnum;

/**
 * @author Benoît
 *
 */
public class QueenEntity extends APieceEntity {
    private static final int START_COLUMN_QUEEN = 4;
    private static final int START_LINE_BLACK_QUEEN = 8;
    private static final int START_LINE_WHITE_QUEEN = 1;
    private static final int MIN_MOVE = -7;
    private static final int MAX_MOVE = 7;

    /**
     * 
     */
    private static final long serialVersionUID = -4412179301301968841L;

    public QueenEntity(PieceColorEnum color) {
        super(color);
        this.setPosition(
                (color.equals(PieceColorEnum.BLACK) ? new PositionEntity(START_COLUMN_QUEEN, START_LINE_BLACK_QUEEN)
                        : new PositionEntity(START_COLUMN_QUEEN, START_LINE_WHITE_QUEEN)));
    }

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game, boolean verifyCheck) {
        Assert.notNull(getPosition(), "[QueenEntity][generateAvailableMoves] Position shouldn't be null");

        List<PositionEntity> result = new ArrayList<PositionEntity>();

        int positionX = getPosition().getPositionX();
        int positionY = getPosition().getPositionY();

        // All movements
        for (int x = -1; x >= MIN_MOVE; x--) {
            // Horizontal movements
            if (addPossibleSolution(game, positionX, positionY, x, 0, result)) {
                break;
            }
        }
        for (int x = 1; x <= MAX_MOVE; x++) {
            // Horizontal movements
            if (addPossibleSolution(game, positionX, positionY, x, 0, result)) {
                break;
            }
        }

        for (int x = -1; x >= MIN_MOVE; x--) {
            // Vertical movements
            if (addPossibleSolution(game, positionX, positionY, 0, x, result)) {
                break;
            }
        }
        for (int x = 1; x <= MAX_MOVE; x++) {
            // Vertical movements
            if (addPossibleSolution(game, positionX, positionY, 0, x, result)) {
                break;
            }
        }
        for (int x = -1; x >= MIN_MOVE; x--) {
            // Sideways 1 movements
            if (addPossibleSolution(game, positionX, positionY, x, x, result)) {
                break;
            }
        }
        for (int x = 1; x <= MAX_MOVE; x++) {
            // Sideways 1 movements
            if (addPossibleSolution(game, positionX, positionY, x, x, result)) {
                break;
            }
        }
        for (int x = -1; x >= MIN_MOVE; x--) {
            // Sideways 2 movements
            if (addPossibleSolution(game, positionX, positionY, x, -x, result)) {
                break;
            }
        }
        for (int x = 1; x <= MAX_MOVE; x++) {
            // Sideways 2 movements
            if (addPossibleSolution(game, positionX, positionY, x, -x, result)) {
                break;
            }
        }

        return result;
    }

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game) {
        return generateAvailableMoves(game, Boolean.TRUE);
    }

}
