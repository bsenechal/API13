package com.utc.api13.client.ihm.controllers;

import java.io.IOException;
import java.util.Optional;

import com.utc.api13.client.AppClient;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.client.data.interfaces.IClientDataToIHM;
import com.utc.api13.client.ihm.IHMManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyInfoPopUpController {

    private IHMManager IHMManager;
    private AppClient mainApp;
    private IClientDataToIHM myIClientToIHM;
    private Stage currentStage;
    private Text userLabelToUpdateWelcomePage;

    @FXML
    BorderPane userInfoBorderPane;
    @FXML
    AnchorPane userInfoAnchorPane;
    @FXML
    Label userInfoLogin, userInfoFirstName, userInfoLastName;
    @FXML
    TableView<PrivateUserEntity> userInfoTableView;
    @FXML
    TableColumn<PrivateUserEntity, Integer> userInfoWon, userInfoLost, userInfoPlayed;
    @FXML
    Button modifyButton;
    @FXML
    ImageView userInfoImage;

    public MyInfoPopUpController() {
        initialize();
    }
    
    @FXML
    public void onModifyProfileClicked() throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/updateProfilePage.fxml"));
        root = (Pane) fxmlLoader.load();
        ModifyProfileController controller = fxmlLoader.getController();
        controller.setControllerContext(this.IHMManager);
        mainApp.getCurrentStage().close();
        mainApp.setCurrentStage(stage);
        controller.setMainApp(this.mainApp, userLabelToUpdateWelcomePage);
        stage.setScene(new Scene(root));
        stage.setTitle("Update my Profile");
        stage.show();
    }

    public void initialize() {
    }

    public void setMainApp(AppClient app, Text userLabel) {
        this.mainApp = app;

        PrivateUserEntity u = this.myIClientToIHM.getLocalUser();
        ObservableList<PrivateUserEntity> statsPlayer = FXCollections.observableArrayList();
        userInfoWon.setCellValueFactory(new PropertyValueFactory<PrivateUserEntity, Integer>("nbWon"));
        userInfoLost.setCellValueFactory(new PropertyValueFactory<PrivateUserEntity, Integer>("nbLost"));
        userInfoPlayed.setCellValueFactory(new PropertyValueFactory<PrivateUserEntity, Integer>("nbPlayed"));
        statsPlayer.add(u);
        this.userInfoLogin.setText(u.getLogin());
        this.userInfoFirstName.setText(u.getFirstName());
        this.userInfoLastName.setText(u.getLastName());
        userInfoTableView.setItems(statsPlayer);
        userLabelToUpdateWelcomePage = userLabel;
        Optional.ofNullable("file://"+u.getImagePath()).ifPresent(link -> userInfoImage.setImage(new Image(link)));
    }

    public IHMManager getIHMManager() {
        return IHMManager;
    }

    public void setIHMManager(IHMManager iHMManager) {
        IHMManager = iHMManager;
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

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }
}
