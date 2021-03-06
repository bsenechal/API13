
/**
 * 
 */
package com.utc.api13.client.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.utc.api13.client.com.ClientComToDataImpl;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

import javafx.collections.FXCollections;

/**
 * @author DATA
 *
 */
public class ClientDataToIHMImplTest {
    @Mock
    private DataClientManager dataClientManager;

    @Mock
    private ClientComToDataImpl clientComToDataImpl;

    @Mock
    private ClientDataToIHMImpl clientDataToIHMImpl;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        dataClientManager = new DataClientManager();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void disconnectTest() {
        PrivateUserEntity privateUser = new PrivateUserEntity();
        dataClientManager.setUserLocal(privateUser);
        dataClientManager.setIClientComToData(clientComToDataImpl);
        Mockito.doNothing().when(clientComToDataImpl).disconnect(privateUser.getId());

        try {
            dataClientManager.getClientDataToIHMImpl().disconnect();
        } catch (TechnicalException technicalException) {
            Assert.fail("Technical error : " + technicalException.getMessage());
        } catch (FunctionalException functionalException) {
            Assert.fail("Functional error : " + functionalException.getMessage());
        }

        Assert.assertNotNull("dataClientManager shouldn't be null", dataClientManager);
        Assert.assertNull("UserLocal should be null", dataClientManager.getUserLocal());
    }

    @Test
    public void getUserListTest() {
        final PublicUserEntity user = new PublicUserEntity();

        dataClientManager.getCurrentUsers().add(user);
        Assert.assertNotNull("The list shouldn't be null", dataClientManager.getClientDataToIHMImpl().getUserList());
        Assert.assertTrue("The list should contain the user " + user.toString(),
                dataClientManager.getClientDataToIHMImpl().getUserList().contains(user));
    }

    @Test
    public void getCurrentGameTest() {
        final GameEntity game = new GameEntity();

        dataClientManager.setCurrentGame(game);
        Assert.assertNotNull("The current game shouldn't be null",
                dataClientManager.getClientDataToIHMImpl().getCurrentGame());
        Assert.assertEquals("Current game should be equal to " + game.toString(),
                dataClientManager.getClientDataToIHMImpl().getCurrentGame(), game);
    }

    @Test
    public void getLocalUserTest() {
        PrivateUserEntity localUser = new PrivateUserEntity();
        dataClientManager.setUserLocal(localUser);

        Assert.assertNotNull("The local user shouldn't be null",
                dataClientManager.getClientDataToIHMImpl().getLocalUser());
        Assert.assertEquals("Current game should be equal to " + localUser.toString(),
                dataClientManager.getClientDataToIHMImpl().getLocalUser(), localUser);
    }

    @Test
    public void getGamesListTest() {
        List<GameEntity> games = new ArrayList<GameEntity>();
        GameEntity specificGame = new GameEntity();
        final int nbGames = 5;
        final int nbGamesExpected = nbGames + 1;

        games.add(specificGame);

        for (int i = 0; i < nbGames; i++) {
            games.add(new GameEntity());
        }

        dataClientManager.setCurrentGames(FXCollections.observableArrayList(games));

        Assert.assertNotNull("dataClientManager shouldn't be null", dataClientManager);
        Assert.assertEquals("CurrentGames should contains " + nbGamesExpected + " games", nbGames + 1,
                dataClientManager.getClientDataToIHMImpl().getGamesList().size());
        Assert.assertTrue("CurrentGames should contains " + specificGame.toString(),
                dataClientManager.getClientDataToIHMImpl().getGamesList().contains(specificGame));
    }

    // Comment vérifier la svg ?
    @Test
    public void saveGameTest() {
        PublicUserEntity whitePlayer = new PublicUserEntity("whitelogin");
        PublicUserEntity blackPlayer = new PublicUserEntity("blacklogin");

        GameEntity newGame = new GameEntity();
        newGame.setBlackPlayer(blackPlayer);
        newGame.setWhitePlayer(whitePlayer);
        newGame.setIsOservable(true);
        newGame.setIsChattable(true);
        newGame.setTimer(true);
        newGame.setTimerInt(7);

        dataClientManager.setCurrentGame(newGame);
        try {
            clientDataToIHMImpl.saveGame();
        } catch (TechnicalException e) {
            Assert.fail("Erreur technique : " + e.getMessage());
        } catch (FunctionalException e) {
            Assert.fail("Erreur fonctionnelle : " + e.getMessage());
        }

    }

    @Test
    public void playMoveTest() {

        PublicUserEntity whitePlayer = new PublicUserEntity("whitelogin");
        PublicUserEntity blackPlayer = new PublicUserEntity("blacklogin");

        GameEntity newGame = new GameEntity();
        newGame.setBlackPlayer(blackPlayer);
        newGame.setWhitePlayer(whitePlayer);
        newGame.setIsOservable(true);
        newGame.setIsChattable(true);
        newGame.setTimer(true);
        newGame.setTimerInt(7);
        newGame.setCurrentPlayer(whitePlayer);

        dataClientManager.setCurrentGame(newGame);

        int fromLine = 2;
        int toLine = 3;
        int fromCol = 2;
        int toCol = 2;

        PositionEntity fromposition = new PositionEntity(fromLine, fromCol);
        PositionEntity toposition = new PositionEntity(toLine, toCol);
        UUID currentplayer = dataClientManager.getCurrentGame().getCurrentPlayer().getId();
        APieceEntity piece = dataClientManager.getCurrentGame().getPieceFromPosition(fromposition);
        MoveEntity move = new MoveEntity(new Date(), fromposition, toposition, piece);

        Mockito.doNothing().when(clientComToDataImpl).validateMove(currentplayer, move);

        dataClientManager.setIClientComToData(clientComToDataImpl);

        dataClientManager.getClientDataToIHMImpl().playMove(fromLine, fromCol, toLine, toCol);
    }

    @Test
    public void moveTest() {
        PrivateUserEntity whitePrivatePlayer = new PrivateUserEntity("whitelogin", "whitemdp");
        PublicUserEntity whitePlayer = new PublicUserEntity("whitelogin");

        PublicUserEntity blackPlayer = new PublicUserEntity("blacklogin");

        GameEntity newGame = new GameEntity();
        newGame.setBlackPlayer(blackPlayer);
        newGame.setWhitePlayer(whitePlayer);
        newGame.setCurrentPlayer(whitePlayer);

        dataClientManager.setUserLocal(whitePrivatePlayer);

        dataClientManager.setCurrentGame(newGame);

        APieceEntity pieceToMove = dataClientManager.getCurrentGame().getPieceFromPosition(new PositionEntity(4, 2));

        MoveEntity move = new MoveEntity(new Date(), new PositionEntity(4, 2), new PositionEntity(4, 3), pieceToMove);

        Mockito.doNothing().when(clientComToDataImpl).validateMove(whitePlayer.getId(), move);

        dataClientManager.setIClientComToData(clientComToDataImpl);

        try {

            dataClientManager.getClientDataToIHMImpl().move(pieceToMove, new PositionEntity(4, 3));
        } catch (FunctionalException e) {
            Assert.fail("Error : " + e.getMessage());
        }
    }
    /*
     * @Test public void sendAnswerForLeavingTest() { GameEntity game = new
     * GameEntity(); PrivateUserEntity userPrivate = new PrivateUserEntity();
     * PublicUserEntity whiteUser = new PublicUserEntity(); PublicUserEntity
     * blackUser = new PublicUserEntity(); final UUID idWhite =
     * UUID.randomUUID(); final UUID idBlack = UUID.randomUUID();
     * userPrivate.setId(idWhite); whiteUser.setId(idWhite);
     * blackUser.setId(idBlack); game.setBlackPlayer(blackUser);
     * game.setWhitePlayer(whiteUser);
     * dataClientManager.setUserLocal(userPrivate);
     * dataClientManager.setCurrentGame(game);
     * Assert.assertNotNull(dataClientManager.getCurrentGame());
     * 
     * // If false then nothing -> game continue
     * dataClientManager.getClientDataToIHMImpl().sendAnswerForLeaving(false);
     * Assert.assertNotNull(dataClientManager.getCurrentGame());
     * 
     * // If true then quit game without changing stats
     * dataClientManager.getUserLocal().setSavedGames(new
     * ArrayList<GameEntity>());
     * dataClientManager.getClientDataToIHMImpl().sendAnswerForLeaving(true);
     * Assert.assertNull(dataClientManager.getCurrentGame()); }
     */
}
