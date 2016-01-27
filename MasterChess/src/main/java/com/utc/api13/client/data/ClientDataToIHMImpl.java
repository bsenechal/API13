/**
 * 
 */
package com.utc.api13.client.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.util.Assert;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.data.services.GameService;
import com.utc.api13.client.data.services.UserService;
import com.utc.api13.commun.Erreur;
import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.MessageEntity;
import com.utc.api13.commun.entities.MoveEntity;
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

    public ClientDataToIHMImpl(DataClientManager instanceDataClientManager) {
        super();
        Assert.notNull(instanceDataClientManager,
                "[ClientDataToIHMImpl][Constructor] dataClientManager shouldn't be null");

        this.dataClientManager = instanceDataClientManager;
        this.userService = new UserService();
        this.gameService = new GameService();
    }

    @Override
    public ObservableList<PublicUserEntity> getUserList() {
        return dataClientManager.getCurrentUsers();
    }

    @Override
    public void getUsers() {
        dataClientManager.getIClientComToData().getUsers();
    }

    @Override
    public void getUserInfo(final UUID idUser) {
        dataClientManager.getIClientComToData().getUserInfo(idUser);
    }

    @Override
    public void getAllGames() {
        dataClientManager.getIClientComToData().getAllParties();
    }

    @Override
    public void connect(final String login, final String password) throws FunctionalException, TechnicalException {
        Assert.notNull(userService, "[ClientDataToIHMImpl][connect] userService shouldn't be null");

        // Check the login and password
        PrivateUserEntity privateUser = userService.getByLoginAndPassword(login, password);
        if (privateUser == null) {
            List<Erreur> erreurs = new ArrayList<>();
            erreurs.add(new Erreur(ErrorTypeEnum.LOGIN_FAILED));
            throw new FunctionalException(erreurs);
        }
        // Save the local user
        dataClientManager.setUserLocal(privateUser);
        // Notify the server
        PublicUserEntity publicUser = new PublicUserEntity(privateUser);
        dataClientManager.getIClientComToData().notifyConnection(publicUser);
    }

    @Override
    public void disconnect() throws TechnicalException, FunctionalException {
        Assert.notNull(gameService, "[ClientDataToIHMImpl][disconnect] GameService shouldn't be null");
        Assert.notNull(dataClientManager.getUserLocal(),
                "[ClientDataToIHMImpl][disconnect] UserLocal shouldn't be null");

        // Leave the game
        if (dataClientManager.getCurrentGame() != null) {
            if (gameService.isObserver(dataClientManager.getCurrentGame(), dataClientManager.getUserLocal().getId())) {
                observerLeave();
            } else {
                requestPlayerForLeaving();
            }
        }
        // Ask deconnection from server
        dataClientManager.getIClientComToData().disconnect(dataClientManager.getUserLocal().getId());
        // Set the local user to null
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
    public void move(APieceEntity piece, PositionEntity position) throws FunctionalException {
        Assert.notNull(dataClientManager.getCurrentGame(), "[ClientDataToIHMImpl][move] CurrentGame shouldn't be null");
        Assert.notNull(dataClientManager.getUserLocal(), "[ClientDataToIHMImpl][move] UserLocal shouldn't be null");

        MoveEntity move = new MoveEntity(null, piece.getPosition(), position, piece, null, null);

        if (piece.isMovePossible(move, dataClientManager.getCurrentGame())) {
            move.setDate(new Date());
            move.setUserID(dataClientManager.getUserLocal().getId());
            move.setGameID(dataClientManager.getCurrentGame().getId());
            dataClientManager.getIClientComToData().validateMove(dataClientManager.getUserLocal().getId(), move);
        } else {
            List<Erreur> erreurs = new ArrayList<>();
            erreurs.add(new Erreur(ErrorTypeEnum.MOVE_IMPOSSIBLE));
            throw new FunctionalException(erreurs);
        }
    }

    @Override
    public void observerLeave() {
        Assert.notNull(dataClientManager.getUserLocal(),
                "[ClientDataToIHMImpl][observerLeave] UserLocal shouldn't be null");
        dataClientManager.getIClientComToData().observerLeave(dataClientManager.getUserLocal().getId());

    }

    @Override
    public void requestPlayerForLeaving() {
        Assert.notNull(dataClientManager.getUserLocal(),
                "[ClientDataToIHMImpl][requestPlayerForLeaving] UserLocal shouldn't be null");
        // TODO: le second paramètre, c'est fait pour quoi?
        dataClientManager.getIClientComToData().requestPlayerForLeaving(dataClientManager.getUserLocal().getId(), true);

    }

    @Override
    public void sendAnswerForLeaving(boolean answer) {
        if (answer) {
            // Add game in local user saved game (in case the local user wants
            // to
            // save the game after ending)
            dataClientManager.getUserLocal().getSavedGames().add(getCurrentGame());
            // if the local user said yes no it's a win for him
            dataClientManager.getUserLocal().setNbPlayed(getLocalUser().getNbPlayed() + 1);
            // Inform the local user that game is over with result
            // TODO: à faire
            // send information to opponent player
            // dataClientManager.getIClientComToData().sendAnswerForLeaving(answer,
            // dataClientManager.getUserLocal());
            // Inform the server
            // dataClientManager.getIClientComToData().endGameByLeaving(getCurrentGame().getId(),
            // getLocalUser().getId());
            // End the game
            dataClientManager.setCurrentGame(null);
        }
    }

    @Override
    public void updateProfile(PrivateUserEntity user) throws TechnicalException, FunctionalException {
        Assert.notNull(user, "[ClientDataToIHMImpl][updateProfile] user shouldn't be null");

        // delete the existing info
        userService.deleteById(user.getId());
        // Store the new one

        userService.save(user);
        this.dataClientManager.setUserLocal(user);
        // notify the server
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
        Assert.notNull(dataClientManager.getUserLocal().getSavedGames(),
                "[ClientDataToIHMImpl][saveGame] SavedGames shouldn't be null");

        if (dataClientManager.getCurrentGame() != null) {
            dataClientManager.getUserLocal().getSavedGames().add(dataClientManager.getCurrentGame());
            userService.save(getLocalUser());
        } else {
            userService.save(getLocalUser());
        }
    }

    @Override
    public GameEntity getCurrentGame() {
        return dataClientManager.getCurrentGame();
    }

    /**
     * Envoie la proposition sur le serveur Un paramètre en trop coté Com, à
     * supprimer quand ils l'auront enlever
     */
    @Override
    public void createProposition(UUID uidReciever, UUID enquirerUUID, boolean chattable, boolean observable,
            boolean timer, Integer timeInt) {
        dataClientManager.getIClientComToData().sendProposition(dataClientManager.getUserLocal().getId(), uidReciever,
                chattable, observable, timer, timeInt);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.client.data.interfaces.IClientToIHM#surrender()
     */
    @Override
    public void surrender() {
        Assert.notNull(dataClientManager.getUserLocal(),
                "[ClientDataToIHMImpl][requestPlayerForLeaving] UserLocal shouldn't be null");
        dataClientManager.getIClientComToData().surrender(dataClientManager.getUserLocal().getId());
        dataClientManager.getClientDataToComImpl().endGameBySurrender();
    }

    @Override
    public void sendChatText(final String message) {
        Assert.notNull(dataClientManager.getCurrentGame(),
                "[ClientDataToIHMImpl][sendChatText] current game shouldn't be null");
        MessageEntity newMessage = new MessageEntity();
        newMessage.setText(message);
        dataClientManager.getCurrentGame().getMessages().add(newMessage);
        dataClientManager.getIClientComToData().sendTextChat(message, dataClientManager.getCurrentGame().getId());
    }

    @Override
    public void createProfile(final PrivateUserEntity user) throws TechnicalException, FunctionalException {
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

    /**
     * Envoi la réponse vers le second client
     */
    @Override
    public void sendResponse(UUID uidReceiver, UUID uidEnquirer, boolean answer, boolean observable, boolean chattable,
            boolean timer, Integer timeInt) throws TechnicalException {
        dataClientManager.getIClientComToData().answerProposition(uidReceiver, uidEnquirer, chattable, observable,
                answer, timer, timeInt);

    }

    @Override
    public void importProfile(File file, boolean force) throws FunctionalException, TechnicalException {
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

    @Override
    public ObservableList<GameEntity> getGamesList() {
        return dataClientManager.getCurrentGames();
    }

    @Override
    // A tester
    public List<PositionEntity> getAvailablesMoves(int line, int col) {
        PositionEntity myposition = new PositionEntity(line, col);
        APieceEntity piece = dataClientManager.getCurrentGame().getPieceFromPosition(myposition);
        // On vérifie que la pièce existe et qu'elle est bien de la couleur du
        // joueur courant :
        if (piece != null) {
            if (piece.getColor().equals(dataClientManager.getCurrentGame().getCurrentPlayerColor())) {
                return piece.generateAvailableMoves(dataClientManager.getCurrentGame());
            }
        }
        return new ArrayList<PositionEntity>();

    }

    @Override
    public void playMove(int fromLine, int fromCol, int toLine, int toCol) {
        // On crée une position entity de la position de départ
        PositionEntity fromposition = new PositionEntity(fromLine, fromCol);

        // On crée une position entity de la position de fin
        PositionEntity toposition = new PositionEntity(toLine, toCol);

        // On récupère l'UID du currentplayer
        UUID currentplayer = dataClientManager.getCurrentGame().getCurrentPlayer().getId();

        // On récupère la pièce sur la case de départ : fromposition
        APieceEntity piece = dataClientManager.getCurrentGame().getPieceFromPosition(fromposition);

        // On instancie un move entity
        MoveEntity move = new MoveEntity(new Date(), fromposition, toposition, piece, currentplayer,
                dataClientManager.getCurrentGame().getId());

        // On passe le moveEntity à com
        dataClientManager.getIClientComToData().validateMove(currentplayer, move);

    }

    @Override
    public void removeUserFromChat(UUID idUser) {
        Assert.notNull(idUser, "[ClientDataToIHMImpl][removeUserFromChat] current id of user shouldn't be null");
        Assert.notNull(dataClientManager.getCurrentGame(),
                "[ClientDataToIHMImpl][removeUserFromChat] current game shouldn't be null");
        // dataClientManager.getIClientComToData().removeUserFromChat(idUser,
        // dataClientManager.getCurrentGame().getId());
    }

}
