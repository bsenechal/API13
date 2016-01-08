package com.utc.api13.client.ihm.property;

import java.util.UUID;

import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PropositionProperty {
 
    private StringProperty loginAskingPlayer;
    private StringProperty time; 
    private boolean chattable;
    private boolean observable;
    private boolean timer;
   
    public PropositionProperty() {
        super();
        this.loginAskingPlayer=new SimpleStringProperty();
        this.time=new SimpleStringProperty();
    }
   
    public StringProperty loginAskingPayerProperty() {
        return this.loginAskingPlayer;
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

}
