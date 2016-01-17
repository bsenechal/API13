package com.utc.api13.client.ihm.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChatProperty {
    
    private StringProperty message;

    public StringProperty getMessage() {
        return message;
    }

    public void setMessage(StringProperty message) {
        this.message = message;
    }

    /**
     * @param message
     */
    public ChatProperty(StringProperty message) {
        super();
        this.message = message;
    }
    public ChatProperty(){
        this.message=new SimpleStringProperty();
    }
    
}
