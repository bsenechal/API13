package com.utc.api13.commun.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Generated;

import com.utc.api13.commun.dao.IGenericDAO;
import com.utc.api13.commun.entities.ADataEntity;
import com.utc.api13.commun.exceptions.DataAccessException;
import com.utc.api13.commun.exceptions.TechnicalException;
import com.utc.api13.commun.utils.StorageUtils;

public class GenericDAOImpl<T extends ADataEntity> implements IGenericDAO<T>{

    private StorageUtils<T> storageUtils;
    
    public GenericDAOImpl() throws TechnicalException{
        storageUtils = new StorageUtils(getFileName());
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
	
	private String getFileName() throws TechnicalException{
		InputStream inputStream;
		String fileName = "";
		Properties prop = new Properties();
		String propFileName = "config.properties";

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		
			try {
				if (inputStream != null) {
				prop.load(inputStream);
				} else {
					throw new TechnicalException("property file '" + propFileName + "' not found in the classpath");
				}
				String className = getClass().getName();
				className = className.split("DAO")[0].toLowerCase();	
				fileName = prop.getProperty(className);
				fileName = "files/" + fileName + ".ser";
			} catch (Exception e) {
				throw new TechnicalException(e);
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					throw new TechnicalException("Error while closing stream", e);
				}
			}
		return fileName;
	}
    
}
