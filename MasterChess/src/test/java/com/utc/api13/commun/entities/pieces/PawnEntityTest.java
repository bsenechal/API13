/**
 * 
 */
package com.utc.api13.commun.entities.pieces;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.PieceColorEnum;

/**
 * @author Hugo
 *
 */
public class PawnEntityTest {
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
     * {@link com.utc.api13.commun.entities.pieces.PawnEntity#generateAvailableMoves(com.utc.api13.commun.entities.GameEntity)}
     * .
     */
    @Test
    public void testGenerateAvailableMoves() {
        
        //On test la position initial
        final PositionEntity blackPawnPosition = new PositionEntity(5, 7);
        final PositionEntity whitePawnPosition = new PositionEntity(5, 2);
        PawnEntity whitePawn = new PawnEntity(PieceColorEnum.WHITE,5);
        PawnEntity blackPawn = new PawnEntity(PieceColorEnum.BLACK,5);

        Assert.assertEquals("Error init white Pawn horizontal position", whitePawn.getPosition().getPositionX(),
                whitePawnPosition.getPositionX());
        Assert.assertEquals("Error init white Pawn vertical position", whitePawn.getPosition().getPositionY(),
                whitePawnPosition.getPositionY());

        Assert.assertEquals("Error init black Pawn horizontal position", blackPawn.getPosition().getPositionX(),
                blackPawnPosition.getPositionX());
        Assert.assertEquals("Error init black Pawn vertical position", blackPawn.getPosition().getPositionY(),
                blackPawnPosition.getPositionY());

        List<PositionEntity> availablesPositionsWhitePawn = whitePawn.generateAvailableMoves(game);
        Assert.assertFalse("availablesPositionsWhitePawn should not be empty", availablesPositionsWhitePawn.isEmpty());

        game.setCurrentPlayer(blackPlayer);

        List<PositionEntity> availablesPositionsBlackPawn = blackPawn.generateAvailableMoves(game);
        Assert.assertFalse("availablesPositionsBlackPawn should not be empty", availablesPositionsBlackPawn.isEmpty());

        // On crée 6 pions supplémentaires pour tester les déplacements
        PawnEntity blackFriendPawn = new PawnEntity(PieceColorEnum.BLACK, new PositionEntity(5,6));
        PawnEntity blackEnemyPawn1 = new PawnEntity(PieceColorEnum.BLACK, new PositionEntity(5,3));
        PawnEntity blackEnemyPawn2 = new PawnEntity(PieceColorEnum.BLACK, new PositionEntity(4,3));
        PawnEntity blackEnemyPawn3 = new PawnEntity(PieceColorEnum.BLACK, new PositionEntity(6,3));
        
        PawnEntity whiteFriendPawn = new PawnEntity(PieceColorEnum.WHITE, new PositionEntity(5,3));
        PawnEntity whiteEnemyPawn1 = new PawnEntity(PieceColorEnum.WHITE, new PositionEntity(5,6));
        PawnEntity whiteEnemyPawn2 = new PawnEntity(PieceColorEnum.WHITE, new PositionEntity(4,6));
        PawnEntity whiteEnemyPawn3 = new PawnEntity(PieceColorEnum.WHITE, new PositionEntity(6,6));
        
        game.getBlackPieces().add(blackFriendPawn);
        availablesPositionsBlackPawn = blackPawn.generateAvailableMoves(game);
        Assert.assertTrue("availablesPositionsBlackPawn should be empty", availablesPositionsBlackPawn.isEmpty());
        game.getBlackPieces().remove(blackFriendPawn);
        
        game.getWhitePieces().add(whiteEnemyPawn1);
        availablesPositionsBlackPawn = blackPawn.generateAvailableMoves(game);
        Assert.assertTrue("availablesPositionsBlackPawn should be empty", availablesPositionsBlackPawn.isEmpty());
        game.getBlackPieces().remove(whiteEnemyPawn1);

        game.getWhitePieces().add(whiteEnemyPawn2);
        availablesPositionsBlackPawn = blackPawn.generateAvailableMoves(game);
        Assert.assertFalse("availablesPositionsBlackPawn should not be empty", availablesPositionsBlackPawn.isEmpty());
        game.getWhitePieces().remove(whiteEnemyPawn2);
        
        game.getWhitePieces().add(whiteEnemyPawn3);
        availablesPositionsBlackPawn = blackPawn.generateAvailableMoves(game);
        Assert.assertFalse("availablesPositionsBlackPawn should not be empty", availablesPositionsBlackPawn.isEmpty());
        game.getWhitePieces().remove(whiteEnemyPawn3);
        
        game.getWhitePieces().add(whiteFriendPawn);
        availablesPositionsWhitePawn = whitePawn.generateAvailableMoves(game);
        Assert.assertTrue("availablesPositionsWhitePawn should be empty", availablesPositionsBlackPawn.isEmpty());
        game.getWhitePieces().remove(whiteFriendPawn);
        
        game.getBlackPieces().add(blackEnemyPawn1);
        availablesPositionsWhitePawn = whitePawn.generateAvailableMoves(game);
        Assert.assertTrue("availablesPositionsWhitePawn should be empty", availablesPositionsBlackPawn.isEmpty());
        game.getBlackPieces().remove(blackEnemyPawn1);
        
        game.getBlackPieces().add(blackEnemyPawn2);
        availablesPositionsWhitePawn = whitePawn.generateAvailableMoves(game);
        Assert.assertFalse("availablesPositionsWhitePawn should not be empty", availablesPositionsBlackPawn.isEmpty());
        game.getBlackPieces().remove(blackEnemyPawn2);
        
        game.getBlackPieces().add(blackEnemyPawn3);
        availablesPositionsWhitePawn = whitePawn.generateAvailableMoves(game);
        Assert.assertFalse("availablesPositionsWhitePawn should not be empty", availablesPositionsBlackPawn.isEmpty());
        game.getBlackPieces().remove(blackEnemyPawn3);

    }

}
