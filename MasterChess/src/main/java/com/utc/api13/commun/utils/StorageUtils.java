package com.utc.api13.commun.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import java.io.File;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.Erreur;
import com.utc.api13.commun.enumerations.ErrorTypeEnum;
import com.utc.api13.commun.exceptions.DataAccessException;
import com.utc.api13.commun.exceptions.FunctionalException;
import com.utc.api13.commun.exceptions.TechnicalException;

public class StorageUtils{
	private static File PATH = new File(StorageUtils.class.getClassLoader().getResource("user").getFile());
	
	private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    

    
    /**
     * Stores a user in a file<br/> 
     * If the user has not been stored yet, a new file is created and the user is serialized<br/>
     * In the other case, the existing file is overwritten
     * @param user user to store
     * @throws TechnicalException when storage file cannot be created
     */
    public static void write(final PrivateUserEntity user)throws TechnicalException{
        Assert.notNull(user, "[StorageUtils] User shouldn't be null");
        try {
        	String filePath = PATH.getAbsolutePath()+File.separator+user.getLogin()+"_"+user.getId()+".ser";
        	//First let's try to create the file
        	File file = new File(filePath);
        	if(!file.createNewFile()) {
        		throw new TechnicalException("user storage file cannot be created");
        	}
        	//let's write in the content whether the file existed already or not
            oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(user);
        } catch (IOException ex) {
            throw new DataAccessException("Error while writing entity", ex);
        } finally{
        	if(oos != null){
	        	try {
	        		oos.flush();
	        		oos.close(); 
	            } catch (IOException ex) {
	                throw new DataAccessException("Error while closing output stream", ex);
	            }
        	}
        }
    }
    

    /**
     * Reads a user from file
     * @param fileName the file name (login + _ + uuid of the user)
     * @return read user
     * @throws TechnicalException 
     */
    public static PrivateUserEntity read(final String fileName)throws TechnicalException{
        try {
        	File file = new File(PATH.getAbsolutePath() +File.separator + fileName);
        	if(!file.exists()) {
        		throw new TechnicalException("The file you are trying to read doesn't exist");
        	}
        	
            ois = new ObjectInputStream(new FileInputStream(file));
            return (PrivateUserEntity) ois.readObject();
        } catch (InvalidClassException icx) {
            throw new DataAccessException("The class " + icx.classname + " has changed since the serialization of object", icx);
        } catch (StreamCorruptedException scx){
            throw new DataAccessException("The file " + PATH+File.separator+fileName + " is corrupted. Do not modify storage files!", scx);
        } catch (ClassNotFoundException cnx) {
            throw new DataAccessException("The entity you are trying to deserialize doest not have a valid class", cnx);
        } catch (IOException iox) {
            throw new DataAccessException("Error while reading object from the file " + PATH+File.separator+fileName, iox);
        }  finally{
            if(oos != null){
                try {
                    ois.close();
                } catch (IOException ex) {
                    throw new DataAccessException("Error while closing output stream", ex);
                }
            }
        }
    }
    
    
    /**
     * Delete the file with given name
     * @param fileName file to delete
     * @throws TechnicalException when deletion fails
     */
    public static void delete(final String fileName) throws TechnicalException{
    	String filePath = PATH.getAbsolutePath() + File.separator + fileName + ".ser";
        File file = new File(filePath);
		if(!file.delete()) {
			throw new TechnicalException("Delete operation has failed");
		}
    }
    
    /**
     * 
     * @return Returns the list of files in the storage directory
     */
    public static File[] getAllFiles(){
    	File[] listofFiles = PATH.listFiles();
    	File[] NO_FILES = {};
    	return listofFiles == null ? NO_FILES : listofFiles;
    }
    
    /**
     * Method used to import a user in the app
     * Reads the user stored in the given file
     * @param file file
     * @return the user stored in the file
     * @throws FunctionalException when the stored object is not a private user
     * @throws TechnicalException exception when reading file
     */
    public static PrivateUserEntity readUserFromFile(File file) throws FunctionalException, TechnicalException {
    	try {
			ois = new ObjectInputStream(new FileInputStream(file));
			Object ob = ois.readObject();
			if(!(ob instanceof PrivateUserEntity)){
				List<Erreur> erreurs = new ArrayList<>();
				erreurs.add(new Erreur(ErrorTypeEnum.NON_PRIVATE_USER));
				throw new FunctionalException(erreurs);
			}
			return (PrivateUserEntity)ob;
		} catch (IOException | ClassNotFoundException e) {
			throw new TechnicalException("Error while reading object from file", e);
		}
    }

}