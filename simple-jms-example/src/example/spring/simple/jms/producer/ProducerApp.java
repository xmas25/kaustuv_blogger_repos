/**
 *
 * Copyright © Kaustuv Maji , 2014
 * Repos - https://github.com/kaustuvmaji
 * Blog -  http://kaustuvmaji.blogspot.in
 *
 */
package example.spring.simple.jms.producer;


import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author KMaji
 *
 */
public class ProducerApp implements Runnable {

	private static final Logger LOG = Logger
			.getLogger(ProducerApp.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ProducerApp().run();
	}

	@Override
	public void run() {
		String sendType = "TextMessage";
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"simple-producer-jms-context.xml");

		SimpleMessageProducer producer = (SimpleMessageProducer) context
				.getBean("messageProducer");

		LOG.info("Using the sendType "+sendType);
		int i = 0 ;

		while (true) {

			producer.sendMessages(sendType, i++);
			try {

				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
