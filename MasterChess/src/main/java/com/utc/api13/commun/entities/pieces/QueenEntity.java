/**
 * 
 */
package com.utc.api13.commun.entities.pieces;

import java.util.List;

import com.utc.api13.commun.entities.APieceEntity;
import com.utc.api13.commun.entities.GameEntity;
import com.utc.api13.commun.entities.PositionEntity;
import com.utc.api13.commun.enumerations.PieceColorEnum;

/**
 * @author Benoît
 *
 */
public class QueenEntity extends APieceEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4412179301301968841L;

	public QueenEntity(PieceColorEnum color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PositionEntity> generateAvailableMoves(GameEntity game) {
		// TODO Auto-generated method stub
		return null;
	}

}
