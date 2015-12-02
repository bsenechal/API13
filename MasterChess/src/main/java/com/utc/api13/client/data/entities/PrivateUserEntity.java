package com.utc.api13.client.data.entities;

import java.util.List;
import java.util.Map;

import com.utc.api13.commun.entities.AUserEntity;
import com.utc.api13.commun.entities.GameEntity;

public class PrivateUserEntity extends AUserEntity {

    private static final long serialVersionUID = -5408789931031693916L;
    private String password;
    private String imagePath;
    // List of saved games by the local user
    private List<GameEntity> savedGames;
    private GameEntity game;
    private Map<String, Integer> connections;

    // TODO Lors du merge de l'integ et donc de l'ajout du constructeur ajouter
    // la ligne suivante
    // connections = new HashMap<String,Integer>();

    public PrivateUserEntity() {
        super();
    }

    /**
     * @param password
     */
    public PrivateUserEntity(String login, String password) {
        super(login, password);
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
     * @return the connections map (serveur and port)
     */
    public Map<String, Integer> getConnections() {
        return connections;
    }

    /**
     * @param Map<IP
     *            serveur, port serveur> the used connections by the user
     */
    public void setConnections(Map<String, Integer> connections) {
        this.connections = connections;
    }

}
