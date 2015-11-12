package com.utc.api13.commun.entities;

import java.io.Serializable;
import java.util.UUID;

/**
 * Interface générique des entities
 * @author Amstrong
 *
 */
public interface DataEntity extends Serializable {
	
	/**
	 * Permet de récupérer l'identifiant de l'entity
	 * @return l'identifiant de l'entity
	 */
	UUID getId();
	
	/**
	 * Permet de setter l'identifiant de l'entity
	 * @param id identifiant de l'entity
	 */
	void setId(UUID id);

}