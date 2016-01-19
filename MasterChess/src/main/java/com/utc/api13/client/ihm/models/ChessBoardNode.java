package com.utc.api13.client.ihm.models;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.commun.entities.AUserEntity;
import com.utc.api13.commun.entities.ChessboardEntity;
import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.entities.pieces.BishopEntity;
import com.utc.api13.commun.entities.pieces.KingEntity;
import com.utc.api13.commun.entities.pieces.KnightEntity;
import com.utc.api13.commun.entities.pieces.PawnEntity;
import com.utc.api13.commun.entities.pieces.QueenEntity;
import com.utc.api13.commun.entities.pieces.RookEntity;
import com.utc.api13.commun.enumerations.PieceColorEnum;

public class ChessBoardNode {
    private IClientDataToIHM myIClientToIHM;
    private IHMManager myIhmManager;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JPanel chessBoard;
    private static final String COLS = "ABCDEFGH";
    private static final int TAILLE = 8;
    private static final int TAILLE_CASE = 25;
    private Case[][] chessBoardSquares = new Case[TAILLE][TAILLE];
    private PositionEntity currentPosition;
  //setter une variable d'etat pour savoir si on selectionne une piece ou une position pour les déplacements dans le listener
  	private int selection = 1;

    /**public ChessBoardNode(IHMManager ihmManager) {
        myIhmManager = ihmManager;
        initializeGui();
    }*/
  	
  	public ChessBoardNode() {
  		initializeGui();
  	}

    public final void initializeGui() {
        String dossierIcone = "/pictures/pieces/";
        char[] ordrePiece = { 'T', 'C', 'F', 'D', 'R', 'F', 'C', 'T' };
        int increment = 1;
        int ligne = 0;
        char couleur = 'N';
        APieceEntity tempo = null;
        
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);

        // création des cases de l'échiquier avec le listener pour les déplacements
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                Case b = new Case(ii, jj);
                b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Case current = (Case) e.getSource();
						if (selection == 1) {
							currentPosition.setPositionX(current.getLine());
							currentPosition.setPositionY(current.getColumn());
							List<PositionEntity> positionList = myIClientToIHM.getAvailableMoves(current.getLine(), current.getColumn());
							if (positionList != null) {
								selection = 2;
								// passer les cases en surbrillance
								for (int i=0; i < positionList.size(); i++) {
									chessBoardSquares[positionList.get(i).getPositionX()][positionList.get(i).getPositionY()].setBackground(Color.GREEN);
								}
							}
						}
												
						if (selection ==2) {
							if ((current.getLine() == currentPosition.getPositionX()) && (current.getColumn() ==currentPosition.getPositionY())) {
								selection = 1;
							}
							else {
								myIClientToIHM.playmoves(currentPosition.getPositionX(), currentPosition.getPositionY(), current.getLine(), current.getColumn());
							}
						}

					}
				});
                b.setMargin(buttonMargin);
                if ((jj % 2 == 1 && ii % 2 == 1) || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.GRAY);
                }
                chessBoardSquares[jj][ii] = b;
               
            }
        }

        // fill the chess board
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(new JLabel(COLS.substring(ii, ii + 1), SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                case 0:
                    chessBoard.add(new JLabel("" + (ii + 1), SwingConstants.CENTER));
                default:
                    chessBoard.add(chessBoardSquares[jj][ii]);
                }
            }
        }

        // Je place les icônes des pièces sur leur case respective
        while (increment >= -1) {
            for (int ctr = 0; ctr <= 7; ctr++) {
                try {
                    Image img = ImageIO.read(getClass().getResource(dossierIcone + ordrePiece[ctr] + couleur + ".gif"));
                    chessBoardSquares[ctr][ligne].setIcon(new ImageIcon(img));
                } catch (IOException e) {

                }

                switch (ordrePiece[ctr]) {
                case 'T':
                    tempo = new RookEntity(ligne < 5 ? PieceColorEnum.BLACK : PieceColorEnum.WHITE, ctr);
                    break;

                case 'C':
                    tempo = new KnightEntity(ligne < 5 ? PieceColorEnum.BLACK : PieceColorEnum.WHITE, ctr);
                    break;

                case 'F':
                    tempo = new BishopEntity(ligne < 5 ? PieceColorEnum.BLACK : PieceColorEnum.WHITE, ctr);
                    break;

                case 'D':
                    tempo = new QueenEntity(ligne < 5 ? PieceColorEnum.BLACK : PieceColorEnum.WHITE);
                    break;

                case 'R':
                    tempo = new KingEntity(ligne < 5 ? PieceColorEnum.BLACK : PieceColorEnum.WHITE);
                    break;
                }

                try {
                    Image img = ImageIO.read(getClass().getResource(dossierIcone + 'P' + couleur + ".gif"));
                    chessBoardSquares[ctr][ligne + increment].setIcon(new ImageIcon(img));
                 
                } catch (IOException e) {

                }
                tempo = new PawnEntity(ligne < 5 ? PieceColorEnum.BLACK : PieceColorEnum.WHITE, ctr);

            }
            couleur = 'B';
            increment -= 2;
            ligne = 7;
        }
    }

    public final JComponent getChessBoard() {
        return chessBoard;
    }

    public final JPanel getGui() {
        return gui;
    }
    
    public final IHMManager getIHMManager() {
    	return this.myIhmManager;
    }
    
    public final void setIHMManager(IHMManager im) {
    	this.myIhmManager = im;
    }
    
    public final Case[][] getChessBoardSquares() {
    	return this.chessBoardSquares;
    }
}
