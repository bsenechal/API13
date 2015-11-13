package com.utc.api13.server.com;

import java.util.UUID;

public class ErrorMessage extends Message {
	Exception exception;

	@Override
	public void proceed() {
		// TODO Auto-generated method stub

	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	/**
	 * @param sender
	 * @param receiver
	 * @param exception
	 */
	public ErrorMessage(UUID sender, UUID receiver, Exception exception) {
		super(sender, receiver);
		this.exception = exception;
	}


}
