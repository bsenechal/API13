/**
 * 
 */
package com.utc.api13.commun.entities.pieces;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.enumerations.PieceColorEnum;

/**
 * @author Benoît
 *
 */
public class RookEntity extends APieceEntity {
    private static final int START_LINE_BLACK_ROOK = 8;
    private static final int START_LINE_WHITE_ROOK = 1;
    private static final int MIN_MOVE = -7;
    private static final int MAX_MOVE = 7;
    private boolean hasMove = Boolean.FALSE;
    private boolean itIsFirstMove = Boolean.FALSE;

    /**
     * 
     */
    private static final long serialVersionUID = 2587319077980898398L;
    
    @Override
    public void movePiece(final MoveEntity move, GameEntity game){
        super.movePiece(move, game);
        
        if (hasMove== Boolean.FALSE && itIsFirstMove == Boolean.FALSE){
            hasMove = Boolean.TRUE;
            itIsFirstMove = Boolean.TRUE;
        }else if(itIsFirstMove = Boolean.TRUE){
            itIsFirstMove = Boolean.FALSE;
        }
        
    }
    
    @Override
    public void cancelLastMove(GameEntity game) {
        super.cancelLastMove(game);
        
        if(itIsFirstMove == Boolean.TRUE){
            hasMove = Boolean.FALSE;
            itIsFirstMove = Boolean.FALSE;
        }
    }
    
    public boolean getHasMove(){
        return this.hasMove;
    }
    public RookEntity(final PieceColorEnum color, final int startColumn) {
        super(color);
        this.setPosition((color.equals(PieceColorEnum.BLACK) ? new PositionEntity(startColumn, START_LINE_BLACK_ROOK)
                : new PositionEntity(startColumn, START_LINE_WHITE_ROOK)));
    }

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game, boolean verifyCheck) {
        Assert.notNull(getPosition(), "[RookEntity][generateAvailableMoves] Position shouldn't be null");

        List<PositionEntity> result = new ArrayList<PositionEntity>();

        int positionX = getPosition().getPositionX();
        int positionY = getPosition().getPositionY();

        // All movements
        for (int x = -1; x >= MIN_MOVE; x--) {
            // Horizontal movements
            if (addPossibleSolution(game, positionX, positionY, x, 0, result, verifyCheck)) {
                break;
            }
        }
        for (int x = 1; x <= MAX_MOVE; x++) {
            // Horizontal movements
            if (addPossibleSolution(game, positionX, positionY, x, 0, result, verifyCheck)) {
                break;
            }
        }

        for (int x = -1; x >= MIN_MOVE; x--) {
            // Vertical movements
            if (addPossibleSolution(game, positionX, positionY, 0, x, result, verifyCheck)) {
                break;
            }
        }
        for (int x = 1; x <= MAX_MOVE; x++) {
            // Vertical movements
            if (addPossibleSolution(game, positionX, positionY, 0, x, result, verifyCheck)) {
                break;
            }
        }
        
        if(hasMove == Boolean.FALSE){
            
            KingEntity kingTmp = null;
            PositionEntity positionTmp1 = null;
            PositionEntity positionTmp2 = null;
            PositionEntity positionTmp3 = null;
            PositionEntity positionTmp4 = null;
            
            if(this.getPosition().getPositionX() == 1 
                    && ((this.getPosition().getPositionY() == START_LINE_BLACK_ROOK
                            && this.getColor() == PieceColorEnum.BLACK)
                    || (this.getPosition().getPositionY() == START_LINE_WHITE_ROOK
                            && this.getColor() == PieceColorEnum.WHITE))){
                
                //Grand roque
                
                positionTmp1 = new PositionEntity(this.getPosition().getPositionX() + 1, this.getPosition().getPositionY());
                positionTmp2 = new PositionEntity(this.getPosition().getPositionX() + 2, this.getPosition().getPositionY());
                positionTmp3 = new PositionEntity(this.getPosition().getPositionX() + 3, this.getPosition().getPositionY());
                positionTmp4 = new PositionEntity(this.getPosition().getPositionX() + 4, this.getPosition().getPositionY());
                
                if (game.getPieceFromPosition(positionTmp4).toString() == "Rook"){
                    
                    kingTmp = (KingEntity)game.getPieceFromPosition(positionTmp3);
                    
                    if(APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTmp1)
                            && APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTmp2)
                            && APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTmp3)
                            && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTmp1)
                            && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTmp2)
                            && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTmp3)
                            && kingTmp.getColor() == this.getColor()
                            && kingTmp.getHasMove() == Boolean.FALSE){
                        result.add(positionTmp3);
                        
                    }
                }
                
            }else if(this.getPosition().getPositionX() == 8 
                    && ((this.getPosition().getPositionY() == START_LINE_BLACK_ROOK
                            && this.getColor() == PieceColorEnum.BLACK)
                    || (this.getPosition().getPositionY() == START_LINE_WHITE_ROOK
                            && this.getColor() == PieceColorEnum.WHITE))){
                
              //Petit roque
                
                positionTmp1 = new PositionEntity(this.getPosition().getPositionX() - 1, this.getPosition().getPositionY());
                positionTmp2 = new PositionEntity(this.getPosition().getPositionX() - 2, this.getPosition().getPositionY());
                positionTmp3 = new PositionEntity(this.getPosition().getPositionX() - 3, this.getPosition().getPositionY());
                
                if (game.getPieceFromPosition(positionTmp3).toString() == "King"){
                    kingTmp = (KingEntity)game.getPieceFromPosition(positionTmp3);
                    
                    if(APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTmp1)
                            && APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTmp2)
                            && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTmp1)
                            && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTmp2)
                            && kingTmp.getColor() == this.getColor()
                            && kingTmp.getHasMove() == Boolean.FALSE){
                        result.add(positionTmp2);
                    }
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
        return "Rook";
    }

}
