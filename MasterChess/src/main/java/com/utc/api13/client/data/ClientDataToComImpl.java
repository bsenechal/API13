/**
 * 
 */
package com.utc.api13.client.data;

import java.util.List;
import java.util.UUID;

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
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientToComm#getInstanceDataClientManager()
     */
    @Override
    public DataClientManager getInstanceDataClientManager() {
        return this.instanceDataClientManager;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientToComm#setInstanceDataClientManager()
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
	    // TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToComm#displayProfile(com.utc
	 * .api13.commun.entities.PublicUserEntity)
	 */
	@Override
	public void displayProfile(PublicUserEntity user) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToComm#displayAllGames(java.
	 * util.List)
	 */
	@Override
	public void displayAllGames(List<GameEntity> games) {
		// TODO Auto-generated method stub

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToComm#sendMessageToChat(com.
	 * utc.api13.commun.entities.MessageEntity)
	 */
	@Override
	public void sendMessageToChat(MessageEntity message) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToComm#sendAnswerForLeaving(
	 * boolean)
	 */
	@Override
	public void sendAnswerForLeaving(boolean answer) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#
	 * requestPlayerForLeaving(java.util.UUID)
	 */
	@Override
	public void requestPlayerForLeaving(UUID uid) {
		// TODO Auto-generated method stub

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.utc.api13.client.data.interfaces.IClientToComm#notify(java.lang.
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
	 * com.utc.api13.client.data.interfaces.IClientToComm#initGame(com.utc.api13
	 * .commun.entities.GameEntity)
	 */
	@Override
	public void initGame(GameEntity game) {
		// TODO Auto-generated method stub

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToComm#sendProposition(java.
	 * util.UUID, java.util.UUID, boolean, boolean)
	 */
	@Override
	public void sendProposition(UUID uidSender, UUID uidReciever, boolean observable, boolean chattable) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToComm#printProposition(java.
	 * util.UUID, boolean, boolean)
	 */
	@Override
	public void printProposition(UUID uidSender, boolean observable, boolean chattable) {
		// TODO Auto-generated method stub

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.utc.api13.client.data.interfaces.IClientToComm#displayMessage(java.
	 * lang.String)
	 */
	@Override
	public void displayMessage(String message) {
		// TODO: appeler la méthode displayMessage de IHM

	}

    public ClientDataToComImpl(DataClientManager instanceDataClientManager) {
        super();
        this.instanceDataClientManager = instanceDataClientManager;
    }

}