package com.utc.api13.client.ihm.models;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.commun.entities.*;
import com.utc.api13.commun.entities.pieces.BishopEntity;
import com.utc.api13.commun.entities.pieces.KingEntity;
import com.utc.api13.commun.entities.pieces.KnightEntity;
import com.utc.api13.commun.entities.pieces.QueenEntity;
import com.utc.api13.commun.entities.pieces.RookEntity;
import com.utc.api13.commun.enumerations.PieceColorEnum;

public class ChessBoardNode {
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
    	 String dossierIcone = "pictures/pieces/";
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
                b.setMargin(buttonMargin);
                //ImageIcon icon = new ImageIcon(new BufferedImage(TAILLE_CASE, TAILLE_CASE, BufferedImage.TYPE_INT_ARGB));
                //b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)
                        // ) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
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
				chessBoardSquares[ctr][ligne].setIcon(new ImageIcon(dossierIcone + ordrePiece[ctr] + couleur + ".gif"));
				
				switch(ordrePiece[ctr])
				{
				case 'T':
					tempo = new RookEntity(ligne < 5 ? PieceColorEnum.BLACK : PieceColorEnum.WHITE, myIhmManager.getIClientDataToIHM().getCurrentGame());
				break;
				
				case 'C':
					tempo = new KnightEntity(ligne < 5 ? PieceColorEnum.BLACK : PieceColorEnum.WHITE, myIhmManager.getIClientDataToIHM().getCurrentGame());
				break;
				
				case 'F':
					tempo = new BishopEntity(ligne < 5 ? PieceColorEnum.BLACK : PieceColorEnum.WHITE, myIhmManager.getIClientDataToIHM().getCurrentGame());
				break;
				
				case 'D':
					tempo = new QueenEntity(ligne < 5 ? PieceColorEnum.BLACK : PieceColorEnum.WHITE, myIhmManager.getIClientDataToIHM().getCurrentGame());
				break;
				
				case 'R':
					tempo = new KingEntity(ligne < 5 ? PieceColorEnum.BLACK : PieceColorEnum.WHITE, myIhmManager.getIClientDataToIHM().getCurrentGame());
				break;
				}
				//mettre à jour structure de data
				//chessBoardSquares[ctr][ligne].setPiece(tempo);
				chessBoardSquares[ctr][ligne + increment].setIcon(new ImageIcon(dossierIcone + 'P' + couleur + ".gif"));
				//mettre à jour structure de data
				//e.getCase(ctr, ligne + increment).setPiece(new Pion(ligne < 5 ? "noir" : "blanc"));

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
