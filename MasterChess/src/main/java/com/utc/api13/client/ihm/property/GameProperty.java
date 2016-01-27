package com.utc.api13.client.ihm.property;

import java.util.UUID;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameProperty {

    private StringProperty loginOtherPlayer;
    private UUID otherplayerUUID;
    private boolean chattable;
    private boolean observable;
    private boolean timer;
    private StringProperty time;
    // private StringProperty otherPlayerUUID;

    public GameProperty() {
        super();
        this.loginOtherPlayer = new SimpleStringProperty();
        this.time = new SimpleStringProperty();
    }

    public StringProperty loginOtherPlayerProperty() {
        return this.loginOtherPlayer;
    }

    public UUID otherPlayUUID() {
        return this.otherplayerUUID;
    }

    public StringProperty timeProperty() {
        return this.time;
    }

    public boolean chattableProperty() {
        return this.chattable;
    }

    public boolean observableProperty() {
        return this.observable;
    }

    public boolean timerProperty() {
        return this.timer;
    }

    public void setTimerProperty(boolean p) {
        this.timer = p;
    }

    public void setChattableProperty(boolean p) {
        this.chattable = p;
    }

    public void setObservableProperty(boolean p) {
        this.observable = p;
    }

    public void setOtherPlayerUUID(UUID u) {
        this.otherplayerUUID = u;
    }
}
