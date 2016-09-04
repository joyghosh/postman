package org.postman.actors;

import org.postman.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Worker actor which sends email objects to SMTP server.
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public class Worker extends UntypedActor{

	private static final Logger logger = LoggerFactory.getLogger(Worker.class);
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message.getClass().equals(Email.class)){
			
			Email email = (Email)message;
			logger.debug("Email details");
			logger.debug("From: "+email.getFrom());
			logger.debug("Subject: "+email.getSubject());
			logger.debug("Content: "+email.getBody());
			logger.debug("Recipient(s): "+email.getRecipients());
		}else{
			
			logger.error("unhandled message.");
			unhandled(message);
		}
	}

	public static Props createWorker(){
		return Props.create(Worker.class);
	}
}
