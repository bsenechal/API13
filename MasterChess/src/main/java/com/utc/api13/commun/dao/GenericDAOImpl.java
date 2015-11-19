package com.utc.api13.commun.dao;

import java.util.List;
import java.util.UUID;

import com.utc.api13.commun.dao.interfaces.IGenericDAO;
import com.utc.api13.commun.entities.ADataEntity;
import com.utc.api13.commun.exceptions.DataAccessException;
import com.utc.api13.commun.exceptions.TechnicalException;
import com.utc.api13.commun.utils.ConfigFileReader;
import com.utc.api13.commun.utils.StorageUtils;

public class GenericDAOImpl<T extends ADataEntity> implements IGenericDAO<T>{

    private StorageUtils<T> storageUtils;
    private ConfigFileReader configFile;
    
    public GenericDAOImpl(String fileName) throws TechnicalException{
    	configFile = new ConfigFileReader("config/config.properties");
    	
        storageUtils = new StorageUtils("files/" + configFile.getPropValue(fileName) + ".ser");
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
