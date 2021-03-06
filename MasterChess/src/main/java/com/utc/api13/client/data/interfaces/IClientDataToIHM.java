package com.utc.api13.client.data.interfaces;

import java.io.File;
import java.util.List;
import java.util.UUID;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

import javafx.collections.ObservableList;

/**
 * 
 * This interface will be given to the IHM module. This will be use to call
 * Data's methods to react on users' actions
 *
 *
 */

public interface IClientDataToIHM {

    /**
     * Demands the list of users online from the server
     */
    public void getUsers();

    /**
     * Demands a user online with the given uuid
     * 
     * @param iduser
     *            uuid of the demanded user
     */
    public void getUserInfo(final UUID iduser);

    /**
     * 
     * @return Returns the local user
     */
    public PrivateUserEntity getLocalUser();

    /**
     * Demands all games from server
     */
    public void getAllGames();

    /**
     * Connect a user to the app
     * 
     * @param login
     *            login of user
     * @param password
     *            password of user
     * @throws FunctionalException
     *             exception when login or password are incorrect
     * @throws TechnicalException
     *             technical exception
     */
    public void connect(final String login, final String password) throws FunctionalException, TechnicalException;

    /**
     * Signs out the local user from the app
     * 
     * @throws FunctionalException
     *             error while saving the local user: uid and/or login and/or
     *             password missing
     * @throws TechnicalException
     *             error while saving the local user: input/ouput exceptions
     */
    public void disconnect() throws TechnicalException, FunctionalException;

    /**
     * @author ulyss_000 Moves a piece to the given position
     * @param piece
     * @param position
     * @throws FunctionalException
     *             ErrorTypeEnum.MOVE_IMPOSSIBLE -> Is thrown if the move is not
     *             possible !
     */
    public void move(APieceEntity piece, PositionEntity position) throws FunctionalException;

    /**
     * informs the server that the local user is leaving as an observer
     */
    public void observerLeave();

    /**
     * informs the server that the local user is leaving the game he(she) is
     * playing
     */
    public void requestPlayerForLeaving();

    /**
     * send user's answer to leaving request from the other player
     * 
     * @param answer
     *            true if agree, else false
     */
    void sendAnswerForLeaving(boolean answer);

    /**
     * Updates the info of local user
     * 
     * @param user
     *            local user
     * @throws FunctionalException
     *             if the saving fails due to some validation
     * @throws TechnicalException
     *             technical exception
     */

    public void updateProfile(PrivateUserEntity user) throws TechnicalException, FunctionalException;

    /**
     * local user asks to watch game
     * 
     * @param idGame
     *            id of game to watch
     */
    public void watchGame(UUID idGame);

    /**
     * Replays a game do not call the server only between ihm and data
     * 
     * @param idGame
     *            id of the game
     */
    public void chargeReplay(UUID idGame);

    /**
     * Begins the replay of saved game Informs the server of the new game Update
     * the list of games
     */
    public void beginReplay();

    /**
     * Saves the current game into storage
     * 
     * @throws TechnicalException
     *             technical error
     * @throws FunctionalException
     *             validation exception
     */
    public void saveGame() throws TechnicalException, FunctionalException;

    /**
     * get the current game
     * 
     * @return the current game
     */
    public GameEntity getCurrentGame();

    /**
     * Creates a game proposition and sends it to another player
     * 
     * @param uidReciever
     *            opponent
     * @param enquirerUUID
     *            the user
     * @param chattable
     *            true if the chat is allowed
     * @param observable
     *            true if observers are allowed
     * @param timer
     *            true if user wants to add a timer to each move
     * @param timeInt
     *            timer added in sec
     */
    public void createProposition(UUID uidReciever, UUID enquirerUUID, boolean chattable, boolean observable,
            boolean timer, Integer timeInt);

    /**
     * Surrend a game - user will lose the game
     */
    public void surrender();

    /**
     * Sends message for chat
     * 
     * @param message
     *            message to send
     */
    public void sendChatText(String message);

    /**
     * 
     * @return Returns list of online users
     */
    public ObservableList<PublicUserEntity> getUserList();

    /**
     * Creates a new user
     * 
     * @param user
     *            The private user to be created
     * @throws FunctionalException
     *             data access exception
     * @throws TechnicalException
     *             functional exception: to display for the user
     */

    public void createProfile(PrivateUserEntity user) throws FunctionalException, TechnicalException;

    /**
     * Sends the decision of the local user to the other distant proposer
     * 
     * @param idUser
     *            id of player who sent the game proposition
     * @param answer
     *            true if the proposition is accepted
     * @param observable
     *            true if the game may be observable by others
     * @param chattable
     *            true if the chat is available during the game
     * @param time
     *            true if user wants to add a timer to each move
     * @param timeInt
     *            timer added in sec
     * @throws TechnicalException
     *             exception when extracting bytes from image in local user
     *             profile
     */
    public void sendResponse(UUID idUser, UUID requirer, boolean answer, boolean observable, boolean chattable,
            boolean time, Integer timeInt) throws TechnicalException;

    /**
     * Imports the profile of the user contained in file into the app
     * 
     * @param file
     *            file containing the profile of user
     * @param force
     *            if the same user is found in storage, the ancient one is
     *            overwritten if force is true otherwise an exception is thrown
     * @throws FunctionalException
     *             when a user with same id exists already
     * @throws TechnicalException
     *             error while saving
     */
    public void importProfile(File file, boolean force) throws FunctionalException, TechnicalException;

    /**
     * calls the importProfile (File file, boolean force) method with force
     * parameter at false<br/>
     * To call at the first time
     * 
     * @param file
     *            file containing the profile to import
     * @throws FunctionalException
     *             when a user with same id exists already
     * @throws TechnicalException
     *             error while saving
     */
    public void importProfile(File file) throws FunctionalException, TechnicalException;

    /**
     * Exports the local user profile so that the user will be able to import it
     * in an other app
     * 
     * @return file containing the user profile
     * @throws TechnicalException
     *             error while copying exported file to export directory
     */
    public File exportProfile() throws TechnicalException;

    /**
     * 
     * @return Returns the list of observable
     */
    public ObservableList<GameEntity> getGamesList();

    /**
     * @return Returns the list of PositionEntity
     */
    public List<PositionEntity> getAvailablesMoves(int line, int col);

    /**
     * Play the chosen move
     * 
     * @param fromLine
     *            the current line of the piece
     * @param fromCol
     *            the current column of the piece
     * @param toLine
     *            the destination line of the piece
     * @param toCol
     *            the destination column of the piece
     */
    public void playMove(int fromLine, int fromCol, int toLine, int toCol);

    /**
     * Removes the given user from game's chat
     * 
     * @param idUser
     *            uid of user to remove from chat
     */
    public void removeUserFromChat(UUID idUser);

    /**
     * Allows IHM to kill the CurrentGame to enable the launch og a new one !
     */
    public void killCurrentGame();
}