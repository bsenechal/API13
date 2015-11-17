package com.utc.api13.commun.messages;

import java.util.UUID;

import com.utc.api13.commun.entities.MoveEntity;

public class MoveValidationMessage extends Message {

	MoveEntity  move;
	

	/**
	 * @param sender
	 * @param receiver
	 * @param move
	 */
	public MoveValidationMessage(UUID sender, UUID receiver, MoveEntity move) {
		super(sender, receiver);
		this.move = move;
	}

	public MoveEntity getMove() {
		return move;
	}

	public void setMove(MoveEntity move) {
		this.move = move;
	}

	@Override
	public void proceed() {
		// TODO Auto-generated method stub

	}

}
