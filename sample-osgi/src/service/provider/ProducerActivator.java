/**
*
* Copyright © Kaustuv Maji , 2015
* Repos - https://github.com/kaustuvmaji
* Blog -  http://kaustuvmaji.blogspot.in
*/
package service.provider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.log.LogService;

/**
 * @author KMaji
 */
public class ProducerActivator implements BundleActivator, BundleListener {

	protected ServiceRegistration<?> registration;

	private ServiceReference<?> ref;

	private LogService logservice;

	@Override
	public void start(BundleContext context) throws Exception {
		context.addBundleListener(this);
		logservice = log(context);
		registration = context.registerService(ExampleServiceInterface.class.getName(), new ExampleServiceImpl(logservice), null);
		logservice.log(LogService.LOG_INFO, context.getBundle().getSymbolicName() + " started.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		context.removeBundleListener(this);
		logservice.log(LogService.LOG_INFO, context.getBundle().getSymbolicName() + " stoped.");
	}

	@Override
	public void bundleChanged(BundleEvent event) {

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
