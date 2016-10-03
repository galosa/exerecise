/**
 * 
 */
package io.bigpanda.exercise.service;

import io.bigpanda.exercise.model.EventStats;
import io.bigpanda.exercise.model.Stats;
import io.bigpanda.exercise.model.WordStats;
import io.vertx.core.Vertx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author gal.a
 *
 */
@Service
public class StatsServiceImpl implements IStatsService {

	@Autowired
	private Vertx vertx;
	private ObjectMapper objectMapper = new ObjectMapper();
	private Map<String, AtomicInteger> eventsStats = new HashMap<>();
	private Map<String, Map<String, AtomicInteger>> eventWordsCounter = new HashMap<>();
	
	@PostConstruct
	public void init(){
		updateStats();
	}
	
	@Override
	public EventStats getEventStats(String eventType) {
		updateStats();
		Set<WordStats> words = new HashSet<WordStats>();
		eventWordsCounter.getOrDefault(eventType, Collections.emptyMap()).forEach((word,counter) -> words.add(new WordStats(word, counter.longValue())));
		return new EventStats(eventType, eventsStats.getOrDefault(eventType, new AtomicInteger(0)).longValue(), words) ;
	}

	private void updateStats() {
		vertx.eventBus().consumer("events-stats", data -> {
			String jsonValue = data.body().toString();
			try {
				Stats stats = this.objectMapper.readValue( jsonValue , Stats.class);
				eventsStats = stats.getEventTypesCounter();
				eventWordsCounter = stats.getEventWordsCounter();
			} catch (Exception e) {
				
			}
		});		
	}
	@Override
	public Set<EventStats> getAllEventsStats() {
		updateStats();
		Set<EventStats> eventsStatsSet = new HashSet<EventStats>(eventsStats.size());
		for(String eventType : eventsStats.keySet()){
			Set<WordStats> words = new HashSet<WordStats>();
			eventWordsCounter.getOrDefault(eventType, Collections.emptyMap()).forEach((word,counter) -> words.add(new WordStats(word, counter.longValue())));
			eventsStatsSet.add(new EventStats(eventType, eventsStats.getOrDefault(eventType, new AtomicInteger(0)).longValue(), words));
		}
		return eventsStatsSet;
	}

	@Override
	public EventStats getEventStats(String eventType, String word) {
		updateStats();
		Set<WordStats> words = new HashSet<WordStats>();
		eventWordsCounter.getOrDefault(eventType, Collections.emptyMap()).forEach((w,counter) -> { if (w.equalsIgnoreCase(word)) words.add(new WordStats(w, counter.longValue()));});
		return new EventStats(eventType, eventsStats.getOrDefault(eventType, new AtomicInteger(0)).longValue(), words) ;
	}

	@Override
	public WordStats getWordStats(String word) {
		updateStats();
		Long counter = eventWordsCounter.values().stream().map(m -> m.getOrDefault(word, new AtomicInteger(0)).longValue()).reduce(0L, (a,b) -> a+b);
		return new WordStats(word, counter);
	}
	
	@Override
	public Set<WordStats> getAllWordStats() {
		updateStats();
		Set<WordStats> wordsStats = new HashSet<>();
		Map<String, List<Long>> collector = new HashMap<>();
		eventWordsCounter.forEach(
				(event,map) -> map.forEach(
						(word, counter) -> {
							List<Long> l = collector.getOrDefault(word, new ArrayList<Long>());
							l.add(counter.longValue());
							collector.put(word, l);
						}
						)
				);
		for(String word: collector.keySet()){
			Long counter = collector.get(word).stream().reduce(0L, (a,b) -> a+b);
			wordsStats.add(new WordStats(word, counter));
		}
		return wordsStats;
	}

}
