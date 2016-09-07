package org.postman.actors;

import org.postman.actors.messages.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.UntypedActor;

/**
 * Listener actor.
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public class Listener extends UntypedActor{

	private static final Logger logger = LoggerFactory.getLogger(Listener.class);
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message.getClass().equals(Result.class)){
			Result result = (Result)message;
			if(result.getStatus()){
				logger.debug("Email sent successfully!");
			}else{
				logger.error("Failed to send email!");
			}
		}else{
			unhandled(message);
		}
	}
}
