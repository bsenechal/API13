package com.utc.api13.client.ihm.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.swing.SwingUtilities;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.models.ChessBoardNode;
import com.utc.api13.client.ihm.property.ChatProperty;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.embed.swing.SwingNode;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IHMGamePageController {
    private IHMManager IHMManager;
    private IClientDataToIHM myIClientToIHM;
    private AppClient mainApp;
    private Stage currentStage;
    private ChatProperty chat;

   

    @FXML
    Label chatLabel, otherPlayerLoginLabel, otherPlayerTimeLabel, playerLoginLabel, playerTimeLabel,
            numberObserversLabel;
    @FXML
    ImageView iconChat, iconObservers;
    @FXML
    Button excludeChatButton, sendTextButton, abandonButton, quitGameButton;
    @FXML
    TextArea chatTextArea, sendTextArea;
    @FXML
    StackPane chessBoardStackPane;

    public IHMManager getIHMManager() {
        return IHMManager;
    }

    public void setIHMManager(IHMManager iHMManager) {
        IHMManager = iHMManager;
    }

    public IHMGamePageController() {
        initialize();
    }

    public void initialize() {

    }

    @FXML
    private void onExcludeChatClicked(Event event) throws IOException {

      List<UUID> players=Arrays.asList( myIClientToIHM.getCurrentGame().getBlackPlayer().getId(),
              myIClientToIHM.getCurrentGame().getBlackPlayer().getId());
      if(!players.contains(myIClientToIHM.getLocalUser().getId())){    
         openUserObservableList();
      }
      else{
          error(" only the two player can remove someone from the Tchat");
      }
    }

    private void openUserObservableList() throws IOException {
        // TODO Auto-generated method stub
         Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/excludeObservateur.fxml"));
        root = (Pane) fxmlLoader.load();
        ExcludeGameObservateur controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        mainApp.setCurrentStage(stage);
        controller.setMainApp(this.mainApp);
        stage.setScene(new Scene(root));
        stage.setTitle("Exclude observateur ");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        
    }

    @FXML
    private void onSendTextClicked(Event event) {
        
        Optional.ofNullable(sendTextArea.getText()).
            ifPresent(
                    sms->{
                        chatTextArea.clear();
                        sendTextArea.clear();
                        String realMessage=myIClientToIHM.getLocalUser().getLogin()+": "+sms;
                        myIClientToIHM.sendChatText(realMessage);
                    }
             );
    }

    @FXML
    private void onAbandonClicked(Event event) {

    }

    @FXML
    private void onQuitGameClicked(Event event) {

    }

    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null){
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
            chat=new ChatProperty();
            ihmManager.setChat(chat);
        }
        setListenersOnLoad();
        setBindingsOnLoad();
    }

    public void setMainApp(AppClient app ) {
        this.mainApp = app;
        GameEntity game = this.myIClientToIHM.getCurrentGame();
        //final ChessBoardNode cb = new ChessBoardNode(IHMManager);
        this.IHMManager.getChessBoardNode().setIHMManager(IHMManager);
        final SwingNode swingNode = new SwingNode();

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                swingNode.setContent(cb.getGui());
            }
        });
        chatTextArea.setDisable(true);

        chessBoardStackPane.getChildren().add(swingNode);

        // initialisation des différents labels
        PrivateUserEntity u = this.myIClientToIHM.getLocalUser();
        if (u.getId() == game.getWhitePlayer().getId()) {
        	playerLoginLabel.setText(u.getLogin());
        	otherPlayerLoginLabel.setText(game.getBlackPlayer().getLogin());
        }
        else {
        	otherPlayerLoginLabel.setText(u.getLogin());
        	playerLoginLabel.setText(game.getWhitePlayer().getLogin());
        }
        int nbObservers = game.getObservers().size();
        numberObserversLabel.setText(String.valueOf(nbObservers));

    }

    public void setListenersOnLoad() {

    }

    public void setBindingsOnLoad() {
        
        chatTextArea.textProperty().bind(chat.getMessage());
        
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }
    public ChatProperty getChat() {
        return chat;
    }

    public void setChat(ChatProperty chat) {
        this.chat = chat;
    }

    public void error(String message) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/errorPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ErrorController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        mainApp.setCurrentStage(stage);
        controller.setMainApp(this.mainApp, message);
        stage.setScene(new Scene(root));
        stage.setTitle("Error");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
