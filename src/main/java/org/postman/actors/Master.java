package org.postman.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * Email master actor whose responsibility is dispatch email jobs to worker actors.
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 * 
 */
public class Master extends UntypedActor{

	private ActorRef workerRouter;
	
//	public Master(){
//		workerRouter = this.getContext().actorOf(Worker.)
//	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		
	}
}
