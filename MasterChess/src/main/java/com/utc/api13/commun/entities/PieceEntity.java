package com.utc.api13.commun.entities;

public class PieceEntity extends DataEntity {
    
    private static final long serialVersionUID = 6842864968035495956L;
    private PieceColorEnum color;
    /**
     * @return the color
     */
    public PieceColorEnum getColor() {
        return color;
    }
    /**
     * @param color the color to set
     */
    public void setColor(PieceColorEnum color) {
        this.color = color;
    }
}
