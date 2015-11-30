package com.utc.api13.client.ihm;

import com.utc.api13.client.ihm.interfaces.IClientIHMToData;
import com.utc.api13.commun.entities.PublicUserEntity;

public class ClientIHMToDataImpl implements IClientIHMToData {
	
	private IHMManager myIHMManager;
	
	public ClientIHMToDataImpl(IHMManager pIHMManager) {
		myIHMManager = pIHMManager;
	}

    @Override
    public void didReceiveBoard() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void didReceiveEndOfGameBySurrender() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void didReceiveEndOfGameByVictory() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void didReceiveEndOfGameByLoss() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void didReceiveEndOfGameByTie() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void didReceiveEndOfObservation() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void didReceiveNewChatMessage() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void answerForLeavingIsYes() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void answerForLeavingIsNo() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void didReceiveRequestToInterruptGame() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void publicProfileHasBeenCorrectlyUpdated() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void publicProfileHasFailedToBeUpdated() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void didConnectSuccessfully() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void didRefuseConnectionToServer() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void listOfObserversHasBeenUpdated() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void didReceiveListOfConnectedUsers() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moveWasAccepted() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moveWasDenied() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void newMoveFromOpponent() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void didReceiveProfileOtherUser() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void errorReceivingProfileOtherUser() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void didReceivePropositionOfGame() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void propositionOfGameAccepted() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void propositionOfGameDenied() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void didReceiveListOfOngoingAndReplayedGames() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void didReceiveErrorInChatMessage() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void displayProfile(PublicUserEntity u) {
        // TODO Auto-generated method stub
        
    }
}