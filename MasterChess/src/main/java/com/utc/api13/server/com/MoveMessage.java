package com.utc.api13.server.com;

import java.util.UUID;

import com.utc.api13.commun.entities.MoveEntity;

public class MoveMessage extends Message {
 MoveEntity move;
	
	/**
 * @param sender
 * @param receiver
 * @param move
 */
public MoveMessage(UUID sender, UUID receiver, MoveEntity move) {
	super(sender, receiver);
	this.move = move;
}
	@Override
	void proceed() {
		// TODO Auto-generated method stub
		
	}
	public MoveEntity getMove() {
		return move;
	}
	public void setMove(MoveEntity move) {
		this.move = move;
	}

}
