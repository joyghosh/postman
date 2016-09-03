package org.postman.broker;

import java.util.List;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import org.postman.model.Email;
import org.postman.model.Fields;

/**
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public class EmailMessageConverter{
	
	public static Message toMessage(Object object, JMSContext context) throws JMSException{
		Email email = (Email)object;
		MapMessage map = context.createMapMessage();
		map.setString(Fields.SUBJECT, email.getSubject());
		map.setString(Fields.BODY, email.getBody());
		map.setString(Fields.FROM, email.getFrom());
		map.setObject(Fields.TO, email.getRecipients());
		map.setObject(Fields.CC, email.cc());
		map.setObject(Fields.BCC, email.bcc());
		map.setBoolean(Fields.HTML, email.isHtml());
		return map;
	}

	@SuppressWarnings("unchecked")
	public static Object fromMessage(Message message) throws JMSException{
		MapMessage msg = (MapMessage) message;
		Email email = new Email(msg.getString(Fields.SUBJECT), 
								msg.getString(Fields.BODY), 
								msg.getString(Fields.FROM),
								(List<String>)msg.getObject(Fields.TO),
								(List<String>)msg.getObject(Fields.CC), 
								(List<String>)msg.getObject(Fields.BCC), 
								msg.getBoolean(Fields.HTML));
		return email;
	}
}
