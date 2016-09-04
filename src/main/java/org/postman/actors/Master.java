package org.postman.actors;

import org.postman.actors.messages.EmailMessage;
import org.postman.actors.messages.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;

/**
 * Email master actor whose responsibility is dispatch email jobs to worker actors.
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 * 
 */
public class Master extends UntypedActor{

	private static final Logger logger = LoggerFactory.getLogger(Master.class);
	private final ActorRef workerRouter;
	private final ActorRef listener;
	
	public Master(final int noOfWorkers, final ActorRef listener){
		this.listener = listener;
		workerRouter = this.getContext().actorOf(new RoundRobinPool(noOfWorkers)
						.props(Worker.createWorker()),"workerRouter");
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message.getClass().equals(EmailMessage.class)){
			
			EmailMessage em = (EmailMessage)message;
			logger.debug("dispatching email "+em.getMessage().toString()+" to worker actor.");
			workerRouter.tell(em.getMessage(), getSelf());
		}else if(message.getClass().equals(Result.class)){
			
			Result result = (Result)message;
			logger.debug("Forwarding result to listener actor.");
			listener.tell(result, getSelf());
		}else{
			
			logger.error("Unhandled message");
			unhandled(message);
		}
	}
}
