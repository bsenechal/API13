package com.utc.api13.client.ihm.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.property.ProfilProperty;
import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.collections.ListChangeListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class UserInfoPopUpController {
    private IHMManager IHMManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    public PublicUserEntity u;

    public PublicUserEntity getU() {
        return u;
    }

    public void setU(PublicUserEntity u) {
        this.u = u;
    }

    @FXML
    BorderPane userInfoBorderPane;
    @FXML
    AnchorPane userInfoAnchorPane;
    @FXML
    Label userInfoLogin, userInfoFirstName, userInfoLastName;
    @FXML
    TableView userInfoTableView;
    @FXML
    ImageView userInfoImage;
    @FXML
    TableColumn userInfoWon, userInfoLost, userInfoPlayed;

    public UserInfoPopUpController() {

        initialize();
    }

    public void initialize() {

    }

    public void setMainApp(AppClient app) {
        this.mainApp = app;
    }

    public void setControllerContext(IHMManager ihmManager) {
        setIHMMandClient(ihmManager);
        setListenersOnLoad();
        setBindingsOnLoad();

    }

    public void setIHMMandClient(IHMManager ihmManager) {
        this.IHMManager = ihmManager;
        if (ihmManager != null) {
            this.myIClientToIHM = IHMManager.getIClientDataToIHM();

        }

    }

    public void setListenersOnLoad() {

    }

    public void setBindingsOnLoad() {

    }

    public static javafx.scene.image.Image getJavaFXImage(byte[] bytes) throws IOException {

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        BufferedImage image = ImageIO.read(bais);
        return SwingFXUtils.toFXImage(image, null);
    }

    public void setBindings(ProfilProperty profile) {
        userInfoLogin.textProperty().bind(profile.loginProperty());
        userInfoFirstName.textProperty().bind(profile.firstNameProperty());
        userInfoLastName.textProperty().bind(profile.lastNameProperty());

        userInfoWon.setCellValueFactory(new PropertyValueFactory<PrivateUserEntity, Integer>("nbWon"));
        userInfoLost.setCellValueFactory(new PropertyValueFactory<PrivateUserEntity, Integer>("nbLost"));
        userInfoPlayed.setCellValueFactory(new PropertyValueFactory<PrivateUserEntity, Integer>("nbPlayed"));

        profile.statPlayerProperty().addListener((ListChangeListener.Change<? extends PublicUserEntity> el) -> {
            userInfoTableView.setItems(profile.statPlayerProperty());
        });

    }
}
