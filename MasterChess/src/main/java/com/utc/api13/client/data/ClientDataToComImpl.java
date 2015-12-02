/**
 * 
 */
package com.utc.api13.client.data;

import java.util.List;
import java.util.UUID;

import org.springframework.util.Assert;

import com.utc.api13.client.data.interfaces.IClientDataToCom;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MessageEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

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
	 */
	@Override
	public void displayUsersList(List<PublicUserEntity> connectedUserList) {
		Assert.notNull(connectedUserList,
				"[ClientDataToComImpl][displayUsersList] connectedUserList shouldn't be null");
		Assert.notNull(instanceDataClientManager.getUserLocal(),
				"[ClientDataToComImpl][displayUsersList] UserLocal shouldn't be null");
		Assert.notNull(instanceDataClientManager.getCurrentUsers(),
				"[ClientDataToComImpl][displayUsersList] currentUsers shouldn't be null");

		connectedUserList.forEach(u -> {
			if (u.getId().equals(instanceDataClientManager.getUserLocal().getId())) {
				connectedUserList.remove(u);
			}
		});

		instanceDataClientManager.getCurrentUsers().clear();
		instanceDataClientManager.getCurrentUsers().addAll(connectedUserList);

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
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMessageToChat(MessageEntity message) {
		// TODO: instanceDataClientManager.sendMessageToChat(message);

	}

	@Override
	public void sendAnswerForLeaving(boolean answer) {
	    //Add game in local user saved game (in case the local user wants to save the game after ending)
        instanceDataClientManager.getUserLocal().getSavedGames().add(instanceDataClientManager.getCurrentGame());
        //Modify the played games
        instanceDataClientManager.getUserLocal().setNbPlayed(instanceDataClientManager.getUserLocal().getNbPlayed() + 1);
	    //If answer is no the local user loses the game
        if(!answer) {
            instanceDataClientManager.getUserLocal().setNbLost(instanceDataClientManager.getUserLocal().getNbLost() + 1);
        }
	    //End the local game
        UUID idSender = instanceDataClientManager.getUserLocal().getId().equals(instanceDataClientManager.getCurrentGame().getBlackPlayer().getId()) ? instanceDataClientManager.getCurrentGame().getWhitePlayer().getId() : instanceDataClientManager.getCurrentGame().getBlackPlayer().getId();
		//Display answer to local user
        instanceDataClientManager.getIClientIHMToData().displayAnswer(idSender, answer);
	}

	@Override
	public void requestPlayerForLeaving(UUID uid) {
		 instanceDataClientManager.getIClientIHMToData().otherPlayerLeaving();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToComm#endGameByLeaving()
	 */
	@Override
	public void endGameByLeaving() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notify(String message) {
	    // TODO décommenter
		// instanceDataClientManager.getIClientIHMToData().notify(message);

	}

	@Override
	public void initGame(GameEntity game) {
		// Set the current game
		instanceDataClientManager.setCurrentGame(game);
		// Ask the IHM module to display the Chessboard
		instanceDataClientManager.getIClientIHMToData().displayChessBoard();
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
	public void printProposition(UUID uidSender, boolean observable, boolean chattable) {
		instanceDataClientManager.getIClientIHMToData().displayProposition(uidSender, observable, chattable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToComm#victoryBySurrender()
	 */
	@Override
	public void victoryBySurrender() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToComm#endGameBySurrender()
	 */
	@Override
	public void endGameBySurrender() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayMessage(String message) {
		instanceDataClientManager.getIClientIHMToData().displayMessage(message);

	}

    @Override
    public void notifyConnection(PublicUserEntity user) {
        instanceDataClientManager.getCurrentUsers().add(user);
    }

    @Override
    public void notifyDisconnection(UUID idUser) {
        instanceDataClientManager.getCurrentUsers().removeIf(u -> idUser.equals(u.getId()));
        
    }

}