/**
 * 
 */
package io.bigpanda.exercise.processing;

import io.bigpanda.exercise.model.Event;
import io.bigpanda.exercise.model.Stats;
import io.vertx.core.AbstractVerticle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author gal.a
 *
 */
public class EventConsumer extends AbstractVerticle {

	private ObjectMapper objectMapper = new ObjectMapper();
	private Map<String, AtomicInteger> eventTypesCounter = new HashMap<>();
	private Map<String, Map<String, AtomicInteger>> eventWordsCounter = new HashMap<>();

	@Override
	public void start() throws Exception {
		vertx.eventBus().consumer("events", msg -> {
			String jsonEvent = (String) msg.body();
			try {
				Event event = objectMapper.readValue(jsonEvent, Event.class);
				AtomicInteger eventTypeCounter = eventTypesCounter.get(event.getType());
				if(eventTypeCounter == null) {
					eventTypesCounter.put(event.getType(), new AtomicInteger(1));
				} else {
					eventTypeCounter.incrementAndGet();
				}
				
				
				Map<String, AtomicInteger> eventWordCounter = eventWordsCounter.getOrDefault(event.getType(), new HashMap<>());
				
				//do not assume we have only word in the data, maybe we will have more ;)
				for(String word : event.getData().trim().split(" ")){
					AtomicInteger wordCounter = eventWordCounter.get(word);
					if(wordCounter == null) {
						eventWordCounter.put(word, new AtomicInteger(1));
					} else {
						wordCounter.incrementAndGet();
					}
				}
				eventWordsCounter.put(event.getType(), eventWordCounter);
				
				Stats stats = new Stats(eventTypesCounter, eventWordsCounter);
				vertx.eventBus().publish("events-stats", objectMapper.writeValueAsString(stats));
			} catch (JsonParseException|JsonMappingException e) {
				//ignore unparseable event
			} catch (IOException e) {

			}
		});
			
	}
}
