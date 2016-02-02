package com.utc.api13.client.ihm;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import com.utc.api13.client.ihm.controllers.AnswerPropositionController;
import com.utc.api13.client.ihm.controllers.ErrorController;
import com.utc.api13.client.ihm.controllers.GiveUpPopUpController;
import com.utc.api13.client.ihm.controllers.IHMGamePageController;
import com.utc.api13.client.ihm.interfaces.IClientIHMToData;
import com.utc.api13.client.ihm.models.Case;
import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;
import com.utc.api13.commun.enumerations.GameStatusEnum;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientIHMToDataImpl implements IClientIHMToData {

    private IHMManager myIHMManager;
    private IHMGamePageController controller;
    private static final Logger LOGGER = Logger.getLogger(ClientIHMToDataImpl.class);

    public ClientIHMToDataImpl(IHMManager pIHMManager) {
        myIHMManager = pIHMManager;
    }

    @Override
    public void displayProfile(PublicUserEntity u) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                myIHMManager.getProfil().loginProperty().set(u.getLogin());
                myIHMManager.getProfil().firstNameProperty().set(u.getFirstName());
                myIHMManager.getProfil().lastNameProperty().set(u.getLastName());
                myIHMManager.getProfil().statPlayerProperty().setAll(u);
                myIHMManager.getProfil().userUUID().set(u.getId().toString());
                if (u.getImage() != null) {
                    try {
                        ByteArrayInputStream bais = new ByteArrayInputStream(u.getImage());
                        BufferedImage bi = ImageIO.read(bais);
                        WritableImage img = SwingFXUtils.toFXImage(bi, null);
                        myIHMManager.getProfil().imageProperty().set(img);
                    } catch (IOException e) {
                        LOGGER.error("[ClientIHMToDataImpl][displayProfile] " + e.getMessage(), e);
                    }
                }
            }
        });
    }

    @Override
    public void otherPlayerLeaving() {
        List<Object> users = Arrays.asList(myIHMManager.getIClientDataToIHM().getUserList().toArray());
        PublicUserEntity user = (PublicUserEntity) users.stream()
                .filter(u -> ((PublicUserEntity) u).getId().equals(myIHMManager.getUisender())).findFirst()
                .orElse(null);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage stage;
                Parent root = null;
                stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/giveUpPopUp.fxml"));
                try {
                    root = (Pane) fxmlLoader.load();
                    GiveUpPopUpController giveUpPopUpController = fxmlLoader.getController();
                    giveUpPopUpController.setMainApp(myIHMManager.getMainApp(), user != null ? user.getLogin() : null);
                    giveUpPopUpController.setControllerContext(myIHMManager);

                    myIHMManager.setCurrentStage(stage);

                    stage.setScene(new Scene(root));
                    stage.setTitle("asking for leave!");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    LOGGER.error("[ClientIHMToDataImpl][otherPlayerLeaving] " + e.getMessage(), e);
                }
            }
        });
    }

    @Override
    public void displayProposition(UUID uidSender, boolean observable, boolean chattable, boolean timer,
            Integer timeInt) {
        myIHMManager.setUIDistant(uidSender);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                List<Object> users = Arrays.asList(myIHMManager.getIClientDataToIHM().getUserList().toArray());
                PublicUserEntity user = (PublicUserEntity) users.stream()
                        .filter(u -> ((PublicUserEntity) u).getId().equals(uidSender)).findFirst().orElse(null);

                Stage stage;
                Parent root = null;
                stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/answerPropositionPopUp.fxml"));
                try {
                    root = (Pane) fxmlLoader.load();
                    AnswerPropositionController answerPropositionController = fxmlLoader.getController();
                    answerPropositionController.setControllerContext(myIHMManager);
                    myIHMManager.setCurrentStage(stage);
                    answerPropositionController.setMainApp(user.getLogin(), chattable, timer,
                            observable, timeInt);

                    stage.setScene(new Scene(root));
                    stage.setTitle("You've got a new game proposition!");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    LOGGER.error("[ClientIHMToDataImpl][displayProposition] " + e.getMessage(), e);
                }
            }
        });
    }

    @Override
    public void displayAnswer(UUID uidSender, boolean answer, String message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage stage;
                Parent root = null;
                stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/errorPopUp.fxml"));
                try {
                    root = (Pane) fxmlLoader.load();
                    ErrorController errorController = fxmlLoader.getController();
                    errorController.setControllerContext(myIHMManager);
                    errorController.setText(message);
                    stage.setScene(new Scene(root));
                    stage.setTitle("Proposition refused");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    LOGGER.error("[ClientIHMToDataImpl][displayAnswer] " + e.getMessage(), e);
                }
            }
        });
    }

    @Override
    public void displayChessBoard(GameEntity g) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                Stage stage;
                Parent root = null;
                stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/gamePage.fxml"));
                try {
                    root = (Pane) fxmlLoader.load();
                    controller = fxmlLoader.getController();
                    controller.setControllerContext(myIHMManager);

                    controller.setMainApp(myIHMManager.getMainApp());
                    stage.setScene(new Scene(root));
                    stage.setTitle("Game!");
                    myIHMManager.setCurrentGameStage(stage);
                    myIHMManager.setCurrentGameStage(stage);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    LOGGER.error("[ClientIHMToDataImpl][displayAnswer] " + e.getMessage(), e);
                }
            }
        });
    }

    @Override
    public void refreshChessBoard(int lineFrom, int colFrom, int lineTo, int colTo, APieceEntity piece,
            GameEntity game) {
        String dossierIcone = "/pictures/pieces/";
        Case[][] chessBoardSquares = controller.getCb().getChessBoardSquares();
        chessBoardSquares[lineFrom - 1][8 - colFrom].setIcon(null);
        String pieceType;

        switch (piece.toString()) {
        case "Rook":
            pieceType = "T";
            break;
        case "Knight":
            pieceType = "C";
            break;
        case "Queen":
            pieceType = "D";
            break;
        case "King":
            pieceType = "R";
            break;
        case "Pawn":
            pieceType = "P";
            break;
        case "Bishop":
            pieceType = "F";
            break;
        default:
            pieceType = "";
        }

        String iconePath;
        if (!game.getCurrentPlayer().getId().equals(game.getBlackPlayer().getId())) {
            iconePath = dossierIcone + pieceType + "B";
        } else {
            iconePath = dossierIcone + pieceType + "N";
        }
        try {
            Image img = ImageIO.read(getClass().getResource(iconePath + ".gif"));
            Image imgDisabled = ImageIO.read(getClass().getResource(iconePath + "_disabled.gif"));
            chessBoardSquares[lineTo - 1][8 - colTo].setIcon(new ImageIcon(img));
            chessBoardSquares[lineTo - 1][8 - colTo].setDisabledIcon(new ImageIcon(imgDisabled));
        } catch (IOException e) {
            LOGGER.error("[ClientIHMToDataImpl][refreshChessBoard] " + e.getMessage(), e);
        }
    }

    @Override
    public void displayError(String errorMessage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                myIHMManager.getError().errorMessageProperty().set(errorMessage);
            }
        });
    }

    @Override
    public void displayConfirmation(String confirmationMessage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                myIHMManager.getConfirmation().confirmationMessageProperty().set(confirmationMessage);
            }
        });
    }

    @Override
    public void refreshObserverList() {
        // Coming with MasterChess-2.0.0-RELEASE
    }

    @Override
    public void displayGameLiveObserver() {
        // Coming with MasterChess-2.0.0-RELEASE
    }

    @Override
    public void displayMessage(String newMessage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String sms = myIHMManager.getChat().getMessage().get();
                myIHMManager.getChat().getMessage().set((sms == null ? "" : sms) + "\n" + newMessage);
            }
        });
    }

    @Override
    public void activateCases(PublicUserEntity currentUser, GameStatusEnum status) {
        if (status.equals(GameStatusEnum.CHECK)) {
            controller.getCb().changeCheckSituation();
        }

        if (status.equals(GameStatusEnum.CHECKMATE)) {
            controller.getCb().changeCheckMateSituation();
        } else {
            enableCases(currentUser, status);
        }
    }

    @Override
    public void closeGameScreen(boolean answer) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                if (answer)
                    myIHMManager.getMainApp().displayConfirmationPopup(
                            "The other player has accepted your demand Therefore there is no winner in this Game");
                else
                    myIHMManager.getMainApp()
                            .displayErrorPopup("the answer of the other player was NO .Therefore you lose the Game");

                myIHMManager.getCurrentGameStage().close();
            }
        });
    }

    private void enableCases(PublicUserEntity currentUser, GameStatusEnum status) {
        Case[][] cb = controller.getCb().getChessBoardSquares();
        if (status != GameStatusEnum.CHECKMATE
                && myIHMManager.getIClientDataToIHM().getLocalUser().getId().equals(currentUser.getId())) {
            for (Case[] i : cb) {
                for (Case j : i) {
                    j.setEnabled(true);
                }
            }
        }
    }

    @Override
    public void endGameBySurrend() {
        // TODO Auto-generated method stub
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                myIHMManager.getCurrentGameStage().close();
            }
        });
    }
}
