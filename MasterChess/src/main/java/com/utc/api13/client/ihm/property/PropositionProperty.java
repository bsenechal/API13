package com.utc.api13.client.ihm.property;

import java.util.UUID;

import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PropositionProperty {
 
    private StringProperty loginAskingPlayer;
    private IntegerProperty time; 
    private BooleanProperty chattable;
    private BooleanProperty observable;
    private BooleanProperty timer;
   
    public PropositionProperty() {
        super();
        this.loginAskingPlayer=new SimpleStringProperty();
        this.time=new SimpleIntegerProperty();
    }
    
    public PropositionProperty(String login, Integer time, boolean chat, boolean timer, boolean observable) {
        super();
        this.loginAskingPlayer=new SimpleStringProperty(login);
        this.time=new SimpleIntegerProperty(time);
        this.chattable=new SimpleBooleanProperty(chat); 
        this.timer=new SimpleBooleanProperty(timer); 
        this.observable=new SimpleBooleanProperty(observable); 
    }
   
    public StringProperty loginAskingPayerProperty() {
        return this.loginAskingPlayer;
    }
    public IntegerProperty timeProperty() {
        return this.time;
    }
    public BooleanProperty chattableProperty() {
        return this.chattable;
    }
    public BooleanProperty observableProperty() {
        return this.observable;
    }
    public BooleanProperty timerProperty() {
        return this.timer; 
    }

}
