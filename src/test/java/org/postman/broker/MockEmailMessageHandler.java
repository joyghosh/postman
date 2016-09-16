package org.postman.broker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.jms.Message;

/**
 * Mock message handler for test purpose.
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
@Alternative
@ApplicationScoped
public class MockEmailMessageHandler implements Handler {

	private BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
	
	@Override
	public void process(Message message) {
		queue.add(message);
	}
	
	public Message poll(long seconds) throws InterruptedException{
		return queue.poll(seconds, TimeUnit.SECONDS);
	}
}
