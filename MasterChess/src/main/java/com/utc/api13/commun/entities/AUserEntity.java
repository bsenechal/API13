package com.utc.api13.commun.entities;

import java.util.UUID;

public abstract class AUserEntity extends ADataEntity {

    private static final long serialVersionUID = 5601063024006338259L;
    private String login;
    private String firstName;
    private String lastName;
    private int nbPlayed;
    private int nbWon;
    private int nbLost;
    private boolean status;

    public AUserEntity() {
        super();
    }

    /**
     * @param login
     */
    public AUserEntity(String login) {
        super();
        this.login = login;
    }

    /**
     * @param login
     */
    public AUserEntity(String login, String password) {
        super();
        this.login = login;
        this.setId(UUID.randomUUID());
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login
     *            the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the nbPlayed
     */
    public int getNbPlayed() {
        return nbPlayed;
    }

    /**
     * @param nbPlayed
     *            the nbPlayed to set
     */
    public void setNbPlayed(int nbPlayed) {
        this.nbPlayed = nbPlayed;
    }

    /**
     * @return the nbWon
     */
    public int getNbWon() {
        return nbWon;
    }

    /**
     * @param nbWon
     *            the nbWon to set
     */
    public void setNbWon(int nbWon) {
        this.nbWon = nbWon;
    }

    /**
     * @return the nbLost
     */
    public int getNbLost() {
        return nbLost;
    }

    /**
     * @param nbLost
     *            the nbLost to set
     */
    public void setNbLost(int nbLost) {
        this.nbLost = nbLost;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
