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
public class KnightEntity extends APieceEntity {
    private static final int START_LINE_BLACK_KNIGHT = 8;
    private static final int START_LINE_WHITE_KNIGHT = 1;

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
    public List<PositionEntity> generateAvailableMoves(GameEntity game) {
        // TODO Auto-generated method stub
        return null;
    }
}
