/**
 * 
 */
package com.utc.api13.client.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.util.Assert;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToCom;
import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MessageEntity;
import com.utc.api13.commun.entities.MoveEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.GameStatusEnum;

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
     * 
     */
    @Override
    public void displayUsersList(List<PublicUserEntity> connectedUserList) {
        Assert.notNull(connectedUserList,
                "[ClientDataToComImpl][displayUsersList] connectedUserList shouldn't be null");
        Assert.notNull(instanceDataClientManager.getUserLocal(),
                "[ClientDataToComImpl][displayUsersList] UserLocal shouldn't be null");
        Assert.notNull(instanceDataClientManager.getCurrentUsers(),
                "[ClientDataToComImpl][displayUsersList] currentUsers shouldn't be null");
        if (!connectedUserList.isEmpty()) {
            List<PublicUserEntity> connectedUserListTemp = new ArrayList<PublicUserEntity>();
            //Creation d'une nouvelle liste qui exclut le joueur local pour ne pas que son profil
            // s'affiche dans les joueurs connectés
            connectedUserList.forEach(u -> {
                if (!u.getId().equals(instanceDataClientManager.getUserLocal().getId())) {
                    connectedUserListTemp.add(u);
                }
            });
            instanceDataClientManager.getCurrentUsers().clear();
            instanceDataClientManager.getCurrentUsers().addAll(connectedUserListTemp);
        }
    }
    
    /*
     * (non-Javadoc)
     * @see com.utc.api13.client.data.interfaces.IClientDataToCom#displayProfile(com.utc.api13.commun.entities.PublicUserEntity)
     */
    @Override
    public void displayProfile(PublicUserEntity user) {
        instanceDataClientManager.getIClientIHMToData().displayProfile(user);
    }

    /*
     * (non-Javadoc)
     * @see com.utc.api13.client.data.interfaces.IClientDataToCom#displayAllGames(java.util.List)
     */
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
        // On recupere les differentes positions (line / colonne) du move (source / destination)
        int fromLine = move.getFromPosition().getPositionX();
        int fromCol = move.getFromPosition().getPositionY();
        int toLine = move.getToPosition().getPositionX();
        int toCol = move.getToPosition().getPositionY();

        //On recupere le jeu et la piece sur la position d'origine
        GameEntity thisgame = instanceDataClientManager.getCurrentGame();
        APieceEntity piece = thisgame.getPieceFromPosition(move.getFromPosition());
        Assert.notNull(thisgame, "[ClientDataToComImpl][displayResult] currentGames shouldn't be null");

        // gestion des roques
        if ("King".equals(piece.toString())) {
            if (move.getFromPosition().getPositionX() == move.getToPosition().getPositionX() - 2) {
                PositionEntity rookPositionTmp = new PositionEntity(move.getFromPosition().getPositionX() + 3,
                        move.getFromPosition().getPositionY());
                PositionEntity rookPositionToGo = new PositionEntity(move.getFromPosition().getPositionX() + 1,
                        move.getFromPosition().getPositionY());
                APieceEntity rookTmp = thisgame.getPieceFromPosition(rookPositionTmp);

                instanceDataClientManager.getIClientIHMToData().refreshChessBoard(rookPositionTmp.getPositionX(),
                        rookPositionTmp.getPositionY(), rookPositionToGo.getPositionX(),
                        rookPositionToGo.getPositionY(), rookTmp, thisgame);
            }

            if (move.getFromPosition().getPositionX() == move.getToPosition().getPositionX() + 2) {
                PositionEntity rookPositionTmp = new PositionEntity(move.getFromPosition().getPositionX() - 4,
                        move.getFromPosition().getPositionY());
                PositionEntity rookPositionToGo = new PositionEntity(move.getFromPosition().getPositionX() - 1,
                        move.getFromPosition().getPositionY());
                APieceEntity rookTmp = thisgame.getPieceFromPosition(rookPositionTmp);

                instanceDataClientManager.getIClientIHMToData().refreshChessBoard(rookPositionTmp.getPositionX(),
                        rookPositionTmp.getPositionY(), rookPositionToGo.getPositionX(),
                        rookPositionToGo.getPositionY(), rookTmp, thisgame);
            }

        }

        // On suppirme la piece de la position de destination
        thisgame.removePieceFromPosition(move.getToPosition());

        //  On transforme un pion en reine si le pion est dans le camps ennemy
        if (thisgame.transformPawnToQueen(move)) {
            //On récupère la piece s'il s'agit maintenant d'une raine
            piece = thisgame.getPieceFromPosition(move.getFromPosition());
        }
        // On bouge la piece
        thisgame.movePiece(move);

        instanceDataClientManager.getIClientIHMToData().refreshChessBoard(fromLine, fromCol, toLine, toCol, piece,
                thisgame);

    }

    /*
     * (non-Javadoc)
     * @see com.utc.api13.client.data.interfaces.IClientDataToCom#sendAnswerForLeaving(boolean)
     */
    @Override
    public void sendAnswerForLeaving(boolean answer) {

        if (answer) {
            //Si le joueur  accepte de quitter la partie
            
            
            // Si je joueur n'a pas encore de jeu enregistré, alors on initialise 
            if (instanceDataClientManager.getUserLocal().getSavedGames() == null) {
                instanceDataClientManager.getUserLocal().setSavedGames(new ArrayList<GameEntity>());
            }

            //On sauvegarde le jeu en cours
            instanceDataClientManager.getUserLocal().getSavedGames().add(instanceDataClientManager.getCurrentGame());
            //on incrémente le nombre de parties jouees
            instanceDataClientManager.getUserLocal()
                    .setNbPlayed(instanceDataClientManager.getUserLocal().getNbPlayed() + 1);

            // On envoie la reponse a l'autre joueur
            UUID senderId = instanceDataClientManager.getUserLocal().getId()
                    .equals(instanceDataClientManager.getCurrentGame().getBlackPlayer().getId())
                            ? instanceDataClientManager.getCurrentGame().getWhitePlayer().getId()
                            : instanceDataClientManager.getCurrentGame().getBlackPlayer().getId();

            instanceDataClientManager.getIClientIHMToData().displayAnswer(senderId, answer,
                    "The player has quit the game ");
            instanceDataClientManager.getIClientIHMToData()
                    .displayAnswer(instanceDataClientManager.getUserLocal().getId(), answer, "You have quit the game ");
            // On supprime le jeu en cours
            instanceDataClientManager.setCurrentGame(null);
        } else {
            // Si le joueur refuse de quitter la partie on envoie simplement un message pour avertir l'adversaire
            instanceDataClientManager.getIClientIHMToData()
                    .displayAnswer(instanceDataClientManager.getUserLocal()
                            .getId(), answer,
                    "Your opponent doesn't want to quit the game, you'll keep playing. Surrend it if you want, but your stats will change ! ");
        }
    }

    /*
     * (non-Javadoc)
     * @see com.utc.api13.client.data.interfaces.IClientDataToCom#requestPlayerForLeaving(java.util.UUID)
     */
    @Override
    public void requestPlayerForLeaving(UUID uid) {
        instanceDataClientManager.getIClientIHMToData().otherPlayerLeaving();
    }

    /*
     * (non-Javadoc)
     * @see com.utc.api13.client.data.interfaces.IClientDataToCom#endGameByLeaving()
     */
    @Override
    public void endGameByLeaving() {
        // Si le joueur n'a pas de partie sauvegarde alors on initie 
        if (instanceDataClientManager.getUserLocal().getSavedGames() == null) {
            instanceDataClientManager.getUserLocal().setSavedGames(new ArrayList<GameEntity>());
        }
        // On sauvegarde le jeu
        instanceDataClientManager.getUserLocal().getSavedGames().add(instanceDataClientManager.getCurrentGame());
        // On remet a nul le jeu en cours
        instanceDataClientManager.setCurrentGame(null);
    }

    /*
     * (non-Javadoc)
     * @see com.utc.api13.client.data.interfaces.IClientDataToCom#notifyRejection(java.util.UUID, java.lang.String)
     */
    @Override
    public void notifyRejection(UUID uidSender, String rejectionMessage) {
        instanceDataClientManager.getIClientIHMToData().displayAnswer(uidSender, false, rejectionMessage);
        // Appelle la fonction displayAnswer qui n'est censé être utilisée que
        // lorsque le joueur refuse la partie
    }

    /*
     * (non-Javadoc)
     * @see com.utc.api13.client.data.interfaces.IClientDataToCom#initGame(com.utc.api13.commun.entities.GameEntity)
     */
    @Override
    public void initGame(GameEntity game) {
        // On intie le jeu
        instanceDataClientManager.setCurrentGame(game);
        // On demande a afficher le jeu
        instanceDataClientManager.getIClientIHMToData().displayChessBoard(game);
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
        // Fonction a completer lorsque l'on ajoute la possibilite d'observer un jeu
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
        // Fonction a completer lorsque l'on ajoute la possibilite de rejouer un jeu (streaming)

    }

    /*
     * (non-Javadoc)
     * @see com.utc.api13.client.data.interfaces.IClientDataToCom#printProposition(java.util.UUID, boolean, boolean, boolean, java.lang.Integer)
     */
    @Override
    public void printProposition(final UUID uidSender, boolean observable, boolean chattable, boolean timer,
            Integer timerInt) {
        instanceDataClientManager.getIClientIHMToData().displayProposition(uidSender, observable, chattable, timer,
                timerInt);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientToComm#victoryBySurrender()
     */
    @Override
    public void victoryBySurrender() {
        // On sauvegarde la partie
        instanceDataClientManager.getUserLocal().getSavedGames().add(instanceDataClientManager.getCurrentGame());
        // On incremente le nombre de parties jouees
        instanceDataClientManager.getUserLocal()
                .setNbPlayed(instanceDataClientManager.getUserLocal().getNbPlayed() + 1);
        // On incremente le nombre de parties gagnees
        instanceDataClientManager.getUserLocal().setNbWon(instanceDataClientManager.getUserLocal().getNbWon() + 1);
        // On supprime le jeu en cours
        instanceDataClientManager.setCurrentGame(null);
         instanceDataClientManager.getIClientIHMToData().endGameBySurrend();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientToComm#endGameBySurrender()
     */
    @Override
    public void endGameBySurrender(UUID idPlayer) {
        if (idPlayer.equals(instanceDataClientManager.getUserLocal().getId())) {
            GameEntity game = instanceDataClientManager.getCurrentGame();
            //On envoie la notification a l'autre jouueur
            instanceDataClientManager.getIClientComToData().endGameBySurrender(
                    instanceDataClientManager.getUserLocal().getId(),
                    game.getWhitePlayer().getId().equals(instanceDataClientManager.getUserLocal().getId())
                            ? game.getBlackPlayer().getId() : game.getWhitePlayer().getId(),
                    game.getId());
        }
        if (instanceDataClientManager.getUserLocal().getSavedGames() == null) {
            instanceDataClientManager.getUserLocal().setSavedGames(new ArrayList<GameEntity>());
        }
        //On sauvegarde le jeu en cours
        instanceDataClientManager.getUserLocal().getSavedGames().add(instanceDataClientManager.getCurrentGame());
        // On incremente le nombre de parties jouees
        instanceDataClientManager.getUserLocal()
                .setNbPlayed(instanceDataClientManager.getUserLocal().getNbPlayed() + 1);
        
        if (idPlayer.equals(instanceDataClientManager.getUserLocal().getId())) {
            // Si le demndeur est le user local alors il perd
            // On incremente le nombre de parties perdues
            instanceDataClientManager.getUserLocal()
                    .setNbLost(instanceDataClientManager.getUserLocal().getNbLost() + 1);
        } else {
            //sinon , l'autre joueur abandonne et je gagne
            //On incremente le nombre de parties gagnees
            instanceDataClientManager.getUserLocal().setNbWon(instanceDataClientManager.getUserLocal().getNbWon() + 1);
        }
        // On supprime le jeu en cours
        instanceDataClientManager.setCurrentGame(null);
         instanceDataClientManager.getIClientIHMToData().endGameBySurrend();

    }

    /*
     * (non-Javadoc)
     * @see com.utc.api13.client.data.interfaces.IClientDataToCom#displayMessage(java.lang.String)
     */
    @Override
    public void displayMessage(final String message) {
        //On cree un message et on ajoute le text envoye au message
        MessageEntity newMessage = new MessageEntity();
        newMessage.setText(message);
        //On ajoute le message aux messages du jeu en cours
        instanceDataClientManager.getCurrentGame().getMessages().add(newMessage);
        //IHM affiche le message
        instanceDataClientManager.getIClientIHMToData().displayMessage(message);
    }

    /*
     * (non-Javadoc)
     * @see com.utc.api13.client.data.interfaces.IClientDataToCom#notifyConnection(com.utc.api13.commun.entities.PublicUserEntity)
     */
    @Override
    public void notifyConnection(final PublicUserEntity user) {
        //On ajoute le user a la liste des utilisateurs connectes
        instanceDataClientManager.getCurrentUsers().add(user);
    }

    /*
     * (non-Javadoc)
     * @see com.utc.api13.client.data.interfaces.IClientDataToCom#notifyDisconnection(java.util.UUID)
     */
    @Override
    public void notifyDisconnection(final UUID idUser) {
        //On supprime de la liste des users celui qui a l'uid envoye en parametre
        instanceDataClientManager.getCurrentUsers().removeIf(u -> idUser.equals(u.getId()));
    }

    /*
     * (non-Javadoc)
     * @see com.utc.api13.client.data.interfaces.IClientDataToCom#nextTurn(com.utc.api13.commun.enumerations.GameStatusEnum, java.util.UUID)
     */
    @Override
    public void nextTurn(final GameStatusEnum status, final UUID nextPlayer) {
        GameEntity game = instanceDataClientManager.getCurrentGame();

        //C'est a l'autre joueur de jouer
        game.switchCurrentUser();
        // On modifie le statut du jeu
        game.setIsFinished(status);

        // alert IHM:
        instanceDataClientManager.getIClientIHMToData().activateCases(game.getCurrentPlayer(), status);

        PrivateUserEntity localUser = instanceDataClientManager.getUserLocal();
        switch (status) {

        case CHECKMATE: 
            checkMate(localUser, game);
            break;
        
        case DRAW: 
            draw(instanceDataClientManager, localUser, game);
            break;
        
        default:
            // CHECK & CONTINUE
            break;
        }

    }

    /*
     * (non-Javadoc)
     * @see com.utc.api13.client.data.interfaces.IClientDataToCom#updateDistantProfile(com.utc.api13.commun.entities.PublicUserEntity)
     */
    @Override
    public void updateDistantProfile(PublicUserEntity userToUpdate) {
        Assert.notNull(instanceDataClientManager.getCurrentUsers(),
                "[ClientDataToComImpl][updateDistantProfile] current users shouldn't be null");
        Assert.notNull(instanceDataClientManager.getCurrentUsers(),
                "[ClientDataToComImpl][updateDistantProfile] Given user shouldn't be null");

        //On supprime l'utilisateur de la liste 
        instanceDataClientManager.getCurrentUsers().removeIf(u -> u.getId().equals(userToUpdate.getId()));
        // On ajoute la nouvelle version de cet utilisateur
        instanceDataClientManager.getCurrentUsers().add(userToUpdate);
    }
    
    /*
     * CASE : DRAW
     */
    private static void draw(DataClientManager instanceDataClientManager, PrivateUserEntity localUser, GameEntity game) {
        if (instanceDataClientManager.getUserLocal().getSavedGames() == null) {
            instanceDataClientManager.getUserLocal().setSavedGames(new ArrayList<GameEntity>());
        }
        instanceDataClientManager.getUserLocal().getSavedGames().add(game);
        localUser.setNbPlayed(localUser.getNbPlayed() + 1);
        UUID gameID = game.getId();
        instanceDataClientManager.getCurrentGames().removeIf(g -> gameID.equals(g.getId()));
    }
    
    /*
     * CASE : CHECKMATE
     */
    private static void checkMate(PrivateUserEntity localUser, GameEntity game) {
        if (localUser.getId().equals(game.getCurrentPlayer().getId())) {
            localUser.setNbLost(localUser.getNbLost() + 1);
        } else {
            localUser.setNbWon(localUser.getNbWon() + 1);
        }
    }
}
