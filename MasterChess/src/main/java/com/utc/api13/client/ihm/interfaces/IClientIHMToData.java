package com.utc.api13.client.ihm.interfaces;

import java.util.UUID;

import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.GameStatusEnum;

public interface IClientIHMToData {

    // ne concerne que les éléments de data retournés en asynchrone suite à
    // echange avec le serveur
    // le reste est synchrone et appelé par IHM dans l'interface
    // ClientIHMToDataImpl

    /**
     * This method asks the HMI to display the distant profile sent in parameter
     * on screen. The HMI must have previously asked Data for this User
     * information
     * 
     * @param u
     *            a PublicUserEntity object containing all the public
     *            information on the user, passed in parameter
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
     * @return void
     */
    public void displayAnswer(UUID uidSender, boolean answer, String message);

    /**
     * Asks the HMI to display the chessboard when both user have accepted the
     * game, and the game has been created on server
     * 
     * @return void
     */
    public void displayChessBoard(GameEntity g);

    /**
     * After a piece is moved
     */
    public void refreshChessBoard(int line_from, int col_from, int line_to, int col_to, APieceEntity piece, GameEntity game);

    /**
     * Method called when distant user sent a chat message, in order the HMI to
     * display this new message
     * 
     * @param newMessage
     *            String containing the message received from distant user
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
     * when a user leaves before the end of the game, he needs the approbation
     * of the other one to change the final score
     */
    // displayAnswerForLeaving(boolean answer)

    /**
     * when a en error occurs between data and com modules
     */
    void displayError(String errorMessage);

    /**
     * to display confirmation message that everything went ok
     */
    void displayConfirmation(String confirmationMessage);
    /*
     * public void didReceiveBoard(); public void
     * didReceiveEndOfGameBySurrender(); public void
     * didReceiveEndOfGameByVictory(); public void didReceiveEndOfGameByLoss();
     * public void didReceiveEndOfGameByTie(); public void
     * didReceiveEndOfObservation(); public void didReceiveNewChatMessage();
     * public void answerForLeavingIsYes(); public void answerForLeavingIsNo();
     * public void didReceiveRequestToInterruptGame(); public void
     * publicProfileHasBeenCorrectlyUpdated(); public void
     * publicProfileHasFailedToBeUpdated(); public void
     * didRefuseConnectionToServer(); public void
     * listOfObserversHasBeenUpdated(); public void
     * didReceiveListOfConnectedUsers(); public void moveWasAccepted(); public
     * void moveWasDenied(); public void newMoveFromOpponent(); public void
     * didReceiveProfileOtherUser(); public void
     * errorReceivingProfileOtherUser(); public void
     * didReceivePropositionOfGame(); public void propositionOfGameAccepted();
     * public void propositionOfGameDenied(); public void
     * didReceiveListOfOngoingAndReplayedGames(); public void
     * didReceiveErrorInChatMessage();
     */

    // erreurs : il suffit de catcher les exceptions
    // attention dans les fonctions à tout le temps mettre getCardGame pour
    // récup jeu local
    
    public void activateCases(PublicUserEntity currentUser, GameStatusEnum status);

    public void closeGameScreen(boolean bool);
}
