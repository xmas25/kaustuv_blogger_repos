/**
 *
 * Copyright © Kaustuv Maji , 2014
 * Repos - https://github.com/kaustuvmaji
 * Blog -  http://kaustuvmaji.blogspot.in
 *
 */
package example.spring.simple.jms.consumer;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring JMS Consumer example.
 *
 * @author KMaji
 *
 */
public class ConsumerApp implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(ConsumerApp.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ConsumerApp().run();
		LOG.debug("Consumer started ...");
	}

	@Override
	public void run() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("simple-consumer-jms-context.xml");
		SimpleMessageReceiver receiver = (SimpleMessageReceiver) context.getBean("messageReceiver");
		while (true) {
			try {
				receiver.receive();
				Thread.sleep(5000);
			} catch (InterruptedException | JMSException e) {
				LOG.error("unable to process", e);
			}
		}
	}
}
