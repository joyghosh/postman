package org.postman;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Email dispatcher bean. 
 * This bean provides interface for connecting to smtp server.
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
@Startup
@Singleton
@DependsOn("PropertiesBean")
public class Dispatcher {
	
	private static final Logger logger = LoggerFactory.getLogger(Dispatcher.class);
	private static Email email;
	
	@EJB
	PropertiesBean properties;
	
	@PostConstruct
	private void init(){
		email = new SimpleEmail();
		email.setHostName(properties.getValue(Property.HOST));
		email.setSmtpPort(new Integer(properties.getValue(Property.PORT)));
		email.setAuthenticator(new DefaultAuthenticator((String) properties.getValue(Property.USERNAME), 
				(String) properties.getValue(Property.PASSWORD)));
		email.setSSLOnConnect(new Boolean(properties.getValue(Property.SSL)));
	}
	
	/**
	 * Dispatches email to SMTP server.
	 * 
	 * @param from
	 * @param recips
	 * @param subject
	 * @param body
	 * @throws EmailException
	 */
	public void send(String from, List<String> recips, String subject, String body) throws EmailException{
		email.setFrom(from);
		email.addTo(recips.toArray(new String[recips.size()]));
		email.setSubject(subject);
		email.setMsg(body);
		
		logger.debug("Sending email message.");
		email.send();
	}
}
