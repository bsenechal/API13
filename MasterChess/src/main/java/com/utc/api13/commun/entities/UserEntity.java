package com.utc.api13.commun.entities;

public class UserEntity extends DataEntity {
	
    private static final long serialVersionUID = 5601063024006338259L;
    private String Login;
	private String FirstName;
	private String LastName;
	private int NbPlayed;
	private int NbWon;
	private int NbLost;
	private boolean Status;
	
	
	/**
	 * @return the login
	 */
	public String getLogin() {
		return Login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		Login = login;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return FirstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return LastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	/**
	 * @return the nbPlayed
	 */
	public int getNbPlayed() {
		return NbPlayed;
	}
	/**
	 * @param nbPlayed the nbPlayed to set
	 */
	public void setNbPlayed(int nbPlayed) {
		NbPlayed = nbPlayed;
	}
	/**
	 * @return the nbWon
	 */
	public int getNbWon() {
		return NbWon;
	}
	/**
	 * @param nbWon the nbWon to set
	 */
	public void setNbWon(int nbWon) {
		NbWon = nbWon;
	}
	/**
	 * @return the nbLost
	 */
	public int getNbLost() {
		return NbLost;
	}
	/**
	 * @param nbLost the nbLost to set
	 */
	public void setNbLost(int nbLost) {
		NbLost = nbLost;
	}
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return Status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		Status = status;
	}
	


}
