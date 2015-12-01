/**
 * 
 */
package com.utc.api13.client.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.util.Assert;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.data.services.GameService;
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
     * users service
     */
    private UserService userService;
    
    /**
     * game service
     */
    private GameService gameService;

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
	public void connect(final String login, final String password) throws FunctionalException, TechnicalException {
	    Assert.notNull(userService, "[ClientDataToIHMImpl][connect] userService shouldn't be null");

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
	}


	@Override
	public void disconnect() {		
	    Assert.notNull(gameService, "[ClientDataToIHMImpl][disconnect] GameService shouldn't be null");
	    
	    if (dataClientManager.getCurrentGame() != null){
    		if(gameService.isObserver(dataClientManager.getCurrentGame(), dataClientManager.getUserLocal().getId())) {
    			observerLeave();
    		} else {
    			requestPlayerForLeaving();
    		}
	    }
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


	@Override
	public void observerLeave() {
	    Assert.notNull(dataClientManager.getUserLocal(), "[ClientDataToIHMImpl][observerLeave] UserLocal shouldn't be null");

		dataClientManager.getIClientComToData().observerLeave(dataClientManager.getUserLocal().getId());

	}


	@Override
	public void requestPlayerForLeaving() {
	    Assert.notNull(dataClientManager.getUserLocal(), "[ClientDataToIHMImpl][requestPlayerForLeaving] UserLocal shouldn't be null");
		//TODO: le second paramètre, c'est fait pour quoi?
		dataClientManager.getIClientComToData().requestPlayerForLeaving(dataClientManager.getUserLocal().getId(), true);

	}


	@Override
	public void otherPlayerLeaving() {
//		TODO: dataClientManager.getIClientComToData().sendAnswer(answer, dataClientManager.getUserLocal());

	}

	@Override
	public void updateProfile(PrivateUserEntity user) throws TechnicalException, FunctionalException {
	    Assert.notNull(user, "[ClientDataToIHMImpl][updateProfile] user shouldn't be null");
        
		//delete the existing info
		userService.deleteById(user.getId());
		//Store the new one
		
		userService.save(user);
		this.dataClientManager.setUserLocal(user);
		//notify the server
		dataClientManager.getIClientComToData().sendUserUpdates(new PublicUserEntity(user));
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



	@Override
	public void watchGame(UUID idGame) {
		// TODO Auto-generated method stub

	}


	@Override
	public void chargeReplay(UUID idGame) {
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


	
	@Override
	public void saveGame() throws TechnicalException, FunctionalException {
	    Assert.notNull(dataClientManager.getUserLocal(), "[ClientDataToIHMImpl][saveGame] UserLocal shouldn't be null");
	    Assert.notNull(dataClientManager.getUserLocal().getSavedGames(), "[ClientDataToIHMImpl][saveGame] SavedGames shouldn't be null");
	            
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
	public void sendChatText(final String message) {
		dataClientManager.getIClientComToData().sendTextChat(message, dataClientManager.getCurrentGame().getId());
	}


    public ClientDataToIHMImpl(DataClientManager instanceDataClientManager) {
        super();
        Assert.notNull(instanceDataClientManager, "[ClientDataToIHMImpl][Constructor] dataClientManager shouldn't be null");
             
        this.dataClientManager = instanceDataClientManager;
        this.userService = new UserService();
        this.gameService = new GameService();
    }
    
    
    @Override
    public void createProfile(final PrivateUserEntity user) throws TechnicalException, FunctionalException{
        userService.save(user);
    }

    /**
     * @author Hugo R-L
     * @return PrivateUserEntity LocalUser
     */
	@Override
	public PrivateUserEntity getLocalUser() {
		return this.dataClientManager.getUserLocal();
	}

	@Override
	public void sendResponse(UUID idUser, boolean answer) {
		if(answer) {
			//Créer un game et l'ajouter à la liste des games
			//Ajouter sur le server
		}
		
	}

	@Override
	public void importProfile(File file, boolean force) throws FunctionalException, TechnicalException{
		userService.importProfile(file, force);
	}

	@Override
	public File exportProfile() throws TechnicalException {
		return userService.exportProfile(dataClientManager.getUserLocal());
	}


	@Override
	public void importProfile(File file) throws FunctionalException, TechnicalException {
		this.importProfile(file, false);
		
	}
}

