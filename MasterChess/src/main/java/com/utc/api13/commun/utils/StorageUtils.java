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

import com.utc.api13.commun.entities.ADataEntity;
import com.utc.api13.commun.exceptions.DataAccessException;

public class StorageUtils<T extends ADataEntity>{
	private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private NoHeaderObjectOutputStream noHeaderOos;
    private String file;
    
    /**
     * Constructeur
     * @param file fichier à parser
     */
    public StorageUtils(String file){
        this.file = getClass().getClassLoader().getResource(file).getFile();
    }
    
    
    /**
     * écrit un entity à la fin du fichier
     * @param entity entity à écrire dans le fichier
     * @throws DataAccessException erreur lors de l'accès aux données
     */
    public void write(T entity)throws DataAccessException{
        try {
        	if(readAll().size() == 0) {
	            oos = new ObjectOutputStream(new FileOutputStream(file));
	            oos.writeObject(entity);
        	} else {
        		noHeaderOos = new NoHeaderObjectOutputStream(new FileOutputStream(file, true));
        		noHeaderOos.writeObject(entity);
        	}
        } catch (IOException ex) {
            throw new DataAccessException("Error while writing entity", ex);
        } finally{
           
                try {
	            	 if(oos != null){
	            		 oos.flush();
	            		 oos.close();
	            	 } else if(noHeaderOos != null) {
	            		 noHeaderOos.flush();
	            		 noHeaderOos.close();
                    }
                } catch (IOException ex) {
                    throw new DataAccessException("Error while closing output stream", ex);
                }
        }
    }
    
    /**
     * 2crit la liste des entity passée en paramètre à la fin du fichier
     * @param entities liste des entity à écrire
     * @throws DataAccessException erreur d'accès aux données
     */
    public void writeAll(List<T> entities) throws DataAccessException {
    	try{
            oos = new ObjectOutputStream(new FileOutputStream(file));
            noHeaderOos = new NoHeaderObjectOutputStream(new FileOutputStream(file, true));
            Iterator<T> iterator = entities.iterator();
            if(iterator.hasNext()) {
	            oos.writeObject(iterator.next());
	            oos.close();
            }
            while(iterator.hasNext()){
             noHeaderOos.writeObject(iterator.next());   
            }
        } catch (IOException ex) {
            throw new DataAccessException("Error while writing entity", ex);
        } finally{
            
            try {
            	if(oos != null){
	                oos.flush();
	                oos.close();
            	} else if(noHeaderOos != null){
            		noHeaderOos.flush();
            		noHeaderOos.close();
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
    public T read()throws DataAccessException{
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            return (T) ois.readObject();
        } catch (InvalidClassException icx) {
            throw new DataAccessException("The class " + icx.classname + " has changed since the serialization of object", icx);
        } catch (StreamCorruptedException scx){
            throw new DataAccessException("The file " + file + " is corrupted. Do not modify storage files!", scx);
        } catch (ClassNotFoundException cnx) {
            throw new DataAccessException("The entity you are trying to deserialize doest not have a valid class", cnx);
        } catch (IOException iox) {
            throw new DataAccessException("Error while reading object from the file " + file, iox);
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
    
    public List<T> readAll() throws DataAccessException{
        List<T> entities = new ArrayList<T>();
        try{
            ois = new ObjectInputStream(new FileInputStream(file));
            while(true) {
                entities.add((T)ois.readObject());
            }
        } catch (EOFException eox){
                return entities;
        } catch (IOException iox) {
            throw new DataAccessException("Error while reading object from the file " + file, iox);
        } catch (ClassNotFoundException cnx) {
            throw new DataAccessException("The entity you are trying to deserialize doest not have a valid class", cnx);
        } finally {
            try {
            	if(ois != null) {
            		ois.close();
            	}
            } catch (IOException ex) {
                throw new DataAccessException("Error while closing output stream", ex);
            }
        }
    }
    
    public void clearFile() throws DataAccessException {
        try {
            new FileOutputStream(file).close();
        } catch (FileNotFoundException fnfx) {
            throw new DataAccessException("The file " + file + " was not found", fnfx);
        } catch (IOException ex) {
            throw new DataAccessException("Error while closing output stream", ex);
        }
    }

}