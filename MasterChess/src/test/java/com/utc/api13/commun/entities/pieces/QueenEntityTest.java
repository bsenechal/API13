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

	public static void displayChessBoard(List<PositionEntity> positions) {
		for (int x = 1; x <= 8; x++) {
			for (int y = 1; y <= 8; y++) {
				System.out.print("|");
				boolean available = Boolean.TRUE;
				for (PositionEntity pos : positions) {
					if (pos.equals(new PositionEntity(y, x))) {
						available = Boolean.FALSE;
					}

				}
				if (available) {
					System.out.print("O");
				} else {
					System.out.print("X");
				}
			}
			System.out.println("|");
		}
	}

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

		Assert.assertEquals("Error init King position", whiteQueen.getPosition().getPositionX(),
				whiteQueenPosition.getPositionX());
		Assert.assertEquals("Error init King position", whiteQueen.getPosition().getPositionY(),
				whiteQueenPosition.getPositionY());

		Assert.assertEquals("Error init King position", blackQueen.getPosition().getPositionX(),
				blackQueenPosition.getPositionX());
		Assert.assertEquals("Error init King position", blackQueen.getPosition().getPositionY(),
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

		// System.out.println("white pieces");
		// displayChessBoard(availablesPositionsWhiteQueen);

		// STACKOVERFLOW !!!!! -_-
		Assert.assertFalse("availablesPositionsBlackKing shouldn't be empty", availablesPositionsBlackQueen.isEmpty());
		Assert.assertFalse("availablesPositionsWhiteKing shouldn't be empty", availablesPositionsWhiteQueen.isEmpty());
	}

}
