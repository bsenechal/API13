package com.utc.api13.commun;

import java.util.ArrayList;
import java.util.List;

/**
 *Structure de stockage des erreurs
 * @author Amstrong
 */
public class Erreur {

	/**
     * type d'erreur: code fonctionnel correspindant à l'erreur
     */
    private IErrorType errorType;
    /**
     * les paramètres du message
     */
    private List<String> params;
    /**
     * niveau de criticité
     */
    private Level level;
    
    public Erreur(final IErrorType errorType) {
    	this.errorType = errorType;
    	this.params = new ArrayList<String>();
    	this.level = Level.ERROR;
    }
    
    /**
     * Constructeur
     * @param errorType code fonctionnel du message d'erreur
     * @param params paramètres du message d'erreur
     */
    public Erreur(final IErrorType errorType, final List<String> params) {
            this.errorType = errorType;
            this.params = params;
            this.level = Level.ERROR;
    }

    /**
     * Constructeur
     * @param errorType code fonctionnel du message d'erreur
     * @param level le niveau de criticité
     * @param params paramètres du message d'erreur
     */
    public Erreur(final IErrorType errorType, Level level, final List<String> params) {
            this(errorType, params);
            this.level = level;
    }

    public IErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(IErrorType errorType) {
        this.errorType = errorType;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
    
    /**
     * Niveau de criticité de l'erreur
     */
    public enum Level {
        ERROR(1),
        WARNING(2),
        INFO(3);
        /**
         * id de l'enum
         */
        private final int id;
        /**
         * Constructeur privé
         * @param id id de l'enum
         */
        private Level(int id) {
            this.id = id;
        }
        public int getId(){
            return id;
        }
    };
}
