package com.utc.api13.client.ihm.controllers;

import javafx.scene.control.Button;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;
import com.utc.api13.client.ihm.property.ProfilProperty;
import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.collections.ListChangeListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserInfoPopUpController {
    private IHMManager IHMManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    public PublicUserEntity u;
    private Stage currentStage;
    private final Logger log = Logger.getLogger(getClass());

    @FXML
    BorderPane userInfoBorderPane;
    @FXML
    AnchorPane userInfoAnchorPane;
    @FXML
    Label userInfoLogin, userInfoFirstName, userInfoLastName, userUUID;
    @FXML
    TableView userInfoTableView;
    @FXML
    ImageView userInfoImage;
    @FXML
    TableColumn userInfoWon, userInfoLost, userInfoPlayed;
    @FXML
    Button sendPropositionButton;

    public PublicUserEntity getU() {
        return u;
    }

    public void setU(PublicUserEntity u) {
        this.u = u;
    }

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

    @SuppressWarnings("restriction")
	public void setBindings(ProfilProperty profile) {
        userInfoLogin.textProperty().bind(profile.loginProperty());
        userInfoFirstName.textProperty().bind(profile.firstNameProperty());
        userInfoLastName.textProperty().bind(profile.lastNameProperty());
        userUUID.textProperty().bind(profile.userUUID());

        userInfoWon.setCellValueFactory(new PropertyValueFactory<PrivateUserEntity, Integer>("nbWon"));
        userInfoLost.setCellValueFactory(new PropertyValueFactory<PrivateUserEntity, Integer>("nbLost"));
        userInfoPlayed.setCellValueFactory(new PropertyValueFactory<PrivateUserEntity, Integer>("nbPlayed"));

        profile.statPlayerProperty().addListener((ListChangeListener.Change<? extends PublicUserEntity> el) -> {
            userInfoTableView.setItems(profile.statPlayerProperty());
        });

    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    @SuppressWarnings("restriction")
	public void sendProposition() throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/sendPropositionPopUp.fxml"));
            root = (Pane) fxmlLoader.load();
            SendPropositionController controller = fxmlLoader.getController();
            controller.setControllerContext(this.IHMManager);
            mainApp.getCurrentStage().close();
            mainApp.setCurrentStage(stage);
            controller.setMainApp(this.mainApp, userUUID, userInfoLogin);
            stage.setScene(new Scene(root));
            stage.setTitle("Proposition");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            try {
                error("Error when loading proposition window : IOException");
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
            log.error(e.getMessage(), e);
        }

    }

    public void error(String message) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/errorPopUp.fxml"));
        root = (Pane) fxmlLoader.load();
        ErrorController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        mainApp.getCurrentStage().close();
        mainApp.setCurrentStage(stage);
        controller.setMainApp(this.mainApp, message);
        stage.setScene(new Scene(root));
        stage.setTitle("Error");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

}
