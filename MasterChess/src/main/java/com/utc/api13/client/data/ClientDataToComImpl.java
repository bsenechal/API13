/**
 * 
 */
package com.utc.api13.client.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.util.Assert;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToCom;
import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MessageEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.GameStatusEnum;

/**
 * @author Benoît
 *
 */
public class ClientDataToComImpl implements IClientDataToCom {

    private DataClientManager instanceDataClientManager;

    public ClientDataToComImpl(DataClientManager instanceDataClientManager) {
        super();
        Assert.notNull(instanceDataClientManager,
                "[ClientDataToComImpl][Constructor] dataClientManager shouldn't be null");
        this.instanceDataClientManager = instanceDataClientManager;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.client.data.interfaces.IClientToComm#
     * getInstanceDataClientManager()
     */
    @Override
    public DataClientManager getInstanceDataClientManager() {
        return this.instanceDataClientManager;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.client.data.interfaces.IClientToComm#
     * setInstanceDataClientManager()
     */
    @Override
    public void setInstanceDataClientManager(DataClientManager instanceDataClientManager) {
        this.instanceDataClientManager = instanceDataClientManager;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientToComm#displayUsersList()
     * 
     */
    @Override
    public void displayUsersList(List<PublicUserEntity> connectedUserList) {
        Assert.notNull(connectedUserList,
                "[ClientDataToComImpl][displayUsersList] connectedUserList shouldn't be null");
        Assert.notNull(instanceDataClientManager.getUserLocal(),
                "[ClientDataToComImpl][displayUsersList] UserLocal shouldn't be null");
        Assert.notNull(instanceDataClientManager.getCurrentUsers(),
                "[ClientDataToComImpl][displayUsersList] currentUsers shouldn't be null");
        if (!connectedUserList.isEmpty()) {
            List<PublicUserEntity> connectedUserListTemp = new ArrayList<PublicUserEntity>();
            connectedUserList.forEach(u -> {
                if (!u.getId().equals(instanceDataClientManager.getUserLocal().getId())) {
                    connectedUserListTemp.add(u);
                }
            });
            instanceDataClientManager.getCurrentUsers().clear();
            instanceDataClientManager.getCurrentUsers().addAll(connectedUserListTemp);
        }
    }

    @Override
    public void displayProfile(PublicUserEntity user) {
        instanceDataClientManager.getIClientIHMToData().displayProfile(user);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientToComm#print_error(java.lang.
     * String)
     */
    @Override
    public void print_error(String error) {
        // instanceDataClientManager.print_error()
    }

    @Override
    public void displayAllGames(final List<GameEntity> games) {
        Assert.notNull(instanceDataClientManager.getCurrentGames(),
                "[ClientDataToComImpl][displayAllGames] currentGames shouldn't be null");
        instanceDataClientManager.getCurrentGames().clear();
        instanceDataClientManager.getCurrentGames().addAll(games);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientToComm#displayResult(java.
     * util.UUID, com.utc.api13.commun.entities.MoveEntity)
     */
    @Override
    public void displayResult(UUID idPlayer, MoveEntity move) {
        int fromLine = move.getFromPosition().getPositionX();
        int fromCol = move.getFromPosition().getPositionY();
        int toLine = move.getToPosition().getPositionX();
        int toCol = move.getToPosition().getPositionY();

        GameEntity thisgame = instanceDataClientManager.getCurrentGame();
        APieceEntity piece = thisgame.getPieceFromPosition(move.getFromPosition());
        Assert.notNull(thisgame, "[ClientDataToComImpl][displayResult] currentGames shouldn't be null");

        // delete the real destination piece
        thisgame.removePieceFromPosition(move.getToPosition());
        thisgame.movePiece(move);

        instanceDataClientManager.getIClientIHMToData().refreshChessBoard(fromLine, fromCol, toLine, toCol, piece,
                thisgame);
        // #Data l'erreur est normale, on attend que IHM mette à jour sa méthode

    }

    @Override
    public void sendMessageToChat(MessageEntity message) {
        // TODO: instanceDataClientManager.sendMessageToChat(message);
    }

    @Override
    public void sendAnswerForLeaving(boolean answer) {

        if (answer) {
            // Add game in local user saved game (in case the local user wants
            // to
            // save the game after ending)
            instanceDataClientManager.getUserLocal().getSavedGames().add(instanceDataClientManager.getCurrentGame());
            // Modify the played games
            instanceDataClientManager.getUserLocal()
                    .setNbPlayed(instanceDataClientManager.getUserLocal().getNbPlayed() + 1);

            // Display answer to local user
            UUID senderId = instanceDataClientManager.getUserLocal().getId()
                    .equals(instanceDataClientManager.getCurrentGame().getBlackPlayer().getId())
                            ? instanceDataClientManager.getCurrentGame().getWhitePlayer().getId()
                            : instanceDataClientManager.getCurrentGame().getBlackPlayer().getId();

            instanceDataClientManager.getIClientIHMToData().displayAnswer(senderId, answer,
                    "The player has quit the game ");
            instanceDataClientManager.getIClientIHMToData()
                    .displayAnswer(instanceDataClientManager.getUserLocal().getId(), answer, "You have quit the game ");
            // End the local game
            instanceDataClientManager.setCurrentGame(null);
        } else {
            instanceDataClientManager.getIClientIHMToData()
                    .displayAnswer(instanceDataClientManager.getUserLocal()
                            .getId(), answer,
                    "Your opponent doesn't want to quit the game, you'll keep playing. Surrend it if you want, but your stats will change ! ");
        }
    }

    @Override
    public void requestPlayerForLeaving(UUID uid) {
        instanceDataClientManager.getIClientIHMToData().otherPlayerLeaving();
    }

    @Override
    public void endGameByLeaving() {
        // Add game in local user saved game (in case the local user wants to
        // save the game after ending)
        instanceDataClientManager.getUserLocal().getSavedGames().add(instanceDataClientManager.getCurrentGame());
        // Set the current game to null
        instanceDataClientManager.setCurrentGame(null);
    }

    @Override
    public void notifyRejection(UUID uidSender, String REJECTION_MESSAGE) {
        instanceDataClientManager.getIClientIHMToData().displayAnswer(uidSender, false, REJECTION_MESSAGE);
        // Appelle la fonction displayAnswer qui n'est censé être utilisée que
        // lorsque le joueur refuse la partie
    }

    public void notify(String message) {
        // Surement à virer
    }

    @Override
    public void initGame(GameEntity game) {
        // Set the current game
        instanceDataClientManager.setCurrentGame(game);
        // Ask the IHM module to display the Chessboard
        instanceDataClientManager.getIClientIHMToData().displayChessBoard(game);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientToComm#newObserver(java.util.
     * UUID)
     */
    @Override
    public void newObserver(UUID idObserver) {
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientToComm#newPlayer(java.util.
     * UUID)
     */
    @Override
    public void newPlayer(UUID idPlayer) {
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientToComm#newReplay(com.utc.
     * api13.commun.entities.GameEntity)
     */
    @Override
    public void newReplay(GameEntity game) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendProposition(UUID uidSender, UUID uidReciever, boolean observable, boolean chattable) {

    }

    @Override
    public void printProposition(final UUID uidSender, boolean observable, boolean chattable, boolean timer,
            Integer timerInt) {
        instanceDataClientManager.getIClientIHMToData().displayProposition(uidSender, observable, chattable, timer,
                timerInt);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientToComm#victoryBySurrender()
     */
    @Override
    public void victoryBySurrender() {
        instanceDataClientManager.getUserLocal().getSavedGames().add(instanceDataClientManager.getCurrentGame());
        // Modify the played games
        instanceDataClientManager.getUserLocal()
                .setNbPlayed(instanceDataClientManager.getUserLocal().getNbPlayed() + 1);
        // increase the amount of won games
        instanceDataClientManager.getUserLocal().setNbWon(instanceDataClientManager.getUserLocal().getNbWon() + 1);
        // delete the current game
        instanceDataClientManager.setCurrentGame(null);
        // TODO uncomment when IHM function will be done
        // instanceDataClientManager.getIClientIHMToData().victoryBySurrend();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientToComm#endGameBySurrender()
     */
    @Override
    public void endGameBySurrender() {
        instanceDataClientManager.getUserLocal().getSavedGames().add(instanceDataClientManager.getCurrentGame());
        // Modify the played games
        instanceDataClientManager.getUserLocal()
                .setNbPlayed(instanceDataClientManager.getUserLocal().getNbPlayed() + 1);
        // increase the amount of lost games
        instanceDataClientManager.getUserLocal().setNbLost(instanceDataClientManager.getUserLocal().getNbLost() + 1);
        // delete the current game
        instanceDataClientManager.setCurrentGame(null);
        // TODO uncomment when IHM function will be done
        // instanceDataClientManager.getIClientIHMToData().endGameBySurrend();
    }

    @Override
    public void displayMessage(final String message) {
        MessageEntity newMessage = new MessageEntity();
        newMessage.setText(message);
        instanceDataClientManager.getCurrentGame().getMessages().add(newMessage);
        instanceDataClientManager.getIClientIHMToData().displayMessage(message);
    }

    @Override
    public void notifyConnection(final PublicUserEntity user) {
        instanceDataClientManager.getCurrentUsers().add(user);
    }

    @Override
    public void notifyDisconnection(final UUID idUser) {
        instanceDataClientManager.getCurrentUsers().removeIf(u -> idUser.equals(u.getId()));
    }

    @Override
    public void nextTurn(final GameStatusEnum status, final UUID nextPlayer) {
        // Ulysse : lourd : remplacé par switchCurrentUser
        // if (GameStatusEnum.CONTINUE.equals(status)) {
        // instanceDataClientManager.getCurrentGame().setCurrentPlayer(
        // (instanceDataClientManager.getCurrentGame().getBlackPlayer().getId().equals(nextPlayer))
        // ? instanceDataClientManager.getCurrentGame().getBlackPlayer()
        // : instanceDataClientManager.getCurrentGame().getWhitePlayer());
        // }
        // Assert.notNull(instanceDataClientManager.getCurrentGames(),
        // "[ClientDataToComImpl][setFinishedStatus] currentGames shouldn't be
        // null");
        GameEntity game = instanceDataClientManager.getCurrentGame();

        game.switchCurrentUser();
        // set the game status :
        game.setIsFinished(status);

        // alert IHM:
        instanceDataClientManager.getIClientIHMToData().activateCases(game.getCurrentPlayer(), status);

        // TODO Ulysse : virer le switch ?

        PrivateUserEntity localUser = instanceDataClientManager.getUserLocal();
        switch (status) {

        // case CHECK:
        // break;
        case CHECKMATE:
            if (localUser.getId().equals(game.getCurrentPlayer().getId())) {
                localUser.setNbLost(localUser.getNbLost() + 1);
            } else {
                localUser.setNbWon(localUser.getNbWon() + 1);
            }
        case DRAW:
            localUser.setNbPlayed(localUser.getNbPlayed() + 1);
            instanceDataClientManager.setCurrentGame(null);
            break;

        default:
            // CHECK & CONTINUE
            break;

        }

    }

    @Override
    public void updateDistantProfile(PublicUserEntity userToUpdate) {
        Assert.notNull(instanceDataClientManager.getCurrentUsers(),
                "[ClientDataToComImpl][updateDistantProfile] current users shouldn't be null");
        Assert.notNull(instanceDataClientManager.getCurrentUsers(),
                "[ClientDataToComImpl][updateDistantProfile] Given user shouldn't be null");

        instanceDataClientManager.getCurrentUsers().removeIf(u -> u.getId().equals(userToUpdate.getId()));
        instanceDataClientManager.getCurrentUsers().add(userToUpdate);
    }
}
