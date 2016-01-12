package com.utc.api13.client.ihm.property;

import java.util.UUID;

import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConfirmationProperty {
 
    private StringProperty confirmationMessage;

    public ConfirmationProperty() {
        super();
        this.confirmationMessage=new SimpleStringProperty();
    }
   
    public StringProperty confirmationMessageProperty() {
        return this.confirmationMessage;
    }
}
