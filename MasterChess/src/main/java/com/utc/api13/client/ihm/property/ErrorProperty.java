package com.utc.api13.client.ihm.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ErrorProperty {

    private StringProperty errorMessage;

    public ErrorProperty() {
        super();
        this.errorMessage = new SimpleStringProperty();
    }

    public StringProperty errorMessageProperty() {
        return this.errorMessage;
    }
}
