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
public class PawnEntity extends APieceEntity {
    private static final int START_LINE_BLACK_PAWN = 7;
    private static final int START_LINE_WHITE_PAWN = 2;

    /**
     * 
     */
    private static final long serialVersionUID = -1168464136345780891L;

    public PawnEntity(final PieceColorEnum color, final int startColumn) {
        super(color);
        this.setPosition((color.equals(PieceColorEnum.BLACK) ? new PositionEntity(startColumn, START_LINE_BLACK_PAWN)
                : new PositionEntity(startColumn, START_LINE_WHITE_PAWN)));
    }

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game) {
        // TODO Auto-generated method stub
        return null;
    }

}
