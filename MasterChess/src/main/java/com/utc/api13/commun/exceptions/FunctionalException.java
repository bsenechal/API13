package com.utc.api13.commun.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.utc.api13.commun.Erreur;

/**
 * Exception fonctionnelle, elle représente les exceptions fonctionnelles à
 * montrer à l'utilisateur
 *
 */

public class FunctionalException extends Exception implements Serializable {

    private static final long serialVersionUID = -7559305873860060208L;
    /**
     * Object erreurs listant les erreurs levée pour l'exception
     */
    private final List<Erreur> erreurs;

    /**
     * Constructeur
     */
    public FunctionalException() {
        super();
        erreurs = new ArrayList<Erreur>();
    }

    public FunctionalException(List<Erreur> erreurs) {
        super();
        this.erreurs = erreurs;
    }

    /**
     * Retourne la liste des erreurs
     * 
     * @return la liste des erreurs
     */
    public List<Erreur> getErreurs() {
        return erreurs;
    }

    /**
     * 
     * @return true sssi cette instance ne contient aucune erreur
     */
    public boolean hasError() {
        return !erreurs.isEmpty();
    }
}
