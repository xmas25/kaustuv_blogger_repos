/**
 *
 * Copyright © Kaustuv Maji , 2014
 * Repos - https://github.com/kaustuvmaji
 * Blog -  http://kaustuvmaji.blogspot.in
 *
 */
package example.spring.simple.jms.consumer;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

import example.spring.dao.Member;

/**
 *
 * @author KMaji
 *
 */
public class SimpleMessageReceiver {

	private static final Logger LOG = Logger.getLogger(SimpleMessageReceiver.class);

	protected JmsTemplate jmsTemplate;

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void receive() throws JMSException {

		Message message = jmsTemplate.receive();

		if (message == null) {
			// do not work for null message ignore
		}

		if (message instanceof TextMessage) {
			LOG.info("Received a JMS message:  -> [" + message + "]");
		} else if (message instanceof ObjectMessage) {
			try {
				Member obj = (Member) ((ObjectMessage) message).getObject();
				LOG.info("Received a JMS message: ObjectMessage -> [" + obj.toString() + "]");
			} catch (JMSException e) {
				LOG.error("unable to process message", e);
			}
		} else if (message instanceof MapMessage) {
			MapMessage msg = (MapMessage) message;
			LOG.info("Received a JMS message: MapMessage -> [" + msg.getString("payload") + "]");
		} else if (message instanceof BytesMessage) {
			BytesMessage bInMsg = (BytesMessage) message;
			int msgLength = (int) bInMsg.getBodyLength();
			byte[] msg = new byte[msgLength];
			bInMsg.readBytes(msg);
			LOG.info("Received a JMS message: BytesMessage -> [" + msg + "]");
		}else if (message instanceof StreamMessage) {
			StreamMessage msg = (StreamMessage) message;
			LOG.info("Received a JMS message: MapMessage -> [" + msg.readObject() + "]");
		}else {
			// only 5 type of message will come so relax
		}
	}
}
