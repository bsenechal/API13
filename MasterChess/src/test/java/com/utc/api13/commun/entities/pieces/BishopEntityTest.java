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
 * @author Lucie
 *
 */
public class BishopEntityTest {

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

        Assert.assertEquals("Error initing game positions", game.getBlackPieces().size(), 16);
        Assert.assertEquals("Error initing game positions", game.getWhitePieces().size(), 16);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGenerateAvailableMoves() {
        // I only test with one bishop by color
        final PositionEntity blackBishopPosition = new PositionEntity(3, 8);
        final PositionEntity whiteBishopPosition = new PositionEntity(3, 1);
        BishopEntity whiteBishopFirst = new BishopEntity(PieceColorEnum.WHITE, 3);
        BishopEntity blackBishopFirst = new BishopEntity(PieceColorEnum.BLACK, 6);
        BishopEntity blackBishopSecond = new BishopEntity(PieceColorEnum.BLACK, 3);

        Assert.assertEquals("Error init King position", whiteBishopFirst.getPosition().getPositionX(),
                whiteBishopPosition.getPositionX());
        Assert.assertEquals("Error init King position", whiteBishopFirst.getPosition().getPositionY(),
                whiteBishopPosition.getPositionY());

        Assert.assertEquals("Error init King position", blackBishopSecond.getPosition().getPositionX(),
                blackBishopPosition.getPositionX());
        Assert.assertEquals("Error init King position", blackBishopSecond.getPosition().getPositionY(),
                blackBishopPosition.getPositionY());

        List<PositionEntity> availablesPositionsWhiteBishop = whiteBishopFirst.generateAvailableMoves(game);
        Assert.assertTrue("availablesPositionsWhiteBishop should be empty", availablesPositionsWhiteBishop.isEmpty());

        // game.getWhitePieces().remove(new PawnEntity(PieceColorEnum.WHITE,2));
        // System.out.println(whiteBishopFirst.generateAvailableMoves(game));

        // On supprime toutes les pieces
        game.setBlackPieces(new ArrayList<APieceEntity>());
        game.getBlackPieces().add(blackBishopFirst);

        game.setWhitePieces(new ArrayList<APieceEntity>());
        game.getWhitePieces().add(whiteBishopFirst);
        System.out.println("My white bishop :" + whiteBishopFirst.getPosition().getPositionX() + ","
                + whiteBishopFirst.getPosition().getPositionY());

        availablesPositionsWhiteBishop = whiteBishopFirst.generateAvailableMoves(game);
        System.out.println("White possible move");
        for (PositionEntity tmp : availablesPositionsWhiteBishop) {
            System.out.println(String.valueOf(tmp.getPositionX()) + "," + String.valueOf(tmp.getPositionY()));
        }
        // Sans piece, il y a 7 position posibles
        Assert.assertEquals(availablesPositionsWhiteBishop.size(), 7);

        // On ajoute un pion bloquant
        PawnEntity PawnBlock = new PawnEntity(PieceColorEnum.WHITE, new PositionEntity(4, 2));
        System.out.println("Bloking pawn :" + PawnBlock.getPosition().getPositionX() + ","
                + PawnBlock.getPosition().getPositionY());
        game.getWhitePieces().add(PawnBlock);
        availablesPositionsWhiteBishop = whiteBishopFirst.generateAvailableMoves(game);
        System.out.println("White possible move with blocking pawn : ");
        for (PositionEntity tmp : availablesPositionsWhiteBishop) {
            System.out.println(String.valueOf(tmp.getPositionX()) + "," + String.valueOf(tmp.getPositionY()));
        }
        Assert.assertEquals(availablesPositionsWhiteBishop.size(), 2);

        // On supprime le pion bloquant
        game.removePiece(PawnBlock);

        // On ajoute un pion ennemi
        PawnEntity PawnBlockBlack = new PawnEntity(PieceColorEnum.BLACK, new PositionEntity(4, 2));
        game.addPiece(PawnBlockBlack);
        System.out.println("Ennemi bloking pawn :" + PawnBlockBlack.getPosition().getPositionX() + ","
                + PawnBlockBlack.getPosition().getPositionY());
        availablesPositionsWhiteBishop = whiteBishopFirst.generateAvailableMoves(game);
        System.out.println("White possible move with ennemi blocking pawn : ");
        for (PositionEntity tmp : availablesPositionsWhiteBishop) {
            System.out.println(String.valueOf(tmp.getPositionX()) + "," + String.valueOf(tmp.getPositionY()));
        }
        Assert.assertEquals(availablesPositionsWhiteBishop.size(), 3);
    }

}
