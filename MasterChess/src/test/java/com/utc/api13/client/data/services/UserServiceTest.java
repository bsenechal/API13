
package com.utc.api13.client.data.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.utc.api13.client.data.DataClientManager;
import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

/**
 * Tests the methods of the UserService class
 * 
 * @author Amstrong
 *
 */
public class UserServiceTest {

    /**
     * UserService instance
     */
    private final UserService userService = new UserService();

    /**
     * data client manager
     */
    DataClientManager dataClientmanager = new DataClientManager();

    /**
     * Class logger
     */
    private final Logger LOG = Logger.getLogger(getClass());

    protected PrivateUserEntity getEntityWithoutId() {
        PrivateUserEntity user = new PrivateUserEntity("login", "");
        user.setFirstName("first");
        user.setLastName("Last");
        user.setNbPlayed(2);
        user.setNbLost(1);
        user.setNbWon(1);
        user.setStatus(true);
        return user;
    }

    @Test
    public void testGetByLoginAndPassword() {
        PrivateUserEntity foundUser = null;
        try {
            // Test without registering the user
            foundUser = userService.getByLoginAndPassword("login", "password");
            Assert.assertNull(foundUser);
            // Register the user
            PrivateUserEntity newUser = new PrivateUserEntity("login", "password");
            userService.save(newUser);
            // Test after registring
            foundUser = userService.getByLoginAndPassword("login", "password");
            Assert.assertNotNull(foundUser);
            Assert.assertEquals("login", foundUser.getLogin());
            Assert.assertEquals("password", foundUser.getPassword());
        } catch (TechnicalException | FunctionalException e) {
            LOG.error("Error", e);
            Assert.fail("test has failed. Check the logs for more information");
        } finally {
            if (foundUser != null) {
                try {
                    userService.delete(foundUser);
                } catch (TechnicalException e) {
                    LOG.error("Error while deleting", e);
                }
            }
        }
    }
/*
    @Test
    public void testImportExport() {
        // Create a new user and save it
        PrivateUserEntity user = new PrivateUserEntity();
        user.setLogin("login");
        user.setPassword("password");
        try {
            userService.save(user);
        } catch (TechnicalException | FunctionalException e) {
            LOG.error("Error while saving user", e);
            Assert.fail("test has failed. Check the logs for more information");
        }

        // Set the local user
        dataClientmanager.setUserLocal(user);
        File filePath = null;
        try {
            // Export the profile
            filePath = userService.exportProfile(user);
        } catch (TechnicalException e) {
            LOG.error("Error while exporting user", e);
            Assert.fail("test has failed. Check the logs for more information");
        } finally {
            try {
                userService.delete(user);
            } catch (TechnicalException e) {
                LOG.error("Error while deleting user", e);
                Assert.fail("test has failed. Check the logs for more information");
            }
            dataClientmanager.setUserLocal(null);
        }

        // import file
        try {
            userService.importProfile(filePath, false);
        } catch (FunctionalException | TechnicalException e) {
            LOG.error("Error while importing user", e);
            Assert.fail("test has failed. Check the logs for more information");
        } finally {
            // Delete file in export directory
            try {
                Files.delete(Paths.get(filePath.toURI()));
            } catch (IOException e) {
                LOG.error("Error while deleting file", e);
                Assert.fail("test has failed. Check the logs for more information");
            }
        }

        // Get user by id and checks value
        try {
            PrivateUserEntity foundUser = userService.getById(user.getId());
            Assert.assertNotNull(foundUser);
            Assert.assertArrayEquals(new UUID[] { user.getId() }, new UUID[] { foundUser.getId() });
            Assert.assertArrayEquals(new String[] { user.getLogin() }, new String[] { foundUser.getLogin() });
            Assert.assertArrayEquals(new String[] { user.getPassword() }, new String[] { foundUser.getPassword() });
        } catch (FunctionalException | TechnicalException e) {
            LOG.error("Error while importing user", e);
            Assert.fail("test has failed. Check the logs for more information");
        } finally {
            // Delete the imported user
            try {
                userService.delete(user);
            } catch (TechnicalException e) {
                LOG.error("Error while deleting user", e);
                Assert.fail("test has failed. Check the logs for more information");
            }

        }
    }*/

}
