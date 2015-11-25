package com.utc.api13.client.ihm;

import com.utc.api13.client.ihm.interfaces.IClientIHMToData;

public class ClientIHMToDataImpl implements IClientIHMToData {
	
	private IHMManager myIHMManager;
	
	public ClientIHMToDataImpl(IHMManager pIHMManager) {
		myIHMManager = pIHMManager;
	}
	
	public void didReceiveBoard() {
		
	}
	
	public void didReceiveEndOfGameBySurrender() {
		
	}
	
	public void didReceiveEndOfGameByVictory() {
		
	}
	
	public void didReceiveEndOfGameByLoss() {
		
	}
	
	public void didReceiveEndOfGameByTie() {
		
	}
	
	public void didReceiveEndOfObservation() {
		
	}
	public void didReceiveNewChatMessage() {
		
	}
	
	public void answerForLeavingIsYes() {
		
	}
	
	public void answerForLeavingIsNo() {
		
	}
	
	public void didReceiveRequestToInterruptGame() {
		
	}
	
	public void publicProfileHasBeenCorrectlyUpdated() {
		
	}
	
	public void publicProfileHasFailedToBeUpdated() {
		
	}
	
	public void didConnectSuccessfully() {
		
	}
	
	public void didRefuseConnectionToServer() {
		
	}
	
	public void listOfObserversHasBeenUpdated() {
		
	}
	
	public void didReceiveListOfConnectedUsers() {
		
	}
	
	public void moveWasAccepted() {
		
	}
	
	public void moveWasDenied() {
		
	}
	
	public void newMoveFromOpponent() {
		
	}
	
	public void didReceiveProfileOtherUser() {
		
	}
	
	public void errorReceivingProfileOtherUser() {
		
	}
	
	public void didReceivePropositionOfGame() {
		
	}
	
	public void propositionOfGameAccepted() {
		
	}
	
	public void propositionOfGameDenied() {
		
	}
	
	public void didReceiveListOfOngoingAndReplayedGames() {
		
	}
	
	public void didReceiveErrorInChatMessage() {
		
	}
}