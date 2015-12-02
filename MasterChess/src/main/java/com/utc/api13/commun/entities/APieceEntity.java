package com.utc.api13.commun.entities;

import java.util.List;

import org.springframework.util.Assert;

import com.utc.api13.client.data.services.GameService;
import com.utc.api13.commun.enumerations.PieceColorEnum;

public abstract class APieceEntity extends ADataEntity {

    private static final long serialVersionUID = 6842864968035495956L;
    private PieceColorEnum color;
    private PositionEntity position;
    protected GameEntity currentGame;
    protected GameService gameService;

    /**
     * @param color
     * @param currentGame
     */
    public APieceEntity(PieceColorEnum color, GameEntity currentGame) {
        super();
        this.color = color;
        this.currentGame = currentGame;
        this.gameService = new GameService();
    }

    public void move(final MoveEntity move) {
        Assert.notNull(currentGame, "[APieceEntity][move] current game shouldn't be null");
        Assert.notNull(currentGame.getCurrentPlayer(), "[APieceEntity][move] current player shouldn't be null");
        Assert.notNull(currentGame.getWhitePieces(), "[APieceEntity][move] WhitePieces shouldn't be null");
        Assert.notNull(currentGame.getBlackPieces(), "[APieceEntity][move] BlackPieces shouldn't be null");
        Assert.notNull(move, "[APieceEntity][move] move shouldn't be null");
        Assert.notNull(currentGame.getMovesHistory(), "[APieceEntity][move] MovesHistory shouldn't be null");

        if (isMovePossible(move)) {
            if (currentGame.getCurrentPlayer().equals(currentGame.getBlackPlayer())) {
                if (gameService.getAllPositionsByPieces(currentGame.getWhitePieces()).contains(move.getToPosition())) {
                    // Suppression de la pièce dans le jeu de l'adversaire
                    gameService.removePieceByPosition(currentGame.getWhitePieces(), move.getToPosition());
                }
            } else {
                if (gameService.getAllPositionsByPieces(currentGame.getBlackPieces()).contains(move.getToPosition())) {
                    // Suppression de la pièce dans le jeu de l'adversaire
                    gameService.removePieceByPosition(currentGame.getBlackPieces(), move.getToPosition());
                }
            }

            // Déplacement de la pièce
            setPosition(move.getToPosition());

            // Ajout dans l'historique des coups
            currentGame.getMovesHistory().add(move);
        }
    }

    public boolean isMovePossible(final MoveEntity move) {
        Assert.notNull(move, "[APieceEntity][isMovePossible] move shouldn't be null");
        return (generateAvailableMoves().contains(move.getToPosition())) ? true : false;
    }

    public abstract List<PositionEntity> generateAvailableMoves();

    /**
     * @return the position
     */
    public PositionEntity getPosition() {
        return position;
    }

    /**
     * @param position
     *            the position to set
     */
    public void setPosition(PositionEntity position) {
        this.position = position;
    }

    /**
     * @return the color
     */
    public PieceColorEnum getColor() {
        return color;
    }

    /**
     * @param color
     *            the color to set
     */
    public void setColor(PieceColorEnum color) {
        this.color = color;
    }
}
