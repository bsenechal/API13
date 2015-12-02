
package com.utc.api13.client.ihm.controllers;

import java.io.File;
import java.io.IOException;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IHMConnexionPageController {

    private IHMManager IHMManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;

    @FXML
    BorderPane connexionBorderPane;
    @FXML
    Label connexionLabel, loginLabel, passwordLabel, serverAddressLabel, portLabel;
    @FXML
    AnchorPane connexionAnchorPane;
    @FXML
    Button connexionButton;
    @FXML
    TextField loginTextView, passwordTextView, serverAddressTextView, portTextView;
    @FXML
    Hyperlink importLink, exportLink, signUpLink;

    @FXML
    private void onSignInClicked(Event event) throws IOException {
        String login = loginTextView.getText();
        String pw = passwordTextView.getText();
        String sv = serverAddressTextView.getText();
        Integer port = Integer.parseInt(portTextView.getText().isEmpty() ? "0" : portTextView.getText());

        // TODO : Gérer les exceptions avec le logger
      
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader;
      
       
        if (sv == null || port == null) {
                wrongData(true);   
            }
        
        else {
       
            try {
               
                myIClientToIHM.connect(login, pw);
               fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/welcomePage.fxml"));
                root = (Pane) fxmlLoader.load();
                IHMWelcomePageController controllerRight = fxmlLoader.getController();
                controllerRight.setControllerContext(IHMManager);
                controllerRight.setMainApp(mainApp);
               
                stage.setTitle("Connexion to MasterChess");
                stage.setScene(new Scene(root));
                mainApp.getCurrentStage().close();
                mainApp.setCurrentStage(stage);
                stage.setScene(new Scene(root));
                stage.setTitle("User Information");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

            }
            
            catch (FunctionalException e) {
                wrongData(true);   
                
            }

            catch (TechnicalException e) {
                wrongData(false);   
               
            }
        }
           
        
        stage.show();
       
    }

    private void wrongData(boolean bool ) throws IOException {
        
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader;
        // TODO Auto-generated method stub
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/errorPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ErrorController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        controller.setMainApp(this.mainApp, bool ? "Wrong connexion information!": "Technical error!");
        stage.setScene(new Scene(root));
        stage.setTitle("User Information");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
    }

    @FXML
    private void onImportClicked(Event event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import my profile");
        File f = fileChooser.showOpenDialog(new Stage());
        if (f != null) {
            try {
                myIClientToIHM.importProfile(f, true);
            } catch (FunctionalException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (TechnicalException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onSignUpClicked(Event event) throws IOException {
        MyInfoPopUpController controller = new MyInfoPopUpController();

        controller.setNewProfile(true);
        controller.setIHMManager(IHMManager);
        controller.onModifyProfileClicked();

    }

    public IHMConnexionPageController() {
        initialize();
    }

    public void setMainApp(AppClient app) {
        this.mainApp = app;
    }

    public void initialize() {
        // bindings
    }

    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null)
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
    }

}
