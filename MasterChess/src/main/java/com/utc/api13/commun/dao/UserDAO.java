package com.utc.api13.commun.dao;

import java.io.File;
import java.util.UUID;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.exceptions.DataAccessException;
import com.utc.api13.commun.exceptions.TechnicalException;
import com.utc.api13.commun.utils.StorageUtils;

public class UserDAO{


    public void save(PrivateUserEntity user) throws DataAccessException {
	    if(user.getId()==null){
	    	user.setId(UUID.randomUUID());
	    }
	    StorageUtils.write(user);
    }

    
    public void delete(PrivateUserEntity user) throws TechnicalException {
        StorageUtils.delete(user.getLogin() + "_" + user.getId());
    }


    /**
     * Finds the user with the given login and password<br/>
     * Gets all files in the storage directory<br/>
     * Loops on the list and opens the ones with name starting with the given login<br/>
     * The first occurence containing a user with the given password in returned
     * @param login login
     * @param password password
     * @return the first occurence of found user, otherwise null
     * @throws TechnicalException 
     */
    public PrivateUserEntity getByLoginAndPassword(String login, String password) throws TechnicalException {
    	File[] listOfFiles = StorageUtils.getAllFiles();
    	PrivateUserEntity user = null;
    	for(File file: listOfFiles) {
    		if(file.getName().startsWith(login+"_")) {
    			user = StorageUtils.read(file.getName());
    			if(login.equals(user.getLogin()) && password.equals(user.getPassword())){
    				return user;
    			}
    		}
    	}
    	return null;
    }
}

