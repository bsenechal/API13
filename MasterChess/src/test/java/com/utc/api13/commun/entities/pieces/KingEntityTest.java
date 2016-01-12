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
        
        Assert.assertEquals("Error init King position" , game.getBlackPieces().size(), 16);
        Assert.assertEquals("Error init King position" , game.getWhitePieces().size(), 16);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.utc.api13.commun.entities.pieces.KingEntity#generateAvailableMoves(com.utc.api13.commun.entities.GameEntity)}.
     */
    @Test
    public void testGenerateAvailableMoves() {
        final PositionEntity blackKingPosition = new PositionEntity(5,8);
        final PositionEntity whiteKingPosition = new PositionEntity(5,1);
        KingEntity whiteKing = new KingEntity(PieceColorEnum.WHITE);
        KingEntity blackKing = new KingEntity(PieceColorEnum.BLACK);
        
        Assert.assertEquals("Error init King position" , whiteKing.getPosition().getPositionX(), whiteKingPosition.getPositionX());
        Assert.assertEquals("Error init King position" , whiteKing.getPosition().getPositionY(), whiteKingPosition.getPositionY());
        
        Assert.assertEquals("Error init King position" , blackKing.getPosition().getPositionX(), blackKingPosition.getPositionX());
        Assert.assertEquals("Error init King position" , blackKing.getPosition().getPositionY(), blackKingPosition.getPositionY());
        
        
        
        List<PositionEntity> availablesPositionsWhiteKing = whiteKing.generateAvailableMoves(game);
        Assert.assertTrue("availablesPositionsWhiteKing should be empty", availablesPositionsWhiteKing.isEmpty());
        
        game.setCurrentPlayer(blackPlayer);
        
        List<PositionEntity> availablesPositionsBlackKing = blackKing.generateAvailableMoves(game);
        Assert.assertTrue("availablesPositionsBlackKing should be empty", availablesPositionsBlackKing.isEmpty());
        
        
        
        // Suppression des pièces autre que le roi
        game.setBlackPieces(new ArrayList<APieceEntity>());
        game.getBlackPieces().add(blackKing);
        
        game.setWhitePieces(new ArrayList<APieceEntity>());
        game.getWhitePieces().add(whiteKing);
        
        availablesPositionsBlackKing = blackKing.generateAvailableMoves(game);

        availablesPositionsWhiteKing = whiteKing.generateAvailableMoves(game);
        
        // STACKOVERFLOW !!!!! -_-
        Assert.assertFalse("availablesPositionsBlackKing shouldn't be empty", availablesPositionsBlackKing.isEmpty());
        Assert.assertFalse("availablesPositionsWhiteKing shouldn't be empty", availablesPositionsWhiteKing.isEmpty());
    }

}
