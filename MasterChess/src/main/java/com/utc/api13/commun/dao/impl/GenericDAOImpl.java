package com.utc.api13.commun.dao.impl;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.dao.GenericDAO;
import com.utc.api13.commun.entities.DataEntity;
import com.utc.api13.commun.exceptions.DataAccessException;
import com.utc.api13.commun.utils.StorageUtils;

public class GenericDAOImpl<T extends DataEntity> implements GenericDAO<T>{

    private StorageUtils<T> storageUtils;
    
    public GenericDAOImpl(){
        storageUtils = new StorageUtils("./docs/test.ser");
    }
    
    public List<T> findAll() throws DataAccessException {
        return storageUtils.readAll();
    }

    public T save(T entity) throws DataAccessException {
	    entity.setId(UUID.randomUUID());
	    storageUtils.write(entity);
	    return entity;       
    }

    
    public void deleteAll() throws DataAccessException {
        storageUtils.clearFile();
    }


    
    public T merge(T entity) throws DataAccessException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void updateAll(List<T> entities) throws DataAccessException {
		storageUtils.writeAll(entities);
		
	}
    
}
