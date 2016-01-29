package com.utc.api13.commun.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.util.Assert;

import com.utc.api13.commun.entities.pieces.BishopEntity;
import com.utc.api13.commun.entities.pieces.KingEntity;
import com.utc.api13.commun.entities.pieces.KnightEntity;
import com.utc.api13.commun.entities.pieces.PawnEntity;
import com.utc.api13.commun.entities.pieces.QueenEntity;
import com.utc.api13.commun.entities.pieces.RookEntity;
import com.utc.api13.commun.enumerations.GameStatusEnum;
import com.utc.api13.commun.enumerations.PieceColorEnum;

public class GameEntity extends ADataEntity {

    private static final long serialVersionUID = -959030856925179648L;
    private Date creationDate;
    private Boolean isObservable;
    private Boolean isChattable;
    private boolean timer;
    private Integer timerInt;
    private Date limit;
    private PublicUserEntity whitePlayer;
    private PublicUserEntity blackPlayer;
    private PublicUserEntity currentPlayer;
    private GameStatusEnum isFinished;
    private ChessboardEntity chessboardEntity;
    private List<PublicUserEntity> observers;
    private List<MoveEntity> movesHistory;
    private List<APieceEntity> whitePieces;
    private List<APieceEntity> blackPieces;
    private List<MessageEntity> messages;

    /**
     * 
     */
    public GameEntity() {
        super();
        this.movesHistory = new ArrayList<MoveEntity>();
        this.observers = new ArrayList<PublicUserEntity>();
        this.chessboardEntity = new ChessboardEntity();
        this.creationDate = new Date();
        this.isFinished = null;
        this.isObservable = Boolean.FALSE;
        this.isChattable = Boolean.FALSE;
        this.limit = null;
        this.whitePlayer = null;
        this.blackPlayer = null;
        this.currentPlayer = null;
        this.blackPieces = generatePieces(PieceColorEnum.BLACK);
        this.whitePieces = generatePieces(PieceColorEnum.WHITE);
        this.messages = new ArrayList<MessageEntity>();
    }

    /**
     * @param idOservable
     * @param isChattable
     * @param limit
     * @param whitePlayer
     * @param blackPlayer
     */
    public GameEntity(Boolean idOservable, Boolean isChattable, Date limit, PublicUserEntity whitePlayer,
            PublicUserEntity blackPlayer) {
        super();
        this.isObservable = idOservable;
        this.isChattable = isChattable;
        this.limit = limit;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.currentPlayer = whitePlayer;
        this.movesHistory = new ArrayList<MoveEntity>();
        this.observers = new ArrayList<PublicUserEntity>();
        this.chessboardEntity = new ChessboardEntity();
        this.creationDate = new Date();
        this.isFinished = null;
        this.blackPieces = generatePieces(PieceColorEnum.BLACK);
        this.whitePieces = generatePieces(PieceColorEnum.WHITE);
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the idOservable
     */
    public Boolean getIsObservable() {
        return isObservable;
    }

    /**
     * @param idOservable
     *            the idOservable to set
     */
    public void setIsObservable(Boolean idOservable) {
        this.isObservable = idOservable;
    }

    /**
     * @return the isChattable
     */
    public Boolean getIsChattable() {
        return isChattable;
    }

    /**
     * @param isChattable
     *            the isChattable to set
     */
    public void setIsChattable(Boolean isChattable) {
        this.isChattable = isChattable;
    }

    /**
     * @return the limit
     */
    public Date getLimit() {
        return limit;
    }

    /**
     * @param limit
     *            the limit to set
     */
    public void setLimit(Date limit) {
        this.limit = limit;
    }

    /**
     * @return the whitePlayer
     */
    public PublicUserEntity getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * @param whitePlayer
     *            the whitePlayer to set
     */
    public void setWhitePlayer(PublicUserEntity whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    /**
     * @return the blackPlayer
     */
    public PublicUserEntity getBlackPlayer() {
        return blackPlayer;
    }

    /**
     * @param blackPlayer
     *            the blackPlayer to set
     */
    public void setBlackPlayer(PublicUserEntity blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    /**
     * @return the isFinished
     */
    public GameStatusEnum getIsFinished() {
        return isFinished;
    }

    /**
     * @param isFinished
     *            the isFinished to set
     */
    public void setIsFinished(GameStatusEnum isFinished) {
        this.isFinished = isFinished;
    }

    /**
     * @return the chessboardEntity
     */
    public ChessboardEntity getChessboardEntity() {
        return chessboardEntity;
    }

    /**
     * @param chessboardEntity
     *            the chessboardEntity to set
     */
    public void setChessboardEntity(ChessboardEntity chessboardEntity) {
        this.chessboardEntity = chessboardEntity;
    }

    /**
     * @return the observers
     */
    public List<PublicUserEntity> getObservers() {
        return observers;
    }

    /**
     * @param observers
     *            the observers to set
     */
    public void setObservers(List<PublicUserEntity> observers) {
        this.observers = observers;
    }

    /**
     * @return the movesHistory
     */
    public List<MoveEntity> getMovesHistory() {
        return movesHistory;
    }

    /**
     * @param movesHistory
     *            the movesHistory to set
     */
    public void setMovesHistory(List<MoveEntity> movesHistory) {
        this.movesHistory = movesHistory;
    }

    /**
     * @return the whitePieces
     */
    public List<APieceEntity> getWhitePieces() {
        return whitePieces;
    }

    /**
     * @param whitePieces
     *            the whitePieces to set
     */
    public void setWhitePieces(List<APieceEntity> whitePieces) {
        this.whitePieces = whitePieces;
    }

    /**
     * @return the blackPieces
     */
    public List<APieceEntity> getBlackPieces() {
        return blackPieces;
    }

    /**
     * @param blackPieces
     *            the blackPieces to set
     */
    public void setBlackPieces(List<APieceEntity> blackPieces) {
        this.blackPieces = blackPieces;
    }

    /**
     * @return the isOservable
     */
    public Boolean getIsOservable() {
        return isObservable;
    }

    /**
     * @param isOservable
     *            the isOservable to set
     */
    public void setIsOservable(Boolean isOservable) {
        this.isObservable = isOservable;
    }

    /**
     * @return the currentPlayer
     */
    public PublicUserEntity getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @param currentPlayer
     *            the currentPlayer to set
     */
    public void setCurrentPlayer(PublicUserEntity currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * 
     * @return Returns the list of messages from chat
     */
    public List<MessageEntity> getMessages() {
        return messages;
    }

    /**
     * @return the timer
     */
    public boolean isTimer() {
        return timer;
    }

    /**
     * @param timer
     *            the timer to set
     */
    public void setTimer(boolean timer) {
        this.timer = timer;
    }

    /**
     * @return the timerInt
     */
    public Integer getTimerInt() {
        return timerInt;
    }

    /**
     * @param timerInt
     *            the timerInt to set
     */
    public void setTimerInt(Integer timerInt) {
        this.timerInt = timerInt;
    }

    /**
     * Will switch current user between user black and user white
     * 
     * @author ulyss_000
     */
    public void switchCurrentUser() {
        // switch active user :
        if (this.getBlackPlayer().getId().equals(this.getCurrentPlayer().getId())) {
            this.setCurrentPlayer(this.getWhitePlayer());
        } else {
            this.setCurrentPlayer(this.getBlackPlayer());
        }
    }

    /**
     * @author ulyss_000
     * @return the current player king
     */
    public KingEntity getKing() {
        List<APieceEntity> pieces;

        if (this.getCurrentPlayer().getId().equals(this.getBlackPlayer().getId())) {
            // ActivePlayer is BlackPlayer
            pieces = getBlackPieces();
        } else {
            // ActivePlayer is WhitePlayer
            pieces = getWhitePieces();
        }

        for (APieceEntity tmpEntity : pieces) {
            if (tmpEntity.toString().equals("King")) {
                return (KingEntity) tmpEntity;
            }
        }
        return null;
    }

    /**
     * @author ulyss_000
     * @return the current player pieces
     */
    public List<APieceEntity> getCurrentPlayerPieces() {
        if (this.getCurrentPlayer().getId().equals(this.getBlackPlayer().getId())) {
            // ActivePlayer is BlackPlayer
            return this.getBlackPieces();

        } else {
            // ActivePlayer is WhitePlayer
            return this.getWhitePieces();

        }
    }

    /**
     * @author ulyss_000
     * @return the current player color
     */
    public PieceColorEnum getCurrentPlayerColor() {
        if (this.getCurrentPlayer().getId().equals(this.getBlackPlayer().getId())) {
            // ActivePlayer is BlackPlayer
            return PieceColorEnum.BLACK;

        } else {
            // ActivePlayer is WhitePlayer
            return PieceColorEnum.WHITE;

        }
    }

    /**
     * @author ulyss_000
     * @return the other player pieces
     */
    public List<APieceEntity> getOpponentPieces() {
        if (this.getCurrentPlayer().getId().equals(this.getBlackPlayer().getId())) {
            // ActivePlayer is BlackPlayer
            return this.getWhitePieces();
        } else {
            // ActivePlayer is WhitePlayer
            return this.getBlackPieces();

        }
    }

    /**
     * @author ulyss_000
     * @return true if the gameentity is in check, false if not
     */
    public Boolean isCheck() {
        Boolean result = Boolean.FALSE;

        List<APieceEntity> tmp = new ArrayList<APieceEntity>();
        tmp.addAll(this.getOpponentPieces());

        for (APieceEntity op : tmp) {
            List<PositionEntity> tmpP = new ArrayList<PositionEntity>();
            APieceEntity king = this.getKing();
            if (king == null) {
                return Boolean.FALSE;
            }
            tmpP.addAll(op.generateAvailableMoves(this, Boolean.FALSE));
            if (!APieceEntity.isPositionAvailable(tmpP, king.getPosition())) {
                result = Boolean.TRUE;
            }
        }
        return result;
    }

    /**
     * Verify the winning status of the game
     * 
     * @author ulyss_000
     * @return GameStatusEnum -> CHECKMATE,MATE,CONTINUE
     */
    public GameStatusEnum isFinished() {
        Assert.notNull(this.getCurrentPlayer(), "[GameEntity][isFinished] currentPlayer shouldn't be null");
        Assert.notNull(this.getBlackPieces(), "[GameEntity][isFinished] blackPieces shouldn't be null");
        Assert.notNull(this.getWhitePieces(), "[GameEntity][isFinished] whitePieces shouldn't be null");

        // Local variables :
        KingEntity king;
        List<APieceEntity> opponentPieces;
        GameStatusEnum result = GameStatusEnum.CONTINUE;
        Boolean check = false;

        // set local variables according to the local player color :

        opponentPieces = this.getOpponentPieces();

        king = (KingEntity) opponentPieces.stream().filter(bp -> bp.toString().equals("King")).findFirst().orElse(null);

        // Check check
        if (this.isCheck()) {
            check = true;
            result = GameStatusEnum.CHECK;
        }

        // Checkmate check :
        if (check == true) {
            // get all availables moves of the current player, if null ->
            // checkmate :
            List<APieceEntity> currentPlayerPieces = new ArrayList<APieceEntity>();
            currentPlayerPieces.addAll(this.getCurrentPlayerPieces());
            List<PositionEntity> currentPlayerAvailableMoves = new ArrayList<PositionEntity>();
            for (APieceEntity piece : currentPlayerPieces) {
                currentPlayerAvailableMoves.addAll(piece.generateAvailableMoves(this));
            }
            // check if nothing can save the king :
            if (currentPlayerAvailableMoves.isEmpty()) {
                result = GameStatusEnum.CHECKMATE;
            }
        }

        // Draw check :
        // TODO : Ulysse : there are a lot of those...

        return result;
    }

    /**
     * 
     * @return the concatenation of the observer list and the two players
     */
    public List<PublicUserEntity> getAllParticipants() {
        List<PublicUserEntity> participants = new ArrayList<PublicUserEntity>();
        Collections.copy(participants, this.observers);
        participants.add(getBlackPlayer());
        participants.add(getWhitePlayer());
        return participants;
    }

    private List<APieceEntity> generatePieces(final PieceColorEnum color) {
        List<APieceEntity> pieces = new ArrayList<APieceEntity>();
        pieces.add(new KingEntity(color));
        pieces.add(new QueenEntity(color));

        for (int column : GameEntityConstant.getInitialColumnsRook()) {
            pieces.add(new RookEntity(color, column));
        }

        for (int column : GameEntityConstant.getInitialColumnsPawn()) {
            pieces.add(new PawnEntity(color, column));
        }

        for (int column : GameEntityConstant.getInitialColumnsKnight()) {
            pieces.add(new KnightEntity(color, column));
        }

        for (int column : GameEntityConstant.getInitialColumnsBishop()) {
            pieces.add(new BishopEntity(color, column));
        }

        return pieces;
    }

    /**
     * Add a piece to the game
     * 
     * @param piece
     * @author ulyss_000
     */
    public void addPiece(APieceEntity piece) {
        if (piece.getColor().equals(PieceColorEnum.BLACK)) {
            this.blackPieces.add(piece);
        } else {
            this.whitePieces.add(piece);
        }
    }

    /**
     * Removes a piece from the game
     * 
     * @param piece
     * @author ulyss_000
     */
    public void removePiece(APieceEntity piece) {
        if (piece.getColor().equals(PieceColorEnum.BLACK)) {
            this.blackPieces.remove(piece);
        } else {
            this.whitePieces.remove(piece);
        }
    }

    public void removePieceFromPosition(PositionEntity myposition) {
        APieceEntity piece = this.getPieceFromPosition(myposition);
        if (piece != null) {
            this.removePiece(piece);
        }
    }

    /**
     * Return a piece from a position
     * 
     * @param myposition
     * @return APieceEntity
     */
    public APieceEntity getPieceFromPosition(PositionEntity myposition) {
        Assert.notNull(this.getWhitePieces(), "[GameEntity][getPieceFromPosition] whitePieces shouldn't be null");
        Assert.notNull(this.getBlackPieces(), "[GameEntity][getPieceFromPosition] blackPieces shouldn't be null");

        List<APieceEntity> piecelist = Stream.concat(this.getWhitePieces().stream(), this.getBlackPieces().stream())
                .collect(Collectors.toList());

        return piecelist.stream().filter(piece -> piece.getPosition().equals(myposition)).findFirst().orElse(null);

    }

    // utilise pour binder a une partie sur l'ecran d'accueil
    public String getWhitePlayerLogin() {
        return whitePlayer.getLogin();
    }

    public String getBlackPlayerLogin() {
        return blackPlayer.getLogin();
    }

    public String getCreationDateDrawable() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String reportDate = df.format(creationDate);
        return reportDate;
    }

    /**
     * apply a move on a game entity
     * 
     * @author ulyss_000
     * @param move
     */
    public void movePiece(MoveEntity move) {
        //Hugo : ajout pour la gestion des roques
        //A faire avant le reste sinon on ne peut plus utiliser le getFromPosition
        if(move.getPiece().toString().equals("King")){
            KingEntity tmpKing = (KingEntity)this.getPieceFromPosition(move.getFromPosition());
            tmpKing.setHasMove(Boolean.TRUE);
            
            //left castling :
            moveRookIfCastling(move, -2, 3, 1);
            //right casting :
            moveRookIfCastling(move, 2, -4, -1);
            
        }
        if(move.getPiece().toString().equals("Rook")){
            RookEntity tmpRook = (RookEntity)this.getPieceFromPosition(move.getFromPosition());
            tmpRook.setHasMove(Boolean.TRUE);
        }
        
        // ulysse: en mode fast :
        this.getPieceFromPosition(move.getFromPosition()).setPosition(move.getToPosition());
        
        //ajout dans l'historique des coups :
        this.getMovesHistory().add(move);
    }
    
    /**
     * Factor Castling rook move (for HU)
     * @author ulyss_000
     * @param move
     * @param newKingX -> -2 ou 2
     * @param rookOldX -> 3 ou -4
     * @param rookNewX -> 1 ou -1
     */
    public void moveRookIfCastling(MoveEntity move, int newKingX, int rookOldX,int rookNewX){
        if(move.getFromPosition().getPositionX() == move.getToPosition().getPositionX() + newKingX){
            PositionEntity rookPositionTmp = new PositionEntity(move.getFromPosition().getPositionX() + rookOldX, move.getFromPosition().getPositionY());
            PositionEntity rookPositionToGo = new PositionEntity(move.getFromPosition().getPositionX() + rookNewX, move.getFromPosition().getPositionY());
            
            this.getPieceFromPosition(rookPositionTmp).setPosition(rookPositionToGo);
        }
    }
    
    public void cancelMove(MoveEntity moveToCancel) {
         
        //check if it was a castling to undo it :
        if(moveToCancel.getPiece().toString().equals("King")){
            KingEntity tmpKing = (KingEntity)this.getPieceFromPosition(moveToCancel.getToPosition());
            tmpKing.setHasMove(Boolean.FALSE);
            
            //left castling undo :
            moveRookIfCastling(moveToCancel, -2, 1, 3);
            //right casting undo :
            moveRookIfCastling(moveToCancel, 2, -1, -4);
            
        }
        
        if(moveToCancel.getPiece().toString().equals("Rook")){
            RookEntity tmpRook = (RookEntity)this.getPieceFromPosition(moveToCancel.getToPosition());
            tmpRook.setHasMove(Boolean.FALSE);
        }
        
        //reset position
        this.getPieceFromPosition(moveToCancel.getToPosition()).setPosition(moveToCancel.getFromPosition());
        
        //remove from play history :
        this.getMovesHistory().remove(moveToCancel);
    }
}