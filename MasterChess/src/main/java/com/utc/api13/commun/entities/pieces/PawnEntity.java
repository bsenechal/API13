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

    /**
     * 
     */
    private static final long serialVersionUID = -1168464136345780891L;
    
    public PawnEntity(PieceColorEnum color, GameEntity currentGame) {
        super(color);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game) {
        // TODO Auto-generated method stub
        return null;
    }

}
