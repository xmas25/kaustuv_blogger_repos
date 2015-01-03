/**
 *
 * Copyright © Kaustuv Maji , 2015
 * Repos - https://github.com/kaustuvmaji
 * Blog -  http://kaustuvmaji.blogspot.in
 */
package service.consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;

import service.provider.ExampleServiceInterface;

public class ConsumerActivator implements BundleActivator {

	protected ExampleConsumer consumer;

	protected ServiceReference<?> ref;

	private LogService logservice;

	@Override
	public void start(BundleContext context) throws Exception {
		LogService logservice = log(context);
		logservice.log(LogService.LOG_INFO, "Starting " + context.getBundle() + "!");
		logservice.log(LogService.LOG_INFO, "Log service found for " + context.getBundle().getSymbolicName() + "!");
		ref = context.getServiceReference(service.provider.ExampleServiceInterface.class.getName());
		if (ref != null) {
			logservice.log(LogService.LOG_INFO, "Found service reference and now trying to consume provided service!");
			consumer = new ExampleConsumer((ExampleServiceInterface) context.getService(ref));
			if (consumer == null) {
				logservice.log(LogService.LOG_INFO, "consumer didn't found provider service :(");
			}
			consumer.startTimer();
		}
		logservice.log(LogService.LOG_INFO, "Started " + context.getBundle() + "!");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		logservice.log(LogService.LOG_INFO, "Stopping " + context.getBundle() + "!");
		if (consumer != null) {
			consumer.stopTimer();
		}
		logservice.log(LogService.LOG_INFO, "Stopped " + context.getBundle() + "!");
	}

	private LogService log(BundleContext context) {

		this.ref = context.getServiceReference(LogService.class.getName());

		if (ref != null) {
			logservice = (LogService) context.getService(ref);
		}

		logservice.log(LogService.LOG_INFO, " Starting log service for sample osgi example app.");
		return logservice;
	}
}
