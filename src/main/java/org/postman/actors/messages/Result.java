package org.postman.actors.messages;

/**
 * 
 * @author Joy Ghosh
 *
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
