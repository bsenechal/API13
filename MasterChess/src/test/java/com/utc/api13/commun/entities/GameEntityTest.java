/**
 * 
 */
package com.utc.api13.commun.entities;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.utc.api13.commun.entities.pieces.RookEntity;

/**
 * @author Benoît
 *
 */
public class GameEntityTest {
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
     * {@link com.utc.api13.commun.entities.GameEntity#getPieceFromPosition(com.utc.api13.commun.entities.PositionEntity)}
     * .
     */
    @Test
    public void testGetPieceFromPosition() {

        // test sur une position où il y a une pièce
        APieceEntity piece = game.getPieceFromPosition(new PositionEntity(1, 1));

        Assert.assertNotNull("piece shouldn't be null", piece);
        Assert.assertNotNull("piece should be an instance of RookEntity",
                piece.getClass().isInstance(RookEntity.class));

        // test sur une position où il n'y a pas de pièce
        piece = game.getPieceFromPosition(new PositionEntity(5, 5));
        Assert.assertNull("piece should be null", piece);

        // test avec une position null
        piece = game.getPieceFromPosition(null);
        Assert.assertNull("piece should be null", piece);
    }
    
    
    /**
     * Test method for
     * {@link com.utc.api13.commun.entities.GameEntity#isFinished()}
     * .
     */
    @Test
    public void testIsFinished() {

        // test du statut initial du game :
    	System.out.println(game.isFinished());
    }

}
