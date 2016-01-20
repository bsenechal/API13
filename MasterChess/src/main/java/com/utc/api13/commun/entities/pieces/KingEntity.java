package com.utc.api13.commun.entities.pieces;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.enumerations.PieceColorEnum;

public class KingEntity extends APieceEntity {
    private static final int START_COLUMN_KING = 5;
    private static final int START_LINE_BLACK_KING = 8;
    private static final int START_LINE_WHITE_KING = 1;
    private static final int MIN_MOVE = -1;
    private static final int MAX_MOVE = 1;
    private boolean hasMove = Boolean.FALSE;
    private boolean itIsFirstMove = Boolean.FALSE;

    /**
     * 
     */
    private static final long serialVersionUID = 3721412295018328472L;

    /**
     * @author Hugo
     * @param move
     * @param game
     * 
     */
    @Override
    public void movePiece(final MoveEntity move, GameEntity game) {
        super.movePiece(move, game);

        if (hasMove == Boolean.FALSE && itIsFirstMove == Boolean.FALSE) {
            hasMove = Boolean.TRUE;
            itIsFirstMove = Boolean.TRUE;
        } else if (itIsFirstMove = Boolean.TRUE) {
            itIsFirstMove = Boolean.FALSE;
        }

    }

    /**
     * @author Hugo
     * @param game
     */
    @Override
    public void cancelLastMove(GameEntity game) {
        super.cancelLastMove(game);

        if (itIsFirstMove == Boolean.TRUE) {
            hasMove = Boolean.FALSE;
            itIsFirstMove = Boolean.FALSE;
        }
    }

    /**
     * @author Hugo
     * @return boolean
     */
    public boolean getHasMove() {
        return this.hasMove;
    }

    /**
     * @param color
     * @param currentGame
     */
    public KingEntity(PieceColorEnum color) {
        super(color);
        this.setPosition(
                (PieceColorEnum.BLACK.equals(color) ? new PositionEntity(START_COLUMN_KING, START_LINE_BLACK_KING)
                        : new PositionEntity(START_COLUMN_KING, START_LINE_WHITE_KING)));
    }

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game) {
        return generateAvailableMoves(game, Boolean.TRUE);
    }

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game, boolean verifyCheck) {
        Assert.notNull(getPosition(), "[KingEntity][generateAvailableMoves] Position shouldn't be null");

        List<PositionEntity> result = new ArrayList<PositionEntity>();

        int positionX = getPosition().getPositionX();
        int positionY = getPosition().getPositionY();

        // Calcul des positions possibles autour du roi
        for (int x = MIN_MOVE; x <= MAX_MOVE; x++) {
            for (int y = MIN_MOVE; y <= MAX_MOVE; y++) {
                if (!(x == 0 && y == 0)) {
                    addPossibleSolution(game, positionX, positionY, x, y, result, verifyCheck);
                }
            }
        }

//        if (hasMove == Boolean.FALSE) {
//            if (this.getPosition().getPositionX() == START_COLUMN_KING && ((this.getColor() == PieceColorEnum.BLACK
//                    && this.getPosition().getPositionY() == START_LINE_BLACK_KING)
//                    || (this.getColor() == PieceColorEnum.WHITE
//                            && this.getPosition().getPositionY() == START_LINE_WHITE_KING))) {
//
//                RookEntity RookTmp = null;
//
//                PositionEntity positionTmp1 = new PositionEntity(this.getPosition().getPositionX() + 1,
//                        this.getPosition().getPositionY());
//                PositionEntity positionTmp2 = new PositionEntity(this.getPosition().getPositionX() + 2,
//                        this.getPosition().getPositionY());
//                PositionEntity positionTmp3 = new PositionEntity(this.getPosition().getPositionX() + 3,
//                        this.getPosition().getPositionY());
//
//                if (game.getPieceFromPosition(positionTmp3).toString() == "Rook") {
//                    RookTmp = (RookEntity) game.getPieceFromPosition(positionTmp3);
//
//                    // Petit roque
//                    if (APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTmp1)
//                            && APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTmp2)
//                            && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTmp1)
//                            && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTmp2)
//                            && RookTmp.getColor() == this.getColor() && RookTmp.getHasMove() == Boolean.FALSE) {
//                        result.add(positionTmp2);
//                    }
//                }
//
//                positionTmp1 = new PositionEntity(this.getPosition().getPositionX() - 1,
//                        this.getPosition().getPositionY());
//                positionTmp2 = new PositionEntity(this.getPosition().getPositionX() - 2,
//                        this.getPosition().getPositionY());
//                positionTmp3 = new PositionEntity(this.getPosition().getPositionX() - 3,
//                        this.getPosition().getPositionY());
//                PositionEntity positionTmp4 = new PositionEntity(this.getPosition().getPositionX() - 4,
//                        this.getPosition().getPositionY());
//                // Grand Roque
//                if (game.getPieceFromPosition(positionTmp4).toString() == "Rook") {
//
//                    RookTmp = (RookEntity) game.getPieceFromPosition(positionTmp3);
//
//                    if (APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTmp1)
//                            && APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTmp2)
//                            && APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTmp3)
//                            && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTmp1)
//                            && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTmp2)
//                            && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTmp3)
//                            && RookTmp.getColor() == this.getColor() && RookTmp.getHasMove() == Boolean.FALSE) {
//                        result.add(positionTmp2);
//
//                    }
//                }
//
//            }
//        }
        return result;
    }

    @Override
    public String toString() {
        return "King";
    }

}
