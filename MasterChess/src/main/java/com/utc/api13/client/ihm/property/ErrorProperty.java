package com.utc.api13.client.ihm.property;

import java.util.UUID;

import com.utc.api13.commun.entities.PublicUserEntity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ErrorProperty {
 
    private StringProperty errorMessage;

    public ErrorProperty() {
        super();
        this.errorMessage=new SimpleStringProperty();
    }
   
    public StringProperty errorMessageProperty() {
        return this.errorMessage;
    }
}
