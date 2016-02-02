package com.utc.api13.client.ihm.models;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.beans.property.BooleanProperty;

public class ChessBoardNode {
    private IClientDataToIHM myIClientToIHM;
    private GameEntity myGame;
    private IHMManager myIhmManager;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JPanel chessBoard;
    private static final String COLS = "ABCDEFGH";
    private static final int TAILLE = 8;
    private Case[][] chessBoardSquares = new Case[TAILLE][TAILLE];
    private PositionEntity firstPosition = new PositionEntity(-1, -1);
    List<PositionEntity> positionList;
    private int selection = 1;
    private BooleanProperty checkProperty;
    private BooleanProperty checkMateProperty;
    private static final Logger LOGGER = Logger.getLogger(ChessBoardNode.class);

    public ChessBoardNode(IHMManager ihmManager, GameEntity game, IClientDataToIHM myIClientToIHM2,
            BooleanProperty isCheck, BooleanProperty isCheckMate) {
        myIhmManager = ihmManager;
        myGame = game;
        myIClientToIHM = myIClientToIHM2;
        initializeGui();
        checkProperty = isCheck;
        checkMateProperty = isCheckMate;
    }

    public final void initializeGui() {
        String dossierIcone = "/pictures/pieces/";
        char[] ordrePiece = { 'T', 'C', 'F', 'D', 'R', 'F', 'C', 'T' };
        int increment = 1;
        int ligne = 0;
        char couleur = 'N';
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                Case b = new Case(ii, jj);
                b.setEnabled(false);

                if ((jj % 2 == 1 && ii % 2 == 1) || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                    b.setColor(Color.WHITE);
                } else {
                    b.setBackground(Color.GRAY);
                    b.setColor(Color.GRAY);
                }

                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Case movePosition = (Case) e.getSource();
                        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
                            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                                if (movePosition == chessBoardSquares[ii][jj]) {

                                    movePosition.setLine(ii + 1);
                                    movePosition.setColumn(8 - jj);
                                }
                            }
                        }

                        if (selection == 1) {
                            firstPosition.setPositionX(movePosition.getLine());
                            firstPosition.setPositionY(movePosition.getColumn());
                            if (positionList != null) {
                                positionList.clear();
                                positionList = null;
                            }
                            positionList = myIClientToIHM.getAvailablesMoves(firstPosition.getPositionX(),
                                    firstPosition.getPositionY());
                            if (!positionList.isEmpty()) {
                                selection = 2;
                                for (int ii = 0; ii < chessBoardSquares.length; ii++) {
                                    for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {

                                        chessBoardSquares[ii][jj].setEnabled(false);
                                    }
                                }

                                chessBoardSquares[firstPosition.getPositionX() - 1][8 - firstPosition.getPositionY()]
                                        .setBorder(new LineBorder(Color.GREEN));
                                chessBoardSquares[firstPosition.getPositionX() - 1][8 - firstPosition.getPositionY()]
                                        .setEnabled(true);
                                for (int i = 0; i < positionList.size(); i++) {
                                    chessBoardSquares[positionList.get(i).getPositionX() - 1][8
                                            - positionList.get(i).getPositionY()].setEnabled(true);

                                    chessBoardSquares[positionList.get(i).getPositionX() - 1][8
                                            - positionList.get(i).getPositionY()].setBackground(Color.GREEN);
                                }

                            }
                        }

                        else if (selection == 2) {
                            if ((movePosition.getLine() == firstPosition.getPositionX())
                                    && (movePosition.getColumn() == firstPosition.getPositionY())) {
                                activateCases(myGame.getCurrentPlayer());
                            } else {
                                myIClientToIHM.playMove(firstPosition.getPositionX(), firstPosition.getPositionY(),
                                        movePosition.getLine(), movePosition.getColumn());

                                if (checkProperty.get()) {
                                    changeCheckSituation();
                                }

                                for (Case[] i : chessBoardSquares) {
                                    for (Case j : i) {
                                        j.setEnabled(false);
                                    }
                                }
                            }
                            selection = 1;
                            for (Case[] i : chessBoardSquares) {
                                for (Case j : i) {
                                    j.setBackground(j.getColor());
                                }
                            }
                            chessBoardSquares[firstPosition.getPositionX() - 1][8 - firstPosition.getPositionY()]
                                    .setBorder(null);
                        }
                    }
                });
                b.setMargin(buttonMargin);
                chessBoardSquares[jj][ii] = b;

            }
        }

        activateCases(myGame.getCurrentPlayer());

        chessBoard.add(new JLabel(""));
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(new JLabel(COLS.substring(ii, ii + 1), SwingConstants.CENTER));
        }

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

        while (increment >= -1) {
            for (int ctr = 0; ctr <= 7; ctr++) {
                String iconePath = dossierIcone + ordrePiece[ctr] + couleur;
                try {
                    Image img = ImageIO.read(getClass().getResource(iconePath + ".gif"));
                    Image imgDisabled = ImageIO.read(getClass().getResource(iconePath + "_disabled.gif"));
                    chessBoardSquares[ctr][ligne].setIcon(new ImageIcon(img));
                    chessBoardSquares[ctr][ligne].setDisabledIcon(new ImageIcon(imgDisabled));
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }

                String iconePathPawn = dossierIcone + 'P' + couleur;
                try {
                    Image img = ImageIO.read(getClass().getResource(iconePathPawn + ".gif"));
                    Image imgDisabled = ImageIO.read(getClass().getResource(iconePathPawn + "_disabled" + ".gif"));
                    chessBoardSquares[ctr][ligne + increment].setIcon(new ImageIcon(img));
                    chessBoardSquares[ctr][ligne + increment].setDisabledIcon(new ImageIcon(imgDisabled));

                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }

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

    public IClientDataToIHM getMyIClientToIHM() {
        return myIClientToIHM;
    }

    public void setMyIClientToIHM(IClientDataToIHM myIClientToIHM) {
        this.myIClientToIHM = myIClientToIHM;
    }

    public GameEntity getMyGame() {
        return myGame;
    }

    public void setMyGame(GameEntity myGame) {
        this.myGame = myGame;
    }

    public void activateCases(PublicUserEntity currentUser) {
        if (myIClientToIHM.getLocalUser().getId().equals(currentUser.getId())) {
            for (Case[] i : chessBoardSquares) {
                for (Case j : i) {
                    j.setEnabled(true);
                }
            }
        }
    }

    public void changeCheckSituation() {
        checkProperty.set(!checkProperty.get());
    }

    public void changeCheckMateSituation() {
        checkMateProperty.set(!checkMateProperty.get());
    }
}
