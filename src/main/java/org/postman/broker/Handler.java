package org.postman.broker;

import javax.jms.Message;

/**
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public interface Handler {
	public void process(Message message);
}
