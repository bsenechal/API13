package com.utc.api13.client.ihm.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChatProperty {

    private StringProperty message;

    public ChatProperty() {
        this.message = new SimpleStringProperty();
    }

    /**
     * @param message
     */
    public ChatProperty(StringProperty message) {
        super();
        this.message = message;
    }

    /**
     * @return the message
     */
    public StringProperty getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(StringProperty message) {
        this.message = message;
    }
}
