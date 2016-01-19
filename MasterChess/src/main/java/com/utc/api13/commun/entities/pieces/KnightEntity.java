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
public class KnightEntity extends APieceEntity {
    private static final int START_LINE_BLACK_KNIGHT = 8;
    private static final int START_LINE_WHITE_KNIGHT = 1;
    private static final int MIN_MOVE = -2;
    private static final int MAX_MOVE = 2;
    private static final int INCREMENT_NUMBER = 2;
    private static final int LEFT_MOVE = -1;
    private static final int RIGHT_MOVE = 1;

    /**
     *
     */
    private static final long serialVersionUID = 2375251773738605949L;

    public KnightEntity(final PieceColorEnum color, final int startColumn) {
        super(color);
        this.setPosition((color.equals(PieceColorEnum.BLACK) ? new PositionEntity(startColumn, START_LINE_BLACK_KNIGHT)
                : new PositionEntity(startColumn, START_LINE_WHITE_KNIGHT)));
    }

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game, boolean verifyCheck) {
        Assert.notNull(getPosition(), "[KnightEntity][generateAvailableMoves] Position shouldn't be null");

        List<PositionEntity> result = new ArrayList<PositionEntity>();

        int positionX = getPosition().getPositionX();
        int positionY = getPosition().getPositionY();
        // Calcul des positions possibles autour du cavalier

        for (int x = MIN_MOVE; x <= MAX_MOVE; x += INCREMENT_NUMBER) {
                for (int y = MIN_MOVE; y <= MAX_MOVE; y += INCREMENT_NUMBER) {
                    if (x == 0) {
                        addPossibleSolution(game, positionX, positionY, x + LEFT_MOVE, y, result, verifyCheck);
                        
                        addPossibleSolution(game, positionX, positionY, x + RIGHT_MOVE, y, result, verifyCheck);
                    }
                    
                    if (y == 0) {
                        addPossibleSolution(game, positionX, positionY, x, y + LEFT_MOVE, result, verifyCheck);
                        
                        addPossibleSolution(game, positionX, positionY, x, y + RIGHT_MOVE, result, verifyCheck);
                    }
            }
        }
        return result;
    }

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game) {
        return generateAvailableMoves(game, Boolean.TRUE);
    }

    @Override
    public String toString() {
        return "Knight";
    }
}
