package com.utc.api13.commun.dao.interfaces;

import java.util.List;

import com.utc.api13.commun.entities.ADataEntity;
import com.utc.api13.commun.exceptions.DataAccessException;

public interface IGenericDAO<T extends ADataEntity> {

    /**
     * Renvoie tous les objets existants pour la base associÃ©e au generic
     * @return la liste des objets
     * @throws DataAccessException s'il y a une erreur d'accÃ¨s aux donnÃ©es
     */
    List<T> findAll() throws DataAccessException;

    /**
     * Creation d'un objet
     * @param entity objet à créer
     * @return l'objet créé
     * @throws DataAccessException Exception DAO
     */
    T save(T entity) throws DataAccessException;
    /**
     * Vide toutes les données stockées de la classe Entity
     * @throws DataAccessException Exception DAO
     */
    void deleteAll() throws DataAccessException;

    /**
     * Met à jour un entity déjà enregistré
     * @param entity entity à update
     * @return entity updaté
     * @throws DataAccessException exception d'accès aux données
     */
    T merge(T entity) throws DataAccessException;

	void updateAll(List<T> entities) throws DataAccessException;
}
