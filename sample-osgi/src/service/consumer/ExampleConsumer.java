/**
 *
 * Copyright © Kaustuv Maji , 2015
 * Repos - https://github.com/kaustuvmaji
 * Blog -  http://kaustuvmaji.blogspot.in
 */
package service.consumer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import service.provider.ExampleServiceInterface;

/**
 * @author KMaji
 *
 */
public class ExampleConsumer implements ActionListener {

	protected ExampleServiceInterface service;

	protected Timer timer;

	public ExampleConsumer(ExampleServiceInterface service) {
		super();
		this.service = service;
		timer = new Timer(1000, this);
	}

	public void startTimer() {
		timer.start();
	}

	public void stopTimer() {
		timer.stop();
	}
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		service.message("from consumer 1");
	}
}
