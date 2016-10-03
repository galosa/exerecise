/**
 * 
 */
package io.bigpanda.exercise.processing;

import io.vertx.core.AbstractVerticle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author gal.a
 *
 */
public class EventPublisher extends AbstractVerticle {

	private Process process;

	/**
	 * @param process
	 */
	public EventPublisher(Process process) {
		this.process = process;
	}

	@Override
	public void start() throws Exception {
		InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		bufferedReader.lines().forEach(data ->  vertx.eventBus().publish("events", data));
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		process.destroy();
	}

}
