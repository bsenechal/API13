/**
 * 
 */
package com.utc.api13.client.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.data.services.UserService;
import com.utc.api13.commun.Erreur;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PieceEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.ErrorTypeEnum;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

import javafx.collections.ObservableList;

/**
 * @author Benoît
 *
 */
public class ClientDataToIHMImpl implements IClientDataToIHM {
    private DataClientManager dataClientManager;
    /**
     * Service des users
     */
    private UserService userService;
        
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientToIHM#getUserList(java.util.
     * List)
     */
    @Override
    public ObservableList<PublicUserEntity> getUserList() {
        return dataClientManager.getCurrentUsers();
    }


	@Override
	public void getUsers() {
		dataClientManager.getIClientComToData().getUsers();
	}

	@Override
	public void getUserInfo(final UUID iduser) {
		//TODO
		//dataClientManager.getIClientComToData().getUserInfo(idUser);
	}

	@Override
	public void getAllGames() {
		dataClientManager.getIClientComToData().getAllParties();

	}

	@Override
	public void connect(String login, String password) throws FunctionalException, TechnicalException {
		//Check the login and password
		PrivateUserEntity privateUser = userService.getByLoginAndPassword(login, password);
		if(privateUser == null) {
			List<Erreur> erreurs = new ArrayList<>();
			erreurs.add(new Erreur(ErrorTypeEnum.LOGIN_FAILED));
			throw new FunctionalException(erreurs);
		}
		//Save the local user
		dataClientManager.setUserLocal(privateUser);
		//Notify the server
		PublicUserEntity publicUser = new PublicUserEntity(privateUser);
		dataClientManager.getIClientComToData().notifyConnection(publicUser);
		//Open the main frame
		//dataClientManager.getIClientIHMToData().openFrame()
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.utc.api13.client.data.interfaces.IClientToIHM#disconnect()
	 */
	@Override
	public void disconnect() {
		dataClientManager.setUserLocal(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToIHM#move(com.utc.api13.
	 * commun.entities.PieceEntity,
	 * com.utc.api13.commun.entities.PositionEntity)
	 */
	@Override
	public void move(PieceEntity piece, PositionEntity position) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.utc.api13.client.data.interfaces.IClientToIHM#observerLeave()
	 */
	@Override
	public void observerLeave() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToIHM#requestPlayerForLeaving
	 * ()
	 */
	@Override
	public void requestPlayerForLeaving() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToIHM#sendAnserForLeaving(
	 * boolean)
	 */
	@Override
	public void sendAnserForLeaving(boolean answer) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToIHM#updateProfile(com.utc.
	 * api13.commun.entities.PrivateUserEntity)
	 */
	@Override
	public void updateProfile(PrivateUserEntity user) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToIHM#sendUserUpdates(com.utc
	 * .api13.commun.entities.PublicUserEntity)
	 */
	@Override
	public void sendUserUpdates(PublicUserEntity user) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.utc.api13.client.data.interfaces.IClientToIHM#notify(java.lang.
	 * String)
	 */
	@Override
	public void notify(String message) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToIHM#updateProfil(com.utc.
	 * api13.commun.entities.UserEntity)
	 */
	@Override
	public void updateProfil(PublicUserEntity user) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToIHM#watchGame(java.lang.
	 * String)
	 */
	@Override
	public void watchGame(String idGame) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToIHM#chargeReplayFromFile(
	 * java.lang.String)
	 */
	@Override
	public void chargeReplayFromFile(String file) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.utc.api13.client.data.interfaces.IClientToIHM#beginReplay()
	 */
	@Override
	public void beginReplay() {
		// TODO Auto-generated method stub

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.utc.api13.client.data.interfaces.IClientToIHM#saveGame()
	 */
	@Override
	public void saveGame() throws TechnicalException, FunctionalException {
		dataClientManager.getUserLocal().getSavedGames().add(dataClientManager.getCurrentGame());
		//TODO: Ulysse à Amadou : est-ce qu'il vaut mieux pas aussi sauvegarder l'user à ce moment là ?
		userService.save(dataClientManager.getUserLocal());
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.utc.api13.client.data.interfaces.IClientToIHM#getCurrentGame()
	 */
	@Override
	public GameEntity getCurrentGame() {
		return dataClientManager.getCurrentGame();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToIHM#createProposition(java.
	 * util.UUID, boolean, boolean)
	 */
	@Override
	public void createProposition(UUID uidReciever, boolean chattable, boolean observable) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.utc.api13.client.data.interfaces.IClientToIHM#surrender()
	 */
	@Override
	public void surrender() {
		// TODO Auto-generated method stub

	}


	@Override
	public void sendChatText(String message) {
		dataClientManager.getIClientComToData().sendTextChat(message, dataClientManager.getCurrentGame().getId());
	}


    public ClientDataToIHMImpl(DataClientManager instanceDataClientManager) {
        super();
        this.dataClientManager = instanceDataClientManager;
        this.userService = new UserService();
    }
    
    
    /**
     * @param the login and the password of the profil to create
     * @throws FunctionalException 
     * @throws TechnicalException 
     */
    @Override
    public void createProfile(PrivateUserEntity user) throws TechnicalException, FunctionalException{
        userService.save(user);
    }
}

