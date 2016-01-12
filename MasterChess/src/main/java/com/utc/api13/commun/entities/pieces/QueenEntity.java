/**
 * 
 */
package com.utc.api13.commun.entities.pieces;

import java.util.List;

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
    public List<PositionEntity> generateAvailableMoves(GameEntity game) {
        // TODO Auto-generated method stub
        return null;
    }

}
