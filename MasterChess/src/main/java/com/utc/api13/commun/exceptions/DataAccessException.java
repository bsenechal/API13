package com.utc.api13.commun.exceptions;

/**
 * Description de la classe : Exception spécifique à la couche DAO, elle "herite" de l'exception technique TechnicalException().
 */
public class DataAccessException extends TechnicalException {

    private static final long serialVersionUID = 701744806577552453L;

    /**
     * Constructeur de la classe : ApplicationException()
     */
    public DataAccessException() {
        super();
    }

    /**
     * Constructeur de la classe avec un message et une exception source. : ApplicationException()
     * @param msg Message de l'exeption
     */
    public DataAccessException(final String msg) {
        super(msg);
    }

    /**
     * Constructeur de la classe
     * @param ex Exception source
     */
    public DataAccessException(final Throwable ex) {
        super("Erreur DataAccessException", ex);
    }

    /**
     * Constructeur de la classe avec un message et une exception source. : ApplicationException()
     * @param msg Message de l'exeption
     * @param ex Exception source
     */
    public DataAccessException(final String msg, final Throwable ex) {
        super(msg, ex);
    }

}
