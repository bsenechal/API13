package com.utc.api13.client.ihm.controllers;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

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
import javafx.scene.control.PasswordField;
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
    private Stage currentStage;
    private final Logger log = Logger.getLogger(getClass());

    @FXML
    BorderPane connexionBorderPane;
    @FXML
    Label connexionLabel, loginLabel, passwordLabel, serverAddressLabel, portLabel;
    @FXML
    AnchorPane connexionAnchorPane;
    @FXML
    Button connexionButton;
    @FXML
    TextField loginTextView, serverAddressTextView, portTextView;
    @FXML
    Hyperlink importLink, exportLink, signUpLink;
    @FXML
    PasswordField passwordTextView;

    @FXML
    private void onSignInClicked(Event event) throws IOException {
        String login = loginTextView.getText();
        String pw = passwordTextView.getText();
        String sv = serverAddressTextView.getText();
        String portString = portTextView.getText();
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader;

        if (login.length() == 0 || pw.length() == 0 || sv.length() == 0 || portString.length() == 0) {
            try {
                error("Error : please fill all the fields!");
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
        }

        else if (!(portTextView.getText().matches("[0-9][0-9][0-9][0-9]")
                || portTextView.getText().matches("[0-9][0-9][0-9]") || portTextView.getText().matches("[0-9][0-9]")
                || portTextView.getText().matches("[0-9]"))) {
            try {
                error("Error : please use digits for the port field!");
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
        }

        else {

            try {
                Integer port = Integer.parseInt(portString.isEmpty() ? "0" : portTextView.getText());
                // TODO : gérer la connexion au serveur avec le port
                myIClientToIHM.connect(login, pw);
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/welcomePage.fxml"));
                root = (Pane) fxmlLoader.load();
                IHMWelcomePageController controllerRight = fxmlLoader.getController();
                controllerRight.setControllerContext(IHMManager);
                controllerRight.setMainApp(mainApp);
                stage.setTitle("Welcome to MasterChess");
                stage.setScene(new Scene(root));
                mainApp.getCurrentStage().close();
                mainApp.setCurrentStage(stage);
                controllerRight.setDisconnectUserByClosingWindow();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }

            catch (FunctionalException e) {
                try {
                    wrongData(true);
                } catch (IOException e1) {
                    log.error(e1.getMessage(), e1);
                }
                log.error(e.getMessage(), e);
            }

            catch (TechnicalException e) {
                try {
                    wrongData(false);
                } catch (IOException e1) {
                    log.error(e1.getMessage(), e1);
                }
                log.error(e.getMessage(), e);
            }
        }
    }

    private void wrongData(boolean bool) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/errorPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ErrorController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        controller.setMainApp(this.mainApp, bool ? "Wrong connexion information!" : "Technical error!");
        stage.setScene(new Scene(root));
        stage.setTitle("User Information");
        mainApp.setCurrentStage(stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void onImportClicked(Event event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import my profile");
        File f = fileChooser.showOpenDialog(new Stage());
        if (f != null) {
            try {
                myIClientToIHM.importProfile(f, true);
                importOK();
            } catch (FunctionalException e) {
                importNOK("Error : functional exception");
                log.error(e.getMessage(), e);
            } catch (TechnicalException e) {
                importNOK("Error : techninal exception");
                log.error(e.getMessage(), e);
            }
        }
    }

    public void importOK() throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/confirmationPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ConfirmationController controller = fxmlLoader.getController();

        controller.setControllerContext(this.IHMManager);
        controller.setMainApp(this.mainApp, "Successful import!");
        stage.setScene(new Scene(root));
        stage.setTitle("Import success");
        mainApp.getCurrentStage().close();
        mainApp.setCurrentStage(stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void importNOK(String message) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/errorPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ErrorController controller = fxmlLoader.getController();

        controller.setControllerContext(this.IHMManager);
        controller.setMainApp(this.mainApp, message);
        stage.setScene(new Scene(root));
        stage.setTitle("Import error");
        mainApp.getCurrentStage().close();
        mainApp.setCurrentStage(stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void onSignUpClicked(Event event) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/createProfilePage.fxml"));
        root = (Pane) fxmlLoader.load();
        CreateProfileController controller = fxmlLoader.getController();
        controller.setControllerContext(IHMManager);
        controller.setMainApp(mainApp);
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("My Profile");
        stage.setScene(scene);
        mainApp.setCurrentStage(stage);
        stage.show();
    }

    public IHMConnexionPageController() {
        initialize();
    }

    public void setMainApp(AppClient app) {
        this.mainApp = app;
    }

    public void initialize() {
    }

    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null)
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void error(String message) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/errorPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ErrorController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        controller.setMainApp(this.mainApp, message);
        stage.setScene(new Scene(root));
        stage.setTitle("Error");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}
