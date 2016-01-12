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
public class RookEntity extends APieceEntity {
    private static final int START_LINE_BLACK_ROOK = 8;
    private static final int START_LINE_WHITE_ROOK = 1;

    /**
     * 
     */
    private static final long serialVersionUID = 2587319077980898398L;

    public RookEntity(final PieceColorEnum color, final int startColumn) {
        super(color);
        this.setPosition(
                (color.equals(PieceColorEnum.BLACK) ? new PositionEntity(startColumn, START_LINE_BLACK_ROOK)
                : new PositionEntity(startColumn, START_LINE_WHITE_ROOK)));
    }


    @Override
    public List<PositionEntity> generateAvailableMoves(GameEntity game) {
        Assert.notNull(getPosition(), "[RookEntity][generateAvailableMoves] Position shouldn't be null");

        List<PositionEntity> result = new ArrayList<PositionEntity>();

        int positionX = getPosition().getPositionX();
        int positionY = getPosition().getPositionY();

        // Déplacement sur l'axe x
        for (int x = -7; x < 8; x++) {
            final PositionEntity positionTemp = new PositionEntity(positionX + x, positionY);

            // On vérifie que la position est bien sur le plateau de jeu
            if (ChessboardEntity.getCases().contains(positionTemp)) {

                // Si on est le joueur noir
                if (game.getCurrentPlayer().equals(game.getBlackPlayer())) {

                    // On vérifie que la position n'est pas déjà prise
                    if (!APieceEntity.getAllPositionsByPieces(game.getBlackPieces()).contains(positionTemp)) {

                        // On vérifie que cela ne met pas notre roi en échec
                        this.movePiece(new MoveEntity(new Date(), this.getPosition(), positionTemp, this), game);
                        if (!game.isCheck()) {
                            result.add(positionTemp);
                        }
                        this.cancelLastMove(game);

                    }
                } else {
                    if (!APieceEntity.getAllPositionsByPieces(game.getWhitePieces()).contains(positionTemp)) {
                        result.add(positionTemp);
                    }
                }

            }
        }

        
        
        // Déplacement sur l'axe y
        for (int y = -7; y < 8; y++) {
            final PositionEntity positionTemp = new PositionEntity(positionX, positionY + y);

            // On vérifie que la position est bien sur le plateau de jeu
            if (ChessboardEntity.getCases().contains(positionTemp)) {
                // Si on est le joueur noir
                if (game.getCurrentPlayer().equals(game.getBlackPlayer())) {

                    // On vérifie que la position n'est pas déjà prise
                    if (!APieceEntity.getAllPositionsByPieces(game.getBlackPieces()).contains(positionTemp)) {
                        // On vérifie que cela ne met pas notre roi en échec
                        // :
                        this.movePiece(new MoveEntity(new Date(), this.getPosition(), positionTemp, this), game);
                        if (!game.isCheck()) {
                            result.add(positionTemp);
                        }
                        this.cancelLastMove(game);

                    }
                } else {
                    if (!APieceEntity.getAllPositionsByPieces(game.getWhitePieces()).contains(positionTemp)) {
                        result.add(positionTemp);
                    }
                }

            }
        }
        return result;
    }

}
