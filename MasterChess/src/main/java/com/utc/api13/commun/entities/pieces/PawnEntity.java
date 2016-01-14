/**
 * 
 */
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
    
    public PawnEntity(final PieceColorEnum color, final PositionEntity startPosition) {
        super(color);
        this.setPosition(startPosition);
    }
    
    @Override
    public String toString(){
        return "Pawn";
    }

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game, boolean verifyCheck) {
        PositionEntity positionTemp = null;

        Assert.notNull(getPosition(), "[PawnEntity][generateAvailableMoves] Position shouldn't be null");

        List<PositionEntity> result = new ArrayList<PositionEntity>();

        int positionX = getPosition().getPositionX();
        int positionY = getPosition().getPositionY();

        if (this.getColor().equals(PieceColorEnum.WHITE)) {
            

            // mouvement classique
            positionTemp = new PositionEntity(positionX, positionY + 1);
            if (APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp) && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTemp)) {
                result.add(positionTemp);
            }
            
            if (positionX == START_LINE_WHITE_PAWN) {
                positionTemp = new PositionEntity(positionX, positionY + 2);
                if (APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp) && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTemp)) {
                    result.add(positionTemp);
                }
            }

            // ennemis dans les diagonales
            positionTemp = new PositionEntity(positionX + 1 , positionY + 1);
            if (!APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp)) {
                addPossibleSolution(game, positionX, positionY, 1, 1, result, Boolean.FALSE);
            }
            
            positionTemp = new PositionEntity(positionX - 1, positionY + 1);
            if (!APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp)) {
                addPossibleSolution(game, positionX, positionY, -1, 1, result, Boolean.FALSE);
            }

        } else if (this.getColor().equals(PieceColorEnum.BLACK)) {
            
            // mouvement classique
            positionTemp = new PositionEntity(positionX, positionY - 1);
            if (APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp) && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTemp)) {
                result.add(positionTemp);
            }

            if (positionX == START_LINE_BLACK_PAWN) {
                positionTemp = new PositionEntity(positionX, positionY - 2);
                if (APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp) && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTemp)) {
                    result.add(positionTemp);
                }            
            }

            // ennemis dans les diagonales
            positionTemp = new PositionEntity(positionX + 1 , positionY - 1);
            if (!APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp)) {
                addPossibleSolution(game, positionX, positionY, 1, -1, result, Boolean.FALSE);
            }
            
            positionTemp = new PositionEntity(positionX - 1, positionY - 1);
            if (!APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp)) {
                addPossibleSolution(game, positionX, positionY, -1, -1, result, Boolean.FALSE);
            }
        }

        return result;
    }

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game) {
        return generateAvailableMoves(game, Boolean.TRUE);
    }

}
