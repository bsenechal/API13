package com.utc.api13.client.ihm.models;

import java.awt.Color;

import javax.swing.JButton;

public class Case extends JButton {
    private int line;
    private int column;
    private Color color;

    public Case(int x, int y) {
        this.line = x;
        this.column = y;
    }

    public int getLine() {
        return this.line;
    }

    public int getColumn() {
        return this.column;
    }

    public void setLine(int x) {
        this.line = x;
    }

    public void setColumn(int y) {
        this.column = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
