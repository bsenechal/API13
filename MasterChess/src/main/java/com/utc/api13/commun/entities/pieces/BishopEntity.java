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
public class BishopEntity extends APieceEntity {
    private static final int START_LINE_BLACK_BISHOP = 8;
    private static final int START_LINE_WHITE_BISHOP = 1;

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
        // TODO Auto-generated method stub
        return null;
    }

}
