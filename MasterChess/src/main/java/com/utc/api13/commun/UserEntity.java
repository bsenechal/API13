package com.utc.api13.commun;

public interface UserEntity extends DataEntity {
    String getLogin();

    void getFirstName(final String firstName);

    void getLastName(final String lastName);

    int getNbPlayed();

    void setNbWon(final int nbWon);

    int getNbLost();

    void setNbLost(final int nbLost);

    void setLogin(final String login);

    void setFirstName(final String firstName);

    void setLastName(final String lastName);

    boolean getStatus();

    void setStatus(final boolean status);

}
