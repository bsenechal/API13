package com.utc.api13.commun.entities;

import java.io.Serializable;
import java.util.UUID;

/**
 * Interface générique des entities
 * @author Amstrong
 *
 */
public abstract class ADataEntity implements Serializable {

    private static final long serialVersionUID = 6762511056153784212L;
    private UUID id;
    
    /**
     * Constructeur initialisant l'id de l'entity
     */
    public ADataEntity(){
    	id = UUID.randomUUID();
    }
    /**
	 * Permet de récupérer l'identifiant de l'entity
	 * @return l'identifiant de l'entity
	 */
	public UUID getId() {
	    return id;
	}
	
	/**
	 * Permet de setter l'identifiant de l'entity
	 * @param id identifiant de l'entity
	 */
	public void setId(final UUID id) {
	    this.id = id;
	}

}