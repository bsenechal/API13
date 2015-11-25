package com.utc.api13.commun.dao;

import java.util.UUID;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.exceptions.DataAccessException;
import com.utc.api13.commun.utils.StorageUtils;

public class UserDAO{


    public void save(PrivateUserEntity user) throws DataAccessException {
	    if(user.getId()==null){
	    	user.setId(UUID.randomUUID());
	    }
	    StorageUtils.write(user);
    }

    
    public void delete(String login) throws DataAccessException {
        StorageUtils.delete(login);
    }


}
