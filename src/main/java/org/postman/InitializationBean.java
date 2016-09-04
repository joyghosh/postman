package org.postman;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.postman.actors.Listener;
import org.postman.actors.Master;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * <p>
 * Invoked on startup and shutdown of app context.
 * Responsible for premature initializations and 
 * revoking of resources upon shutdown. 
 * </p>
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 *
 */
@Startup
@Singleton
public class InitializationBean {
	
	private static final Logger logger = LoggerFactory.getLogger(InitializationBean.class);
	private static ActorSystem system;
	private static ActorRef listener;
	private static ActorRef master;
	private static final int nrOfWorkers = 10;
	
	/**
	 * Initialize the akka actor system at application startup.
	 */
	@PostConstruct
	private void init(){
		system = ActorSystem.create("postman_actor_system");
		listener = system.actorOf(Props.create(Listener.class), "listener");
		master = system.actorOf(Props.create(Master.class, nrOfWorkers, listener), "master");
		
		logger.debug(listener.path().name());
		logger.debug(master.path().name());
	}
	
	/**
	 * Shuts down the actor system at application shutdown.
	 */
	@PreDestroy
	private void destroy(){
		system.shutdown();
	}
	
	/**
	 * Returns a reference to master actor.
	 * @return
	 */
	public ActorRef getMaster(){
		return master;
	}
	
	/**
	 * Returns a reference to listener actor.
	 * @return
	 */
	public ActorRef getListener(){
		return listener;
	}
}
