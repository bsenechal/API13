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
public class KingEntityTest {
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

    /**
     * Test method for
     * {@link com.utc.api13.commun.entities.pieces.KingEntity#generateAvailableMoves(com.utc.api13.commun.entities.GameEntity)}
     * .
     */
    @Test
    public void testGenerateAvailableMoves() {
        final PositionEntity blackKingPosition = new PositionEntity(5, 8);
        final PositionEntity whiteKingPosition = new PositionEntity(5, 1);
        KingEntity whiteKing = new KingEntity(PieceColorEnum.WHITE);
        KingEntity blackKing = new KingEntity(PieceColorEnum.BLACK);

        // Test du déplacement avec toutes les pièces lors de l'initialisation
        // du jeu
        Assert.assertEquals("Error init King position", whiteKing.getPosition().getPositionX(),
                whiteKingPosition.getPositionX());
        Assert.assertEquals("Error init King position", whiteKing.getPosition().getPositionY(),
                whiteKingPosition.getPositionY());

        Assert.assertEquals("Error init King position", blackKing.getPosition().getPositionX(),
                blackKingPosition.getPositionX());
        Assert.assertEquals("Error init King position", blackKing.getPosition().getPositionY(),
                blackKingPosition.getPositionY());

        List<PositionEntity> availablesPositionsWhiteKing = whiteKing.generateAvailableMoves(game);
        Assert.assertTrue("availablesPositionsWhiteKing should be empty", availablesPositionsWhiteKing.isEmpty());

        game.setCurrentPlayer(whitePlayer);

        List<PositionEntity> availablesPositionsBlackKing = blackKing.generateAvailableMoves(game);
        Assert.assertTrue("availablesPositionsBlackKing should be empty", availablesPositionsBlackKing.isEmpty());

        // Suppression des pièces autre que le roi pour tester les déplacement
        // sur un plateau vide
        game.setBlackPieces(new ArrayList<APieceEntity>());
        game.getBlackPieces().add(blackKing);

        game.setWhitePieces(new ArrayList<APieceEntity>());
        game.getWhitePieces().add(whiteKing);

        availablesPositionsBlackKing = blackKing.generateAvailableMoves(game);

        availablesPositionsWhiteKing = whiteKing.generateAvailableMoves(game);

        Assert.assertFalse("availablesPositionsBlackKing shouldn't be empty", availablesPositionsBlackKing.isEmpty());
        Assert.assertFalse("availablesPositionsWhiteKing shouldn't be empty", availablesPositionsWhiteKing.isEmpty());
        Assert.assertEquals("There should be 5 possible positions for the black king",
                availablesPositionsBlackKing.size(), 5);
        Assert.assertEquals("There should be 5 possible positions for the black king",
                availablesPositionsWhiteKing.size(), 5);

        // Test les déplacements si il y a un pion adverse en face du roi
        PawnEntity blackPawn = new PawnEntity(PieceColorEnum.BLACK, 4);
        blackPawn.setPosition(new PositionEntity(4, 2));

        game.getBlackPieces().add(blackPawn);

        PawnEntity whitePawn = new PawnEntity(PieceColorEnum.WHITE, 4);
        whitePawn.setPosition(new PositionEntity(4, 7));

        game.getWhitePieces().add(whitePawn);

        Assert.assertFalse("availablesPositionsBlackKing shouldn't be empty", availablesPositionsBlackKing.isEmpty());
        Assert.assertFalse("availablesPositionsWhiteKing shouldn't be empty", availablesPositionsWhiteKing.isEmpty());
        Assert.assertEquals("There should be 5 possible positions for the black king",
                availablesPositionsBlackKing.size(), 5);
        Assert.assertEquals("There should be 5 possible positions for the black king",
                availablesPositionsWhiteKing.size(), 5);
    }

}
