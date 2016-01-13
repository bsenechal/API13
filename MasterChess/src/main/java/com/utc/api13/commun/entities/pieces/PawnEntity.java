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

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game, boolean verifyCheck) {
        APieceEntity tmpOpponentPiece = null;
        PositionEntity positionTemp = null;

        Assert.notNull(getPosition(), "[PawnEntity][generateAvailableMoves] Position shouldn't be null");

        List<PositionEntity> result = new ArrayList<PositionEntity>();

        int positionX = getPosition().getPositionX();
        int positionY = getPosition().getPositionY();

        if (this.getColor() == PieceColorEnum.WHITE) {
            // Si position de départ, alors on peut avancer de deux
            positionTemp = new PositionEntity(positionX, positionY + 2);
            if (positionX == START_LINE_WHITE_PAWN
                    && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTemp)
                    && APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp)) {
                this.movePiece(new MoveEntity(new Date(), this.getPosition(), positionTemp, this), game);
                if (!game.isCheck()) {
                    result.add(positionTemp);
                }
                this.cancelLastMove(game);
            }

            // mouvement classique
            positionTemp = new PositionEntity(positionX, positionY + 1);
            if (ChessboardEntity.isCaseOnChessboard(positionTemp)
                    && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTemp)
                    && APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp)) {
                this.movePiece(new MoveEntity(new Date(), this.getPosition(), positionTemp, this), game);
                if (!game.isCheck()) {
                    result.add(positionTemp);
                }
                this.cancelLastMove(game);
            }

            // ennemis dans les diagonales
            PositionEntity positionTemp2 = new PositionEntity(positionX + 1, positionY + 1);
            if (!APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp)) {
                this.movePiece(new MoveEntity(new Date(), this.getPosition(), positionTemp, this), game);

                tmpOpponentPiece = game.getOpponentPieces().stream()
                        .filter(piece -> piece.getPosition().equals(positionTemp2)).findFirst().orElse(null);
                game.removePiece(tmpOpponentPiece);

                if (!game.isCheck()) {
                    result.add(positionTemp);
                }
                game.addPiece(tmpOpponentPiece);
                this.cancelLastMove(game);

            }
            PositionEntity positionTemp3 = new PositionEntity(positionX - 1, positionY + 1);
            if (!APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp)) {
                this.movePiece(new MoveEntity(new Date(), this.getPosition(), positionTemp, this), game);

                tmpOpponentPiece = game.getOpponentPieces().stream()
                        .filter(piece -> piece.getPosition().equals(positionTemp3)).findFirst().orElse(null);
                game.removePiece(tmpOpponentPiece);

                if (!game.isCheck()) {
                    result.add(positionTemp);
                }
                game.addPiece(tmpOpponentPiece);
                this.cancelLastMove(game);
            }

        } else if (this.getColor() == PieceColorEnum.BLACK) {
            // si position de départ on peut avancer de deux
            positionTemp = new PositionEntity(positionX, positionY - 2);
            if (positionX == START_LINE_BLACK_PAWN
                    && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTemp)
                    && APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp)) {
                this.movePiece(new MoveEntity(new Date(), this.getPosition(), positionTemp, this), game);
                if (!game.isCheck()) {
                    result.add(positionTemp);
                }
                this.cancelLastMove(game);
            }

            // mouvement classique
            positionTemp = new PositionEntity(positionX, positionY - 1);
            if (ChessboardEntity.isCaseOnChessboard(positionTemp)
                    && APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTemp)
                    && APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp)) {
                this.movePiece(new MoveEntity(new Date(), this.getPosition(), positionTemp, this), game);
                if (!game.isCheck()) {
                    result.add(positionTemp);
                }
                this.cancelLastMove(game);
            }

            // ennemis dans les diagonales
            PositionEntity positionTemp2 = new PositionEntity(positionX + 1, positionY - 1);
            if (APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTemp)
                    && APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp)) {
                this.movePiece(new MoveEntity(new Date(), this.getPosition(), positionTemp, this), game);

                tmpOpponentPiece = game.getOpponentPieces().stream()
                        .filter(piece -> piece.getPosition().equals(positionTemp2)).findFirst().orElse(null);
                game.removePiece(tmpOpponentPiece);

                if (!game.isCheck()) {
                    result.add(positionTemp);
                }
                game.addPiece(tmpOpponentPiece);
                this.cancelLastMove(game);
            }
            PositionEntity positionTemp3 = new PositionEntity(positionX - 1, positionY - 1);
            if (APieceEntity.isPositionAvailableFromPieces(game.getCurrentPlayerPieces(), positionTemp)
                    && APieceEntity.isPositionAvailableFromPieces(game.getOpponentPieces(), positionTemp)) {
                this.movePiece(new MoveEntity(new Date(), this.getPosition(), positionTemp, this), game);

                tmpOpponentPiece = game.getOpponentPieces().stream()
                        .filter(piece -> piece.getPosition().equals(positionTemp3)).findFirst().orElse(null);
                game.removePiece(tmpOpponentPiece);

                if (!game.isCheck()) {
                    result.add(positionTemp);
                }
                game.addPiece(tmpOpponentPiece);
                this.cancelLastMove(game);
            }
        }

        return result;
    }

    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game) {
        return generateAvailableMoves(game, Boolean.TRUE);
    }

}
