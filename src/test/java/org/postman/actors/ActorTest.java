package org.postman.actors;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.postman.actors.messages.Result;
import org.postman.actors.messages.TestMessage;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.testkit.JavaTestKit;
import akka.testkit.TestActorRef;

/**
 * postman Akka actors unit test.
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public class ActorTest {
	
	private static ActorSystem system;
	
	@BeforeClass
	public static void setup(){
		system = ActorSystem.create();
	}
	
	@Test
	public void testWorkerActor() throws Exception{
		final Props props = Props.create(Worker.class);
		final TestActorRef<Worker> ref = TestActorRef.create(system, props, "test_worker");
		final Future<Object> future = Patterns.ask(ref, new TestMessage(), 3000);
		Assert.assertTrue(future.isCompleted());
		Assert.assertTrue("Not Result object", Await.result(future, Duration.Zero()) instanceof Result);
	}
	
	@AfterClass
	public static void teardown(){
		JavaTestKit.shutdownActorSystem(system);
		system = null;
	}
}
