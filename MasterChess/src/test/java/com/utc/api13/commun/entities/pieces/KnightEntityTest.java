/**
 * 
 */
package com.utc.api13.commun.entities.pieces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.PieceColorEnum;

/**
 * @author Benoît
 *
 */
public class KnightEntityTest {
    private GameEntity game;
    private PublicUserEntity blackPlayer;
    private PublicUserEntity whitePlayer;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        whitePlayer = new PublicUserEntity();
        blackPlayer = new PublicUserEntity();
        game = new GameEntity(Boolean.FALSE, Boolean.FALSE, new Date(), whitePlayer, blackPlayer);

        Assert.assertEquals("Error init pieces", game.getBlackPieces().size(), 16);
        Assert.assertEquals("Error init pieces", game.getWhitePieces().size(), 16);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void generateAvailableMovesTest() {
        KnightEntity whiteKnight = (KnightEntity) game.getPieceFromPosition(new PositionEntity(2, 1));

        // Test des déplacements à l'init du jeu (2 déplacements possibles)
        List<PositionEntity> availablesPositionsWhiteKnight = whiteKnight.generateAvailableMoves(game);
        Assert.assertFalse("availablesPositionsWhiteKnight shouldn't be empty",
                availablesPositionsWhiteKnight.isEmpty());
        Assert.assertEquals("availablesPositionsWhiteKnight should be equal to 2",
                availablesPositionsWhiteKnight.size(), 2);

        // Ajout d'un pion de l'autre couleur pour vérifier s'il peut le prendre
        PawnEntity blackPawn = new PawnEntity(PieceColorEnum.BLACK, 1);
        blackPawn.setPosition(new PositionEntity(1, 3));

        game.getBlackPieces().add(blackPawn);

        availablesPositionsWhiteKnight = whiteKnight.generateAvailableMoves(game);
        Assert.assertFalse("availablesPositionsWhiteKnight shouldn't be empty",
                availablesPositionsWhiteKnight.isEmpty());
        Assert.assertEquals("availablesPositionsWhiteKnight should be equal to 2",
                availablesPositionsWhiteKnight.size(), 2);

        // Ajout d'un pion de la même couleur pour vérifier que le déplacement
        // n'est plus disponible
        PawnEntity whitePawn = new PawnEntity(PieceColorEnum.WHITE, 1);
        whitePawn.setPosition(new PositionEntity(3, 3));

        game.getWhitePieces().add(whitePawn);

        availablesPositionsWhiteKnight = whiteKnight.generateAvailableMoves(game);
        Assert.assertFalse("availablesPositionsWhiteKnight shouldn't be empty",
                availablesPositionsWhiteKnight.isEmpty());
        Assert.assertEquals("availablesPositionsWhiteKnight should be equal to 1",
                availablesPositionsWhiteKnight.size(), 1);

        // Vérification des positions dans un chessboard vide
        game.setBlackPieces(new ArrayList<APieceEntity>());
        game.setWhitePieces(new ArrayList<APieceEntity>());

        whiteKnight.setPosition(new PositionEntity(4, 4));
        availablesPositionsWhiteKnight = whiteKnight.generateAvailableMoves(game);
        game.getWhitePieces().add(whiteKnight);
        Assert.assertFalse("availablesPositionsWhiteKnight shouldn't be empty",
                availablesPositionsWhiteKnight.isEmpty());
        Assert.assertEquals("availablesPositionsWhiteKnight should be equal to 8",
                availablesPositionsWhiteKnight.size(), 8);
    }

}
