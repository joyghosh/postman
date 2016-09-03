package org.postman.actors;

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

	@Override
	public void onReceive(Object message) throws Exception {
		
	}

	public static Props createWorker(){
		return Props.create(Worker.class);
	}
}
