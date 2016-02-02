package com.utc.api13.client.data.entities;

import java.util.ArrayList;
import java.util.List;

import com.utc.api13.commun.entities.AUserEntity;
import com.utc.api13.commun.entities.GameEntity;

public class PrivateUserEntity extends AUserEntity {

    private static final long serialVersionUID = -5408789931031693916L;
    private String password;
    private String imagePath;
    // List of saved games by the local user
    private List<GameEntity> savedGames;
    private GameEntity game;
    private transient List<Connection> connections;

    public PrivateUserEntity() {
        super();
        connections = new ArrayList<Connection>();
        savedGames = new ArrayList<GameEntity>();
    }

    /**
     * @param password
     */
    public PrivateUserEntity(String login, String password) {
        super(login);
        this.password = password;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath
     *            the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return the observedGame
     */
    public List<GameEntity> getSavedGames() {
        return savedGames;
    }

    /**
     * @param observedGame
     *            the observedGame to set
     */
    public void setSavedGames(List<GameEntity> observedGames) {
        this.savedGames = observedGames;
    }

    /**
     * @return the game
     */
    public GameEntity getGame() {
        return game;
    }

    /**
     * @param game
     *            the game to set
     */
    public void setGame(GameEntity game) {
        this.game = game;
    }

    /**
     * @return the connections list (serveur and port)
     */
    public List<Connection> getConnections() {
        return connections;
    }

    /**
     * @param List<Conn>
     *            the used connections by the user
     */
    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    /**
     * @return the last connection by the user
     */

    public Connection getLastConnection() {
        return connections.get(connections.size());
    }

    /**
     * @param String
     *            serveur the used serveur by the user
     * @param String
     *            port the used port by the user
     */
    public void addConnection(String serveur, String port) {
        connections.add(new Connection(serveur, port));
    }
}
