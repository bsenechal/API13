package com.utc.api13.commun.entities;

import java.util.Date;

public class MessageEntity extends ADataEntity {

    private static final long serialVersionUID = 4634723100677874653L;
    private Date date;
    private String text;

    /**
     * Create a new message at the current time
     */
    public MessageEntity() {
        super();
        this.date = new Date();
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
}
