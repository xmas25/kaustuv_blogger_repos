/**
 *
 * Copyright © Kaustuv Maji , 2015
 * Repos - https://github.com/kaustuvmaji
 * Blog -  http://kaustuvmaji.blogspot.in
 */
package service.provider;

import org.osgi.service.log.LogService;

/**
 * Example Implementation class for service interface.
 *
 * @author KMaji
 *
 */
public class ExampleServiceImpl implements ExampleServiceInterface {

	private LogService logservice;

	/**
	 * @param logservice
	 */
	public ExampleServiceImpl(LogService logservice) {
		this.logservice = logservice;
	}

	@Override
	public void message(String message) {
		logservice.log(LogService.LOG_INFO, "service provider printing message. -> [ " + message + " ]");
	}
}
