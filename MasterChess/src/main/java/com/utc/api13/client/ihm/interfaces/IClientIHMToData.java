package com.utc.api13.client.ihm.interfaces; 

import java.util.UUID;

import com.utc.api13.commun.entities.ChessboardEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

public interface IClientIHMToData {
	
	//ne concerne que les éléments de data retournés en asynchrone suite à echange avec le serveur 
	//le reste est synchrone et appelé par IHM dans l'interface ClientIHMToDataImpl
	
	/**
	 * Display distant profile 
	 */
	public void displayProfile(PublicUserEntity u); 
	/**
	 * The other player is leaving the game
	 */
	public void otherPlayerLeaving(); 
	/**
	 * notify the other user someone wants to play
	 */
	public void displayProposition(UUID uidSender, boolean observable, boolean chattable);
	/**
	 * notify the proposer about the answer of the distant user
	 */
	public void displayAnswer(UUID uidSender, boolean observable);
	/**
	 * Game launching 
	 */
	public void displayChessBoard(); 
	/**
	 * After a piece is moved 
	 */
	public void refreshChessBoard(); 
	/**
	 * when a new chat message is received
	 */
	public void displayMessage(String newMessage); 
	/**
	 * when a new observer is added/removed : the modified list is sent to ihm by data through the local gameEntity
	 */
	public void refreshObserverList();
	/**
	 * display the game for an observer -live
	 */
	public void displayGameLiveObserver(); 
	/**
	 * when a user leaves before the end of the game, he needs the approbation of the other one to change the final score
	 */
	//displayAnswerForLeaving(boolean answer)
	
	/*public void didReceiveBoard(); 
	public void didReceiveEndOfGameBySurrender(); 
	public void didReceiveEndOfGameByVictory(); 
	public void didReceiveEndOfGameByLoss(); 
	public void didReceiveEndOfGameByTie(); 
	public void didReceiveEndOfObservation(); 
	public void didReceiveNewChatMessage(); 
	public void answerForLeavingIsYes(); 
	public void answerForLeavingIsNo(); 
	public void didReceiveRequestToInterruptGame(); 
	public void publicProfileHasBeenCorrectlyUpdated(); 
	public void publicProfileHasFailedToBeUpdated(); 
	public void didRefuseConnectionToServer(); 
	public void listOfObserversHasBeenUpdated(); 
	public void didReceiveListOfConnectedUsers(); 
	public void moveWasAccepted(); 
	public void moveWasDenied(); 
	public void newMoveFromOpponent(); 
	public void didReceiveProfileOtherUser(); 
	public void errorReceivingProfileOtherUser(); 
	public void didReceivePropositionOfGame(); 
	public void propositionOfGameAccepted();
	public void propositionOfGameDenied(); 
	public void didReceiveListOfOngoingAndReplayedGames(); 
	public void didReceiveErrorInChatMessage(); */
	
	//erreurs : il suffit de catcher les exceptions
	//attention dans les fonctions à tout le temps mettre getCardGame pour récup jeu local
}