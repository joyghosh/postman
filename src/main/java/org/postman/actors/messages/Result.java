package org.postman.actors.messages;

/**
 * Actor message for status.
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public class Result {
	
	private final boolean status;
	
	public Result(final boolean status){
		this.status = status;
	}

	public boolean getStatus() {
		return status;
	}
}
