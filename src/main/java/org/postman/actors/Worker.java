package org.postman.actors;

import org.postman.Dispatcher;
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
	
	Dispatcher dispatcher;
	
	public Worker(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message.getClass().equals(Email.class)){
			
			Email email = (Email)message;
			logger.debug("dispacther: "+dispatcher);
			dispatcher.send(email.getFrom(), email.getRecipients(), email.getSubject(), email.getBody());
		}else{
			
			logger.error("unhandled message.");
			unhandled(message);
		}
	}

	public static Props createWorker(Dispatcher dispatcher){
		return Props.create(Worker.class, dispatcher);
	}
}
