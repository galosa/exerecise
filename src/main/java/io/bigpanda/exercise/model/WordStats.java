/**
 * 
 */
package io.bigpanda.exercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gal.a
 *
 */
public class WordStats {

	@JsonProperty("word")
	private String word;
	
	@JsonProperty("counter")
	private Long counter;

	public WordStats() {
	}
	
	/**
	 * @param word
	 * @param counter
	 */
	public WordStats(String word, Long counter) {
		this.word = word;
		this.counter = counter;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
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
}
