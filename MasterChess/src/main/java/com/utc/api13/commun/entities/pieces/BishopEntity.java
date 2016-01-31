
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
 * 
 * @author Lucie
 *
 */
public class BishopEntity extends APieceEntity {
    private static final int START_LINE_BLACK_BISHOP = 8;
    private static final int START_LINE_WHITE_BISHOP = 1;
    private static final int MIN_MOVE = -7;
    private static final int MAX_MOVE = 7;

    /**
     * 
     */
    private static final long serialVersionUID = 2036717333681570183L;

    public BishopEntity(final PieceColorEnum color, final int startColumn) {
        super(color);
        this.setPosition((color.equals(PieceColorEnum.BLACK) ? new PositionEntity(startColumn, START_LINE_BLACK_BISHOP)
                : new PositionEntity(startColumn, START_LINE_WHITE_BISHOP)));
    }

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game) {
        return generateAvailableMoves(game, Boolean.TRUE);
    }

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game, boolean verifyCheck) {
        Assert.notNull(getPosition(), "[BishopEntity][generateAvailableMoves] Position shouldn't be null");

        List<PositionEntity> result = new ArrayList<PositionEntity>();

        int positionX = getPosition().getPositionX();
        int positionY = getPosition().getPositionY();

        for (int x = -1; x >= MIN_MOVE; x--) {
            // Sideways 1 movements
            if (addPossibleSolution(game, positionX, positionY, x, x, result, verifyCheck)) {
                break;
            }
        }
        for (int x = 1; x <= MAX_MOVE; x++) {
            // Sideways 1 movements
            if (addPossibleSolution(game, positionX, positionY, x, x, result, verifyCheck)) {
                break;
            }
        }
        for (int x = -1; x >= MIN_MOVE; x--) {
            // Sideways 2 movements
            if (addPossibleSolution(game, positionX, positionY, x, -x, result, verifyCheck)) {
                break;
            }
        }
        for (int x = 1; x <= MAX_MOVE; x++) {
            // Sideways 2 movements
            if (addPossibleSolution(game, positionX, positionY, x, -x, result, verifyCheck)) {
                break;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "Bishop";
    }

}
