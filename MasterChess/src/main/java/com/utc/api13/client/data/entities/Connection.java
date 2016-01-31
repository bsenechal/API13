/**
 * 
 */
package com.utc.api13.client.data.entities;

/**
 * @author Data
 *
 */
public class Connection {
    private String port;
    private String serveur;

    public Connection(String serv, String port) {
        this.serveur = serv;
        this.port = port;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port
     *            the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the serveur
     */
    public String getServeur() {
        return serveur;
    }

    /**
     * @param serveur
     *            the serveur to set
     */
    public void setServeur(String serveur) {
        this.serveur = serveur;
    }
}
