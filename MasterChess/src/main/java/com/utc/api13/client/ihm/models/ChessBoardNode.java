package com.utc.api13.client.ihm.models;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class ChessBoardNode implements ActionListener {
    private IClientDataToIHM myIClientToIHM;
    private IHMManager myIhmManager;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JPanel chessBoard;
    private static final String COLS = "ABCDEFGH";
    private static final int TAILLE = 8;
    private static final int TAILLE_CASE = 25;
    private JButton[][] chessBoardSquares = new JButton[TAILLE][TAILLE];

    public ChessBoardNode(IHMManager ihmManager) {
        myIhmManager = ihmManager;
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

        // create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						//setter une variable d'etat pour savoir quand on selectionne une piece ou une position
						
						
						//cette fonction est appellée a chaque clic sur un bouton
						//la position de ce bouton est -> ii , jj
						
						//if(mode == 1) listePosition = getAvailableMoves(ii,jj)
						//if(listePosition != vide) -> surbrillance + mode = 2
						//garder dans une variable la position
						
						
						//if(mode == 2) ... si(position choisi = position actuelle) -> mode == 1
						// sinon playMoves(ancienne position, nouvelle position(ii,jj))
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

}
