package com.utc.api13.client.data.interfaces;

import java.io.File;
import java.util.UUID;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PieceEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

import javafx.collections.ObservableList;


public interface IClientDataToIHM {
    
	/**
	 * Demands the list of users online from the server
	 */
    public void getUsers();
    
    /**
     *Demands a user online with the given uuid
     * @param iduser uuid of the demanded user
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
     * @param login login of user
     * @param password password of user
     * @throws FunctionalException exception when login or password are incorrect
     * @throws TechnicalException technical exception
     */
    public void connect(final String login, final String password) throws FunctionalException, TechnicalException;
    
    /**
     * Signs out the local user from the app
     */
    public void disconnect();
    
    /**
     * Moves a piece to the given position
     * @param piece
     * @param position
     */
    public void move(PieceEntity piece, PositionEntity position);
    
    /**
     * informs the server that the local user is leaving as an observer
     */
    public void observerLeave();
    
    /**
     * informs the server that the local user is leaving the game he(she) is playing
     */
    public void requestPlayerForLeaving();
    
    public void otherPlayerLeaving();
    
    /**
     * Updates the info of local user
     * @param user local user
     * @throws FunctionalException if the saving fails due to some validation
     * @throws TechnicalException technical exception
     */
    
    public void updateProfile(PrivateUserEntity user) throws TechnicalException, FunctionalException;
    
    
    //TODO:à virer
    public void notify (String message);
    
    /**
     * local user asks to watch game
     * @param idGame id of game to watch
     */
    public void watchGame (UUID idGame);
    
    /**
     * Replays a game
     * do not call the server
     * only between ihm and data
     * @param idGame id of the game
     */
    public void chargeReplay(UUID idGame);
    
    /**
     * Begins the replay of saved game
     * Informs the server of the new game
     * Update the list of games
     */
    public void beginReplay();
    
    /**
     * Saves the current game into storage
     * @throws TechnicalException technical error
     * @throws FunctionalException validation exception
     */
    public void saveGame() throws TechnicalException, FunctionalException;
    
    
    public GameEntity getCurrentGame();
    
    /**
     * Creates a game proposition and sends it to the other player
     * @param uidReciever opponent
     * @param chattable true if the chat is allowed
     * @param observable true if observers are allowed
     */
    public void createProposition(UUID uidReciever, boolean chattable, boolean observable);
    
    public void surrender();
    
    /**
     * Sends message for chat
     * @param message message to send
     */
    public void sendChatText(String message);

    
    /**
     * Creates a new user
     * @param the login and the password of the profile to create
     * @throws FunctionalException data access exception
     * @throws TechnicalException functional exception: to display for the user
     */
    
  	public void createProfile(PrivateUserEntity user) throws FunctionalException, TechnicalException;
  	
  	/**
  	 * Sends the decision of the local user to the other distant proposer
  	 * @param idUser id of reciever
  	 * @param answer answer
  	 */
  	public void sendResponse(UUID idUser, boolean answer);
  	//TODO : endGameByLeaving
  	
  	/**
  	 * Imports the profile of the user contained in file into the app
  	 * @param file file containing the profile of user
  	 * @param force if the same user is found in storage, the ancient one is overwritten if force is true otherwise an exception is thrown
  	 * @throws FunctionalException when a user with same id exists already
  	 * @throws TechnicalException error while saving
  	 */
  	public void importProfile(File file, boolean force) throws FunctionalException, TechnicalException;
  	
  	/**
  	 * calls the importProfile (File file, boolean force) method with force parameter at false<br/>
  	 * To call at the first time
  	 * @param file file containing the profile to import
  	 * @throws FunctionalException when a user with same id exists already
  	 * @throws TechnicalException error while saving
  	 */
  	public void importProfile(File file) throws FunctionalException, TechnicalException;
  	
  	/**
  	 * Exports the local user profile so that the user will be able to import it in an other app
  	 * @return file containing the user profile
  	 */
  	public File exportProfile();

  	/**
  	 * 
  	 * @return Returns the list of connected users
  	 */
	ObservableList<PublicUserEntity> getUserList();
}