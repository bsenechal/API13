package com.utc.api13.commun.dao.impl;

import java.util.List;

import com.utc.api13.commun.dao.IGenericDAO;
import com.utc.api13.commun.entities.DataEntity;
import com.utc.api13.commun.exceptions.DataAccessException;
import com.utc.api13.commun.utils.StorageUtils;

public class GenericDAOImpl<T extends DataEntity> implements IGenericDAO<T>{

    private StorageUtils<T> storageUtils;
    
    public GenericDAOImpl(){
        storageUtils = new StorageUtils("./docs/test.ser");
    }
    
    public List<T> findAll() throws DataAccessException {
        return storageUtils.readAll();
    }

    public T save(T entity) throws DataAccessException {
        if(entity.getId() == null){
            //TODO; get an id for the new object
            storageUtils.write(entity);
            return entity;
        } else {
            return merge(entity);
        }
        
    }

    
    public void deleteAll() throws DataAccessException {
        storageUtils.clearFile();
    }


    
    public T merge(T entity) throws DataAccessException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
