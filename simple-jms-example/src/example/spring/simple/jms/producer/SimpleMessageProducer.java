/**
 *
 * Copyright © Kaustuv Maji , 2014
 * Repos - https://github.com/kaustuvmaji
 * Blog -  http://kaustuvmaji.blogspot.in
 *
 */
package example.spring.simple.jms.producer;

import java.util.Date;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import example.spring.dao.Member;

/**
 * Example spring jms producer.
 *
 * @author KMaji
 *
 */
public class SimpleMessageProducer {

	private static final Logger LOG = Logger.getLogger(SimpleMessageProducer.class.getName());

	protected JmsTemplate jmsTemplate;

	/**
	 * Getter Method of JMSTemplate
	 *
	 * @return the jmsTemplate
	 */
	public final JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	/**
	 * Setter Method of JMSTemplate
	 *
	 * @param jmsTemplate
	 *            the jmsTemplate to set
	 */
	public final void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	protected final void sendMessages(String sendType, int i) {
		try {
			if ("textMessage".equalsIgnoreCase(sendType)) {
				jmsSendTextMessages(i);
			} else if ("objectMessage".equalsIgnoreCase(sendType)) {
				jmsSendObjectMessages(i);
			} else if ("ByteMessage".equalsIgnoreCase(sendType)) {
				jmsSendBytesMessages(i);
			} else if ("MapMessage".equalsIgnoreCase(sendType)) {
				jmsSendMapMessages(i);
			}
		} catch (JMSException e) {
			LOG.error("unable to process", e);
		}
	}

	/**
	 * This method is used to produce TextMessages
	 *
	 * @throws JMSException
	 */
	protected void jmsSendTextMessages(final int i) throws JMSException {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("jmsSendTextMessages '").append(i).append("' sent at: ").append(new Date());

		final String payload = buffer.toString();
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createTextMessage(payload);
				message.setIntProperty("messageCount", i);
				LOG.info("Sending message number ->" + i + " message [" + message + "]");
				return message;
			}
		});
	}

	/**
	 * This method is used to produce ObjectMessages
	 *
	 * @throws JMSException
	 */
	protected void jmsSendObjectMessages(final int i) {

		jmsTemplate.send(new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				Member member = new Member();
				member.setId(i);
				member.setName("jmsSendObjectMessage_" + i);
				member.setAge(10 + i);
				Message message = session.createObjectMessage(member);
				LOG.info("Sending message number ->" + i + " message [" + message + "]");
				return message;
			}
		});
	}

	/**
	 * This method is used to produce BytesMessages.
	 *
	 * @throws JMSException
	 */
	public void jmsSendBytesMessages(final int i) throws JMSException {
		final StringBuilder buffer = new StringBuilder();

		buffer.append("Byte message [").append(i).append("] sent at -> ").append(new Date());

		final String payload = buffer.toString();

		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				BytesMessage message = session.createBytesMessage();
				message.writeUTF(payload);
				message.setIntProperty("messageCount", i);
				LOG.info("Sending message number ->" + i + " message [" + message + "]");
				return message;
			}
		});
	}

	/**
	 * This method is used to produce MapMessages.
	 *
	 * @throws JMSException
	 */
	public void jmsSendMapMessages(final int i) throws JMSException {
		final StringBuilder buffer = new StringBuilder();

		buffer.append("Map message [").append(i).append("] sent at -> ").append(new Date());

		final String payload = buffer.toString();

		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				message.setString("payload", payload);
				LOG.info("Sending message number ->" + i + " message [" + message + "]");
				return message;
			}
		});
	}

	/**
	 * This method is used to produce StreamMessages.
	 *
	 * @throws JMSException
	 */
	public void jmsSendStreamMessages(final int i) throws JMSException {
		final StringBuilder buffer = new StringBuilder();

		buffer.append("Stream message [").append(i).append("] sent at -> ").append(new Date());

		final String payload = buffer.toString();

		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				javax.jms.StreamMessage message = session.createStreamMessage();
				message.writeObject(payload);
				LOG.info("Sending message number ->" + i + " message [" + message + "]");
				return message;
			}
		});
	}
}
