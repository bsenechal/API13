package com.utc.api13.client.ihm.interfaces;

import java.util.UUID;

import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.GameStatusEnum;

public interface IClientIHMToData {

    /**
     * This method asks the HMI to display the distant profile sent in parameter
     * on screen. The HMI must have previously asked Data for this User
     * information
     * 
     * @param u
     *            a PublicUserEntity object containing all the public
     *            information on the user, passed in parameter
     *@return void
     */
    public void displayProfile(PublicUserEntity u);

    /**
     * This method informs the local HMI that the distant player has left the
     * game
     * 
     * @return void
     */
    public void otherPlayerLeaving();

    /**
     * This method notifies the local HMI that another user wants to play. It
     * informs the HMI of the options selected by the distant user for the game,
     * in order to display them.
     * 
     * @param uidSender
     *            The unique identifier of the distant user that sends the game
     *            proposition. HMI has to store it, for future communication
     *            that will always need that UID.
     * @param observable
     *            The observability of the proposed game sent by the distant
     *            user. True if observable, false otherwise
     * @param chattable
     *            Indicates wether or not the chat will be active in the
     *            proposed game
     * @return void
     */
    public void displayProposition(UUID uidSender, boolean observable, boolean chattable, boolean timer,
            Integer timeInt);

    /**
     * This method notifies local user (who proposed a game) that distant user
     * rejected the proposition of game
     * 
     * @param uidSender
     *            The unique identifier of the distant answer who accepted or
     *            rejected the game proposition. Allows to identify which user
     *            answered.
     * @param boolean answer
     *            The answer of the idstant player.
     * @param String message
     *        	   A specific message to display.
     * @return void
     */
    public void displayAnswer(UUID uidSender, boolean answer, String message);

    /**
     * Asks the HMI to display the chessboard when both user have accepted the
     * game, and the game has been created on server
     * @param g
     * 		  The game entity of the launched game.
     * 
     * @return void
     */
    public void displayChessBoard(GameEntity g);

    /**
     * After a piece is moved
     * @param lineFrom
     * 		  The line that has to be refreshed 
     * @param colFrom
     *        The column that has to be refreshed
     * @param lineTo
     *        The target line
     * @param colTo	
     *        The target column
     * @param piece
     *        The piece concerned
     * @param game
     *        The related Game Entity
     * @return void
     */
    public void refreshChessBoard(int lineFrom, int colFrom, int lineTo, int colTo, APieceEntity piece,
            GameEntity game);

    /**
     * Method called when distant user sent a chat message, in order the HMI to
     * display this new message
     * 
     * @param newMessage
     *            String containing the message received from distant user
     * @return void
     */
    public void displayMessage(String newMessage);

    /**
     * Asks the HMI to refresh the observer when a new observer is added/removed
     * : the modified list is sent to ihm by data through the local gameEntity
     * 
     * @return void
     */
    public void refreshObserverList();

    /**
     * Displays the game for an observer -live
     * 
     * @return void
     */
    public void displayGameLiveObserver();

    /**
     * when a en error occurs between data and com modules
     * @param errorMessage
     * 		  The error message that has to be displayed.
     * @return void
     */
    void displayError(String errorMessage);

    /**
     * to display confirmation message that everything went ok
     * @param errorMessage
     * 		  The confirmation message that has to be displayed.
     * @return void
     */
    void displayConfirmation(String confirmationMessage);
    /**
     * to allow the cases of the current player 
     * @param currentUser
     *        the user concerned
     * @param status
     *        the status of this user
     */
    public void activateCases(PublicUserEntity currentUser, GameStatusEnum status);
    /**
     * to close the game screen
     * @param bool
     * 		  If the game screen has to be closed or not.
     * @return void
     */
    public void closeGameScreen(boolean bool);
}
