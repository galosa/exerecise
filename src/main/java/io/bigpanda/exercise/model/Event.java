/**
 * 
 */
package io.bigpanda.exercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gal.a
 *
 */
public class Event {

	@JsonProperty("event_type")
	private String type;
	@JsonProperty("data")
	private String data;
	@JsonProperty("timestamp")
	private Long epochTimestamp;

	/**
	 * 
	 */
	public Event() {
	}

	/**
	 * @param type
	 * @param data
	 * @param epochTimestamp
	 */
	public Event(String type, String data, Long epochTimestamp) {
		this.type = type;
		this.data = data;
		this.epochTimestamp = epochTimestamp;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the epochTimestamp
	 */
	public Long getEpochTimestamp() {
		return epochTimestamp;
	}

	/**
	 * @param epochTimestamp
	 *            the epochTimestamp to set
	 */
	public void setEpochTimestamp(Long epochTimestamp) {
		this.epochTimestamp = epochTimestamp;
	}
}