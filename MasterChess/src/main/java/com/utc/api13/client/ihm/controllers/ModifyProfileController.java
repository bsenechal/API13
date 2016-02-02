package com.utc.api13.client.ihm.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.commun.Erreur;
import com.utc.api13.commun.enumerations.ErrorTypeEnum;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ModifyProfileController {
    private IHMManager ihmManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private static final Logger LOGGER = Logger.getLogger(ModifyProfileController.class);
    private Stage currentStage;
    private Text userLabelToUpdateWelcomePage;

    @FXML
    BorderPane createProfileBorderPane;
    @FXML
    Label createProfileLabel, loginLabel, passwordLabel, firstNameLabel, lastNameLabel, errorInfo;
    @FXML
    TextField loginTextView, firstNameTextView, lastNameTextView;
    @FXML
    PasswordField passwordTextView;
    @FXML
    Button saveProfileButton;
    @FXML
    ImageView changeProfilePicture;
    @FXML
    AnchorPane createProfileAnchorPane;

    public ModifyProfileController() {
        initialize();
    }

    @FXML
    public void onSaveProfileClicked() throws IOException {

        String login = loginTextView.getText();
        String pw = passwordTextView.getText();
        String firstName = firstNameTextView.getText();
        String lastName = lastNameTextView.getText();

        if (login.length() == 0 || pw.length() == 0 || firstName.length() == 0 || lastName.length() == 0) {
            try {
                error("Error : please fill all the fields!", false);
            } catch (IOException e1) {
                LOGGER.error(e1.getMessage(), e1);
            }
        }

        else {
            myIClientToIHM.getLocalUser().setLogin(login);
            myIClientToIHM.getLocalUser().setPassword(pw);
            myIClientToIHM.getLocalUser().setFirstName(firstName);
            myIClientToIHM.getLocalUser().setLastName(lastName);

            Stage stage;
            Parent root;
            stage = new Stage();
            FXMLLoader fxmlLoader;

            try {

                this.myIClientToIHM.updateProfile(myIClientToIHM.getLocalUser());
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/confirmationPopUp.fxml"));
                root = (Pane) fxmlLoader.load();
                ConfirmationController controller = fxmlLoader.getController();
                controller.setControllerContext(this.ihmManager);
                controller.setMainApp("Your profile has been saved!");
                stage.setScene(new Scene(root));
                stage.setTitle("Your profile");
                mainApp.getCurrentStage().close();
                mainApp.setCurrentStage(stage);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
                userLabelToUpdateWelcomePage.setText(myIClientToIHM.getLocalUser().getLogin());
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> stage.close());
                delay.play();

            } catch (TechnicalException e) {
                try {
                    error("Error when saving your profile : Technical Exception", true);
                } catch (IOException e1) {
                    LOGGER.error(e1.getMessage(), e1);
                }
                LOGGER.error(e.getMessage(), e);

            } catch (FunctionalException e) {
                LOGGER.error(e.getMessage(), e);
                try {
                    error("Error when saving your profile : Functional Exception", true);
                } catch (IOException e1) {
                    LOGGER.error(e1.getMessage(), e1);
                }
                for (Erreur erreur : e.getErreurs()) {
                    LOGGER.error(((ErrorTypeEnum) erreur.getErrorType()).getCode());
                }
            }
        }
    }

    public static boolean copyFile(File source, File dest) {
        try {

            java.io.FileInputStream sourceFile = new java.io.FileInputStream(source);

            try {
                java.io.FileOutputStream destinationFile = null;

                try {
                    destinationFile = new FileOutputStream(dest);

                    byte[] buffer = new byte[512 * 1024];
                    int nbLecture;

                    while ((nbLecture = sourceFile.read(buffer)) != -1) {
                        destinationFile.write(buffer, 0, nbLecture);
                    }
                } finally {
                    if (destinationFile != null) {
                        destinationFile.close();
                    }
                }
            } finally {
                sourceFile.close();
            }
        } catch (IOException e) {
            LOGGER.error("[ModifyProfileController][copyFile] " + e.getMessage(), e);
            return false;
        }

        return true;
    }

    @FXML
    public void onChangePictureClicked() throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir le document");
        fileChooser.setInitialDirectory(new File("/"));
        File f = fileChooser.showOpenDialog(new Stage());
        if (f != null && f.exists()) {
            try {
                String extensionFile = FilenameUtils.getExtension(f.getAbsolutePath());
                File dest = Paths.get("user/avatar_" + UUID.randomUUID().toString() + "." + extensionFile).toFile();
                copyFile(f, dest);
                changeProfilePicture.setImage(new Image("file:///" + dest.getAbsolutePath()));
                dest.getAbsolutePath();
                myIClientToIHM.getLocalUser().setImagePath(dest.getAbsolutePath());
            } catch (Exception e) {
                try {
                    error("Error when changing your picture", false);
                } catch (IOException e1) {
                    LOGGER.error(e1.getMessage(), e1);
                }
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public void initialize() {
    }

    public void setMainApp(AppClient app, Text userLabel) {
        this.mainApp = app;
        PrivateUserEntity u = this.myIClientToIHM.getLocalUser();
        this.loginTextView.setText(u.getLogin());
        this.passwordTextView.setText(u.getPassword());
        this.firstNameTextView.setText(u.getFirstName());
        this.lastNameTextView.setText(u.getLastName());
        this.userLabelToUpdateWelcomePage = userLabel;

        Optional.ofNullable("file:///" + u.getImagePath())
                .ifPresent(link -> changeProfilePicture.setImage(new Image(link)));
    }

    public void setControllerContext(IHMManager ihmManager) {
        this.ihmManager = ihmManager;
        if (ihmManager != null)
            this.myIClientToIHM = ihmManager.getIClientDataToIHM();
        setListenersOnLoad();
        setBindingsOnLoad();
    }

    public void setListenersOnLoad() {
    }

    public void setBindingsOnLoad() {
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void error(String message, boolean close) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/errorPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ErrorController controller = fxmlLoader.getController();
        controller.setControllerContext(this.ihmManager);
        controller.setText(message);
        stage.setScene(new Scene(root));
        stage.setTitle("Error");
        if (close == Boolean.TRUE) {
            mainApp.getCurrentStage().close();
        }
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

}
