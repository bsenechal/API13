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

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.client.data.interfaces.IClientDataToIHM#getUserList()
     */
    @Override
    public ObservableList<PublicUserEntity> getUserList() {
        return dataClientManager.getCurrentUsers();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.client.data.interfaces.IClientDataToIHM#getUsers()
     */
    @Override
    public void getUsers() {
        dataClientManager.getIClientComToData().getUsers();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#getUserInfo(java.
     * util.UUID)
     */
    @Override
    public void getUserInfo(final UUID idUser) {
        dataClientManager.getIClientComToData().getUserInfo(idUser);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.client.data.interfaces.IClientDataToIHM#getAllGames()
     */
    @Override
    public void getAllGames() {
        dataClientManager.getIClientComToData().getAllParties();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#connect(java.lang.
     * String, java.lang.String)
     */
    @Override
    public void connect(final String login, final String password) throws FunctionalException, TechnicalException {
        Assert.notNull(userService, "[ClientDataToIHMImpl][connect] userService shouldn't be null");

        // On verifie le login et le mot de passe
        PrivateUserEntity privateUser = userService.getByLoginAndPassword(login, password);
        if (privateUser == null) {
            List<Erreur> erreurs = new ArrayList<>();
            erreurs.add(new Erreur(ErrorTypeEnum.LOGIN_FAILED));
            throw new FunctionalException(erreurs);
        }
        // On enregistre le private user
        dataClientManager.setUserLocal(privateUser);
        // On notifie le serveur
        PublicUserEntity publicUser = new PublicUserEntity(privateUser);
        dataClientManager.getIClientComToData().notifyConnection(publicUser);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.client.data.interfaces.IClientDataToIHM#disconnect()
     */
    @Override
    public void disconnect() throws TechnicalException, FunctionalException {
        Assert.notNull(gameService, "[ClientDataToIHMImpl][disconnect] GameService shouldn't be null");
        Assert.notNull(dataClientManager.getUserLocal(),
                "[ClientDataToIHMImpl][disconnect] UserLocal shouldn't be null");

        // Pour quitter le jeu on verifie d'abord qu'il y a un jeu en cours
        if (dataClientManager.getCurrentGame() != null) {
            // Si l'utilisateur est un observateur alors on le retire de la
            // liste des observeurs
            if (gameService.isObserver(dataClientManager.getCurrentGame(), dataClientManager.getUserLocal().getId())) {
                observerLeave();
            } else {
                // Sinon on demande a quitter le jeu
                requestPlayerForLeaving();
            }
        }
        dataClientManager.getIClientComToData().disconnect(dataClientManager.getUserLocal().getId());
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
            // On verifie que le coup soit permi
            // On cree le cours et on le valide
            move.setDate(new Date());
            move.setUserID(dataClientManager.getUserLocal().getId());
            move.setGameID(dataClientManager.getCurrentGame().getId());
            dataClientManager.getIClientComToData().validateMove(dataClientManager.getUserLocal().getId(), move);
        } else {
            // On renvoie l'erreur MOVE IMPOSSIBLE
            List<Erreur> erreurs = new ArrayList<>();
            erreurs.add(new Erreur(ErrorTypeEnum.MOVE_IMPOSSIBLE));
            throw new FunctionalException(erreurs);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#observerLeave()
     */
    @Override
    public void observerLeave() {
        Assert.notNull(dataClientManager.getUserLocal(),
                "[ClientDataToIHMImpl][observerLeave] UserLocal shouldn't be null");
        dataClientManager.getIClientComToData().observerLeave(dataClientManager.getUserLocal().getId());

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.client.data.interfaces.IClientDataToIHM#
     * requestPlayerForLeaving()
     */
    @Override
    public void requestPlayerForLeaving() {
        Assert.notNull(dataClientManager.getUserLocal(),
                "[ClientDataToIHMImpl][requestPlayerForLeaving] UserLocal shouldn't be null");

        GameEntity game = dataClientManager.getCurrentGame();
        // On envoie la requete a l'adversaire
        dataClientManager.getIClientComToData().requestPlayerForLeaving(dataClientManager.getUserLocal().getId(),
                game.getWhitePlayer().getId().equals(dataClientManager.getUserLocal().getId())
                        ? game.getBlackPlayer().getId() : game.getWhitePlayer().getId());

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.client.data.interfaces.IClientDataToIHM#
     * sendAnswerForLeaving(boolean)
     */
    @Override
    public void sendAnswerForLeaving(boolean answer) {
        if (answer) {

            if (dataClientManager.getUserLocal().getSavedGames() == null) {
                dataClientManager.getUserLocal().setSavedGames(new ArrayList<GameEntity>());
            }
            // On sauvegarde le jeu
            dataClientManager.getUserLocal().getSavedGames().add(getCurrentGame());
            // Si le joueur accepte alors il on incrémente le nombre de parties
            // jouees mais pas les gagnees / perdues
            dataClientManager.getUserLocal().setNbPlayed(getLocalUser().getNbPlayed() + 1);
        }

        dataClientManager.getUserLocal().getSavedGames().add(getCurrentGame());
        // if the local user said yes no it's a win for him
        dataClientManager.getUserLocal().setNbPlayed(getLocalUser().getNbPlayed() + 1);

        GameEntity game = getCurrentGame();
        UUID otherPlayer = dataClientManager.getUserLocal().getId().equals(game.getBlackPlayer().getId())
                ? game.getWhitePlayer().getId() : game.getBlackPlayer().getId();
        dataClientManager.getIClientComToData().endGameByLeaving(dataClientManager.getUserLocal().getId(), otherPlayer,
                getCurrentGame().getId(), answer);
        dataClientManager.setCurrentGame(null);
        dataClientManager.getIClientIHMToData().closeGameScreen(answer);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#updateProfile(com.
     * utc.api13.client.data.entities.PrivateUserEntity)
     */
    @Override
    public void updateProfile(PrivateUserEntity user) throws TechnicalException, FunctionalException {
        Assert.notNull(user, "[ClientDataToIHMImpl][updateProfile] user shouldn't be null");

        // On supprime les infos actuelles
        userService.deleteById(user.getId());
        // On ajoute les nouvelles

        userService.save(user);
        this.dataClientManager.setUserLocal(user);
        // On notifie le serveur
        dataClientManager.getIClientComToData().sendUserUpdates(new PublicUserEntity(user));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#watchGame(java.util
     * .UUID)
     */
    @Override
    public void watchGame(UUID idGame) {
        // A faire lorsque l'observation d'un jeu sera faite

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#chargeReplay(java.
     * util.UUID)
     */
    @Override
    public void chargeReplay(UUID idGame) {
        // A faire lorsque la fonctionnalite replay sera faite

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.client.data.interfaces.IClientToIHM#beginReplay()
     */
    @Override
    public void beginReplay() {
        // A faire lorsque la fonctionnalite replay sera faite

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.client.data.interfaces.IClientDataToIHM#saveGame()
     */
    @Override
    public void saveGame() throws TechnicalException, FunctionalException {
        Assert.notNull(dataClientManager.getUserLocal(), "[ClientDataToIHMImpl][saveGame] UserLocal shouldn't be null");
        Assert.notNull(dataClientManager.getUserLocal().getSavedGames(),
                "[ClientDataToIHMImpl][saveGame] SavedGames shouldn't be null");

        if (dataClientManager.getCurrentGame() != null) {
            // On enregistre le jeu et l'etat actuel du user
            dataClientManager.getUserLocal().getSavedGames().add(dataClientManager.getCurrentGame());
            userService.save(getLocalUser());
        } else {
            userService.save(getLocalUser());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#getCurrentGame()
     */
    @Override
    public GameEntity getCurrentGame() {
        return dataClientManager.getCurrentGame();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#createProposition(
     * java.util.UUID, java.util.UUID, boolean, boolean, boolean,
     * java.lang.Integer)
     */
    @Override
    public void createProposition(UUID uidReciever, UUID enquirerUUID, boolean chattable, boolean observable,
            boolean timer, Integer timeInt) {
        // Envoie la proposition sur le serveur
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
        dataClientManager.getClientDataToComImpl().endGameBySurrender(dataClientManager.getUserLocal().getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#sendChatText(java.
     * lang.String)
     */
    @Override
    public void sendChatText(final String message) {
        Assert.notNull(dataClientManager.getCurrentGame(),
                "[ClientDataToIHMImpl][sendChatText] current game shouldn't be null");
        MessageEntity newMessage = new MessageEntity();
        newMessage.setText(message);
        // On ajoute le message aux messages du jeu
        dataClientManager.getCurrentGame().getMessages().add(newMessage);
        // On envoit a COM
        dataClientManager.getIClientComToData().sendTextChat(message, dataClientManager.getCurrentGame().getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#createProfile(com.
     * utc.api13.client.data.entities.PrivateUserEntity)
     */
    @Override
    public void createProfile(final PrivateUserEntity user) throws TechnicalException, FunctionalException {
        userService.save(user);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.client.data.interfaces.IClientDataToIHM#getLocalUser()
     */
    @Override
    public PrivateUserEntity getLocalUser() {
        return this.dataClientManager.getUserLocal();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#sendResponse(java.
     * util.UUID, java.util.UUID, boolean, boolean, boolean, boolean,
     * java.lang.Integer)
     */
    @Override
    public void sendResponse(UUID uidReceiver, UUID uidEnquirer, boolean answer, boolean observable, boolean chattable,
            boolean timer, Integer timeInt) throws TechnicalException {
        dataClientManager.getIClientComToData().answerProposition(uidReceiver, uidEnquirer, chattable, observable,
                answer, timer, timeInt);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#importProfile(java.
     * io.File, boolean)
     */
    @Override
    public void importProfile(File file, boolean force) throws FunctionalException, TechnicalException {
        userService.importProfile(file, force);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#exportProfile()
     */
    @Override
    public File exportProfile() throws TechnicalException {
        return userService.exportProfile(dataClientManager.getUserLocal());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#importProfile(java.
     * io.File)
     */
    @Override
    public void importProfile(File file) throws FunctionalException, TechnicalException {
        this.importProfile(file, false);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.client.data.interfaces.IClientDataToIHM#getGamesList()
     */
    @Override
    public ObservableList<GameEntity> getGamesList() {
        return dataClientManager.getCurrentGames();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#getAvailablesMoves(
     * int, int)
     */
    @Override
    // A tester
    public List<PositionEntity> getAvailablesMoves(int line, int col) {
        PositionEntity myposition = new PositionEntity(line, col);
        APieceEntity piece = dataClientManager.getCurrentGame().getPieceFromPosition(myposition);
        // On vérifie que la pièce existe et qu'elle est bien de la couleur du
        // joueur courant :
        if (piece != null && piece.getColor().equals(dataClientManager.getCurrentGame().getCurrentPlayerColor())) {
            return piece.generateAvailableMoves(dataClientManager.getCurrentGame());
        }
        return new ArrayList<PositionEntity>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.utc.api13.client.data.interfaces.IClientDataToIHM#playMove(int,
     * int, int, int)
     */
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#removeUserFromChat(
     * java.util.UUID)
     */
    @Override
    public void removeUserFromChat(UUID idUser) {
        Assert.notNull(idUser, "[ClientDataToIHMImpl][removeUserFromChat] current id of user shouldn't be null");
        Assert.notNull(dataClientManager.getCurrentGame(),
                "[ClientDataToIHMImpl][removeUserFromChat] current game shouldn't be null");
        // dataClientManager.getIClientComToData().removeUserFromChat(idUser,
        // dataClientManager.getCurrentGame().getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.utc.api13.client.data.interfaces.IClientDataToIHM#killCurrentGame()
     */
    @Override
    public void killCurrentGame() {
        dataClientManager.setCurrentGame(null);
    }
}
