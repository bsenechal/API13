package com.utc.api13.commun.entities;

public class PositionEntity extends DataEntity {

    private static final long serialVersionUID = -9132052141494726494L;
    private String positionX;
    private String positionY;
    /**
     * @return the positionX
     */
    public String getPositionX() {
        return positionX;
    }
    /**
     * @param positionX the positionX to set
     */
    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }
    /**
     * @return the positionY
     */
    public String getPositionY() {
        return positionY;
    }
    /**
     * @param positionY the positionY to set
     */
    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }
}
