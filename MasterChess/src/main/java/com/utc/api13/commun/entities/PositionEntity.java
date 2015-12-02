package com.utc.api13.commun.entities;

public class PositionEntity extends ADataEntity {

    private static final long serialVersionUID = -9132052141494726494L;
    private int positionX;
    private int positionY;
    
    /**
     * @param positionX
     * @param positionY
     */
    public PositionEntity(int positionX, int positionY) {
        super();
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * @return the positionX
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * @param positionX
     *            the positionX to set
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * @return the positionY
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * @param positionY
     *            the positionY to set
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
