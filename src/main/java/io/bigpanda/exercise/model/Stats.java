/**
 * 
 */
package io.bigpanda.exercise.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gal.a
 *
 */
public class Stats implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5864057095852525530L;
	
	private Map<String, AtomicInteger> eventTypesCounter = new HashMap<>();
	private Map<String, Map<String, AtomicInteger>> eventWordsCounter = new HashMap<>();

	public Stats() {
	}
	
	/**
	 * @param eventTypesCounter
	 * @param eventWordsCounter
	 */
	public Stats(Map<String, AtomicInteger> eventTypesCounter,
			Map<String, Map<String, AtomicInteger>> eventWordsCounter) {
		this.eventTypesCounter = eventTypesCounter;
		this.eventWordsCounter = eventWordsCounter;
	}

	/**
	 * @return the eventTypesCounter
	 */
	public Map<String, AtomicInteger> getEventTypesCounter() {
		return eventTypesCounter;
	}

	/**
	 * @param eventTypesCounter
	 *            the eventTypesCounter to set
	 */
	public void setEventTypesCounter(
			Map<String, AtomicInteger> eventTypesCounter) {
		this.eventTypesCounter = eventTypesCounter;
	}

	/**
	 * @return the eventWordsCounter
	 */
	public Map<String, Map<String, AtomicInteger>> getEventWordsCounter() {
		return eventWordsCounter;
	}

	/**
	 * @param eventWordsCounter
	 *            the eventWordsCounter to set
	 */
	public void setEventWordsCounter(
			Map<String, Map<String, AtomicInteger>> eventWordsCounter) {
		this.eventWordsCounter = eventWordsCounter;
	}
}
