package com.utc.api13.commun.utils;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;

import com.utc.api13.client.data.entities.PrivateUserEntity;
import com.utc.api13.commun.entities.ADataEntity;
import com.utc.api13.commun.exceptions.DataAccessException;

public class StorageUtils{
	private static String PATH = "user";
	
	private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    

    
    /**
     * écrit un entity à la fin du fichier
     * @param entity entity à écrire dans le fichier
     * @throws DataAccessException erreur lors de l'accès aux données
     */
    public static void write(PrivateUserEntity user)throws DataAccessException{
        try {
	            oos = new ObjectOutputStream(new FileOutputStream(PATH+File.separator+user.getLogin()));
	            oos.writeObject(user);
        } catch (IOException ex) {
            throw new DataAccessException("Error while writing entity", ex);
        } finally{
                try {
	            	 if(oos != null){
	            		 oos.flush();
	            		 oos.close();
	            	 }
                } catch (IOException ex) {
                    throw new DataAccessException("Error while closing output stream", ex);
                }
        }
    }
    

    /**
     * Permet de stocker dans le fichier un objet<br/>Ici ce sera un objet héritant de Entity
     * @return l'entity lu à partir du fichier
     * @throws DataAccessException 
     */
    public static PrivateUserEntity read(String login)throws DataAccessException{
        try {
            ois = new ObjectInputStream(new FileInputStream(PATH+File.separator+login));
            return (PrivateUserEntity) ois.readObject();
        } catch (InvalidClassException icx) {
            throw new DataAccessException("The class " + icx.classname + " has changed since the serialization of object", icx);
        } catch (StreamCorruptedException scx){
            throw new DataAccessException("The file " + PATH+File.separator+login + " is corrupted. Do not modify storage files!", scx);
        } catch (ClassNotFoundException cnx) {
            throw new DataAccessException("The entity you are trying to deserialize doest not have a valid class", cnx);
        } catch (IOException iox) {
            throw new DataAccessException("Error while reading object from the file " + PATH+File.separator+login, iox);
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
    
    
    public static void delete(String login) throws DataAccessException {
        try {
            new FileOutputStream(PATH+File.separator+login).close();
        } catch (FileNotFoundException fnfx) {
            throw new DataAccessException("The file " + PATH+File.separator+login + " was not found", fnfx);
        } catch (IOException ex) {
            throw new DataAccessException("Error while closing output stream", ex);
        }
    }

}