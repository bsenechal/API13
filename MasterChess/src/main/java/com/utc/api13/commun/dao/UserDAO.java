package com.utc.api13.commun.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.util.Assert;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;
import com.utc.api13.commun.utils.StorageUtils;

public class UserDAO {

    public void save(PrivateUserEntity user) throws TechnicalException {
        StorageUtils.write(user);
    }

    public void delete(final PrivateUserEntity user) throws TechnicalException {
        Assert.notNull(user, "[UserDAO][delete] user shouldn't be null");
        StorageUtils.delete(user.getLogin() + "_" + user.getId());
    }

    /**
     * Deletes user's stored info
     * 
     * @param userId
     *            user id
     */
    public void deleteById(final UUID userId) {
        File[] listOfFiles = StorageUtils.getAllFiles();
        for (File file : listOfFiles) {
            if (file.getName().endsWith("_" + userId + ".ser")) {
                file.delete();
            }
        }
    }

    /**
     * Finds the user with the given login and password<br/>
     * Gets all files in the storage directory<br/>
     * Loops on the list and opens the ones with name starting with the given
     * login<br/>
     * The first occurence containing a user with the given password in returned
     * 
     * @param login
     *            login
     * @param password
     *            password
     * @return the first occurence of found user, otherwise null
     * @throws TechnicalException
     */
    public PrivateUserEntity getByLoginAndPassword(final String login, final String password)
            throws TechnicalException {
        File[] listOfFiles = StorageUtils.getAllFiles();
        PrivateUserEntity user;
        for (File file : listOfFiles) {
            if (file.getName().startsWith(login + "_")) {
                user = StorageUtils.read(file.getName());
                if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                    return user;
                }
            }
        }
        return null;
    }

    /**
     * Method used to import a user in the app Reads the user stored in the
     * given file
     * 
     * @param file
     *            file
     * @return the user stored in the file
     * @throws FunctionalException
     *             when the stored object is not a private user
     * @throws TechnicalException
     *             exception when reading file
     */
    public PrivateUserEntity getUserFromFile(File file) throws FunctionalException, TechnicalException {
        return StorageUtils.readUserFromFile(file);
    }

    /**
     * Gets the user with the given id from storage
     * 
     * @param userIdid
     *            of user
     * @return returns the found user or null when non found
     * @throws FunctionalException
     *             when the stored object is not a private user
     * @throws TechnicalException
     *             exception when reading file
     */
    public PrivateUserEntity getById(UUID userId) throws FunctionalException, TechnicalException {
        File[] listOfFiles = StorageUtils.getAllFiles();
        for (File file : listOfFiles) {
            if (file.getName().endsWith("_" + userId + ".ser")) {
                PrivateUserEntity user = getUserFromFile(file);
                if (userId.equals(user.getId())) {
                    return user;
                }
            }
        }
        return null;
    }

    public Path copy(String source, String target, CopyOption... options) throws TechnicalException {
        try {
            return Files.copy(Paths.get(source), Paths.get(target), options);
        } catch (IOException e) {
            throw new TechnicalException("Error while copying the file " + source + " to " + target, e);
        }
    }
}
