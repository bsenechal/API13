package com.utc.api13.client.ihm.controllers;

import java.io.File;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.commun.Erreur;
import com.utc.api13.commun.enumerations.ErrorTypeEnum;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class IHMManageProfileController {
    private IHMManager IHMManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private final Logger log = Logger.getLogger(getClass());
    private boolean newProfile = false;

    public boolean isNewProfile() {
        return newProfile;
    }

    public void setNewProfile(boolean newProfile) {
        this.newProfile = newProfile;
    }

    @FXML
    BorderPane createProfileBorderPane;
    @FXML
    Label createProfileLabel, loginLabel, passwordLabel, firstNameLabel, lastNameLabel, errorInfo;
    @FXML
    TextField loginTextView, passwordTextView, firstNameTextView, lastNameTextView;
    @FXML
    Button saveProfileButton;
    @FXML
    ImageView changeProfilePicture;
    @FXML
    AnchorPane createProfileAnchorPane;

    @FXML
    public void onSaveProfileClicked() {

        PrivateUserEntity user = null;
        if (!newProfile)
            user = this.myIClientToIHM.getLocalUser();
        else
            user = new PrivateUserEntity();

        user.setLogin(loginTextView.getText());
        user.setPassword(passwordTextView.getText());
        user.setFirstName(firstNameTextView.getText());
        user.setLastName(lastNameTextView.getText());
        try {

            if (newProfile)
                this.myIClientToIHM.createProfile(user);
            else
                this.myIClientToIHM.updateProfile(user);
            // }

        } catch (TechnicalException e) {
            // TODO afficher a l'utlisateur l'erreur soit dans une popup ou dans
            // la fenetre courante
            log.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (FunctionalException e) {
            // TODO Auto-generated catch block
            for (Erreur erreur : e.getErreurs()) {
                // TODO gerer les multi langues ant de remplir les fichiers logs
                log.error(((ErrorTypeEnum) erreur.getErrorType()).getCode());
            }
            e.printStackTrace();
        }

    }

    @FXML
    public void onChangePictureClicked() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir le document");
        fileChooser.setInitialDirectory(new File("/"));
        File f = fileChooser.showOpenDialog(new Stage());
        System.out.println(" le chemin est :" + f.getAbsolutePath());
        Image newProfil = null;
        try {

            changeProfilePicture.setImage(new Image("file:///" + f.getAbsolutePath()));
            this.myIClientToIHM.getLocalUser().setImagePath("file:///" + f.getAbsolutePath());

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public IHMManageProfileController() {

        initialize();
    }

    public void initialize() {

    }

    public void setMainApp(AppClient app) {

        this.mainApp = app;
        if (!newProfile) {
            PrivateUserEntity u = this.myIClientToIHM.getLocalUser();
            this.loginTextView.setText(u.getLogin());
            this.passwordTextView.setText(u.getPassword());
            this.firstNameTextView.setText(u.getFirstName());
            this.lastNameTextView.setText(u.getLastName());

            Optional.ofNullable(u.getImagePath()).ifPresent(link -> changeProfilePicture.setImage(new Image(link)));
        }

    }

    public void setControllerContext(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null)
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();
        setListenersOnLoad();
        setBindingsOnLoad();
    }

    public void setListenersOnLoad() {

    }

    public void setBindingsOnLoad() {
    }

}