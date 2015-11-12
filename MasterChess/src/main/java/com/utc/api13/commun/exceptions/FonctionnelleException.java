package com.utc.api13.commun.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.utc.api13.commun.common.Erreur;

/**
 * Exception fonctionnelle, elle représente les exceptions fonctionnelles à montrer à l'utilisateur
 *
 */

public class FonctionnelleException extends Exception {

    private static final long serialVersionUID = -1133994271218706723L;
   
    /**
     * Object erreurs listant les erreurs levée pour l'exception
     */
    private final List<Erreur> erreurs;

    /**
     * Constructeur
     */
    public FonctionnelleException() {
        super();
        erreurs = new ArrayList<Erreur>();
    }

    /**
     * Retourne la liste des erreurs
     * @return la liste des erreurs
     */
    public List<Erreur> getErreurs() {
        return erreurs;
    }

    /**
     * 
     * @return true sssi cette instance ne contient aucune erreur
     */
    public boolean hasError(){
        return !erreurs.isEmpty();
    }
}
