
package com.utc.api13.client.ihm.models;

import java.awt.Color;

import javax.swing.JButton;

public class Case extends JButton {

    private static final long serialVersionUID = 6292971463823922156L;
    private int line;
    private int column;
    private Color color;

    /**
     * @param line
     * @param column
     */
    public Case(int line, int column) {
        super();
        this.line = line;
        this.column = column;
    }
    /**
     * @return the line
     */
    public int getLine() {
        return line;
    }
    /**
     * @param line the line to set
     */
    public void setLine(int line) {
        this.line = line;
    }
    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }
    /**
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }
    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }
    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

}
