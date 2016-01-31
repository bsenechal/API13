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
                if(u.getImage()!=null){
                    try {
                        ByteArrayInputStream bais=  new ByteArrayInputStream(u.getImage());
                        BufferedImage bi = ImageIO.read(bais);
                        WritableImage img = SwingFXUtils.toFXImage(bi, null);
                        myIHMManager.getProfil().imageProperty().set(img);
                    } catch (IOException e) {
                        e.printStackTrace();
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
                    GiveUpPopUpController controller = fxmlLoader.getController();
                    controller.setMainApp(myIHMManager.getMainApp(), user != null ? user.getLogin() : null);
                    controller.setControllerContext(myIHMManager);

                    myIHMManager.setCurrentStage(stage);

                    stage.setScene(new Scene(root));
                    stage.setTitle("asking for leave!");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    LOGGER.error("[ClientIHMToDataImpl][otherPlayerLeaving] " + e.getMessage());
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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AnswerPropositionPopUp.fxml"));
                try {
                    root = (Pane) fxmlLoader.load();
                    AnswerPropositionController controller = fxmlLoader.getController();
                    controller.setControllerContext(myIHMManager);
                    myIHMManager.setCurrentStage(stage);
                    controller.setMainApp(myIHMManager.getMainApp(), user.getLogin(), chattable, timer, observable,
                            timeInt);

                    stage.setScene(new Scene(root));
                    stage.setTitle("You've got a new game proposition!");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    LOGGER.error("[ClientIHMToDataImpl][displayProposition] " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void displayAnswer(UUID uidSender, boolean answer, String message) {
        // uniquement si réponse négative
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage stage;
                Parent root = null;
                stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/errorPopUp.fxml"));
                try {
                    root = (Pane) fxmlLoader.load();
                    ErrorController controller = fxmlLoader.getController();
                    controller.setControllerContext(myIHMManager);
                    controller.setMainApp(myIHMManager.getMainApp(), message);
                    stage.setScene(new Scene(root));
                    stage.setTitle("Proposition refused");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    LOGGER.error("[ClientIHMToDataImpl][displayAnswer] " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void displayChessBoard(GameEntity g) { // si yep data should call
                                                  // that function
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
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    LOGGER.error("[ClientIHMToDataImpl][displayAnswer] " + e.getMessage());
                }
            }
        });
    }

    public void refreshChessBoard(int line_from, int col_from, int line_to, int col_to, APieceEntity piece,
            GameEntity game) {
        String dossierIcone = "/pictures/pieces/";
        // récupérer chessboardsquares
        Case[][] chessBoardSquares = controller.getCb().getChessBoardSquares();

        // effacer la pièce de l'ancienne case
        chessBoardSquares[line_from - 1][8 - col_from].setIcon(null);
        // trouver le type de piece
        String pieceType = "";

        switch (piece.toString()) {
        case "Rook": {
            pieceType = "T";
            break;
        }
        case "Knight": {
            pieceType = "C";
            break;
        }
        case "Queen": {
            pieceType = "D";
            break;
        }
        case "King": {
            pieceType = "R";
            break;
        }
        case "Pawn": {
            pieceType = "P";
            break;
        }
        case "Bishop": {
            pieceType = "F";
            break;
        }
        }

        // afficher la pièce sur la nouvelle case
        try {
            String iconePath;
            if (!game.getCurrentPlayer().getId().equals(game.getBlackPlayer().getId())) {
                iconePath = dossierIcone + pieceType + "B";
            } else {
                iconePath = dossierIcone + pieceType + "N";
            }
            Image img = ImageIO.read(getClass().getResource(iconePath + ".gif"));
            chessBoardSquares[line_to - 1][8 - col_to].setIcon(new ImageIcon(img));
            Image img_disabled = ImageIO.read(getClass().getResource(iconePath + "_disabled.gif"));
            chessBoardSquares[line_to - 1][8 - col_to].setDisabledIcon(new ImageIcon(img_disabled));

        } catch (IOException e) {
            LOGGER.error("[ClientIHMToDataImpl][refreshChessBoard] " + e.getMessage());
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
        // TODO Auto-generated method stub
    }

    @Override
    public void displayGameLiveObserver() {
        // TODO Auto-generated method stub

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

    public void activateCases(PublicUserEntity currentUser, GameStatusEnum status) {
        // Check the game status
        if (status.equals(GameStatusEnum.CHECK)) {
            controller.getCb().changeCheckSituation();
        }

        if (status.equals(GameStatusEnum.CHECKMATE)) {
            controller.getCb().changeCheckMateSituation();
        } else {
            Case[][] cb = controller.getCb().getChessBoardSquares();
            if (status != GameStatusEnum.CHECKMATE) {
                if (myIHMManager.getIClientDataToIHM().getLocalUser().getId().equals(currentUser.getId())) {
                    for (Case i[] : cb) {
                        for (Case j : i) {
                            j.setEnabled(true);
                        }
                    }
                }
            }
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
                // myIHMManager.getCurrentGameStage().hide();

            }
        });
    }
}
