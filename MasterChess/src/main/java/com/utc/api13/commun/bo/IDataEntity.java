/**
 * 
 */
package com.utc.api13.commun.bo;

import java.io.Serializable;
import java.util.UUID;

/**
 * Interface générique des entities
 * @author Amstrong
 *
 */
public interface IDataEntity extends Serializable {
	
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
