/**
 * 
 */
package io.bigpanda.exercise.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author gal.a
 *
 */
@JsonPropertyOrder({"event_type" , "counter", "words"})
public class EventStats {

	@JsonProperty("event_type")
	private String type;
	
	@JsonProperty("counter")
	private Long counter;
	
	private Set<WordStats> words;

	
	public EventStats() {
	}
	/**
	 * @param type
	 * @param counter
	 * @param words
	 */
	public EventStats(String type, Long counter, Set<WordStats> words) {
		this.type = type;
		this.counter = counter;
		this.words = words;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the counter
	 */
	public Long getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public void setCounter(Long counter) {
		this.counter = counter;
	}

	/**
	 * @return the words
	 */
	public Set<WordStats> getWords() {
		return words;
	}

	/**
	 * @param words the words to set
	 */
	public void setWords(Set<WordStats> words) {
		this.words = words;
	}
	
	
}
