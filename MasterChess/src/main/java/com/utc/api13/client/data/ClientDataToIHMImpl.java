/**
 * 
 */
package com.utc.api13.client.data;

import java.util.List;
import java.util.UUID;

import javafx.collections.ObservableSet;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.services.UserService;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PieceEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.AUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

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
    public ObservableSet<AUserEntity> getUserList() {
//        return instanceDataClientManager.getUserList();
    	return null;
    }


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToIHM#getUsers(java.util.
	 * List)
	 */
	@Override
	public void getUsers(List<AUserEntity> users) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToIHM#getUserInfo(java.util.
	 * UUID)
	 */
	@Override
	public AUserEntity getUserInfo(UUID iduser) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.utc.api13.client.data.interfaces.IClientToIHM#getAllGames()
	 */
	@Override
	public void getAllGames() {
		// TODO Auto-generated method stub

	}

	@Override
	public void connect(String login, String password) throws FunctionalException, TechnicalException {
		userService.connect(login, password);
		dataClientManager.setUserLocal(userService.getByLoginAndPassword(login, password));

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
	public void updateProfil(AUserEntity user) {
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
	public void saveGame() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.utc.api13.client.data.interfaces.IClientToIHM#getCurrentGame()
	 */
	@Override
	public GameEntity getCurrentGame() {
		// TODO Auto-generated method stub
		return null;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToIHM#sendChatText(java.lang.
	 * String)
	 */
	@Override
	public void sendChatText(String message) {
		// TODO Auto-generated method stub

	}


    public ClientDataToIHMImpl(DataClientManager instanceDataClientManager) {
        super();
        this.dataClientManager = instanceDataClientManager;
        this.userService = new UserService(dataClientManager.getIClientComToData());
    }
    
    
    /**
     * @param the login and the password of the profil to create
     * @throws FunctionalException 
     * @throws TechnicalException 
     */
    @Override
    public void createProfil(String login, String firstName, String lastName) throws TechnicalException, FunctionalException{
        PrivateUserEntity newUser = new PrivateUserEntity();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setLogin(login);
        newUser.setNbLost(0);
        newUser.setNbPlayed(0);
        newUser.setNbWon(0);
        userService.save(newUser);

    }

}
