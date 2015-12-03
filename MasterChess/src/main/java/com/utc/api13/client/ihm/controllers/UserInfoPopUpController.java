package com.utc.api13.client.ihm.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class UserInfoPopUpController {
    private IHMManager IHMManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;

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
        // this.IHMManager = new IHMManager();
        // this.myIClientToIHM=IHMManager.getIClientDataToIHM();
        initialize();
    }

    public void initialize() {
    }

    public void setMainApp(AppClient app) {
        this.mainApp = app;

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

    public void displayProfile(PublicUserEntity u) {

        this.userInfoLogin.setText(u.getLogin());
        this.userInfoFirstName.setText(u.getFirstName());
        this.userInfoLastName.setText(u.getLastName());
        // TODO Dealing with the Table Won, Lost, Played
        this.userInfoWon.setText(Integer.toString(u.getNbWon()));
        this.userInfoLost.setText(Integer.toString(u.getNbLost()));
        this.userInfoPlayed.setText(Integer.toString(u.getNbPlayed()));

        try {
            Image image = getJavaFXImage(u.getImage());
            this.userInfoImage.setImage(image);
        } catch (Exception e) {
            System.out.println("Error on the function getJavaFXImage. due to the conversion of byte[] to javafx.image");
            e.printStackTrace();
        }

    }

    private javafx.scene.image.Image getJavaFXImage(byte[] bytes) throws IOException {

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        BufferedImage image = ImageIO.read(bais);
        return SwingFXUtils.toFXImage(image, null);
    }
}
