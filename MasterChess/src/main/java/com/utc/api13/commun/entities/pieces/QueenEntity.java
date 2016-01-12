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
public class QueenEntity extends APieceEntity {
    private static final int START_COLUMN_QUEEN = 4;
    private static final int START_LINE_BLACK_QUEEN = 8;
    private static final int START_LINE_WHITE_QUEEN = 1;
    private static final int MIN_MOVE = -7;
    private static final int MAX_MOVE = 8;

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



       
    private void addPossibleSolution(final GameEntity game, final int positionX,final int positionY, int x, int y, List<PositionEntity> result){
    	PositionEntity positionTemp = new PositionEntity(positionX + x, positionY + y);

        // On vérifie que la position est bien sur le plateau de jeu
           if (ChessboardEntity.isCaseOnChessboard(positionTemp)) {

               // Si on est le joueur noir
               if (game.getCurrentPlayer().equals(game.getBlackPlayer())) {

                   // On vérifie que la position n'est pas déjà prise
                   if (APieceEntity.isPositionAvailableFromPieces(game.getBlackPieces(), positionTemp)) {
                       // On vérifie que cela ne met pas notre roi en échec
                       // :
                       this.movePiece(new MoveEntity(new Date(), this.getPosition(), positionTemp, this), game);
                       if (!game.isCheck()) {
                           result.add(positionTemp);
                       }
                       this.cancelLastMove(game);

                   }
               } else {
                   if (APieceEntity.isPositionAvailableFromPieces(game.getWhitePieces(), positionTemp)) {
                       result.add(positionTemp);
                   }
               }
           }
    }

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game) {
        Assert.notNull(getPosition(), "[QueenEntity][generateAvailableMoves] Position shouldn't be null");

        List<PositionEntity> result = new ArrayList<PositionEntity>();

        int positionX = getPosition().getPositionX();
        int positionY = getPosition().getPositionY();
        

        //All movements
        for (int x = MIN_MOVE; x < MAX_MOVE && x != 0; x++) {
            //Horizontal movements
            addPossibleSolution(game, positionX, positionY, x, 0, result);
            //Vertical movements
            addPossibleSolution(game, positionX, positionY, 0, x, result);
            //Sideways 1 movements
            addPossibleSolution(game, positionX, positionY, x, x, result);
            //Sideways 2 movements
            addPossibleSolution(game, positionX, positionY, x, -x, result);

        }

        return result;
    }
    
}
