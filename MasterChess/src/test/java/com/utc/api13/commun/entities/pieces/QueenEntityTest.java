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
 * @author ulyss_000
 *
 */
public class QueenEntityTest {
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
        final PositionEntity blackQueenPosition = new PositionEntity(4, 8);
        final PositionEntity whiteQueenPosition = new PositionEntity(4, 1);
        QueenEntity whiteQueen = new QueenEntity(PieceColorEnum.WHITE);
        QueenEntity blackQueen = new QueenEntity(PieceColorEnum.BLACK);

        Assert.assertEquals("Error init Queen X position", whiteQueen.getPosition().getPositionX(),
                whiteQueenPosition.getPositionX());
        Assert.assertEquals("Error init Queen Y position", whiteQueen.getPosition().getPositionY(),
                whiteQueenPosition.getPositionY());

        Assert.assertEquals("Error init Queen X position", blackQueen.getPosition().getPositionX(),
                blackQueenPosition.getPositionX());
        Assert.assertEquals("Error init Queen Y position", blackQueen.getPosition().getPositionY(),
                blackQueenPosition.getPositionY());

        List<PositionEntity> availablesPositionsWhiteQueen = whiteQueen.generateAvailableMoves(game);
        Assert.assertTrue("availablesPositionsWhiteQueen should be empty", availablesPositionsWhiteQueen.isEmpty());

        game.setCurrentPlayer(blackPlayer);

        List<PositionEntity> availablesPositionsBlackQueen = blackQueen.generateAvailableMoves(game);
        Assert.assertTrue("availablesPositionsBlackQueen should be empty", availablesPositionsBlackQueen.isEmpty());

        // Suppression des pièces autre que la reine
        game.setBlackPieces(new ArrayList<APieceEntity>());
        game.getBlackPieces().add(blackQueen);

        game.setWhitePieces(new ArrayList<APieceEntity>());
        game.getWhitePieces().add(whiteQueen);

        availablesPositionsBlackQueen = blackQueen.generateAvailableMoves(game);

        availablesPositionsWhiteQueen = whiteQueen.generateAvailableMoves(game);

        Assert.assertFalse("availablesPositionsBlackQueen shouldn't be empty", availablesPositionsBlackQueen.isEmpty());
        Assert.assertFalse("availablesPositionsWhiteQueen shouldn't be empty", availablesPositionsWhiteQueen.isEmpty());

        Assert.assertTrue("There should be 21 possible positions for the white Queen",
                availablesPositionsWhiteQueen.size() == 21);
        Assert.assertTrue("There should be 21 possible positions for the black Queen",
                availablesPositionsBlackQueen.size() == 21);

        // Ajout de pièces bloquantes :
        PawnEntity whitePawn = new PawnEntity(PieceColorEnum.WHITE, 3);
        game.getWhitePieces().add(whitePawn);
        PawnEntity blackPawn = new PawnEntity(PieceColorEnum.BLACK, 4);
        game.getBlackPieces().add(blackPawn);

        availablesPositionsWhiteQueen = whiteQueen.generateAvailableMoves(game);

        Assert.assertTrue("There should be 17 possible positions for the white Queen",
                availablesPositionsWhiteQueen.size() == 17);
    }
}
