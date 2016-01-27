package com.utc.api13.client.ihm.property;

import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProfilProperty {

    private StringProperty login;
    private StringProperty firstName;
    private StringProperty lastName;
    private ObservableList<PublicUserEntity> statPlayer;
    private StringProperty userUUID;

    public ObservableList<PublicUserEntity> getStatPlayer() {
        return statPlayer;
    }

    /**
     * 
     */
    public ProfilProperty() {
        super();
        login = new SimpleStringProperty();
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
        statPlayer = FXCollections.observableArrayList();
        userUUID = new SimpleStringProperty();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty userUUID() {
        return userUUID;
    }

    public ObservableList<PublicUserEntity> statPlayerProperty() {
        return statPlayer;
    }

}
