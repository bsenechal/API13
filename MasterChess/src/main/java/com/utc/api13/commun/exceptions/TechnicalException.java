package com.utc.api13.commun.exceptions;

/**
 * Exception technique, elle représente les exceptions techniques: à écrire dans les Logs
 */
public class TechnicalException extends Exception {

	/**
     * Constructeur de la classe. : ApplicationException()
     */
    public TechnicalException() {
        super();
    }

    /**
     * Constructeur de la classe avec un message.
     * @param message Message l'exception
     */
    public TechnicalException(final String message) {
        super(message);
    }

    /**
     * Constructeur avec un message et une exception
     * @param message le message de l'exception
     * @param ex Exception source
     */
    public TechnicalException(final String message, final Throwable ex) {
        super(message, ex);
    }

    /**
     * Constructeur avec un message et une exception
     * @param ex Exception source
     */
    public TechnicalException(final Throwable ex) {
        super(ex.getMessage(), ex);
    }
}
