package com.utc.api13.client.ihm;

import java.io.IOException;
import java.util.UUID;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.ihm.controllers.AnswerPropositionController;
import com.utc.api13.client.ihm.controllers.ErrorController;
import com.utc.api13.client.ihm.controllers.IHMGamePageController;
import com.utc.api13.client.ihm.interfaces.IClientIHMToData;
import com.utc.api13.commun.entities.GameEntity;
//github.com/bsenechal/API13.git
import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientIHMToDataImpl implements IClientIHMToData {

    private IHMManager myIHMManager;

    public ClientIHMToDataImpl(IHMManager pIHMManager) {
        myIHMManager = pIHMManager;
    }

    @SuppressWarnings("restriction")
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
            }
        });
    }

    @Override
    public void otherPlayerLeaving() {
        // TODO Auto-generated method stub

    }

    @SuppressWarnings("restriction")
    @Override
    public void displayProposition(UUID uidSender, boolean observable,
            boolean chattable , boolean timer, Integer timeInt) {

	    Platform.runLater(new Runnable() {
            @Override
            public void run() {

            	    Stage stage;
                    Parent root = null;
                    String l=""; 
                    stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AnswerPropositionPopUp.fxml"));
                    try {
                           root = (Pane) fxmlLoader.load();
                           AnswerPropositionController controller = fxmlLoader.getController();
                           controller.setControllerContext(myIHMManager);

                           controller.setMainApp(myIHMManager.getMainApp(), 
                                   myIHMManager.getIClientDataToIHM().getUserList().stream()
                                   .filter(u->u.getId()== uidSender).map(PublicUserEntity::getLogin)
                                   .findFirst().orElse("unknown User"), chattable, timer, 
                                   observable, timeInt);        
                             
                           stage.setScene(new Scene(root));
                           stage.setTitle("You've got a new game proposition!");
                           stage.initModality(Modality.APPLICATION_MODAL);
                           stage.show();
                       } catch (IOException e) {
                          e.printStackTrace();
                          }
            }
	    });
    }

    @SuppressWarnings("restriction")
	@Override
    public void displayAnswer(UUID uidSender, boolean observable) {
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
		               controller.setMainApp(myIHMManager.getMainApp(), "Sorry: the player has refused to play!"); 
		               stage.setScene(new Scene(root));
		               stage.setTitle("Proposition refused");
		               stage.initModality(Modality.APPLICATION_MODAL);
		               stage.show();
		           } catch (IOException e) {
		              e.printStackTrace();
		              }
		}
});
    }

    @SuppressWarnings("restriction")
	@Override
    public void displayChessBoard(GameEntity g) { // si yep data should call that function 
        // TODO Auto-generated method stub
    	Platform.runLater(new Runnable() {
            @Override
            public void run() {

            	    Stage stage;
                    Parent root = null;
                    stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/gamePage.fxml"));
                    try {
                           root = (Pane) fxmlLoader.load();
                           IHMGamePageController controller = fxmlLoader.getController();
                           controller.setControllerContext(myIHMManager);
                           controller.setMainApp(myIHMManager.getMainApp(), g); 
                           stage.setScene(new Scene(root));
                           stage.setTitle("Game!");
                           stage.initModality(Modality.APPLICATION_MODAL);
                           stage.show();
                       } catch (IOException e) {
                          e.printStackTrace();
                          }
            }
	    });
    }

    @Override
    public void refreshChessBoard() {
        // TODO Auto-generated method stub

    }

    @SuppressWarnings("restriction")
	@Override
    public void displayError(String errorMessage) {
        // TODO Auto-generated method stub
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                myIHMManager.getError().errorMessageProperty().set(errorMessage);
            }
        });
    }

    @Override
    public void displayConfirmation(String confirmationMessage) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String sms= myIHMManager.getChat().getMessage().get();
                myIHMManager.getChat().getMessage().set(sms+"\n"+newMessage);
            }
        });
    }

}