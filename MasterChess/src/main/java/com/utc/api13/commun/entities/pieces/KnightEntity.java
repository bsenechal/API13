package com.utc.api13.commun.entities.pieces;

import java.util.List;

import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.ChessboardEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.enumerations.PieceColorEnum;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.util.Assert;

/**
 * @author Benoît
 *
 */
public class KnightEntity extends APieceEntity {
    private static final int START_LINE_BLACK_KNIGHT = 8;
    private static final int START_LINE_WHITE_KNIGHT = 1;
    private static final int MIN_MOVE = -2;
    private static final int MAX_MOVE = 3;

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

        for (int x = MIN_MOVE; x < MAX_MOVE; x++) {
            if (x != 0) {
                for (int y = MIN_MOVE; y < MAX_MOVE; y++) {
                    if (y != 0 && Math.abs(x) != Math.abs(y)) {
                        
                        addPossibleSolution(game, positionX, positionY, x, y, result, verifyCheck);
                        
                        /*
                         
                        PositionEntity positionTemp = new PositionEntity(positionX + x, positionY + y);

                        // On vérifie que la position est bien sur le plateau de
                        // jeu
                        if (ChessboardEntity.isCaseOnChessboard(positionTemp)) {
                            List<APieceEntity> opponentPieces = null;
                            // Si on est le joueur noir
                            if (game.getCurrentPlayer().equals(game.getBlackPlayer())) {
                                opponentPieces = game.getBlackPieces();
                            } else {
                                opponentPieces = game.getWhitePieces();
                            }
                            // On vérifie que la position n'est pas déjà prise
                            if (APieceEntity.isPositionAvailableFromPieces(opponentPieces, positionTemp)) {
                                // On vérifie que cela ne met pas notre roi en
                                // échec
                                this.movePiece(new MoveEntity(new Date(), this.getPosition(), positionTemp, this),
                                        game);
                                if (!game.isCheck()) {
                                    result.add(positionTemp);
                                }
                                this.cancelLastMove(game);

                            }
                        }
                        */
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
    	return "Knight";
    }
}
