package com.utc.api13.client.ihm.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConfirmationProperty {

    private StringProperty confirmationMessage;

    public ConfirmationProperty() {
        super();
        this.confirmationMessage = new SimpleStringProperty();
    }

    public StringProperty confirmationMessageProperty() {
        return this.confirmationMessage;
    }
}
